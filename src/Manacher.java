public class Manacher {
    // Returns the length of the longest palindromic substring
    public static int longestPalindrome(String s) {
        char[] arr = preprocess(s);
        int n = arr.length;
        int[] p = new int[n]; // p[i] = radius of palindrome centered at i
        int center = 0, right = 0;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            // Mirror index with respect to current center
            int mirror = 2 * center - i;

            if (i < right) {
                p[i] = Math.min(right - i, p[mirror]);
            } else {
                p[i] = 0;
            }

            // Expand around i
            while (i - p[i] - 1 >= 0 && i + p[i] + 1 < n &&
                    arr[i - p[i] - 1] == arr[i + p[i] + 1]) {
                p[i]++;
            }

            // Update center and right boundary
            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }

            // Track max
            maxLen = Math.max(maxLen, p[i]);
        }

        return maxLen;
    }

    // Preprocess: insert '#' between characters and at ends
    private static char[] preprocess(String s) {
        char[] arr = new char[2 * s.length() + 1];
        for (int i = 0; i < s.length(); i++) {
            arr[2 * i] = '#';
            arr[2 * i + 1] = s.charAt(i);
        }
        arr[2 * s.length()] = '#';
        return arr;
    }

    // Demo
    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad")); // 3 ("bab" or "aba")
        System.out.println(longestPalindrome("cbbd"));  // 2 ("bb")
        System.out.println(longestPalindrome("aaaa"));  // 4
    }
}
