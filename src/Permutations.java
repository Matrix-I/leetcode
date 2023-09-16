import java.util.*;

public class Permutations {

  // Function to generate and print all permutations of a string
  public static void generatePermutations(String str) {
    if (str == null || str.isEmpty()) {
      return;
    }
    List<String> permutations = new ArrayList<>();
    permute(str, "", permutations);
    for (String perm : permutations) {
      System.out.println(perm);
    }
  }

  // Helper function to generate permutations
  private static void permute(String str, String prefix, List<String> permutations) {
    int n = str.length();
    if (n == 0) {
      permutations.add(prefix);
    } else {
      for (int i = 0; i < n; i++) {
        permute(
            str.substring(0, i) + str.substring(i + 1, n), prefix + str.charAt(i), permutations);
      }
    }
  }

  public static void main(String[] args) {
    String str = "ABC";
    generatePermutations(str);
  }
}
