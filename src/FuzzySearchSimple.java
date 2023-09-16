import java.util.*;
import java.util.stream.Collectors;

public class FuzzySearchSimple {

  // Levenshtein distance (iterative DP)
  //  public static int levenshtein(String a, String b) {
  //    a = a == null ? "" : a;
  //    b = b == null ? "" : b;
  //    int n = a.length(), m = b.length();
  //    if (n == 0) return m;
  //    if (m == 0) return n;
  //
  //    int[] prev = new int[m + 1];
  //    int[] cur = new int[m + 1];
  //
  //    for (int j = 0; j <= m; j++) prev[j] = j;
  //    for (int i = 1; i <= n; i++) {
  //      cur[0] = i;
  //      for (int j = 1; j <= m; j++) {
  //        int cost = a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1;
  //        cur[j] = Math.min(Math.min(cur[j - 1] + 1, prev[j] + 1), prev[j - 1] + cost);
  //      }
  //      // swap
  //      int[] tmp = prev;
  //      prev = cur;
  //      cur = tmp;
  //    }
  //    return prev[m];
  //  }

  /** Levenshtein distance with O(min(n,m)) space. Time: O(n*m). Space: O(min(n,m)). */
  public static int levenshtein(String a, String b) {
    if (a == null) a = "";
    if (b == null) b = "";

    // ensure s is the shorter string (length m), t is the longer (length n)
    String s = a.length() <= b.length() ? a : b;
    String t = a.length() <= b.length() ? b : a;

    int m = s.length();
    int n = t.length();

    // trivial cases
    if (m == 0) {
      return n;
    }
    if (n == 0) {
      return m;
    }

    int[] prev = new int[m + 1];
    int[] cur = new int[m + 1];

    // initialize prev row: distance from empty t-prefix to s-prefix
    for (int j = 0; j <= m; j++) prev[j] = j;

    for (int i = 1; i <= n; i++) {
      cur[0] = i; // distance from t[0..i] to empty s
      char tc = t.charAt(i - 1);
      for (int j = 1; j <= m; j++) {
        int cost = (s.charAt(j - 1) == tc) ? 0 : 1;
        // deletion from s, insertion into s, substitution
        int deletion = prev[j] + 1;
        int insertion = cur[j - 1] + 1;
        int substitution = prev[j - 1] + cost;
        cur[j] = Math.min(Math.min(deletion, insertion), substitution);
      }
      // swap prev and cur (reuse arrays)
      int[] tmp = prev;
      prev = cur;
      cur = tmp;
    }

    // prev now holds last row
    return prev[m];
  }

  // Fuzzy search over collection: return candidates with distance <= maxDist sorted by distance asc
  // then alphabetically
  public static List<Result> fuzzySearch(Collection<String> corpus, String query, int maxDist) {
    List<Result> results = new ArrayList<>();
    for (String item : corpus) {
      int d = levenshtein(query.toLowerCase(), item.toLowerCase());
      if (d <= maxDist) results.add(new Result(item, d));
    }
    return results.stream()
        .sorted(Comparator.comparingInt((Result r) -> r.distance).thenComparing(r -> r.text))
        .collect(Collectors.toList());
  }

  public static class Result {
    public final String text;
    public final int distance;

    public Result(String t, int d) {
      this.text = t;
      this.distance = d;
    }

    @Override
    public String toString() {
      return text + " (d=" + distance + ")";
    }
  }

  // Demo
  public static void main(String[] args) {
    List<String> corpus = Arrays.asList("apple", "apply", "pineapple", "banana", "applet", "ape");
    String query = "aplpe";
    int maxDist = 3;

    List<Result> found = fuzzySearch(corpus, query, maxDist);
    System.out.println("Query: " + query);
    found.forEach(System.out::println);
  }
}
