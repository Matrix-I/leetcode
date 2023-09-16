import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Interview2 {
  public static void main(String[] args) {
    List<String> test =
        List.of(
            "apple", "banana", "apple", "orange", "banana", "apple", "banana", "orange", "orange",
            "grape");

    List<String> result = mostK(test);
    System.out.println(result);
  }

  /**
   * Implement a Thread-Safe Cache with Expiry. Đây là một bài tập yêu cầu bạn xây dựng một cấu trúc
   * cache có thể lưu trữ dữ liệu với thời gian tồn tại xác định và hỗ trợ truy cập đồng thời an
   * toàn
   */
  public class CacheWithExpiry<K, V> {

    // Inner class to hold value and expiry time
    private static class CacheEntry<V> {
      private final V value;
      private final long expiryTime;

      public CacheEntry(V value, long expiryTime) {
        this.value = value;
        this.expiryTime = expiryTime;
      }

      public boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
      }
    }

    // Use ConcurrentHashMap for thread-safe operations
    private final Map<K, CacheEntry<V>> cache;

    // Scheduler for cleanup task
    private final ScheduledExecutorService cleanup;

    // Default expiry time in milliseconds
    private final long defaultExpiryTime;

    /**
     * Constructor with default expiry time
     *
     * @param defaultExpiryTimeMillis default time until entries expire
     * @param cleanupIntervalMillis interval between cleanup runs
     */
    public CacheWithExpiry(long defaultExpiryTimeMillis, long cleanupIntervalMillis) {
      this.cache = new ConcurrentHashMap<>();
      this.defaultExpiryTime = defaultExpiryTimeMillis;
      this.cleanup = Executors.newSingleThreadScheduledExecutor();

      // Schedule periodic cleanup of expired entries
      this.cleanup.scheduleAtFixedRate(
          this::cleanup, cleanupIntervalMillis, cleanupIntervalMillis, TimeUnit.MILLISECONDS);
    }

    /** Put value in cache with default expiry time */
    public void put(K key, V value) {
      put(key, value, defaultExpiryTime);
    }

    /** Put value in cache with custom expiry time */
    public void put(K key, V value, long expiryTimeMillis) {
      long expiryTime = System.currentTimeMillis() + expiryTimeMillis;
      cache.put(key, new CacheEntry<>(value, expiryTime));
    }

    /** Get value from cache if it exists and hasn't expired */
    public V get(K key) {
      CacheEntry<V> entry = cache.get(key);

      if (entry != null && !entry.isExpired()) {
        return entry.value;
      } else {
        // Remove expired entry
        cache.remove(key);
        return null;
      }
    }

    /** Remove entry from cache */
    public void remove(K key) {
      cache.remove(key);
    }

    /** Clear all entries from cache */
    public void clear() {
      cache.clear();
    }

    /** Get current size of cache */
    public int size() {
      cleanup(); // Clean expired entries before returning size
      return cache.size();
    }

    /** Cleanup expired entries */
    private void cleanup() {
      for (Map.Entry<K, CacheEntry<V>> entry : cache.entrySet()) {
        if (entry.getValue().isExpired()) {
          cache.remove(entry.getKey());
        }
      }
    }

    /** Shutdown the cleanup scheduler */
    public void shutdown() {
      cleanup.shutdown();
    }
  }

  /**
   * tìm K từ xuất hiện nhiều nhất trong mảng từ {"apple", "banana", "apple", "orange", "banana",
   * "apple", "banana", "orange", "orange", "grape"}
   */
  public static List<String> mostK(List<String> words) {
    if (words == null || words.isEmpty()) {
      return new ArrayList<>();
    }
    if (words.size() == 1) {
      return new ArrayList<>(words);
    }
    Map<String, Integer> map = new HashMap<>();
    for (int i = 0; i < words.size() - 1; i++) {
      if (map.containsKey(words.get(i))) {
        map.put(words.get(i), map.get(words.get(i)) + 1);
      } else {
        map.put(words.get(i), 1);
      }
    }
    int max = 0;
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      max = Math.max(max, entry.getValue());
    }
    List<String> result = new ArrayList<>();
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      if (entry.getValue() == max) {
        result.add(entry.getKey());
      }
    }
    return result;
  }
}
