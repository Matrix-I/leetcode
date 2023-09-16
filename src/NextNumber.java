public class NextNumber {

  public static int getNextLarger(int n) {
    int c = n;
    int c0 = 0;
    int c1 = 0;

    // count the trailing zeros
    while ((c & 1) == 0 && (c != 0)) {
      c0++;
      c >>= 1;
    }

    // count the ones
    while ((c & 1) == 1) {
      c1++;
      c >>= 1;
    }

    // If n == 11..1100..00 in binary, then there is no bigger number with the same number of 1s
    if (c0 + c1 == 31 || c0 + c1 == 0) {
      return -1;
    }

    int pos = c0 + c1; // position of rightmost non-trailing zero

    // Flip the rightmost non-trailing zero
    n |= (1 << pos);

    // Clear all bits to the right of pos
    n &= ~((1 << pos) - 1);

    // Insert (c1-1) ones on the right.
    n |= (1 << (c1 - 1)) - 1;

    return n;
  }

  public static int getNextSmaller(int n) {
    int temp = n;
    int c0 = 0;
    int c1 = 0;

    // count the trailing ones
    while ((temp & 1) == 1) {
      c1++;
      temp >>= 1;
    }

    // If n == 00..0011..11 in binary, then there is no smaller number with the same number of 1s
    if (temp == 0) {
      return -1;
    }

    // count the zeros
    while ((temp & 1) == 0 && (temp != 0)) {
      c0++;
      temp >>= 1;
    }

    int pos = c0 + c1; // position of rightmost non-trailing one

    // Clear from bit pos onwards
    n &= ((~0) << (pos + 1));

    // Insert (c1+1) ones to the right of pos
    int mask = (1 << (c1 + 1)) - 1;
    n |= mask << (c0 - 1);

    return n;
  }

  public static void main(String[] args) {
    int n = 1443344; // Example number
    System.out.println("Next Larger: " + getNextLarger(n)); // Output the next larger number
    System.out.println("Next Smaller: " + getNextSmaller(n)); // Output the next smaller number
  }
}
