import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.*;

public class TimestampedNonce {

  private static final int RANDOM_BITS_SIZE = 128 / 8;
  private static final int TIMESTAMP_BITS_SIZE = 64 / 8; /* a long */
  private static final int NONCE_SIZE = RANDOM_BITS_SIZE + TIMESTAMP_BITS_SIZE;

  /* Stores the timestamp to avoid extracting again */
  private final long expirationInEpochMillis;
  private final String nonceEncoded;

  public static TimestampedNonce expiresAt(Instant expiration) {
    long expirationInEpochMillis = expiration.toEpochMilli();

    var nonceOctets = new byte[NONCE_SIZE];
    new SecureRandom().nextBytes(nonceOctets);

    var nonceBuffer = ByteBuffer.wrap(nonceOctets);
    nonceBuffer.putLong(expirationInEpochMillis);

    return new TimestampedNonce(expirationInEpochMillis, nonceOctets);
  }

  public static TimestampedNonce parse(String timestampedNonceLiteral) {
    var decoded = Base64.getUrlDecoder().decode(timestampedNonceLiteral);
    if (decoded.length != (NONCE_SIZE)) {
      throw new IllegalArgumentException(
          "Invalid decoded size: " + decoded.length + "/" + NONCE_SIZE);
    }

    var nonceBuffer = ByteBuffer.wrap(decoded);
    long expirationInEpochMillis = nonceBuffer.getLong();
    return new TimestampedNonce(expirationInEpochMillis, decoded);
  }

  private TimestampedNonce(long expirationInEpochMillis, byte[] rawNonce) {
    this.expirationInEpochMillis = expirationInEpochMillis;
    this.nonceEncoded = Base64.getUrlEncoder().encodeToString(rawNonce);
  }

  public Instant expiration() {
    return Instant.ofEpochMilli(this.expirationInEpochMillis);
  }

  public boolean isExpired() {
    return this.expiration().isBefore(Instant.now());
  }

  public String toString() {
    return this.nonceEncoded;
  }

  private byte[] rawNonce() {
    return Base64.getUrlDecoder().decode(this.nonceEncoded);
  }

  public static void main(String[] args) {
    SignedTimestampedNonce sign = SignedTimestampedNonce.sign(
        TimestampedNonce.expiresAt(
            Instant.now().plusSeconds(10)), "74f8f1ff5f2567a12a9abccd1e7dafb74911cda8fafba".getBytes());
    System.out.println(sign.timestampedNonce.nonceEncoded);
    System.out.println(sign.timestampedNonce.expirationInEpochMillis);
    System.out.println(sign.hmacSignature);
  }

  public static class SignedTimestampedNonce {

    private static final String SINGING_ALGORITHM = "HmacSHA256";
    private final TimestampedNonce timestampedNonce;
    private final String hmacSignature;

    public static SignedTimestampedNonce sign(TimestampedNonce nonce, byte[] key) {
      var mac = createMac(key);
      byte[] signatureOctets = mac.doFinal(nonce.rawNonce());
      return new SignedTimestampedNonce(
          nonce, Base64.getUrlEncoder().encodeToString(signatureOctets));
    }

    private static Mac createMac(byte[] key) {
      try {
        if (key.length < 32) {
          throw new IllegalArgumentException(
              "HmacSHA256 requires at least 256-bit key. Got " + key.length);
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, SINGING_ALGORITHM);
        Mac mac = Mac.getInstance(SINGING_ALGORITHM);
        mac.init(secretKeySpec);
        return mac;
      } catch (Exception any) {
        throw new RuntimeException("Fail to create HmacSHA256", any);
      }
    }

    public static SignedTimestampedNonce verify(
        String timestampedNonceLiteral, String signature, byte[] key) {
      var unsureTimestampedNonce = TimestampedNonce.parse(timestampedNonceLiteral);

      var signed = SignedTimestampedNonce.sign(unsureTimestampedNonce, key);
      if (!signed.hmacSignature.equals(signature)) {
        throw new IllegalArgumentException("Signature mismatch");
      }

      return signed;
    }

    private SignedTimestampedNonce(TimestampedNonce nonce, String signature) {
      this.timestampedNonce = nonce;
      this.hmacSignature = signature;
    }

    public TimestampedNonce signedNonce() {
      return this.timestampedNonce;
    }

    public String signature() {
      return this.hmacSignature;
    }

    public String toString() {
      return this.timestampedNonce + ":HMAC-SHA256:" + this.hmacSignature;
    }
  }
}
