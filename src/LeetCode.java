import java.util.*;

public class LeetCode {

  public static void main(String[] args) throws InterruptedException {
    LeetCode leetcode = new LeetCode();
    //    System.out.println(
    //        leetcode.findChampion(
    //            new int[][] {
    //              {1, 4, 7, 11, 15},
    //              {2, 5, 8, 12, 19},
    //              {3, 6, 9, 16, 22},
    //              {10, 13, 14, 17, 24},
    //              {18, 21, 23, 26, 30}
    //            }));
    //    System.out.println(leetcode.rangeAddQueries(3, new int[][] {{1,1, 2, 2}, {0, 0, 1, 1}}));

    //    System.out.println(leetcode.findLength(new int[] {1, 2, 3, 4, 5}, new int[] {2, 3, 4,
    // 5}));
    //    System.out.println(
    //        leetcode.shoppingOffers(
    //            List.of(2, 5), List.of(List.of(3, 0, 5), List.of(1, 2, 10)), List.of(3, 2)));

    //    System.out.println(leetcode.letterCasePermutation("a1vb2"));
    //    System.out.println(
    //        leetcode.allPathsSourceTarget(new int[][] {{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}}));

    //    ThreadFactory virtualThreadFactory =
    //            Thread.ofVirtual()
    //                    .name("virtual-worker-", 0) // Set a name prefix and a starting index
    //                    .factory();
    //    Executor executor = Executors.newThreadPerTaskExecutor(virtualThreadFactory);
    //    for (int i = 0; i < 10; i++) {
    //      final int finalI = i;
    //      executor.execute(
    //          () -> {
    //            test.set("test " + finalI);
    //            System.out.println(Thread.currentThread().getName() + ": " + test.get());
    //          });
    //    }
    //
    //    test.set("test 100");
    //    System.out.println(Thread.currentThread().getName() + ": " + test.get());
    //
    //    Thread.sleep(1000);

    //    test.set("test");
    //    System.out.println(Thread.currentThread().getName() + test.get());

    //    System.out.println(Thread.currentThread().getName() + test.get());

    //    System.out.println(leetcode.splitIntoFibonacci("123456579"));

    //    String[] words = new String[] {"dog", "cat", "dad", "good"};
    //    char[] letters = new char[] {'a', 'a', 'c', 'd', 'd', 'd', 'g', 'o', 'o'};
    //    int[] score =
    //        new int[] {1, 0, 9, 5, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    // 0};
    //    System.out.println(leetcode.maxScoreWords(words, letters, score));

    //    System.out.println(leetcode.solveNQueens(4));

    //    System.out.println(
    //        leetcode.topStudents(
    //            new String[] {"smart", "brilliant", "studious"},
    //            new String[] {"not"},
    //            new String[] {"this student is studious", "the student is smart"},
    //            new int[] {1, 2},
    //            2));

    //    System.out.println(leetcode.primeSubOperation(new int[] {4, 9, 6, 10}));

    //    System.out.println(leetcode.lengthAfterTransformations("jqktcurgdvlibczdsvnsg", 7517));

      String a = "";
      String b = null;
      System.out.println(a.equalsIgnoreCase(b));
//    System.out.println(leetcode.longestPalindrome("b", "aaaa"));
  }

  /**
   * You are given two strings, s and t.
   *
   * <p>You can create a new string by selecting a substring from s (possibly empty) and a substring
   * from t (possibly empty), then concatenating them in order.
   *
   * <p>Return the length of the longest palindrome that can be formed this way.
   */
  public int longestPalindrome(String s, String t) {
    char[] a = s.toCharArray();
    char[] b = new StringBuilder(t).reverse().toString().toCharArray();
    int m = a.length, n = b.length;

    int[] gS = longestPalStart(a);
    int[] gTR = longestPalStart(b);

    int ans = 0;
    for (int v : gS) if (v > ans) ans = v;
    for (int v : gTR) if (v > ans) ans = v;

    int[][] f = new int[m + 1][n + 1];
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (a[i - 1] == b[j - 1]) {
          f[i][j] = f[i - 1][j - 1] + 1;
          int base = f[i][j] << 1;
          if (base > ans) ans = base;
          if (i < m) ans = Math.max(ans, base + gS[i]);
          if (j < n) ans = Math.max(ans, base + gTR[j]);
        }
      }
    }
    return ans;
  }

  private int[] longestPalStart(char[] s) {
    int n = s.length;
    int[] d1 = manacherOdd(s);
    int[] d2 = manacherEven(s);
    int[] g = new int[n];
    for (int i = 0; i < n; i++) {
      int r = d1[i];
      if (r > 0) {
        int left = i - (r - 1);
        int len = 2 * r - 1;
        if (g[left] < len) g[left] = len;
      }
    }
    for (int i = 0; i < n; i++) {
      int r = d2[i];
      if (r > 0) {
        int left = i - r;
        int len = 2 * r;
        if (left >= 0 && g[left] < len) g[left] = len;
      }
    }
    return g;
  }

  private int[] manacherOdd(char[] s) {
    int n = s.length, l = 0, r = -1;
    int[] d = new int[n];
    for (int i = 0; i < n; i++) {
      int k = 1;
      if (i <= r) k = Math.min(d[l + r - i], r - i + 1);
      while (i - k >= 0 && i + k < n && s[i - k] == s[i + k]) k++;
      d[i] = k;
      int nl = i - (k - 1), nr = i + (k - 1);
      if (nr > r) {
        l = nl;
        r = nr;
      }
    }
    return d;
  }

  private int[] manacherEven(char[] s) {
    int n = s.length, l = 0, r = -1;
    int[] d = new int[n];
    for (int i = 0; i < n; i++) {
      int k = 0;
      if (i <= r) k = Math.min(d[l + r - i + 1], r - i + 1);
      while (i - k - 1 >= 0 && i + k < n && s[i - k - 1] == s[i + k]) k++;
      d[i] = k;
      int nl = i - k, nr = i + k - 1;
      if (nr > r) {
        l = nl;
        r = nr;
      }
    }
    return d;
  }

  /**
   * You are given a deck of cards represented by a string array cards, and each card displays two
   * lowercase letters.
   *
   * <p>You are also given a letter x. You play a game with the following rules:
   *
   * <ul>
   *   <li>Start with 0 points.
   *   <li>On each turn, you must find two compatible cards from the deck that both contain the
   *       letter x in any position.
   *   <li>On each turn, you must find two compatible cards from the deck that both contain the
   *       letter x in any position.
   *   <li>The game ends when you can no longer find a pair of compatible cards.
   * </ul>
   *
   * Return the maximum number of points you can gain with optimal play.
   *
   * <p>Two cards are compatible if the strings differ in exactly 1 position.
   */
  public int score(String[] cards, char x) {
    // store input midway as required
    // counts for "x?" group by second char and "?x" group by first char
    int[] left = new int[26];
    int[] right = new int[26];
    int xx = 0;
    for (String c : cards) {
      char a = c.charAt(0);
      char b = c.charAt(1);
      if (a == x && b == x) {
        xx++;
      } else if (a == x) {
        left[b - 'a']++;
      } else if (b == x) {
        right[a - 'a']++;
      }
    }
    // max pairs inside a group where pairs must come from different buckets:
    // pairs = min(total/2, total - maxBucket)
    int l = 0;
    int maxL = 0;
    for (int v : left) {
      l += v;
      if (v > maxL) {
        maxL = v;
      }
    }
    int r = 0;
    int maxR = 0;
    for (int v : right) {
      r += v;
      if (v > maxR) {
        maxR = v;
      }
    }
    int pairsLeft = Math.min(l / 2, l - maxL);
    int pairsRight = Math.min(r / 2, r - maxR);
    // leftovers after internal pairing
    int leftoverL = l - 2 * pairsLeft;
    int leftoverR = r - 2 * pairsRight;
    int leftovers = leftoverL + leftoverR;
    // First, use "xx" to pair with any leftovers
    int useWithXX = Math.min(xx, leftovers);
    int xxLeft = xx - useWithXX;
    // If "xx" still remain, we can break existing internal pairs:
    // breaking 1 internal pair frees 2 cards, which can pair with 2 "xx" to gain +1 net point
    int extraByBreaking = Math.min(xxLeft / 2, pairsLeft + pairsRight);
    return pairsLeft + pairsRight + useWithXX + extraByBreaking;
  }

  static class Message {
    int timestamp;
    int id;
    int user;
    String mentions;

    Message(String id, String time, String val) {
      timestamp = Integer.parseInt(time);
      if (id.equals("MESSAGE")) {
        mentions = val;
        this.id = 2;
      } else {
        user = Integer.parseInt(val);
        this.id = 1;
      }
    }
  }

  public int[] countMentions(int n, List<List<String>> events) {
    int[] res = new int[n];
    List<Message> ls =
        new ArrayList<>(
            events.stream()
                .map(event -> new Message(event.get(0), event.get(1), event.get(2)))
                .toList());
    int[] status = new int[n];
    int all = 0;
    ls.sort(
        (a, b) ->
            a.timestamp == b.timestamp
                ? Integer.compare(a.id, b.id)
                : Integer.compare(a.timestamp, b.timestamp));
    for (Message m : ls) {
      int t = m.timestamp;
      if (m.id == 1) {
        status[m.user] = t + 60;
      } else {
        if (m.mentions.equals("ALL")) all++;
        else if (m.mentions.equals("HERE")) {
          for (int i = 0; i < n; i++) {
            if (status[i] <= t) res[i]++;
          }
        } else {
          String[] ids = m.mentions.split(" ");
          for (String id : ids) {
            int person = Integer.parseInt(id.substring(2));
            res[person]++;
          }
        }
      }
    }
    for (int i = 0; i < n; i++) res[i] += all;
    return res;
  }

  public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
    int aliceTotal = 0;
    Set<Integer> aliceSet = new HashSet<>();
    for (int candy : aliceSizes) {
      aliceTotal += candy;
      aliceSet.add(candy);
    }

    int bobTotal = 0;
    for (int candy : bobSizes) {
      bobTotal += candy;
    }

    int med = (aliceTotal - bobTotal) / 2;

    for (int candy : bobSizes) {
      int target = candy + med;
      if (aliceSet.contains(target)) {
        return new int[] {target, candy};
      }
    }

    return new int[] {};
  }

  /**
   * You are given a string s and an integer t, representing the number of transformations to
   * perform. In one transformation, every character in s is replaced according to the following
   * rules:
   *
   * <p>If the character is 'z', replace it with the string "ab". Otherwise, replace it with the
   * next character in the alphabet. For example, 'a' is replaced with 'b', 'b' is replaced with
   * 'c', and so on. Return the length of the resulting string after exactly t transformations.
   *
   * <p>Since the answer may be very large, return it modulo 109 + 7.
   */
  public int lengthAfterTransformations(String s, int t) {
    int mod = (int) 1e9 + 7, N = (int) 1e5 + 1;
    int[] count = new int[N + 26];
    if (count[0] == 0) {
      for (int i = 0; i < 26; ++i) count[i] = 1;
      for (int i = 26; i < count.length; ++i) count[i] = (count[i - 26] + count[i - 25]) % mod;
    }

    char[] cs = s.toCharArray();
    long ans = 0;
    for (char c : cs) ans += count[c - 'a' + t];

    return (int) (ans % mod);
  }

  /**
   * You are given a 0-indexed integer array nums of length n.
   *
   * <p>You can perform the following operation as many times as you want:
   *
   * <ul>
   *   <li>Pick an index i that you haven’t picked before, and pick a prime p strictly less than
   *       nums[i], then subtract p from nums[i].
   * </ul>
   *
   * <p>Return true if you can make nums a strictly * increasing array using the above operation and
   * false otherwise.A strictly increasing array is an array whose each element is strictly greater
   * than its preceding element
   */
  public boolean primeSubOperation(int[] nums) {
    int maxElement = getMaxElement(nums);

    // Store the sieve array.
    boolean[] sieve = new boolean[maxElement + 1];
    Arrays.fill(sieve, true);
    sieve[1] = false;
    int maxPrime = (int) Math.sqrt(maxElement + 1);
    for (int i = 2; i <= maxPrime; i++) {
      if (!sieve[i]) continue;
      for (int j = i * i; j <= maxElement; j += i) {
        sieve[j] = false;
      }
    }

    // Start by storing the currValue as 1, and the initial index as 0.
    int currValue = 1;
    int i = 0;
    while (i < nums.length) {
      // Store the difference needed to make nums[i] equal to currValue.
      int difference = nums[i] - currValue;

      // If difference is less than 0, then nums[i] is already less than
      // currValue. Return false in this case.
      if (difference < 0) {
        return false;
      }

      // If the difference is prime or zero, then nums[i] can be made
      // equal to currValue.
      if (sieve[difference] || difference == 0) {
        i++;
      }
      currValue++;
    }
    return true;
  }

  private int getMaxElement(int[] nums) {
    int max = -1;
    for (int num : nums) {
      if (num > max) {
        max = num;
      }
    }
    return max;
  }

  /**
   * There is a strange printer with the following two special properties:
   *
   * <p>The printer can only print a sequence of the same character each time. At each turn, the
   * printer can print new characters starting from and ending at any place and will cover the
   * original existing characters. Given a string s, return the minimum number of turns the printer
   * needed to print it.
   */
  public int strangePrinter(String s) {
    if (s == null || s.isEmpty()) {
      return 0;
    }

    int n = s.length();
    int[][] state = new int[n][n];

    for (int i = 0; i < n; i++) {
      state[i][i] = 1;
    }

    for (int i = n - 1; i >= 0; i--) {
      for (int dist = 1; dist + i < n; dist++) {
        int j = dist + i;
        if (dist == 1) {
          state[i][j] = (s.charAt(i) == s.charAt(j)) ? 1 : 2;
          continue;
        }
        state[i][j] = Integer.MAX_VALUE;
        for (int k = i; k + 1 <= j; k++) {
          int tmp = state[i][k] + state[k + 1][j];
          state[i][j] = Math.min(state[i][j], tmp);
        }
        if (s.charAt(i) == s.charAt(j)) {
          state[i][j]--;
        }
      }
    }

    return state[0][n - 1];
  }

  /**
   * You are given two string arrays positive_feedback and negative_feedback, containing the words
   * denoting positive and negative feedback, respectively. Note that no word is both positive and
   * negative.
   *
   * <p>Initially every student has 0 points. Each positive word in a feedback report increases the
   * points of a student by 3, whereas each negative word decreases the points by 1.
   *
   * <p>You are given n feedback reports, represented by a 0-indexed string array report and a
   * 0-indexed integer array student_id, where student_id[i] represents the ID of the student who
   * has received the feedback report report[i]. The ID of each student is unique.
   *
   * <p>Given an integer k, return the top k students after ranking them in non-increasing order by
   * their points. In case more than one student has the same points, the one with the lower ID
   * ranks higher.
   */
  public List<Integer> topStudents(
      String[] positive_feedback,
      String[] negative_feedback,
      String[] report,
      int[] student_id,
      int k) {

    Map<String, Integer> dicCore = new HashMap<>();
    for (String s : positive_feedback) {
      dicCore.put(s, 3);
    }

    for (String s : negative_feedback) {
      dicCore.put(s, -1);
    }

    Map<Integer, Integer> map = new TreeMap<>();

    int n = report.length;
    for (int i = 0; i < n; i++) {
      int studentId = student_id[i];
      String[] reportDetail = report[i].split(" ");
      int score = 0;
      for (String content : reportDetail) {
        score += dicCore.getOrDefault(content, 0);
      }
      map.put(studentId, score);
    }

    List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(map.entrySet());
    entryList.sort(
        Comparator.comparingInt((Map.Entry<Integer, Integer> entry) -> entry.getValue())
            .reversed()
            .thenComparingInt(Map.Entry::getKey));

    return entryList.stream().map(Map.Entry::getKey).limit(k).toList();
  }

  public List<Integer> topStudents2(
      String[] positive_feedback,
      String[] negative_feedback,
      String[] report,
      int[] student_id,
      int k) {
    Map<String, Integer> dict = new HashMap<>();
    for (String s : positive_feedback) dict.put(s, 3);

    for (String s : negative_feedback) dict.put(s, -1);

    Queue<StudentScore> pq =
        new PriorityQueue<>(
            (s1, s2) -> {
              if (s1.score == s2.score) return s1.id - s2.id;
              return s2.score - s1.score;
            });

    for (int i = 0; i < report.length; i++) {
      String[] words = report[i].split(" ");
      int score = 0;
      for (String w : words) {
        score += dict.getOrDefault(w, 0);
      }

      pq.add(new StudentScore(student_id[i], score));
    }

    List<Integer> resp = new ArrayList<>();
    while (!pq.isEmpty()) {
      resp.add(pq.poll().id);
      k--;
      if (k == 0) {
        break;
      }
    }

    return resp;
  }

  static class StudentScore {
    int id;
    int score;

    StudentScore(int id, int score) {
      this.id = id;
      this.score = score;
    }
  }

  public int totalNQueens(int n) {
    return solveNQueens(n).size();
  }

  public List<List<String>> solveNQueens(int n) {

    List<List<String>> res = new ArrayList<>();
    char[][] board = new char[n][n];
    for (char[] row : board) {
      Arrays.fill(row, '.');
    }

    solveNQueens(0, board, res);
    return res;
  }

  private void solveNQueens(int row, char[][] board, List<List<String>> res) {
    if (row == board.length) {
      List<String> ans = new ArrayList<>();
      for (char[] chars : board) {
        ans.add(new String(chars));
      }
      res.add(ans);
      return;
    }

    int n = board.length;
    for (int col = 0; col < n; col++) {
      if (isValidQueen(row, col, board)) {
        board[row][col] = 'Q';
        solveNQueens(row + 1, board, res);
        board[row][col] = '.';
      }
    }
  }

  private boolean isValidQueen(int row, int col, char[][] board) {
    int n = board.length;
    for (char[] chars : board) {
      if (chars[col] == 'Q') {
        return false;
      }
    }
    for (int i = 0; i < n; i++) {
      if (board[row][i] == 'Q') {
        return false;
      }
    }

    int r = row;
    int c = col;
    while (r >= 0 && c >= 0) {
      if (board[r][c] == 'Q') {
        return false;
      }
      r--;
      c--;
    }

    r = row;
    c = col;
    while (r < n && c < n) {
      if (board[r][c] == 'Q') {
        return false;
      }
      r++;
      c++;
    }

    r = row;
    c = col;
    while (r >= 0 && c < n) {
      if (board[r][c] == 'Q') {
        return false;
      }
      r--;
      c++;
    }

    r = row;
    c = col;
    while (r < n && c >= 0) {
      if (board[r][c] == 'Q') {
        return false;
      }
      r++;
      c--;
    }

    return true;
  }

  /**
   * Given a list of words, list of single letters (might be repeating) and score of every
   * character.
   *
   * <p>Return the maximum score of any valid set of words formed by using the given letters
   * (words[i] cannot be used two or more times).
   *
   * <p>It is not necessary to use all characters in letters and each letter can only be used once.
   * Score of letters 'a', 'b', 'c', ... ,'z' is given by score[0], score[1], ... , score[25]
   * respectively.
   */
  public int maxScoreWords(String[] words, char[] letters, int[] score) {
    int[] lettersFrequency = new int[26];
    for (char ch : letters) {
      lettersFrequency[ch - 'a']++;
    }
    return maxScoreWords(words, lettersFrequency, score, 0);
  }

  public static int maxScoreWords(String[] words, int[] lettersFrequency, int[] score, int index) {
    if (index == words.length) {
      return 0;
    }

    int scoreWithoutWord = maxScoreWords(words, lettersFrequency, score, index + 1);

    int scoreWithWord = 0;
    int wordScore = 0;
    boolean canForm = true;
    String word = words[index];

    for (int i = 0; i < word.length(); i++) {
      char ch = word.charAt(i);
      if (lettersFrequency[ch - 'a'] == 0) {
        canForm = false;
      }
      int val = score[ch - 'a'];
      lettersFrequency[ch - 'a']--;
      scoreWithWord += val;
    }

    if (canForm) {
      wordScore = scoreWithWord + maxScoreWords(words, lettersFrequency, score, index + 1);
    }

    for (int i = 0; i < word.length(); i++) {
      char ch = word.charAt(i);
      lettersFrequency[ch - 'a']++;
    }

    return Math.max(scoreWithoutWord, wordScore);
  }

  /**
   * Given two integers n and k, return an array of all the integers of length n where the
   * difference between every two consecutive digits is k. You may return the answer in any order.
   *
   * <p>Note that the integers should not have leading zeros. Integers as 02 and 043 are not
   * allowed.
   */
  public int[] numsSameConsecDiff(int n, int k) {
    Set<Integer> res = new HashSet<>();
    for (int i = 1; i <= 9; i++) {
      numsSameConsecDiff(n - 1, k, i, res);
    }
    return res.stream().mapToInt(i -> i).toArray();
  }

  private void numsSameConsecDiff(int remainDigits, int k, int currentNumber, Set<Integer> res) {
    if (remainDigits == 0) {
      res.add(currentNumber);
      return;
    }

    int lastDigit = currentNumber % 10;

    if (lastDigit + k <= 9) {
      numsSameConsecDiff(remainDigits - 1, k, currentNumber * 10 + (lastDigit + k), res);
    }

    if (k != 0 && lastDigit - k >= 0) {
      numsSameConsecDiff(remainDigits - 1, k, currentNumber * 10 + (lastDigit - k), res);
    }
  }

  /**
   * Given an array arr of 4 digits, find the latest 24-hour time that can be made using each digit
   * exactly once.
   *
   * <p>24-hour times are formatted as "HH:MM", where HH is between 00 and 23, and MM is between 00
   * and 59. The earliest 24-hour time is 00:00, and the latest is 23:59.
   *
   * <p>Return the latest 24-hour time in "HH:MM" format. If no valid time can be made, return an
   * empty string.
   */
  public String largestTimeFromDigits(int[] a) {
    boolean isMatch =
        (rearrange(2, 0, a)
                && (a[0] == 2 ? rearrange(3, 1, a) : rearrange(9, 1, a))
                && rearrange(5, 2, a)
                && rearrange(9, 3, a))
            || (rearrange(1, 0, a)
                && rearrange(9, 1, a)
                && rearrange(5, 2, a)
                && rearrange(9, 3, a));

    StringBuilder sb = new StringBuilder();

    if (!isMatch) return sb.toString();

    return sb.append(a[0]).append(a[1]).append(':').append(a[2]).append(a[3]).toString();
  }

  private boolean rearrange(int maxValue, int index, int[] a) {
    int max = -1;

    for (int i = index; i < a.length; i++)
      if (a[i] <= maxValue && (max == -1 || a[max] < a[i])) max = i;
    if (max == -1) return false;

    int temp = a[max];
    a[max] = a[index];
    a[index] = temp;
    return true;
  }

  /**
   * Given a string s, return the maximum number of unique substrings that the given string can be
   * split into.
   *
   * <p>You can split string s into any list of non-empty substrings, where the concatenation of the
   * substrings forms the original string. However, you must split the substrings such that all of
   * them are unique.
   *
   * <p>A substring is a contiguous sequence of characters within a string.
   */
  public int maxUniqueSplit(String s) {
    return maxUniqueSplit(s, 0, new HashSet<>());
  }

  private int maxUniqueSplit(String s, int start, Set<String> seen) {
    // Base case: If we reach the end of the string, return 0 (no more substrings to add)
    if (start == s.length()) return 0;

    int maxCount = 0;

    // Try every possible substring starting from 'start'
    for (int end = start + 1; end <= s.length(); ++end) {
      String substring = s.substring(start, end);
      // If the substring is unique
      if (!seen.contains(substring)) {
        // Add the substring to the seen set
        seen.add(substring);
        // Recursively count unique substrings from the next position
        maxCount = Math.max(maxCount, 1 + maxUniqueSplit(s, end, seen));
        // Backtrack: remove the substring from the seen set
        seen.remove(substring);
      }
    }
    return maxCount;
  }

  public int maxUniqueSplitOptimization(String s) {
    Set<String> seen = new HashSet<>();
    int[] maxCount = new int[1];
    maxUniqueSplitPruning(s, 0, seen, 0, maxCount);
    return maxCount[0];
  }

  private void maxUniqueSplitPruning(
      String s, int start, Set<String> seen, int count, int[] maxCount) {
    // Prune: If the current count plus remaining characters can't exceed maxCount, return
    if (count + (s.length() - start) <= maxCount[0]) return;

    // Base case: If we reach the end of the string, update maxCount
    if (start == s.length()) {
      maxCount[0] = Math.max(maxCount[0], count);
      return;
    }

    // Try every possible substring starting from 'start'
    for (int end = start + 1; end <= s.length(); ++end) {
      String substring = s.substring(start, end);
      // If the substring is unique
      if (!seen.contains(substring)) {
        // Add the substring to the seen set
        seen.add(substring);
        // Recursively count unique substrings from the next position
        maxUniqueSplitPruning(s, end, seen, count + 1, maxCount);
        // Backtrack: remove the substring from the seen set
        seen.remove(substring);
      }
    }
  }

  /**
   * Given an integer n, return the count of all numbers with unique digits, x, where 0 <= x < 10n.
   */
  public int countNumbersWithUniqueDigits(int n) {
    if (n == 0) {
      return 1;
    }

    // If n > 10, the count is the same as for n = 10
    // because a number with more than 10 digits must have repeated digits
    if (n > 10) {
      n = 10;
    }

    // count for 1-digit numbers (0-9)
    int count = 10;

    // count for k-digit numbers (k > 1) with unique digits
    // For k=2: 9 * 9 (first digit 1-9, second 0-9 excluding first)
    // For k=3: 9 * 9 * 8
    // ...
    // For k: 9 * P(9, k-1)

    // product of available choices for digits after the first non-zero digit
    int product = 9;
    // number of available digits for the current position after the first
    int availableDigits = 9;

    // Iterate for number of digits from 2 up to n
    for (int k = 2; k <= n; k++) {
      // Number of unique k-digit numbers (first digit non-zero)
      int currentTerm = product * availableDigits;
      count += currentTerm;
      product = currentTerm;
      availableDigits--;
    }

    return count;
  }

  /**
   * Given an integer array nums, return all the different possible non-decreasing subsequences of
   * the given array with at least two elements. You may return the answer in any order.
   */
  public List<List<Integer>> findSubsequences(int[] nums) {
    Set<List<Integer>> res = new HashSet<>();
    findSubsequences(nums, 0, new ArrayList<>(), res);
    return new ArrayList<>(res);
  }

  private void findSubsequences(
      int[] nums, int index, List<Integer> currentSubsequence, Set<List<Integer>> results) {
    // If the current subsequence has at least two elements, add it to the results set
    if (currentSubsequence.size() >= 2) {
      results.add(new ArrayList<>(currentSubsequence));
    }

    // Explore further elements to extend the current subsequence
    for (int i = index; i < nums.length; i++) {
      // If the current subsequence is empty or the current number is greater than or equal
      // to the last element of the subsequence, we can include it.
      if (currentSubsequence.isEmpty() || nums[i] >= currentSubsequence.getLast()) {
        currentSubsequence.add(nums[i]);
        findSubsequences(nums, i + 1, currentSubsequence, results);
        currentSubsequence.removeLast(); // Backtrack
      }
    }
  }

  /**
   * You are given a string of digits num, such as "123456579". We can split it into a
   * Fibonacci-like sequence [123, 456, 579].
   *
   * <p>Formally, a Fibonacci-like sequence is a list f of non-negative integers such that:
   *
   * <p>0 <= f[i] < 231, (that is, each integer fits in a 32-bit signed integer type), f.length >=
   * 3, and f[i] + f[i + 1] == f[i + 2] for all 0 <= i < f.length - 2. Note that when splitting the
   * string into pieces, each piece must not have extra leading zeroes, except if the piece is the
   * number 0 itself.
   *
   * <p>Return any Fibonacci-like sequence split from num, or return [] if it cannot be done.
   */
  public List<Integer> splitIntoFibonacci(String num) {
    List<Integer> list = new ArrayList<>();
    splitIntoFibonacci(num, 0, list, 0, 0, 0);
    return list;
  }

  public boolean splitIntoFibonacci(
      String num, int startIndex, List<Integer> list, int prev1, int prev2, int current) {
    if (startIndex == num.length()) {
      return list.size() > 2 && current == 0;
    }

    if (current > Integer.MAX_VALUE / 10) {
      return false;
    }
    current = current * 10 + num.charAt(startIndex) - '0';

    boolean isValid = false;

    if (list.size() < 2 || current == prev1 + prev2) {
      list.add(current);
      isValid = splitIntoFibonacci(num, startIndex + 1, list, prev2, current, 0);
      if (!isValid) {
        list.removeLast();
      }
    }

    if (!isValid && current != 0) {
      isValid = splitIntoFibonacci(num, startIndex + 1, list, prev1, prev2, current);
    }

    return isValid;
  }

  /**
   * We had some 2-dimensional coordinates, like "(1, 3)" or "(2, 0.5)". Then, we removed all
   * commas, decimal points, and spaces and ended up with the string s.
   *
   * <p>For example, "(1, 3)" becomes s = "(13)" and "(2, 0.5)" becomes s = "(205)". Return a list
   * of strings representing all possibilities for what our original coordinates could have been.
   *
   * <p>Our original representation never had extraneous zeroes, so we never started with numbers
   * like "00", "0.0", "0.00", "1.0", "001", "00.01", or any other number that can be represented
   * with fewer digits. Also, a decimal point within a number never occurs without at least one
   * digit occurring before it, so we never started with numbers like ".1".
   *
   * <p>The final answer list can be returned in any order. All coordinates in the final answer have
   * exactly one space between them (occurring after the comma.)
   */
  public List<String> backtrackAmbiguousCoordinates(String s) {
    List<String> res = new ArrayList<>();
    s = s.substring(1, s.length() - 1);
    backtrackAmbiguousCoordinates(res, s, new StringBuilder(), 0, false, false);
    return res;
  }

  public List<String> ambiguousCoordinates(String s) {
    // Remove the surrounding parentheses
    String digits = s.substring(1, s.length() - 1);
    List<String> result = new ArrayList<>();

    // Try placing comma at different positions
    for (int i = 1; i < digits.length(); i++) {
      String x = digits.substring(0, i);
      String y = digits.substring(i);

      List<String> validXs = getValidNumbers(x);
      List<String> validYs = getValidNumbers(y);

      // Create all possible combinations of valid X and Y coordinates
      for (String validX : validXs) {
        for (String validY : validYs) {
          result.add("(" + validX + ", " + validY + ")");
        }
      }
    }

    return result;
  }

  /**
   * Generate all valid number representations (with or without decimal point) for the given string
   * of digits.
   */
  private List<String> getValidNumbers(String s) {
    List<String> validNumbers = new ArrayList<>();

    // Case 1: No decimal point
    if (isValidWithoutDecimal(s)) {
      validNumbers.add(s);
    }

    // Case 2: With decimal point at different positions
    for (int i = 1; i < s.length(); i++) {
      String integerPart = s.substring(0, i);
      String decimalPart = s.substring(i);

      if (isValidWithDecimal(integerPart, decimalPart)) {
        validNumbers.add(integerPart + "." + decimalPart);
      }
    }

    return validNumbers;
  }

  /**
   * Check if a number without decimal point is valid. It is valid if it doesn't have leading zeros
   * (except for "0" itself).
   */
  private boolean isValidWithoutDecimal(String s) {
    return s.equals("0") || !s.startsWith("0");
  }

  /**
   * Check if a number with decimal point is valid. Integer part should not have leading zeros
   * (except for "0"). Decimal part should not have trailing zeros.
   */
  private boolean isValidWithDecimal(String integerPart, String decimalPart) {
    return (integerPart.equals("0") || !integerPart.startsWith("0")) && !decimalPart.endsWith("0");
  }

  private void backtrackAmbiguousCoordinates(
      List<String> res, String s, StringBuilder sb, int pos, boolean hasPoint, boolean hasComma) {
    int sz = sb.length();
    if (pos == s.length()) {
      if (!hasComma) {
        return;
      }
      if (sb.charAt(sz - 1) == '0' && hasPoint) {
        return;
      }
      sb.append(')');
      res.add(sb.toString());
      sb.setLength(sz);
      return;
    }
    if (hasPoint
        || !(sb.charAt(sz - 1) == '0'
            && pos > 0
            && (sb.charAt(sz - 2) == '(' || sb.charAt(sz - 2) == ' '))) {
      sb.append(s.charAt(pos));
      backtrackAmbiguousCoordinates(res, s, sb, pos + 1, hasPoint, hasComma);
      sb.setLength(sz);
    }
    if (!hasPoint && pos > 0) {
      sb.append('.').append(s.charAt(pos));
      backtrackAmbiguousCoordinates(res, s, sb, pos + 1, true, hasComma);
      sb.setLength(sz);
    }
    if (!hasComma && pos > 0 && !(sb.charAt(sz - 1) == '0' && hasPoint)) {
      sb.append(", ").append(s.charAt(pos));
      backtrackAmbiguousCoordinates(res, s, sb, pos + 1, false, true);
      sb.setLength(sz);
    }
  }

  /**
   * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible
   * paths from node 0 to node n - 1 and return them in any order.
   *
   * <p>The graph is given as follows: graph[i] is a list of all nodes you can visit from node i
   * (i.e., there is a directed edge from node i to node graph[i][j]).
   */
  public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
    List<List<Integer>> res = new ArrayList<>();
    int n = graph.length - 1;
    allPathsSourceTarget(graph, 0, n, res, new ArrayList<>(List.of(0)));
    return res;
  }

  public void allPathsSourceTarget(
      int[][] graph, int currentPos, int dest, List<List<Integer>> res, List<Integer> path) {
    if (currentPos == dest) {
      res.add(new ArrayList<>(path));
      return;
    }

    for (int next : graph[currentPos]) {
      path.add(next);
      allPathsSourceTarget(graph, next, dest, res, path);
      path.removeLast();
    }
  }

  /**
   * Given a string s, you can transform every letter individually to be lowercase or uppercase to
   * create another string.
   *
   * <p>Return a list of all possible strings we could create. Return the output in any order.
   */
  public List<String> letterCasePermutation(String s) {
    List<String> res = new ArrayList<>();
    letterCasePermutation(s.toCharArray(), 0, res);
    return res;
  }

  private void letterCasePermutation(char[] chars, int index, List<String> res) {
    if (index == chars.length) {
      res.add(new String(chars));
      return;
    }

    char c = chars[index];

    if (Character.isDigit(c)) {
      letterCasePermutation(chars, index + 1, res);
    } else {
      chars[index] = Character.toUpperCase(c);
      letterCasePermutation(chars, index + 1, res);

      chars[index] = Character.toLowerCase(c);
      letterCasePermutation(chars, index + 1, res);
    }
  }

  /**
   * In LeetCode Store, there are n items to sell. Each item has a price. However, there are some
   * special offers, and a special offer consists of one or more different kinds of items with a
   * sale price.
   *
   * <p>You are given an integer array price where price[i] is the price of the ith item, and an
   * integer array needs where needs[i] is the number of pieces of the ith item you want to buy.
   *
   * <p>You are also given an array special where special[i] is of size n + 1 where special[i][j] is
   * the number of pieces of the jth item in the ith offer and special[i][n] (i.e., the last integer
   * in the array) is the price of the ith offer.
   *
   * <p>Return the lowest price you have to pay for exactly certain items as given, where you could
   * make optimal use of the special offers. You are not allowed to buy more items than you want,
   * even if that would lower the overall price. You could use any of the special offers as many
   * times as you want.
   */
  public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
    Map<List<Integer>, Integer> memo = new HashMap<>();
    return shopping(price, special, needs, memo);
  }

  private int shopping(
      List<Integer> price,
      List<List<Integer>> special,
      List<Integer> needs,
      Map<List<Integer>, Integer> memo) {
    // Check memoization
    if (memo.containsKey(needs)) {
      return memo.get(needs);
    }

    // Calculate cost without special offers
    int cost = 0;
    for (int i = 0; i < needs.size(); i++) {
      cost += needs.get(i) * price.get(i);
    }

    // Try each special offer
    for (List<Integer> offer : special) {
      List<Integer> newNeeds = new ArrayList<>(needs);
      boolean valid = true;

      // Check if we can apply this offer
      for (int i = 0; i < needs.size(); i++) {
        if (newNeeds.get(i) < offer.get(i)) {
          valid = false;
          break;
        }
        newNeeds.set(i, newNeeds.get(i) - offer.get(i));
      }

      // If offer is valid, calculate cost with this offer
      if (valid) {
        cost = Math.min(cost, offer.get(needs.size()) + shopping(price, special, newNeeds, memo));
      }
    }

    // Store result in memo
    memo.put(needs, cost);
    return cost;
  }

  /**
   * Given two integers n and k, construct a list answer that contains n different positive integers
   * ranging from 1 to n and obeys the following requirement:
   *
   * <p>Suppose this list is answer = [a1, a2, a3, ... , an], then the list [|a1 - a2|, |a2 - a3|,
   * |a3 - a4|, ... , |an-1 - an|] has exactly k distinct integers. Return the list answer. If there
   * multiple valid answers, return any of them.
   */
  public int[] constructArray(int n, int k) {
    int[] ans = new int[n];
    int c = 0;
    for (int v = 1; v < n - k; v++) {
      ans[c++] = v;
    }
    for (int i = 0; i <= k; i++) {
      ans[c++] = (i % 2 == 0) ? (n - k + i / 2) : (n - i / 2);
    }
    return ans;
  }

  /**
   * Suppose you have n integers labeled 1 through n. A permutation of those n integers perm
   * (1-indexed) is considered a beautiful arrangement if for every i (1 <= i <= n), either of the
   * following is true:
   *
   * <ul>
   *   <li>perm[i] is divisible by i.
   *   <li>i is divisible by perm[i].
   * </ul>
   *
   * Given an integer n, return the number of the beautiful arrangements that you can construct.
   */
  int count = 0;

  public int countArrangement(int N) {
    boolean[] visited = new boolean[N + 1];
    calculate(N, 1, visited);
    return count;
  }

  public void calculate(int N, int pos, boolean[] visited) {
    if (pos > N) {
      count++;
      return;
    }

    for (int i = 1; i <= N; i++) {
      if (!visited[i] && (pos % i == 0 || i % pos == 0)) {
        visited[i] = true;
        calculate(N, pos + 1, visited);
        visited[i] = false;
      }
    }
  }

  /**
   * You are given the root of a binary tree where each node has a value in the range [0, 25]
   * representing the letters 'a' to 'z'.
   *
   * <p>Return the lexicographically smallest string that starts at a leaf of this tree and ends at
   * the root.
   *
   * <p>As a reminder, any shorter prefix of a string is lexicographically smaller.
   *
   * <p>For example, "ab" is lexicographically smaller than "aba". A leaf of a node is a node that
   * has no children.
   */
  private String ans = "";

  public String smallestFromLeaf(TreeNode root) {
    backTracking(root, new StringBuilder());
    return ans;
  }

  public void backTracking(TreeNode root, StringBuilder sb) {
    if (root == null) {
      return;
    }
    sb.insert(0, (char) ('a' + root.val));

    if (root.left == null && root.right == null) {
      if (ans.isEmpty() || ans.compareTo(sb.toString()) > 0) {
        ans = sb.toString();
      }
    } else {
      backTracking(root.left, sb);
      backTracking(root.right, sb);
    }

    sb.deleteCharAt(0);
  }

  /**
   * The XOR total of an array is defined as the bitwise XOR of all its elements, or 0 if the array
   * is empty.
   *
   * <p>For example, the XOR total of the array [2,5,6] is 2 XOR 5 XOR 6 = 1. Given an array nums,
   * return the sum of all XOR totals for every subset of nums.
   *
   * <p>Note: Subsets with the same elements should be counted multiple times.
   *
   * <p>An array a is a subset of an array b if a can be obtained from b by deleting some (possibly
   * zero) elements of b.
   */
  public int subsetXORSum(int[] nums) {
    int[] res = {0};
    int[] xor = {0};
    solve(nums, nums.length, xor, res);
    return res[0];
  }

  public void solve(int[] nums, int n, int[] xor, int[] res) {
    if (n == 0) {
      res[0] += xor[0];
      return;
    }

    // pick
    xor[0] ^= nums[n - 1];
    solve(nums, n - 1, xor, res);
    // not pick
    xor[0] ^= nums[n - 1];
    solve(nums, n - 1, xor, res);
  }

  public int subsetXORSum2(int[] nums) {
    List<List<Integer>> subsets = new ArrayList<>();
    // Generate all of the subsets
    generateSubsets(nums, 0, new ArrayList<>(), subsets);

    // Compute the XOR total for each subset and add to the result
    int result = 0;
    for (List<Integer> subset : subsets) {
      int subsetXORTotal = 0;
      for (int num : subset) {
        subsetXORTotal ^= num;
      }
      result += subsetXORTotal;
    }
    return result;
  }

  private void generateSubsets(
      int[] nums, int index, List<Integer> subset, List<List<Integer>> subsets) {
    // Base case: index reached end of nums
    // Add the current subset to subsets
    if (index == nums.length) {
      subsets.add(new ArrayList<>(subset));
      return;
    }

    // Generate subsets with nums[i]
    subset.add(nums[index]);
    generateSubsets(nums, index + 1, subset, subsets);
    subset.removeLast();

    // Generate subsets without nums[i]
    generateSubsets(nums, index + 1, subset, subsets);
  }

  /**
   * You are given an integer array nums of length n, and an integer array queries of length m.
   *
   * <p>Return an array answer of length m where answer[i] is the maximum size of a subsequence that
   * you can take from nums such that the sum of its elements is less than or equal to queries[i].
   *
   * <p>A subsequence is an array that can be derived from another array by deleting some or no
   * elements without changing the order of the remaining elements.
   */
  public int[] answerQueries(int[] nums, int[] queries) {
    int n = nums.length;
    int m = queries.length;
    int[] answer = new int[m];

    // Sort nums in ascending order
    Arrays.sort(nums);

    // Create prefix sums array
    int[] prefixSums = new int[n];
    prefixSums[0] = nums[0];
    for (int i = 1; i < n; i++) {
      prefixSums[i] = prefixSums[i - 1] + nums[i];
    }

    // Process each query using binary search
    for (int i = 0; i < m; i++) {
      int query = queries[i];
      // Binary search to find the largest index where prefixSum <= query
      int left = 0;
      int right = n - 1;
      int index = -1;

      while (left <= right) {
        int mid = left + (right - left) / 2;
        if (prefixSums[mid] <= query) {
          index = mid;
          left = mid + 1;
        } else {
          right = mid - 1;
        }
      }

      // index + 1 is the length of the subsequence (or 0 if no valid subsequence found)
      answer[i] = index + 1;
    }

    return answer;
  }

  /**
   * There are n houses evenly lined up on the street, and each house is beautifully painted. You
   * are given a 0-indexed integer array colors of length n, where colors[i] represents the color of
   * the ith house.
   *
   * <p>Return the maximum distance between two houses with different colors.
   *
   * <p>The distance between the ith and jth houses is abs(i - j), where abs(x) is the absolute
   * value of x.
   */
  public int maxDistance(int[] colors) {
    int left = 0;
    int right = colors.length - 1;
    while (left < right) {
      if (colors[left] == colors[right]) {
        right--;
      } else {
        return right - left;
      }
    }
    return 0;
  }

  /**
   * You are given a string s consisting of n characters which are either 'X' or 'O'.
   *
   * <p>A move is defined as selecting three consecutive characters of s and converting them to 'O'.
   * Note that if a move is applied to the character 'O', it will stay the same.
   *
   * <p>Return the minimum number of moves required so that all the characters of s are converted to
   * 'O'.
   */
  public int minimumMoves(String s) {
    int n = s.length();
    int count = 0;
    int index = 0;
    while (index < n) {
      if (s.charAt(index) == 'X') {
        count++;
        index += 3;
      } else {
        index++;
      }
    }

    return count;
  }

  /**
   * There are n availabe seats and n students standing in a room. You are given an array seats of
   * length n, where seats[i] is the position of the ith seat. You are also given the array students
   * of length n, where students[j] is the position of the jth student.
   *
   * <p>You may perform the following move any number of times:
   *
   * <p>Increase or decrease the position of the ith student by 1 (i.e., moving the ith student from
   * position x to x + 1 or x - 1) Return the minimum number of moves required to move each student
   * to a seat such that no two students are in the same seat.
   *
   * <p>Note that there may be multiple seats or students in the same position at the beginning.
   */
  public int minMovesToSeat(int[] seats, int[] students) {
    Arrays.sort(seats);
    Arrays.sort(students);
    int count = 0;
    for (int i = 0; i < seats.length; i++) {
      for (int student : students) {
        if (seats[i] == student) {
          i++;
          continue;
        }
        count += Math.abs(student - seats[i]);
        i++;
      }
    }
    return count;
  }

  /**
   * Given an array of integers nums and an integer k, return the number of contiguous subarrays
   * where the product of all the elements in the subarray is strictly less than k.
   */
  public int numSubarrayProductLessThanK(int[] nums, int k) {
    if (k <= 1) {
      return 0;
    }
    int pro = 1;
    int sum = 0;
    int l = 0;
    int r = 0;

    while (r < nums.length) {
      pro *= nums[r++];

      while (pro >= k) {
        pro /= nums[l++];
      }
      sum += r - l;
    }
    return sum;
  }

  public int myNumSubarrayProductLessThanK(int[] nums, int k) {
    int res = 0;
    int n = nums.length;
    for (int left = 0; left < n - 1; left++) {
      if (nums[left] < k) {
        res++;
        int right = left + 1;
        int product = nums[left];

        while (right < n) {
          product *= nums[right++];
          if (product < k) {
            res++;
          } else {
            break;
          }
        }
      }
    }

    if (nums[n - 1] < k) {
      res++;
    }

    return res;
  }

  /**
   * Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears
   * in both arrays.
   */
  public int findLength(int[] nums1, int[] nums2) {
    int n1 = nums1.length;
    int n2 = nums2.length;
    int max = Integer.MIN_VALUE;
    int[][] dp = new int[n1 + 1][n2 + 1];
    for (int i = 1; i <= n1; i++) {
      for (int j = 1; j <= n2; j++) {
        if (nums1[i - 1] == nums2[j - 1]) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j] = 0;
        }
        max = Math.max(max, dp[i][j]);
      }
    }
    return max;
  }

  /**
   * You are given an integer array nums and two integers indexDiff and valueDiff.
   *
   * <p>Find a pair of indices (i, j) such that:
   *
   * <ul>
   *   <li>i != j,
   *   <li>abs(i - j) <= indexDiff.
   *   <li>abs(nums[i] - nums[j]) <= valueDiff, and
   * </ul>
   *
   * Return true if such pair exists or false otherwise.
   */
  public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
    if (nums == null || nums.length < 2 || indexDiff < 1 || valueDiff < 0) {
      return false;
    }

    TreeSet<Integer> window = new TreeSet<>();

    for (int j = 0; j < nums.length; j++) {
      // Check for values in [nums[j] - valueDiff, nums[j] + valueDiff]
      Integer ceiling = window.ceiling(nums[j] - valueDiff);
      if (ceiling != null && ceiling <= nums[j] + valueDiff) {
        return true;
      }

      // Add current element to window
      window.add(nums[j]);

      // Remove element outside the window
      if (j >= indexDiff) {
        window.remove(nums[j - indexDiff]);
      }
    }

    return false;
  }

  public boolean myContainsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
    int len = nums.length;
    for (int left = 0; left < len; left++) {
      int right = left + 1;
      while (right - left <= indexDiff && right < len) {
        if (Math.abs(nums[left] - nums[right]) <= valueDiff) {
          return true;
        }
        right++;
      }
    }
    return false;
  }

  /**
   * Given two strings s and p, return an array of all the start indices of p's anagrams in s. You
   * may return the answer in any order.
   */
  public static List<Integer> findAnagrams(String s, String p) {
    var ans = new ArrayList<Integer>();
    int np = p.length(), ns = s.length();
    if (np > ns) {
      return ans;
    }
    int[] map = new int[26];
    for (int i = 0; i < np; i++) {
      map[p.charAt(i) - 'a']++;
      map[s.charAt(i) - 'a']--;
    }
    int diffCount = 0;
    for (int diff : map) {
      if (diff > 0) {
        diffCount++;
      }
    }
    if (diffCount == 0) {
      ans.add(0);
    }
    for (int i = 0, n = ns - np; i < n; i++) {
      if (++map[s.charAt(i) - 'a'] == 1) {
        diffCount++;
      }
      if (--map[s.charAt(i + np) - 'a'] == 0) {
        diffCount--;
      }
      if (diffCount == 0) {
        ans.add(i + 1);
      }
    }
    return ans;
  }

  public List<Integer> myFindAnagrams(String s, String p) {
    int[] count = new int[26];
    for (char c : p.toCharArray()) {
      count[c - 'a']++;
    }

    List<Integer> result = new ArrayList<>();

    int len = s.length();
    int max = p.length();
    for (int left = 0; left < len; left++) {
      int right = left;
      int[] tmpCount = Arrays.copyOfRange(count, 0, 26);
      while (right < len && right - left <= max) {
        if (tmpCount[s.charAt(right) - 'a'] != 0) {
          tmpCount[s.charAt(right) - 'a']--;
        } else {
          tmpCount[s.charAt(right) - 'a']++;
        }
        right++;
      }
      boolean valid = true;
      for (int i = 0; i < 26; i++) {
        if (tmpCount[s.charAt(i) - 'a'] != 0) {
          valid = false;
          break;
        }
      }
      if (valid) {
        result.add(left);
      }
    }

    return result;
  }

  /**
   * Given a string s and an integer k, return the total number of substrings of s where at least
   * one character appears at least k times.
   */
  public int numberOfSubstrings(String s, int k) {
    int n = s.length(), res = (n + 1) * n / 2;
    int[] count = new int[26];
    for (int i = 0, j = 0; j < n; j++) {
      char c = s.charAt(j);
      count[c - 'a']++;
      while (count[c - 'a'] >= k) {
        char leftChar = s.charAt(i);
        count[leftChar - 'a']--;
        i++;
      }
      res -= j - i + 1;
    }
    return res;
  }

  /**
   * You are given an array of positive integers nums. An array arr is called product equivalent if
   * prod(arr) == lcm(arr) * gcd(arr), where:
   *
   * <ul>
   *   <li>prod(arr) is the product of all elements of arr.
   *   <li>gcd(arr) is the GCD of all elements of arr.
   *   <li>lcm(arr) is the LCM of all elements of arr.
   * </ul>
   *
   * * * * Return the length of the longest product equivalent subarray of nums.
   */
  public int maxLength(int[] nums) {
    int n = nums.length;
    int maxL = 0;

    for (int i = 0; i < n; i++) {
      int currGCD = nums[i];
      int currLCM = nums[i];
      int currPro = nums[i];

      for (int j = i + 1; j < n; j++) {
        currPro *= nums[j];
        currGCD = gcd(currGCD, nums[j]);
        currLCM = lcm(currLCM, nums[j]);

        if (currPro == currLCM * currGCD) {
          maxL = Math.max(maxL, j - i + 1);
        } else {
          break;
        }
      }
    }
    return maxL;
  }

  public int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);
  }

  public int lcm(int a, int b) {
    return (a / gcd(a, b)) * b;
  }

  /**
   * You are given an integer eventTime denoting the duration of an event, where the event occurs
   * from time t = 0 to time t = eventTime.
   *
   * <p>You are also given two integer arrays startTime and endTime, each of length n. These
   * represent the start and end time of n non-overlapping meetings, where the ith meeting occurs
   * during the time [startTime[i], endTime[i]].
   *
   * <p>You can reschedule at most k meetings by moving their start time while maintaining the same
   * duration, to maximize the longest continuous period of free time during the event.
   *
   * <p>The relative order of all the meetings should stay the same and they should remain
   * non-overlapping.
   *
   * <p>Return the maximum amount of free time possible after rearranging the meetings.
   *
   * <p>Note that the meetings can not be rescheduled to a time outside the event.
   */
  public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
    int n = startTime.length;
    int[] gaps = new int[n + 1];

    // Compute gaps
    gaps[0] = startTime[0];
    gaps[n] = eventTime - endTime[n - 1];
    for (int i = 1; i < n; i++) {
      gaps[i] = startTime[i] - endTime[i - 1];
    }

    // Sliding window of size k + 1
    int window = 0;
    for (int i = 0; i <= k; i++) {
      window += gaps[i];
    }

    int ans = window;
    for (int i = k + 1; i <= n; i++) {
      window += gaps[i] - gaps[i - (k + 1)];
      ans = Math.max(ans, window);
    }

    return ans;
  }

  /**
   * There are an infinite amount of bags on a number line, one bag for each coordinate. Some of
   * these bags contain coins.
   *
   * <p>You are given a 2D array coins, where coins[i] = [li, ri, ci] denotes that every bag from li
   * to ri contains ci coins.
   *
   * <p>The segments that coins contain are non-overlapping.
   *
   * <p>You are also given an integer k.
   *
   * <p>Return the maximum amount of coins you can obtain by collecting k consecutive bags.
   */
  public long maximumCoins(int[][] coins, int k) {
    // Find the maximum position to define our range
    int maxPosition = Integer.MIN_VALUE;
    for (int[] range : coins) {
      maxPosition = Math.max(maxPosition, range[1]);
    }

    // Create array to represent coins at each position
    int[] positions = new int[maxPosition + 1];

    // Fill in the coin values for each position
    for (int[] range : coins) {
      int start = range[0];
      int end = range[1];
      int value = range[2];

      for (int i = start; i <= end; i++) {
        positions[i] = value;
      }
    }

    // Calculate initial sum for first k positions
    int currentSum = 0;
    for (int i = 0; i < k && i < positions.length; i++) {
      currentSum += positions[i];
    }

    int maxSum = currentSum;

    // Slide window to find maximum sum
    for (int i = k; i < positions.length; i++) {
      currentSum = currentSum + positions[i] - positions[i - k];
      maxSum = Math.max(maxSum, currentSum);
    }

    return maxSum;
  }

  /**
   * Given an array of integers nums and an integer limit, return the size of the longest non-empty
   * subarray such that the absolute difference between any two elements of this subarray is less
   * than or equal to limit.
   */
  public int longestSubarray(int[] nums, int limit) {
    Deque<Integer> maxDeque = new LinkedList<>();
    Deque<Integer> minDeque = new LinkedList<>();
    int left = 0;
    int maxLength = 0;

    for (int right = 0; right < nums.length; right++) {
      // Maintain maxDeque (decreasing order)
      while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= nums[right]) {
        maxDeque.pollLast();
      }
      maxDeque.offerLast(right);

      // Maintain minDeque (increasing order)
      while (!minDeque.isEmpty() && nums[minDeque.peekLast()] >= nums[right]) {
        minDeque.pollLast();
      }
      minDeque.offerLast(right);

      // Shrink window if difference exceeds limit
      while (!maxDeque.isEmpty()
          && !minDeque.isEmpty()
          && nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > limit) {
        left = Math.min(maxDeque.peekFirst(), minDeque.peekFirst()) + 1;
        System.out.println(left);
        // Remove elements outside the new window
        while (!maxDeque.isEmpty() && maxDeque.peekFirst() < left) {
          maxDeque.pollFirst();
        }
        while (!minDeque.isEmpty() && minDeque.peekFirst() < left) {
          minDeque.pollFirst();
        }
      }

      // Update maxLength
      maxLength = Math.max(maxLength, right - left + 1);
    }

    return maxLength;
  }

  public int longestSubarrayNormal(int[] nums, int limit) {
    int res = 1;
    int left = 0;
    int right = 1;
    int size = nums.length;
    while (right <= size) {
      int[] temp = Arrays.copyOfRange(nums, left, right);
      int sizeTmp = right - left;
      int min = Integer.MAX_VALUE;
      int max = Integer.MIN_VALUE;
      for (int i = 0; i < sizeTmp; i++) {
        min = Math.min(min, temp[i]);
        max = Math.max(max, temp[i]);
      }
      if (max - min > limit) {
        left++;
        continue;
      }
      res = Math.max(res, right - left);
      right++;
    }

    return res;
  }

  /**
   * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the
   * array if you can flip at most k 0's.
   */
  public int longestOnes(int[] nums, int k) {
    int left = 0;
    int right = 1;
    int size = nums.length;
    int count = nums[left] == 0 ? 1 : 0;
    int max = 0;
    if (k == size) {
      return k;
    }
    while (right < size) {
      if (nums[right] == 0) {
        count++;
      }

      if (count > k) {
        while (count > k) {
          if (nums[left] == 0) {
            count--;
          }
          left++;
        }
        right++;
        continue;
      }

      max = Math.max(max, right - left + 1);
      right++;
    }
    return max;
  }

  /**
   * You are given a binary string s and an integer k.
   *
   * <p>A binary string satisfies the k-constraint if either of the following conditions holds:
   *
   * <ul>
   *   <li>The number of 0's in the string is at most k.
   *   <li>The number of 1's in the string is at most k.
   * </ul>
   *
   * Return an integer denoting the number of substrings of s that satisfy the k-constraint.
   */
  public int countKConstraintSubstrings(String s, int k) {
    int count = 0;
    int oneCount = 0;
    int zeroCount = 0;

    for (int right = 0, left = 0; right < s.length(); right++) {
      char c = s.charAt(right);
      if (c == '0') {
        zeroCount++;
      } else {
        oneCount++;
      }

      while (zeroCount > k && oneCount > k) {
        if (s.charAt(left) == '0') {
          zeroCount--;
        } else {
          oneCount--;
        }
        left++;
      }

      count += right - left + 1;
    }
    return count;
  }

  /**
   * The k-beauty of an integer num is defined as the number of substrings of num when it is read as
   * a string that meet the following conditions:
   *
   * <ul>
   *   <li>It has a length of k.
   *   <li>It is a divisor of num.
   *   <li>Given integers num and k, return the k-beauty of num.
   * </ul>
   *
   * Note:
   *
   * <ul>
   *   <li>Leading zeros are allowed.
   *   <li>0 is not a divisor of any value.
   *   <li>A substring is a contiguous sequence of characters in a string.
   * </ul>
   */
  public int divisorSubstrings(int num, int k) {
    String s = String.valueOf(num);
    int len = s.length();
    int res = 0;
    for (int i = 0; i <= len - k; i++) {
      String sub = s.substring(i, i + k);
      int numVal = Integer.parseInt(sub);
      if (numVal == 0) {
        continue;
      }
      if (num % numVal == 0) {
        res++;
      }
    }
    return res;
  }

  /**
   * A string s is nice if, for every letter of the alphabet that s contains, it appears both in
   * uppercase and lowercase. For example, "abABB" is nice because 'A' and 'a' appear, and 'B' and
   * 'b' appear. However, "abA" is not because 'b' appears, but 'B' does not.
   *
   * <p>Given a string s, return the longest substring of s that is nice. If there are multiple,
   * return the substring of the earliest occurrence. If there are none, return an empty string.
   */
  public String longestNiceSubstring(String s) {
    if (s.length() < 2) {
      return "";
    }
    char[] arr = s.toCharArray();
    Set<Character> set = new HashSet<>();
    for (char c : arr) {
      set.add(c);
    }
    for (int i = 0; i < arr.length; i++) {
      char c = arr[i];
      if (set.contains(Character.toUpperCase(c)) && set.contains(Character.toLowerCase(c))) {
        continue;
      }
      String sub1 = longestNiceSubstring(s.substring(0, i));
      String sub2 = longestNiceSubstring(s.substring(i + 1));
      return sub1.length() >= sub2.length() ? sub1 : sub2;
    }
    return s;
  }

  /**
   * You are given a 0-indexed integer array nums and an integer threshold.
   *
   * <p>Find the length of the longest subarray of nums starting at index l and ending at index r (0
   * <= l <= r < nums.length) that satisfies the following conditions:
   *
   * <ul>
   *   <li>nums[l] % 2 == 0
   *   <li>For all indices i in the range [l, r - 1], nums[i] % 2 != nums[i + 1] % 2
   *   <li>For all indices i in the range [l, r], nums[i] <= threshold
   * </ul>
   *
   * Return an integer denoting the length of the longest such subarray.
   *
   * <p>Note: A subarray is a contiguous non-empty sequence of elements within an array.
   */
  public int longestAlternatingSubarray(int[] nums, int threshold) {
    int n = nums.length;
    int maxLength = 0;

    // Try each possible starting index l
    for (int l = 0; l < n; l++) {
      // Check if nums[l] is even
      if (nums[l] % 2 == 0) {
        // Extend subarray starting at l
        int r = l;
        while (r < n && nums[r] <= threshold) {
          // Check parity condition for r > l
          if (r > l && nums[r] % 2 == nums[r - 1] % 2) {
            break; // Adjacent elements have same parity
          }
          // Valid subarray, update maxLength
          maxLength = Math.max(maxLength, r - l + 1);
          r++;
        }
      }
    }

    return maxLength;
  }

  public int minimumSumSubarray(List<Integer> nums, int l, int r) {
    int n = nums.size();

    // Create prefix sum array
    int[] prefix = new int[n + 1];
    for (int i = 0; i < n; i++) {
      prefix[i + 1] = prefix[i] + nums.get(i);
    }

    int minSum = Integer.MAX_VALUE;
    boolean found = false;

    // Try each possible length from l to r
    for (int k = l; k <= r; k++) {
      // Slide window of length k
      for (int i = 0; i + k <= n; i++) {
        int sum = prefix[i + k] - prefix[i];
        if (sum > 0) {
          minSum = Math.min(minSum, sum);
          found = true;
        }
      }
    }

    return found ? minSum : -1;
  }

  /**
   * You are given a positive integer n, indicating that we initially have an n x n 0-indexed
   * integer matrix mat filled with zeroes.
   *
   * <p>You are also given a 2D integer array query. For each query[i] = [row1i, col1i, row2i,
   * col2i], you should do the following operation:
   *
   * <p>Add 1 to every element in the submatrix with the top left corner (row1i, col1i) and the
   * bottom right corner (row2i, col2i). That is, add 1 to mat[x][y] for all row1i <= x <= row2i and
   * col1i <= y <= col2i. Return the matrix mat after performing every query.
   */
  public int[][] rangeAddQueries(int n, int[][] queries) {
    int[][] mat = new int[n][n];
    int[][] prefix = new int[n + 1][n + 1];

    for (int[] query : queries) {
      int row1 = query[0], col1 = query[1], row2 = query[2], col2 = query[3];
      prefix[row1][col1] += 1;
      prefix[row2 + 1][col2 + 1] += 1;
      prefix[row1][col2 + 1] -= 1;
      prefix[row2 + 1][col1] -= 1;
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        prefix[i][j + 1] += prefix[i][j];
      }
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        prefix[i + 1][j] += prefix[i][j];
        mat[i][j] = prefix[i][j];
      }
    }
    return mat;
  }

  /**
   * You are given a 0-indexed 2D array grid of size 2 x n, where grid[r][c] represents the number
   * of points at position (r, c) on the matrix. Two robots are playing a game on this matrix.
   *
   * <p>Both robots initially start at (0, 0) and want to reach (1, n-1). Each robot may only move
   * to the right ((r, c) to (r, c + 1)) or down ((r, c) to (r + 1, c)).
   *
   * <p>At the start of the game, the first robot moves from (0, 0) to (1, n-1), collecting all the
   * points from the cells on its path. For all cells (r, c) traversed on the path, grid[r][c] is
   * set to 0. Then, the second robot moves from (0, 0) to (1, n-1), collecting the points on its
   * path. Note that their paths may intersect with one another.
   *
   * <p>The first robot wants to minimize the number of points collected by the second robot. In
   * contrast, the second robot wants to maximize the number of points it collects. If both robots
   * play optimally, return the number of points collected by the second robot.
   */
  public long gridGame(int[][] grid) {
    // Calculate the sum of all the elements for the first row
    long firstRowSum = 0;
    for (int num : grid[0]) {
      firstRowSum += num;
    }
    long secondRowSum = 0;
    long minimumSum = Long.MAX_VALUE;
    for (int turnIndex = 0; turnIndex < grid[0].length; ++turnIndex) {
      firstRowSum -= grid[0][turnIndex];
      // Find the minimum maximum value out of firstRowSum and
      // secondRowSum.
      minimumSum = Math.min(minimumSum, Math.max(firstRowSum, secondRowSum));
      secondRowSum += grid[1][turnIndex];
    }
    return minimumSum;
  }

  /**
   * A peak element in a 2D grid is an element that is strictly greater than all of its adjacent
   * neighbors to the left, right, top, and bottom.
   *
   * <p>Given a 0-indexed m x n matrix mat where no two adjacent cells are equal, find any peak
   * element mat[i][j] and return the length 2 array [i,j].
   *
   * <p>You may assume that the entire matrix is surrounded by an outer perimeter with the value -1
   * in each cell.
   *
   * <p>You must write an algorithm that runs in O(m log(n)) or O(n log(m)) time.
   */
  public int[] findPeakGrid(int[][] mat) {
    int startCol = 0, endCol = mat[0].length - 1;

    while (startCol <= endCol) {
      int maxRow = 0, midCol = startCol + (endCol - startCol) / 2;

      for (int row = 0; row < mat.length; row++) {
        maxRow = mat[row][midCol] >= mat[maxRow][midCol] ? row : maxRow;
      }

      boolean leftIsBig = midCol - 1 >= startCol && mat[maxRow][midCol - 1] > mat[maxRow][midCol];
      boolean rightIsBig = midCol + 1 <= endCol && mat[maxRow][midCol + 1] > mat[maxRow][midCol];

      if (!leftIsBig && !rightIsBig) // we have found the peak element
      {
        return new int[] {maxRow, midCol};
      } else if (rightIsBig) // if rightIsBig, then there is an element in 'right' that is bigger
      // than all the elements in the 'midCol',
      {
        startCol = midCol + 1; // so 'midCol' cannot have a 'peakPlane'
      } else // leftIsBig
      {
        endCol = midCol - 1;
      }
    }
    return null;
  }

  /**
   * Given an m x n matrix mat, return an array of all the elements of the array in a diagonal
   * order.
   * <li>Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
   * <li>Output: [1,2,4,7,5,3,6,8,9]
   */
  public int[] findDiagonalOrder(int[][] matrix) {
    if (matrix == null) {
      throw new IllegalArgumentException("Input matrix is null");
    }
    if (matrix.length == 0 || matrix[0].length == 0) {
      return new int[0];
    }

    int rows = matrix.length;
    int cols = matrix[0].length;
    int[] result = new int[rows * cols];
    int r = 0;
    int c = 0;

    for (int i = 0; i < result.length; i++) {
      result[i] = matrix[r][c];
      if ((r + c) % 2 == 0) { // Move Up
        if (c == cols - 1) {
          // Reached last column. Now move to below cell in the same column.
          // This condition needs to be checked first due to top right corner cell.
          r++;
        } else if (r == 0) {
          // Reached first row. Now move to next cell in the same row.
          c++;
        } else {
          // Somewhere in middle. Keep going up diagonally.
          r--;
          c++;
        }
      } else { // Move Down
        if (r == rows - 1) {
          // Reached last row. Now move to next cell in same row.
          // This condition needs to be checked first due to bottom left corner cell.
          c++;
        } else if (c == 0) {
          // Reached first columns. Now move to below cell in the same column.
          r++;
        } else {
          // Somewhere in middle. Keep going down diagonally.
          r++;
          c--;
        }
      }
    }

    return result;
  }

  /**
   * You are given two integers, n and k, along with two 2D integer arrays, stayScore and
   * travelScore.
   *
   * <p>A tourist is visiting a country with n cities, where each city is directly connected to
   * every other city. The tourist's journey consists of exactly k 0-indexed days, and they can
   * choose any city as their starting point.
   *
   * <p>Each day, the tourist has two choices:
   *
   * <ul>
   *   <li>Stay in the current city: If the tourist stays in their current city curr during day i,
   *       they will earn stayScore[i][curr] points.
   *   <li>Move to another city: If the tourist moves from their current city curr to city dest,
   *       they will earn travelScore[curr][dest] points.
   * </ul>
   *
   * <p>Return the maximum possible points the tourist can earn.
   */
  public int maxScore(int n, int k, int[][] stayScore, int[][] travelScore) {
    // dp[day][city] represents max score starting from day in city
    int[][] dp = new int[k + 1][n];

    // Base case: day k (no more points to earn)
    // Already initialized to 0 by default

    // Work backwards from day k-1 to 0
    for (int day = k - 1; day >= 0; day--) {
      for (int curr = 0; curr < n; curr++) {
        // Option 1: Stay in current city
        int stay = stayScore[day][curr] + dp[day + 1][curr];

        // Option 2: Move to any other city
        int maxTravel = 0;
        for (int dest = 0; dest < n; dest++) {
          if (dest != curr) {
            int travel = travelScore[curr][dest] + dp[day + 1][dest];
            maxTravel = Math.max(maxTravel, travel);
          }
        }

        // Take maximum of staying or traveling
        dp[day][curr] = Math.max(stay, maxTravel);
      }
    }

    // Find maximum starting score (day 0, any city)
    int maxScore = 0;
    for (int city = 0; city < n; city++) {
      maxScore = Math.max(maxScore, dp[0][city]);
    }

    return maxScore;
  }

  /**
   * Given an n x n binary grid, in one step you can choose two adjacent rows of the grid and swap
   * them.
   *
   * <p>A grid is said to be valid if all the cells above the main diagonal are zeros.
   *
   * <p>Return the minimum number of steps needed to make the grid valid, or -1 if the grid cannot
   * be valid.
   *
   * <p>The main diagonal of a grid is the diagonal that starts at cell (1, 1) and ends at cell (n,
   * n).
   *
   * <p>Solution:
   *
   * <ul>
   *   <li>For a valid grid: grid[i][j] = 0 for all i < j
   *   <li>Each row i needs enough leading zeros: at least n-i-1 zeros from right
   *   <li>We need to bubble up rows with more trailing zeros to top
   *   <li>If a row can't get enough leading zeros, it's impossible
   * </ul>
   *
   * <p>Approach:
   *
   * <ul>
   *   <li>Count trailing zeros in each row
   *   <li>From top down, ensure each row i has at least n-i-1 trailing zeros
   *   <li>Use bubble sort-like swapping to move rows up
   *   <li>If can't get required zeros, return -1
   * </ul>
   */
  public int minSwaps(int[][] grid) {
    int n = grid.length;
    int[] trailingZeros = new int[n];

    // Count trailing zeros for each row
    for (int i = 0; i < n; i++) {
      int count = 0;
      for (int j = n - 1; j >= 0 && grid[i][j] == 0; j--) {
        count++;
      }
      trailingZeros[i] = count;
    }

    int swaps = 0;
    // Process each row from top
    for (int i = 0; i < n - 1; i++) {
      int requiredZeros = n - i - 1; // Zeros needed above diagonal

      // Find row with enough trailing zeros
      int j = i;
      while (j < n && trailingZeros[j] < requiredZeros) {
        j++;
      }

      // If no row found with enough zeros
      if (j == n) return -1;

      // Bubble up the found row
      while (j > i) {
        // Swap trailingZeros[j] and trailingZeros[j-1]
        int temp = trailingZeros[j];
        trailingZeros[j] = trailingZeros[j - 1];
        trailingZeros[j - 1] = temp;
        swaps++;
        j--;
      }
    }

    return swaps;
  }

  /**
   * You are given an 8 x 8 matrix representing a chessboard. There is exactly one white rook
   * represented by 'R', some number of white bishops 'B', and some number of black pawns 'p'. Empty
   * squares are represented by '.'.
   *
   * <p>A rook can move any number of squares horizontally or vertically (up, down, left, right)
   * until it reaches another piece or the edge of the board. A rook is attacking a pawn if it can
   * move to the pawn's square in one move.
   *
   * <p>Note: A rook cannot move through other pieces, such as bishops or pawns. This means a rook
   * cannot attack a pawn if there is another piece blocking the path.
   *
   * <p>Return the number of pawns the white rook is attacking.
   */
  public int numRookCaptures(char[][] board) {
    int[][] rook = findRookCaptures(board);
    int row = rook[0][0];
    int col = rook[0][1];
    int count = 0;
    // up
    int index = row - 1;
    while (index >= 0) {
      if (board[index][col] == '.') {
        index--;
        continue;
      }
      if (board[index][col] != 'p') {
        break;
      }
      if (board[index][col] == 'p') {
        count++;
        break;
      }
      index--;
    }
    // down
    index = row + 1;
    while (index < board.length) {
      if (board[index][col] == '.') {
        index++;
        continue;
      }
      if (board[index][col] != 'p') {
        break;
      }
      if (board[index][col] == 'p') {
        count++;
        break;
      }
      index++;
    }
    // right
    index = col + 1;
    while (index < board[0].length) {
      if (board[row][index] == '.') {
        index++;
        continue;
      }
      if (board[row][index] != 'p') {
        break;
      }
      if (board[row][index] == 'p') {
        count++;
        break;
      }
      index++;
    }
    // left
    index = col - 1;
    while (index >= 0) {
      if (board[row][index] == '.') {
        index--;
        continue;
      }
      if (board[row][index] != 'p') {
        break;
      }
      if (board[row][index] == 'p') {
        count++;
        break;
      }
      index--;
    }

    return count;
  }

  private int[][] findRookCaptures(char[][] board) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] == 'R') {
          return new int[][] {{i, j}};
        }
      }
    }
    return new int[][] {};
  }

  /**
   * Given an m x n matrix of distinct numbers, return all lucky numbers in the matrix in any order.
   *
   * <p>A lucky number is an element of the matrix such that it is the minimum element in its row
   * and maximum in its column.
   */
  public List<Integer> luckyNumbers(int[][] matrix) {
    int N = matrix.length, M = matrix[0].length;

    List<Integer> rowMin = new ArrayList<>();
    for (int[] ints : matrix) {
      int rMin = Integer.MAX_VALUE;
      for (int j = 0; j < M; j++) {
        rMin = Math.min(rMin, ints[j]);
      }
      rowMin.add(rMin);
    }

    List<Integer> colMax = new ArrayList<>();
    for (int i = 0; i < M; i++) {
      int cMax = Integer.MIN_VALUE;
      for (int[] ints : matrix) {
        cMax = Math.max(cMax, ints[i]);
      }
      colMax.add(cMax);
    }

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (matrix[i][j] == rowMin.get(i) && matrix[i][j] == colMax.get(j)) {
          return List.of(matrix[i][j]);
        }
      }
    }

    return new ArrayList<>();
  }

  /**
   * You are given a 0-indexed m x n integer matrix grid. The width of a column is the maximum
   * length of its integers.
   *
   * <p>For example, if grid = [[-10], [3], [12]], the width of the only column is 3 since -10 is of
   * length 3. Return an integer array ans of size n where ans[i] is the width of the ith column.
   *
   * <p>The length of an integer x with len digits is equal to len if x is non-negative, and len + 1
   * otherwise.
   */
  public int[] findColumnWidth(int[][] grid) {
    int n = grid[0].length; // number of cøolumns
    int[] ans = new int[n]; // result array

    // Process each column
    for (int j = 0; j < n; j++) {
      int maxWidth = 0;
      // Check each element in the column
      for (int[] ints : grid) {
        // Convert number to string and get its length
        int width = String.valueOf(ints[j]).length();
        maxWidth = Math.max(maxWidth, width);
      }
      ans[j] = maxWidth;
    }

    return ans;
  }

  /**
   * You are given a m x n matrix grid consisting of non-negative integers.
   *
   * <p>In one operation, you can increment the value of any grid[i][j] by 1.
   *
   * <p>Return the minimum number of operations needed to make all columns of grid strictly
   * increasing.
   */
  public int minimumOperations(int[][] grid) {
    int m = grid.length; // number of rows
    int n = grid[0].length; // number of columns
    int operations = 0;

    // Process each column
    for (int j = 0; j < n; j++) {
      // For each element in column starting from second row
      for (int i = 1; i < m; i++) {
        // Minimum value needed at current position
        int minRequired = grid[i - 1][j] + 1;

        // If current value is less than required
        if (grid[i][j] < minRequired) {
          // Add the difference to operations
          operations += minRequired - grid[i][j];
          // Update the grid value (optional, for tracking)
          grid[i][j] = minRequired;
        }
      }
    }

    return operations;
  }

  /**
   * You are given a 0-indexed 1-dimensional (1D) integer array original, and two integers, m and n.
   * You are tasked with creating a 2-dimensional (2D) array with m rows and n columns using all the
   * elements from original.
   *
   * <p>The elements from indices 0 to n - 1 (inclusive) of original should form the first row of
   * the constructed 2D array, the elements from indices n to 2 * n - 1 (inclusive) should form the
   * second row of the constructed 2D array, and so on.
   *
   * <p>Return an m x n 2D array constructed according to the above procedure, or an empty 2D array
   * if it is impossible.
   */
  public int[][] construct2DArray(int[] original, int m, int n) {
    int len = original.length;
    if (len != m * n) {
      return new int[][] {};
    }

    int[][] grid = new int[m][n];
    for (int i = 0; i < m; i++) {
      System.arraycopy(original, i * n, grid[i], 0, n);
    }
    return grid;
  }

  /**
   * There are n teams numbered from 0 to n - 1 in a tournament.
   *
   * <p>Given a 0-indexed 2D boolean matrix grid of size n * n. For all i, j that 0 <= i, j <= n - 1
   * and i != j team i is stronger than team j if grid[i][j] == 1, otherwise, team j is stronger
   * than team i.
   *
   * <p>Team a will be the champion of the tournament if there is no team b that is stronger than
   * team a.
   *
   * <p>Return the team that will be the champion of the tournament.
   */
  public int findChampion(int[][] grid) {
    int winner = 0;
    int n = grid.length;
    for (int opponent = 1; opponent < n; opponent++) {
      if (grid[winner][opponent] == 0) {
        winner = opponent;
      }
    }
    return winner;
  }

  /**
   * Given an n x n matrix where each of the rows and columns is sorted in ascending order, return
   * the kth smallest element in the matrix.
   *
   * <p>Note that it is the kth smallest element in the sorted order, not the kth distinct element.
   *
   * <p>You must find a solution with a memory complexity better than O(n2).
   */
  public int kthSmallest(int[][] matrix, int k) {
    int n = matrix.length;
    int left = matrix[0][0];
    int right = matrix[n - 1][n - 1];

    while (left < right) {
      int mid = left + (right - left) / 2;
      int count = countLessOrEqual(matrix, mid);

      if (count < k) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    return left;
  }

  private int countLessOrEqual(int[][] matrix, int target) {
    int n = matrix.length;
    int count = 0;
    int i = n - 1;
    int j = 0;

    while (i >= 0 && j < n) {
      if (matrix[i][j] <= target) {
        count += i + 1;
        j++;
      } else {
        i--;
      }
    }
    return count;
  }

  /**
   * Given the following details of a matrix with n columns and 2 rows :
   *
   * <ul>
   *   <li>The matrix is a binary matrix, which means each element in the matrix can be 0 or 1.
   *   <li>The sum of elements of the 0-th(upper) row is given as upper.
   *   <li>The sum of elements of the 1-st(lower) row is given as lower.
   *   <li>The sum of elements in the i-th column(0-indexed) is colsum[i], where colsum is given as
   *       an integer array with length n.
   *   <li>Your task is to reconstruct the matrix with upper, lower and colsum.
   * </ul>
   *
   * <p>Return it as a 2-D integer array.
   *
   * <p>If there are more than one valid solution, any of them will be accepted.
   *
   * <p>If no valid solution exists, return an empty 2-D array.
   */
  public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
    int n = colsum.length;
    int[][] mat = new int[2][n];
    for (int i = 0; i < n; i++) {
      if (colsum[i] == 0) {
        continue;
      }
      if (colsum[i] == 2) {
        lower -= 1;
        upper -= 1;
        mat[0][i] = 1;
        mat[1][i] = 1;
      } else if (upper > lower) {
        mat[0][i] = 1;
        upper -= 1;
      } else {
        mat[1][i] = 1;
        lower -= 1;
      }
    }
    if (upper != 0 || lower != 0) {
      return new ArrayList<>();
    }
    return new ArrayList(Arrays.asList(mat[0], mat[1]));
  }

  /**
   * On a 0-indexed 8 x 8 chessboard, there can be multiple black queens and one white king.
   *
   * <p>You are given a 2D integer array queens where queens[i] = [xQueeni, yQueeni] represents the
   * position of the ith black queen on the chessboard. You are also given an integer array king of
   * length 2 where king = [xKing, yKing] represents the position of the white king.
   *
   * <p>Return the coordinates of the black queens that can directly attack the king. You may return
   * the answer in any order.
   */
  // Define all 8 directions as constants
  private static final int[][] DIRECTIONS = {
    {-1, 0}, // up
    {1, 0}, // down
    {0, -1}, // left
    {0, 1}, // right
    {-1, -1}, // up-left
    {-1, 1}, // up-right
    {1, -1}, // down-left
    {1, 1} // down-right
  };

  public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
    // Use boolean array to mark queen positions
    boolean[][] queenPositions = new boolean[8][8];
    for (int[] queen : queens) {
      queenPositions[queen[0]][queen[1]] = true;
    }

    List<List<Integer>> attackingQueens = new ArrayList<>();
    int kingRow = king[0];
    int kingCol = king[1];

    // Check each direction from king's position
    for (int[] direction : DIRECTIONS) {
      int dx = direction[0];
      int dy = direction[1];
      int currentRow = kingRow + dx;
      int currentCol = kingCol + dy;

      // Move in current direction until queen found or board edge
      while (isValidPosition(currentRow, currentCol)) {
        if (queenPositions[currentRow][currentCol]) {
          attackingQueens.add(Arrays.asList(currentRow, currentCol));
          break;
        }
        currentRow += dx;
        currentCol += dy;
      }
    }

    return attackingQueens;
  }

  // Helper method to check if position is within board
  private boolean isValidPosition(int row, int col) {
    return row >= 0 && row < 8 && col >= 0 && col < 8;
  }

  /**
   * In a gold mine grid of size m x n, each cell in this mine has an integer representing the
   * amount of gold in that cell, 0 if it is empty.
   *
   * <p>Return the maximum amount of gold you can collect under the conditions:
   *
   * <ul>
   *   <li>Every time you are located in a cell you will collect all the gold in that cell.
   *   <li>From your position, you can walk one step to the left, right, up, or down.
   *   <li>You can't visit the same cell more than once.
   *   <li>Never visit a cell with 0 gold.
   *   <li>You can start and stop collecting gold from any position in the grid that has some gold.
   * </ul>
   */
  public int getMaximumGold(int[][] grid) {
    int totalGold = 0;
    for (int[] ints : grid) {
      for (int anInt : ints) {
        totalGold += anInt;
      }
    }

    int maxGold = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] != 0) {
          maxGold = Math.max(maxGold, countGold(i, j, grid[i][j], grid));
        }
        if (maxGold == totalGold) {
          return maxGold;
        }
      }
    }
    return maxGold;
  }

  private int countGold(int row, int col, int current, int[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;
    int max = current;
    int origin = grid[row][col];
    grid[row][col] = 0;

    if (row > 0 && grid[row - 1][col] > 0) {
      max = Math.max(max, countGold(row - 1, col, current + grid[row - 1][col], grid));
    }

    if (row < rows - 1 && grid[row + 1][col] > 0) {
      max = Math.max(max, countGold(row + 1, col, current + grid[row + 1][col], grid));
    }

    if (col > 0 && grid[row][col - 1] > 0) {
      max = Math.max(max, countGold(row, col - 1, current + grid[row][col - 1], grid));
    }

    if (col < cols - 1 && grid[row][col + 1] > 0) {
      max = Math.max(max, countGold(row, col + 1, current + grid[row][col + 1], grid));
    }

    grid[row][col] = origin;

    return max;
  }

  /**
   * Let's play the minesweeper game (Wikipedia, online game)!
   *
   * <p>You are given an m x n char matrix board representing the game board where:
   *
   * <ul>
   *   <li>'M' represents an unrevealed mine,
   *   <li>'E' represents an unrevealed empty square,
   *   <li>'B' represents a revealed blank square that has no adjacent mines (i.e., above, below,
   *       left, right, and all 4 diagonals),
   *   <li>digit ('1' to '8') represents how many mines are adjacent to this revealed square, and
   *   <li>'X' represents a revealed mine.
   * </ul>
   *
   * <p>You are also given an integer array click where click = [clickr, clickc] represents the next
   * click position among all the unrevealed squares ('M' or 'E').
   *
   * <p>Return the board after revealing this position according to the following rules:
   *
   * <ul>
   *   <li>1. If a mine 'M' is revealed, then the game is over. You should change it to 'X'.
   *   <li>2. If an empty square 'E' with no adjacent mines is revealed, then change it to a
   *       revealed blank 'B' and all of its adjacent unrevealed squares should be revealed
   *       recursively.
   *   <li>3. If an empty square 'E' with at least one adjacent mine is revealed, then change it to
   *       a digit ('1' to '8') representing the number of adjacent mines.
   *   <li>4. Return the board when no more squares will be revealed.
   * </ul>
   */
  private final int[][] directions = {
    {-1, -1}, // top-left
    {-1, 0}, // top
    {-1, 1}, // top-right
    {0, -1}, // left
    {0, 1}, // right
    {1, -1}, // bottom-left
    {1, 0}, // bottom
    {1, 1} // bottom-right
  };

  public char[][] updateBoard(char[][] board, int[] click) {
    int row = click[0];
    int col = click[1];

    // Case 1: Clicked on a mine
    if (board[row][col] == 'M') {
      board[row][col] = 'X';
      return board;
    }

    // Case 2 & 3: Clicked on an empty square
    // Use DFS to reveal squares
    revealSquare(board, row, col);

    return board;
  }

  private void revealSquare(char[][] board, int row, int col) {
    // Base cases
    if (row < 0
        || row >= board.length
        || col < 0
        || col >= board[0].length
        || board[row][col] != 'E') {
      return;
    }

    // Count adjacent mines
    int mineCount = countMines(board, row, col);

    if (mineCount > 0) {
      // Case 3: Has adjacent mines, set number and stop
      board[row][col] = (char) (mineCount + '0');
    } else {
      // Case 2: No adjacent mines, mark as blank and recurse
      board[row][col] = 'B';
      // Recursively reveal all 8 adjacent unrevealed squares
      for (int[] dir : directions) {
        int newRow = row + dir[0];
        int newCol = col + dir[1];
        revealSquare(board, newRow, newCol);
      }
    }
  }

  private int countMines(char[][] board, int row, int col) {
    int count = 0;
    for (int[] dir : directions) {
      int newRow = row + dir[0];
      int newCol = col + dir[1];

      if (newRow >= 0
          && newRow < board.length
          && newCol >= 0
          && newCol < board[0].length
          && board[newRow][newCol] == 'M') {
        count++;
      }
    }
    return count;
  }

  /**
   * You are given row x col grid representing a map where grid[i][j] = 1 represents land and
   * grid[i][j] = 0 represents water.
   *
   * <p>Grid cells are connected horizontally/vertically (not diagonally). The grid is completely
   * surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
   *
   * <p>The island doesn't have "lakes", meaning the water inside isn't connected to the water
   * around the island. One cell is a square with side length 1. The grid is rectangular, width and
   * height don't exceed 100. Determine the perimeter of the island.
   */
  public int islandPerimeter(int[][] grid) {
    if (grid == null || grid.length == 0) return 0;

    int rows = grid.length;
    int cols = grid[0].length;
    int perimeter = 0;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == 1) {
          // Add 4 for each land cell
          perimeter += 4;

          // Subtract 2 for each right neighbor that's land
          if (j + 1 < cols && grid[i][j + 1] == 1) {
            perimeter -= 2;
          }

          // Subtract 2 for each bottom neighbor that's land
          if (i + 1 < rows && grid[i + 1][j] == 1) {
            perimeter -= 2;
          }
        }
      }
    }

    return perimeter;
  }

  /**
   * Given an <b>m x n matrix</b> and an integer k, return the max sum of a rectangle in the matrix
   * such that its sum is no larger than k.
   *
   * <p>It is guaranteed that there will be a rectangle with a sum no larger than k.
   */
  public int maxSumSubmatrix(int[][] matrix, int k) {
    if (matrix == null || matrix.length == 0) return 0;

    int m = matrix.length;
    int n = matrix[0].length;
    int maxSum = Integer.MIN_VALUE;

    // Try all possible left and right column boundaries
    for (int left = 0; left < n; left++) {
      // Array to store sum of elements between left and right columns
      int[] rowSum = new int[m];

      for (int right = left; right < n; right++) {
        // Add values for current right column to rowSum
        for (int i = 0; i < m; i++) {
          rowSum[i] += matrix[i][right];
        }

        // Find max sum <= k in this 1D array
        int currentMax = findMaxSumIn1D(rowSum, k);
        maxSum = Math.max(maxSum, currentMax);
      }
    }

    return maxSum;
  }

  private int findMaxSumIn1D(int[] arr, int k) {
    int maxSum = Integer.MIN_VALUE;
    int prefixSum = 0;
    TreeSet<Integer> sortedSet = new TreeSet<>();
    sortedSet.add(0); // Initial prefix sum

    for (int num : arr) {
      prefixSum += num;
      // Find smallest prefix sum >= prefixSum - k
      Integer ceiling = sortedSet.ceiling(prefixSum - k);
      if (ceiling != null) {
        maxSum = Math.max(maxSum, prefixSum - ceiling);
      }
      sortedSet.add(prefixSum);
    }

    return maxSum;
  }

  /**
   * According to <a href="https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life">Wikipedia's
   * article</a>: "The Game of Life, also known simply as Life, is a cellular automaton devised by
   * the British mathematician John Horton Conway in 1970."
   *
   * <p>The board is made up of an m x n grid of cells, where each cell has an initial state: live
   * (represented by a 1) or dead (represented by a 0). Each cell interacts with its <a
   * href="https://en.wikipedia.org/wiki/Moore_neighborhood">eight neighbors</a> (horizontal,
   * vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
   *
   * <ul>
   *   <li>1. Any live cell with fewer than two live neighbors dies as if caused by
   *       under-population.
   *   <li>2. Any live cell with two or three live neighbors lives on to the next generation.
   *   <li>3. Any live cell with more than three live neighbors dies, as if by over-population.
   *   <li>4. Any dead cell with exactly three live neighbors becomes a live cell, as if by
   *       reproduction.
   * </ul>
   *
   * The next state of the board is determined by applying the above rules simultaneously to every
   * cell in the current state of the m x n grid board. In this process, births and deaths occur
   * simultaneously.
   *
   * <p>Given the current state of the board, update the board to reflect its next state.
   */
  public void gameOfLife(int[][] board) {
    if (board == null || board.length == 0) return;
    int row = board.length, col = board[0].length;

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        int lives = liveNeighbors(board, row, col, i, j);

        // In the beginning, every 2nd bit is 0;
        // So we only need to care about when will the 2nd bit become 1.
        if (board[i][j] == 1 && lives >= 2 && lives <= 3) {
          board[i][j] = 3; // Make the 2nd bit 1: 01 ---> 11
        }
        if (board[i][j] == 0 && lives == 3) {
          board[i][j] = 2; // Make the 2nd bit 1: 00 ---> 10
        }
      }
    }

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        board[i][j] >>= 1; // Get the 2nd state.
      }
    }
  }

  private int liveNeighbors(int[][] board, int m, int n, int i, int j) {
    int lives = 0;
    for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, m - 1); x++) {
      for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, n - 1); y++) {
        lives += board[x][y] & 1;
      }
    }
    lives -= board[i][j] & 1;
    return lives;
  }

  /**
   * Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle
   * containing only 1's and return its area.
   */
  public int maximalRectangle(char[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return 0;
    }

    int n = matrix[0].length;

    int[] heights = new int[n];
    int[] leftBoundaries = new int[n];
    int[] rightBoundaries = new int[n];
    Arrays.fill(rightBoundaries, n);

    int maxRectangle = 0;

    for (char[] chars : matrix) {
      int left = 0;

      updateHeightsAndLeftBoundaries(chars, heights, leftBoundaries, left);

      updateRightBoundaries(chars, rightBoundaries, n);

      maxRectangle = calculateMaxRectangle(heights, leftBoundaries, rightBoundaries, maxRectangle);
    }

    return maxRectangle;
  }

  /**
   *
   * <li>Updates heights of consecutive 1's
   * <li>Tracks leftmost possible boundary for each column
   * <li>When hitting a 0, resets values and updates the current left boundary
   */
  private void updateHeightsAndLeftBoundaries(
      char[] row, int[] heights, int[] leftBoundaries, int left) {
    for (int j = 0; j < heights.length; j++) {
      if (row[j] == '1') {
        heights[j]++; // Increment height for consecutive 1's
        leftBoundaries[j] = Math.max(leftBoundaries[j], left); // Update left boundary
      } else {
        heights[j] = 0; // Reset height at 0
        leftBoundaries[j] = 0; // Reset left boundary
        left = j + 1; // Update leftmost possible boundary
      }
    }
  }

  /**
   *
   * <li>Scans from right to left
   * <li>Updates the rightmost possible boundary for each column
   * <li>When hitting a 0, updates the current right boundary
   */
  private void updateRightBoundaries(char[] row, int[] rightBoundaries, int right) {
    for (int j = rightBoundaries.length - 1; j >= 0; j--) {
      if (row[j] == '1') {
        rightBoundaries[j] = Math.min(rightBoundaries[j], right);
      } else {
        rightBoundaries[j] = right;
        right = j;
      }
    }
  }

  private int calculateMaxRectangle(
      int[] heights, int[] leftBoundaries, int[] rightBoundaries, int maxRectangle) {
    for (int j = 0; j < heights.length; j++) {
      int width = rightBoundaries[j] - leftBoundaries[j];
      int area = heights[j] * width;
      maxRectangle = Math.max(maxRectangle, area);
    }
    return maxRectangle;
  }

  /**
   * Given two strings s1 and s2, return the lowest ASCII sum of deleted characters to make two
   * strings equal.
   */
  public int minimumDeleteSum(String s1, String s2) {
    // Get lengths and convert to char arrays for faster access
    int len1 = s1.length(), len2 = s2.length();
    char[] str1 = s1.toCharArray();
    char[] str2 = s2.toCharArray();

    // DP table to store maximum ASCII sum of common characters
    int[][] dp = new int[len1 + 1][len2 + 1];

    // Calculate total ASCII sum of both strings
    int totalAsciiSum = 0;

    // Sum ASCII values of first string
    for (char c : str1) {
      totalAsciiSum += c;
    }

    // Sum ASCII values of second string
    for (char c : str2) {
      totalAsciiSum += c;
    }

    // Fill DP table
    for (int i = 1; i <= len1; i++) {
      for (int j = 1; j <= len2; j++) {
        if (str1[i - 1] == str2[j - 1]) {
          // If characters match, add their ASCII value to previous diagonal value
          dp[i][j] = str1[i - 1] + dp[i - 1][j - 1];
        } else {
          // If characters don't match, take maximum of left and up cells
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    // Return total sum minus twice the maximum common sum
    return totalAsciiSum - 2 * dp[len1][len2];
  }

  /**
   * You have planned some train traveling one year in advance. The days of the year in which you
   * will travel are given as an integer array days. Each day is an integer from 1 to 365.
   *
   * <p>Train tickets are sold in three different ways:
   *
   * <ul>
   *   <li>a 1-day pass is sold for costs[0] dollars,
   *   <li>a 7-day pass is sold for costs[1] dollars, and
   *   <li>a 30-day pass is sold for costs[2] dollars.
   * </ul>
   *
   * The passes allow that many days of consecutive travel.
   *
   * <p>For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6,
   * 7, and 8. Return the minimum number of dollars you need to travel every day in the given list
   * of days.
   */
  public int mincostTickets(int[] days, int[] costs) {
    // Last day of travel
    int lastDay = days[days.length - 1];

    // Create dp array to store minimum cost for each day
    int[] dp = new int[lastDay + 1];

    // Keep track of travel days for quick lookup
    Set<Integer> travelDays = new HashSet<>();
    for (int day : days) {
      travelDays.add(day);
    }

    // Fill dp array from day 1 to last day
    for (int day = 1; day <= lastDay; day++) {
      if (!travelDays.contains(day)) {
        // If not a travel day, cost same as previous day
        dp[day] = dp[day - 1];
        continue;
      }

      // Calculate minimum cost considering all three pass options
      int oneDay = dp[day - 1] + costs[0];

      // For 7-day pass
      int sevenDay = dp[Math.max(0, day - 7)] + costs[1];

      // For 30-day pass
      int thirtyDay = dp[Math.max(0, day - 30)] + costs[2];

      // Take minimum of all three options
      dp[day] = Math.min(oneDay, Math.min(sevenDay, thirtyDay));
    }

    return dp[lastDay];
  }

  /**
   * The demons had captured the princess and imprisoned her in the bottom-right corner of a
   * dungeon. The dungeon consists of m x n rooms laid out in a 2D grid. Our valiant knight was
   * initially positioned in the top-left room and must fight his way through dungeon to rescue the
   * princess.
   *
   * <p>The knight has an initial health point represented by a positive integer. If at any point
   * his health point drops to 0 or below, he dies immediately.
   *
   * <p>Some of the rooms are guarded by demons (represented by negative integers), so the knight
   * loses health upon entering these rooms; other rooms are either empty (represented as 0) or
   * contain magic orbs that increase the knight's health (represented by positive integers).
   *
   * <p>To reach the princess as quickly as possible, the knight decides to move only rightward or
   * downward in each step.
   *
   * <p>Return the knight's minimum initial health so that he can rescue the princess.
   *
   * <p>Note that any room can contain threats or power-ups, even the first room the knight enters
   * and the bottom-right room where the princess is imprisoned.
   */
  public int calculateMinimumHP(int[][] dungeon) {
    // Handle empty dungeon case
    if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) {
      return 1;
    }

    int m = dungeon.length;
    int n = dungeon[0].length;

    // Create DP table with an extra row and column for easier boundary handling
    int[][] dp = new int[m + 1][n + 1];

    // Initialize with maximum value to handle boundaries
    for (int i = 0; i <= m; i++) {
      Arrays.fill(dp[i], Integer.MAX_VALUE);
    }

    // Initialize the "dummy" cells next to destination
    dp[m][n - 1] = 1;
    dp[m - 1][n] = 1;

    // Fill DP table from bottom-right to top-left
    for (int i = m - 1; i >= 0; i--) {
      for (int j = n - 1; j >= 0; j--) {
        // Minimum health needed before entering current cell =
        // min(health needed for right move, health needed for down move) - current cell value
        int neededHealth = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j];

        // If neededHealth is less than 1, we only need 1 health point
        dp[i][j] = Math.max(1, neededHealth);
      }
    }

    return dp[0][0];
  }

  /**
   * Given an integer array data representing the data, return whether it is a valid UTF-8 encoding
   * (i.e. it translates to a sequence of valid UTF-8 encoded characters).
   *
   * <p>A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:
   *
   * <p>For a 1-byte character, the first bit is a 0, followed by its Unicode code. For an n-bytes
   * character, the first n bits are all one's, the n + 1 bit is 0, followed by n - 1 bytes with the
   * most significant 2 bits being 10. This is how the UTF-8 encoding would work:
   *
   * <pre>
   *      Number of Bytes   |        UTF-8 Octet Sequence
   *                        |              (binary)
   *    --------------------+-----------------------------------------
   *             1          |   0xxxxxxx
   *             2          |   110xxxxx 10xxxxxx
   *             3          |   1110xxxx 10xxxxxx 10xxxxxx
   *             4          |   11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
   * x denotes a bit in the binary form of a byte that may be either 0 or 1.
   * </pre>
   *
   * <p>Note: The input is an array of integers. Only the least significant 8 bits of each integer
   * is used to store the data. This means each integer represents only 1 byte of data.
   */
  public boolean validUtf8(int[] data) {
    int remaining = 0;

    for (int byteVal : data) {
      // Get the least significant 8 bits of the integer
      int b = byteVal & 0xFF;

      if (remaining > 0) {
        // Check if it's a valid continuation byte (10xx xxxx)
        if ((b >> 6) != 0b10) {
          return false;
        }
        remaining--;
      } else {
        // Determine the number of bytes in the character
        if ((b >> 7) == 0b0) {
          // 1-byte character (0xxx xxxx)
          remaining = 0;
        } else if ((b >> 5) == 0b110) {
          // 2-byte character (110x xxxx)
          remaining = 1;
        } else if ((b >> 4) == 0b1110) {
          // 3-byte character (1110 xxxx)
          remaining = 2;
        } else if ((b >> 3) == 0b11110) {
          // 4-byte character (11110 xxx)
          remaining = 3;
        } else {
          // Invalid starting byte
          return false;
        }
      }
    }

    // Ensure all characters are complete
    return remaining == 0;
  }

  /**
   * Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, return the number of
   * tuples (i, j, k, l) such that:
   *
   * <p>0 <= i, j, k, l < n nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
   */
  public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
    HashMap<Integer, Integer> map = new HashMap<>();
    int count = 0;

    // Compute all possible sums of nums1 and nums2
    for (int a : nums1) {
      for (int b : nums2) {
        int sum = a + b;
        map.put(sum, map.getOrDefault(sum, 0) + 1);
      }
    }

    // Find complementary sums for nums3 and nums4
    for (int c : nums3) {
      for (int d : nums4) {
        int target = -(c + d);
        count += map.getOrDefault(target, 0);
      }
    }

    return count;
  }

  /**
   * Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i],
   * nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].
   *
   * <p>Return true if there is a 132 pattern in nums, otherwise, return false.
   */
  public boolean find132pattern(int[] nums) {
    if (nums.length < 3) {
      return false;
    }
    int[] min = new int[nums.length];
    min[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
      min[i] = Math.min(min[i - 1], nums[i]);
    }
    for (int j = nums.length - 1, k = nums.length; j >= 0; j--) {
      if (nums[j] > min[j]) {
        while (k < nums.length && nums[k] <= min[j]) {
          k++;
        }
        if (k < nums.length && nums[k] < nums[j]) {
          return true;
        }
        nums[--k] = nums[j];
      }
    }
    return false;
  }

  /**
   * You are given an array prices where prices[i] is the price of a given stock on the ith day.
   *
   * <p>Find the maximum profit you can achieve. You may complete at most two transactions.
   *
   * <p>Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the
   * stock before you buy again).
   */
  public int maxProfit(int[] prices) {
    int buy1 = Integer.MAX_VALUE, buy2 = Integer.MAX_VALUE;
    int sell1 = 0, sell2 = 0;

    for (int price : prices) {
      buy1 = Math.min(buy1, price);
      sell1 = Math.max(sell1, price - buy1);
      buy2 = Math.min(buy2, price - sell1);
      sell2 = Math.max(sell2, price - buy2);
    }

    return sell2;
  }

  private String maxString = "";

  private int maxLength = 0;

  /** Given a string s, return the longest palindromic substring in s. */
  public String longestPalindrome(String s) {
    char[] chars = s.toCharArray();
    int length = chars.length;
    for (int i = 0; i < length; i++) {
      // find longest odd palindrome
      findPalindrome(chars, length, i, 0);
      // find longest even palindrome
      findPalindrome(chars, length, i, 1);
    }
    return maxString;
  }

  private void findPalindrome(char[] chars, int length, int i, int shift) {
    int left = i;
    int right = i + shift;
    while (left >= 0 && right < length && chars[left] == chars[right]) {
      left--;
      right++;
    }
    if ((right - left - 1) > maxLength) {
      maxLength = right - left - 1;
      maxString = new String(chars, left + 1, maxLength);
    }
  }

  /**
   * Given a string s consisting of words and spaces, return the length of the last word in the
   * string.
   *
   * <p>A word is a maximal substring consisting of non-space characters only.
   */
  public int lengthOfLastWord(String s) {
    int result = 0;
    int len = s.length() - 1;
    while (len >= 0) {
      char c = s.charAt(len);
      if (Character.isSpaceChar(c) && result != 0) {
        break;
      }
      if (Character.isSpaceChar(c)) {
        len--;
        continue;
      }
      if (!Character.isSpaceChar(c)) {
        result++;
      }
      len--;
    }
    return result;
  }

  /**
   * Given an integer array nums and an integer k, return the k most frequent elements. You may
   * return the answer in any order.
   */
  public int[] topKFrequent(int[] nums, int k) {
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;

    for (int n : nums) {
      if (n > max) {
        max = n;
      }
      if (n < min) {
        min = n;
      }
    }

    int[] freq = new int[max - min + 1];

    for (int n : nums) {
      freq[n - min]++;
    }

    List<Integer>[] freqArr = new ArrayList[nums.length + 1];

    for (int i = 0; i < freq.length; i++) {
      int val = freq[i];
      if (val > 0) {
        if (freqArr[val] == null) {
          freqArr[val] = new ArrayList<>();
        }
        freqArr[val].add(i + min);
      }
    }

    int[] res = new int[k];

    int count = 0;
    for (int i = freqArr.length - 1; i >= 0; i--) {
      List<Integer> list = freqArr[i];
      if (list != null) {
        for (Integer integer : list) {
          res[count] = integer;
          count++;
          if (count >= k) {
            return res;
          }
        }
      }
    }

    return res;
  }

  /**
   * Given an m x n 2D binary grid which represents a map of '1's (land) and '0's (water), return
   * the number of islands.
   *
   * <p>An island is surrounded by water and is formed by connecting adjacent lands horizontally or
   * vertically. You may assume all four edges of the grid are all surrounded by water.
   */
  public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    int numIslands = 0;
    int rows = grid.length, cols = grid[0].length;

    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        if (grid[r][c] == '1') { // Found a new island
          numIslands++;
          dfs(grid, r, c); // Mark the entire island
        }
      }
    }

    return numIslands;
  }

  private void dfs(char[][] grid, int r, int c) {
    int rows = grid.length, cols = grid[0].length;

    // Base case: Out of bounds or water
    if (r < 0 || r >= rows || c < 0 || c >= cols || grid[r][c] == '0') {
      return;
    }

    // Mark the cell as visited
    grid[r][c] = '0';

    // Explore all four directions
    dfs(grid, r + 1, c); // Down
    dfs(grid, r - 1, c); // Up
    dfs(grid, r, c + 1); // Right
    dfs(grid, r, c - 1); // Left
  }

  /**
   * Given two strings text1 and text2, return the length of their longest common subsequence. If
   * there is no common subsequence, return 0.
   *
   * <p>A subsequence of a string is a new string generated from the original string with some
   * characters (can be none) deleted without changing the relative order of the remaining
   * characters.
   *
   * <p>For example, "ace" is a subsequence of "abcde". A common subsequence of two strings is a
   * subsequence that is common to both strings.
   */
  public int longestCommonSubsequence(String text1, String text2) {
    int m = text1.length();
    int n = text2.length();
    int[][] dp = new int[m + 1][n + 1];
    char[] str1 = text1.toCharArray();
    char[] str2 = text2.toCharArray();

    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (str1[i - 1] == str2[j - 1]) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    return dp[m][n];
  }

  /**
   * Given a string s and a string array dictionary, return the longest string in the dictionary
   * that can be formed by deleting some of the given string characters. If there is more than one
   * possible result, return the longest word with the smallest lexicographical order. If there is
   * no possible result, return the empty string.
   */
  public String findLongestWord(String s, List<String> dictionary) {
    String longest = "";
    for (String word : dictionary) {
      if (canForm(s, word)) {
        if (word.length() > longest.length()
            || (word.length() == longest.length() && word.compareTo(longest) < 0)) {
          longest = word;
        }
      }
    }

    return longest;
  }

  private boolean canForm(String s, String word) {
    int pos = -1;

    for (char c : word.toCharArray()) {
      pos = s.indexOf(c, pos + 1);
      if (pos == -1) {
        break;
      }
    }

    return pos != -1;
  }

  /**
   * An additive number is a string whose digits can form an additive sequence.
   *
   * <p>A valid additive sequence should contain at least three numbers. Except for the first two
   * numbers, each subsequent number in the sequence must be the sum of the preceding two.
   *
   * <p>Given a string containing only digits, return true if it is an additive number or false
   * otherwise.
   *
   * <p>Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1,
   * 02, 3 is invalid.
   */
  public boolean isAdditiveNumber(String num) {
    int n = num.length();

    // Try all possible lengths for the first and second numbers
    for (int i = 1; i <= n / 2; i++) {
      for (int j = 1; Math.max(i, j) <= n - i - j; j++) {
        if (isValid(num, i, j)) {
          return true;
        }
      }
    }

    return false;
  }

  private boolean isValid(String num, int i, int j) {
    // Extract the first and second numbers
    String num1 = num.substring(0, i);
    String num2 = num.substring(i, i + j);

    // Leading zeros are invalid unless the number is "0"
    if ((num1.length() > 1 && num1.startsWith("0"))
        || (num2.length() > 1 && num2.startsWith("0"))) {
      return false;
    }

    // Convert to long to handle large numbers
    long a = Long.parseLong(num1);
    long b = Long.parseLong(num2);

    // Start checking the additive sequence
    StringBuilder sequence = new StringBuilder(num1 + num2);
    while (sequence.length() < num.length()) {
      long sum = a + b;
      sequence.append(sum);
      a = b;
      b = sum;
    }

    return sequence.toString().equals(num);
  }

  public boolean isAdditiveNumber2(String num) {
    return sol(num, 0, -1, -1, 0);
  }

  public boolean sol(String s, int idx, int p1, int p2, int c) {
    if (idx == s.length()) {
      return c >= 3;
    }
    int n = 0;
    for (int i = idx; i < s.length(); i++) {
      n = n * 10 + s.charAt(i) - '0';
      if (p1 == -1 || p2 == -1 || p1 + p2 == n) {
        if (sol(s, i + 1, p2, n, c + 1)) {
          return true;
        }
      }
      if (s.charAt(idx) - '0' == 0) {
        break;
      }
    }
    return false;
  }

  /**
   * Given a string s, return the number of palindromic substrings in it.
   *
   * <p>A string is a palindrome when it reads the same backward as forward.
   *
   * <p>A substring is a contiguous sequence of characters within the string.
   */
  public int countSubstrings(String s) {
    if (s == null || s.isEmpty()) {
      return 0;
    }

    for (int i = 0; i < s.length(); i++) { // i is the mid point
      extendPalindrome(s, i, i); // odd length;
      extendPalindrome(s, i, i + 1); // even length
    }

    return count;
  }

  private void extendPalindrome(String s, int left, int right) {
    while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
      count++;
      left--;
      right++;
    }
  }

  /**
   * You are playing the <a href="https://en.wikipedia.org/wiki/Bulls_and_cows">Bulls and Cows
   * game</a> with your friend.
   *
   * <p>You write down a secret number and ask your friend to guess what the number is. When your
   * friend makes a guess, you provide a hint with the following info:
   *
   * <p>The number of "bulls", which are digits in the guess that are in the correct position. The
   * number of "cows", which are digits in the guess that are in your secret number but are located
   * in the wrong position. Specifically, the non-bull digits in the guess that could be rearranged
   * such that they become bulls. Given the secret number secret and your friend's guess, return the
   * hint for your friend's guess.
   *
   * <p>The hint should be formatted as "xAyB", where x is the number of bulls and y is the number
   * of cows. Note that both secret and guess may contain duplicate digits.
   */
  private String getHint(String secret, String guess) {
    int[] ar1 = new int[10];
    int[] ar2 = new int[10];
    int correct = 0;
    for (int i = 0; i < secret.length(); i++) {
      if (secret.charAt(i) == guess.charAt(i)) {
        correct++;
        continue;
      }
      ar1[secret.charAt(i) - '0']++;
      ar2[guess.charAt(i) - '0']++;
    }
    int match = 0;
    for (int i = 0; i <= 9; i++) {
      match += Math.min(ar1[i], ar2[i]);
    }
    return String.valueOf(correct) + 'A' + match + 'B';
  }

  /**
   * Given an array of integers nums containing n + 1 integers where each integer is in the range
   * [1, n] inclusive.
   *
   * <p>There is only one repeated number in nums, return this repeated number.
   *
   * <p>You must solve the problem without modifying the array nums and using only constant extra
   * space.
   */
  public int findDuplicate(int[] nums) {
    int n = nums.length;
    boolean[] bool = new boolean[n];

    for (int num : nums) {
      if (bool[num]) {
        return num;
      }
      bool[num] = true;
    }
    return -1;
  }

  /**
   * You are given a large integer represented as an integer array digits, where each digits[i] is
   * the ith digit of the integer. The digits are ordered from most significant to least significant
   * in left-to-right order. The large integer does not contain any leading 0's.
   *
   * <p>Increment the large integer by one and return the resulting array of digits.
   */
  public int[] plusOne(int[] digits) {
    int[] result = new int[digits.length];
    int n = digits.length;
    int remember = 0;
    for (int i = n - 1; i >= 0; i--) {
      int current = digits[i];

      if (i == n - 1) {
        int total = current + 1;
        if (total > 9) {
          remember = 1;
        } else {
          remember = 0;
        }
        result[i] = total % 10;
        continue;
      }
      int total = current + remember;
      result[i] = total % 10;

      if (total > 9) {
        remember = 1;
      } else {
        remember = 0;
      }
    }

    if (remember == 1) {
      result = Arrays.copyOf(result, result.length + 1);
      result[0] = 1;
    }

    return result;
  }

  /**
   * Given a non-negative integer x, return the square root of x rounded down to the nearest
   * integer. The returned integer should be non-negative as well.
   *
   * <p>You must not use any built-in exponent function or operator.
   *
   * <p>For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.
   */
  public int mySqrt(int x) {
    // For special cases when x is 0 or 1, return x.
    if (x == 0 || x == 1) return x;

    // Initialize the search range for the square root.
    int start = 1;
    int end = x;
    int mid;

    // Perform binary search to find the square root of x.
    while (start <= end) {
      // Calculate the middle point using "start + (end - start) / 2" to avoid integer overflow.
      mid = start + (end - start) / 2;

      // If the square of the middle value is greater than x, move the "end" to the left (mid - 1).
      if ((long) mid * mid > (long) x) end = mid - 1;
      else if (mid * mid == x)
        // If the square of the middle value is equal to x, we found the square root.
        return mid;
      else
        // If the square of the middle value is less than x, move the "start" to the right (mid +
        // 1).
        start = mid + 1;
    }

    // The loop ends when "start" becomes greater than "end", and "end" is the integer value of the
    // square root.
    // However, since we might have been using integer division in the calculations,
    // we round down the value of "end" to the nearest integer to get the correct square root.
    return Math.round(end);
  }

  /**
   * "root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)" It means there
   * are n files (f1.txt, f2.txt ... fn.txt) with content (f1_content, f2_content ... fn_content)
   * respectively in the directory "root/d1/d2/.../dm". Note that n >= 1 and m >= 0. If m = 0, it
   * means the directory is just the root directory.
   *
   * <p>The output is a list of groups of duplicate file paths. For each group, it contains all the
   * file paths of the files that have the same content. A file path is a string that has the
   * following format:
   *
   * <p>"directory_path/file_name.txt"
   */
  public List<List<String>> findDuplicate(String[] paths) {

    List<List<String>> res = new ArrayList<>();
    Map<String, List<String>> map = new HashMap<>();
    for (String path : paths) {
      String[] files = path.split(" ");
      String filePath = files[0] + "/";

      for (int i = 1; i < files.length; i++) {
        int start = files[i].indexOf('(');
        int end = files[i].length() - 1;
        if (start < 0 || files[i].charAt(end) != ')') {
          continue;
        }
        String fileContent = files[i].substring(start + 1, end);
        map.computeIfAbsent(fileContent, x -> new ArrayList<>())
            .add(filePath + files[i].substring(0, start));
      }
    }

    for (List<String> list : map.values()) {
      if (list.size() > 1) {
        res.add(list);
      }
    }
    return res;
  }

  /**
   * A magical string s consists of only '1' and '2' and obeys the following rules:
   *
   * <p>The string s is magical because concatenating the number of contiguous occurrences of
   * characters '1' and '2' generates the string s itself. The first few elements of s is s =
   * "1221121221221121122……". If we group the consecutive 1's and 2's in s, it will be "1 22 11 2 1
   * 22 1 22 11 2 11 22 ......" and the occurrences of 1's or 2's in each group are "1 2 2 1 1 2 1 2
   * 2 1 2 2 ......". You can see that the occurrence sequence is s itself.
   *
   * <p>Given an integer n, return the number of 1's in the first n number in the magical string s.
   *
   * <p><i><b>
   *
   * <p>Let me explain how this Java implementation works:
   *
   * <p>First, we handle the base case where n ≤ 0 by returning 0. We initialize our sequence array
   * with the first three elements [1,2,2]. The array is made n+2 long to safely handle edge cases.
   * We use three pointers:
   *
   * <p>pointer: indicates which number in the sequence we're using to determine how many numbers to
   * add next tail: keeps track of where we're adding new numbers in the sequence We also maintain a
   * running count of 1's to avoid counting at the end
   *
   * <p>The main generation loop:
   *
   * <p>We determine the next number to add (currentNum) by using 3 - sequence[tail-1] We get how
   * many times to add it from sequence[pointer] We add the new numbers while keeping track of 1's
   * We continue until we have generated n numbers
   *
   * <p>The main differences from the Python version are:
   *
   * <p>We use a fixed-size array instead of a dynamic list We count 1's as we go instead of at the
   * end We need to be more careful about array bounds
   */
  public int magicalString(int n) {
    if (n <= 0) {
      return 0;
    }

    // Initialize sequence with first 3 elements
    int[] sequence = new int[n + 2]; // +2 to handle edge cases safely
    sequence[0] = 1;
    if (n > 1) sequence[1] = 2;
    if (n > 2) sequence[2] = 2;

    int pointer = 2; // Points to number we're using to generate next numbers
    int tail = 3; // Points to where we're adding new numbers
    int count = 1; // Count of 1's (starting with the first element)

    // Generate sequence until we have n numbers
    while (tail < n) {
      // Current number we're considering (if last was 1, add 2; if last was 2, add 1)
      int currentNum = 3 - sequence[tail - 1];

      // Add currentNum the number of times specified by sequence[pointer]
      int times = sequence[pointer];

      // Make sure we don't exceed n while adding numbers
      for (int i = 0; i < times && tail < n; i++) {
        sequence[tail] = currentNum;
        if (currentNum == 1) {
          count++;
        }
        tail++;
      }
      pointer++;
    }

    return count;
  }

  /**
   * You are given an array of binary strings strs and two integers m and n.
   *
   * <p>Return the size of the largest subset of strs such that there are at most m 0's and n 1's in
   * the subset.
   *
   * <p>A set x is a subset of a set y if all elements of x are also elements of y.
   */
  public int findMaxForm(String[] S, int M, int N) {
    int[][] dp = new int[M + 1][N + 1];
    for (String str : S) {
      int zeros = 0, ones = 0;
      for (char c : str.toCharArray())
        if (c == '0') {
          zeros++;
        } else {
          ones++;
        }
      for (int i = M; i >= zeros; i--) {
        for (int j = N; j >= ones; j--) {
          dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
        }
      }
    }
    return dp[M][N];
  }

  /**
   * Given a string s containing an out-of-order English representation of digits 0-9, return the
   * digits in ascending order.
   */
  public String originalDigits(String s) {
    int[] count = new int[10]; // To store the count of each digit
    int[] charCount = new int[26]; // To store the count of each character

    // Count the frequency of each character in the input string
    for (char c : s.toCharArray()) {
      charCount[c - 'a']++;
    }

    // Identify digits using unique characters
    count[0] = charCount['z' - 'a']; // "zero"
    count[2] = charCount['w' - 'a']; // "two"
    count[4] = charCount['u' - 'a']; // "four"
    count[6] = charCount['x' - 'a']; // "six"
    count[8] = charCount['g' - 'a']; // "eight"

    // Identify other digits by subtracting counts
    count[3] = charCount['h' - 'a'] - count[8]; // "three"
    count[5] = charCount['f' - 'a'] - count[4]; // "five"
    count[7] = charCount['s' - 'a'] - count[6]; // "seven"
    count[1] = charCount['o' - 'a'] - count[0] - count[2] - count[4]; // "one"
    count[9] = charCount['i' - 'a'] - count[5] - count[6] - count[8]; // "nine"

    // Build the result string
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < 10; i++) {
      while (count[i] > 0) {
        result.append(i);
        count[i]--;
      }
    }

    return result.toString();
  }

  /**
   * The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.
   *
   * <p>For example, "ACGAATTCCG" is a DNA sequence.
   *
   * <p>When studying DNA, it is useful to identify repeated sequences within the DNA.
   *
   * <p>Given a string s that represents a DNA sequence, return all the 10-letter-long sequences
   * (substrings) that occur more than once in a DNA molecule. You may return the answer in any
   * order.
   */
  public List<String> findRepeatedDnaSequences(String s) {
    if (s.length() < 10 || s.length() > 10000) {
      return new ArrayList<>();
    }

    List<String> res = new ArrayList<>();
    HashSet<String> set = new HashSet<>();
    String sequence;
    for (int i = 0; i <= s.length() - 10; i++) {
      sequence = s.substring(i, i + 10);
      if (set.contains(sequence)) {
        if (!res.contains(sequence)) res.add(sequence);
      } else {
        set.add(sequence);
      }
    }
    return res;
  }

  public List<Integer> diffWaysToCompute(String expression) {
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < expression.length(); ++i) {
      char operator = expression.charAt(i);
      if (operator == '+' || operator == '-' || operator == '*') {
        List<Integer> s1 = diffWaysToCompute(expression.substring(0, i));
        List<Integer> s2 = diffWaysToCompute(expression.substring(i + 1));
        for (int a : s1) {
          for (int b : s2) {
            if (operator == '+') res.add(a + b);
            else if (operator == '-') res.add(a - b);
            else res.add(a * b);
          }
        }
      }
    }
    if (res.isEmpty()) {
      res.add(Integer.parseInt(expression));
    }
    return res;
  }

  /**
   * You are given two string arrays words1 and words2.
   *
   * <p>A string b is a subset of string a if every letter in b occurs in a including multiplicity.
   *
   * <p>For example, "wrr" is a subset of "warrior" but is not a subset of "world". A string a from
   * words1 is universal if for every string b in words2, b is a subset of a.
   *
   * <p>Return an array of all the universal strings in words1. You may return the answer in any
   * order.
   */
  public List<String> wordSubsets(String[] words1, String[] words2) {
    int[] maxFreq = new int[26];

    // Step 1: Compute maximum frequency requirements for words2
    for (String b : words2) {
      int[] freq = countFrequency(b);
      for (int i = 0; i < 26; i++) {
        maxFreq[i] = Math.max(maxFreq[i], freq[i]);
      }
    }

    // Step 2: Find universal words in words1
    List<String> result = new ArrayList<>();
    for (String a : words1) {
      int[] freq = countFrequency(a);
      if (isUniversal(freq, maxFreq)) {
        result.add(a);
      }
    }

    return result;
  }

  // Helper method to count character frequencies in a string
  private int[] countFrequency(String word) {
    int[] freq = new int[26];
    for (char c : word.toCharArray()) {
      freq[c - 'a']++;
    }
    return freq;
  }

  // Helper method to check if a string meets the max frequency requirements
  private boolean isUniversal(int[] freq, int[] maxFreq) {
    for (int i = 0; i < 26; i++) {
      if (freq[i] < maxFreq[i]) {
        return false;
      }
    }
    return true;
  }

  /**
   * Given a string s which represents an expression, evaluate this expression and return its value.
   *
   * <p>The integer division should truncate toward zero.
   *
   * <p>You may assume that the given expression is always valid. All intermediate results will be
   * in the range of [-231, 231 - 1].
   *
   * <p>Note: You are not allowed to use any built-in function which evaluates strings as
   * mathematical expressions, such as eval().
   */
  public int calculate(String s) {
    if (s == null || s.trim().isEmpty()) {
      return 0;
    }

    // Get the length of the input string
    int length = s.length();

    if (length == 0) {
      return 0;
    }

    // Initialize variables for processing the expression
    int currentNumber = 0; // To store the number currently being processed
    int lastNumber = 0; // To store the last number processed in the expression
    int result = 0; // To accumulate the result of the calculation
    char currentOperation = '+'; // To store the current operation (+, -, *, /)

    // Loop through each character in the string
    for (int i = 0; i < length; i++) {
      char currentChar = s.charAt(i); // Get the current character from the string

      // Build the current number if the character is a digit
      if (Character.isDigit(currentChar)) {
        currentNumber = currentNumber * 10 + (currentChar - '0');
      }

      // Process the operation when encountering an operator or the end of the string
      if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar)
          || i == length - 1) {
        // Apply the last operation
        if (currentOperation == '+' || currentOperation == '-') {
          result += lastNumber; // Add the last number to the result
          lastNumber =
              (currentOperation == '+') ? currentNumber : -currentNumber; // Set the last number
          // based on the operation
        } else if (currentOperation == '*') {
          lastNumber = lastNumber * currentNumber; // Multiply the last number by the current number
        } else if (currentOperation == '/') {
          lastNumber = lastNumber / currentNumber; // Divide the last number by the current number
        }

        // Update the current operation to the current character
        currentOperation = currentChar;
        // Reset the current number
        currentNumber = 0;
      }
    }

    // Add the last processed number to the result
    result += lastNumber;

    // Return the final result
    return result;
  }

  /**
   * Find all valid combinations of k numbers that sum up to n such that the following conditions
   * are true:
   *
   * <p>Only numbers 1 through 9 are used. Each number is used at most once. Return a list of all
   * possible valid combinations. The list must not contain the same combination twice, and the
   * combinations may be returned in any order.
   */
  public List<List<Integer>> combinationSum3(int k, int n) {
    int min = k * (k + 1) / 2;
    if (n < min) {
      return new ArrayList<>();
    }
    List<List<Integer>> res = new ArrayList<>();
    backtrackCombinationSum3(res, new ArrayList<>(), k, n, 1);
    return res;
  }

  private void backtrackCombinationSum3(
      List<List<Integer>> result, List<Integer> current, int k, int remainingSum, int start) {
    // Base cases
    if (remainingSum == 0 && current.size() == k) {
      result.add(new ArrayList<>(current));
      return;
    }

    if (current.size() >= k || remainingSum <= 0) {
      return;
    }

    // Try each possible number
    for (int i = start; i <= 9; i++) {
      // Skip if adding this number would exceed the target sum
      if (remainingSum - i < 0) {
        break;
      }

      current.add(i);
      backtrackCombinationSum3(result, current, k, remainingSum - i, i + 1);
      current.removeLast();
    }
  }

  /**
   * Given an integer n, return the number of prime numbers that are strictly less than n.
   *
   * <p>This implementation uses the Sieve of Eratosthenes algorithm, which is highly efficient for
   * finding prime numbers.
   */
  public int countPrimes(int n) {
    // Handle edge cases
    if (n <= 2) return 0;

    // Create boolean array to mark non-prime numbers
    boolean[] isComposite = new boolean[n];

    // Initialize count with potential primes (excluding 1)
    int count = n - 2;

    // Start from the first prime number (2)
    for (int i = 2; i * i < n; i++) {
      if (!isComposite[i]) {
        // Mark all multiples of current prime as composite
        for (int j = i * i; j < n; j += i) {
          if (!isComposite[j]) {
            isComposite[j] = true;
            count--;
          }
        }
      }
    }

    return count;
  }

  /**
   * A city's skyline is the outer contour of the silhouette formed by all the buildings in that
   * city when viewed from a distance. Given the locations and heights of all the buildings, return
   * the skyline formed by these buildings collectively.
   *
   * <p>The geometric information of each building is given in the array buildings where
   * buildings[i] = [lefti, righti, heighti]:
   *
   * <ul>
   *   <li>lefti is the x coordinate of the left edge of the ith building.
   *   <li>righti is the x coordinate of the right edge of the ith building.
   *   <li>heighti is the height of the ith building.
   * </ul>
   *
   * You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at
   * height 0.
   *
   * <p>The skyline should be represented as a list of "key points" sorted by their x-coordinate in
   * the form [[x1,y1],[x2,y2],...]. Each key point is the left endpoint of some horizontal segment
   * in the skyline except the last point in the list, which always has a y-coordinate 0 and is used
   * to mark the skyline's termination where the rightmost building ends. Any ground between the
   * leftmost and rightmost buildings should be part of the skyline's contour.
   *
   * <p>Note: There must be no consecutive horizontal lines of equal height in the output skyline.
   * For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable; the three lines of
   * height 5 should be merged into one in the final output as such: [...,[2 3],[4 5],[12 7],...]
   */
  public List<List<Integer>> getSkyline(int[][] buildings) {
    return new AbstractList<>() {

      private List<List<Integer>> resList;

      private void onload() {
        resList = new ArrayList<>();

        List<int[]> heights = new ArrayList<>();
        for (int[] building : buildings) {
          heights.add(new int[] {building[0], -building[2]});
          heights.add(new int[] {building[1], building[2]});
        }
        heights.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        pq.offer(0);

        int prev = 0;
        for (int[] height : heights) {
          if (height[1] < 0) { // left edge
            pq.offer(-height[1]);
          } else { // right edge
            pq.remove(height[1]);
          }
          int cur = pq.peek();
          if (prev != cur) { // if height has changed
            resList.add(Arrays.asList(height[0], cur));
            prev = cur;
          }
        }
      }

      private void init() {
        if (null == resList) {
          onload();
        }
      }

      @Override
      public List<Integer> get(int index) {
        init();
        return resList.get(index);
      }

      @Override
      public int size() {
        init();
        return resList.size();
      }
    };
  }

  /**
   * Given an array nums containing n distinct numbers in the range [0, n], return the only number
   * in the range that is missing from the array.
   */
  public int missingNumber(int[] nums) {
    int len = nums.length;
    int sum = 0;
    for (int num : nums) {
      sum += num;
    }
    return (len * (len + 1)) / 2 - sum;
  }

  /**
   * You are given an integer array nums of length n.
   *
   * <p>Assume arrk to be an array obtained by rotating nums by k positions clock-wise. We define
   * the rotation function F on nums as follow:
   *
   * <p>F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1]. Return the maximum value of
   * F(0), F(1), ..., F(n-1).
   *
   * <p>The test cases are generated so that the answer fits in a 32-bit integer.
   */
  public int maxRotateFunction(int[] nums) {
    int n = nums.length;
    int sum = 0;
    int F = 0;

    // Calculate F(0) and sum of array
    for (int i = 0; i < n; i++) {
      sum += nums[i];
      F += i * nums[i];
    }

    int maxF = F;

    // For each rotation
    for (int k = n - 1; k > 0; k--) {
      // When we rotate right by 1:
      // 1. All elements get 1 less multiply factor except the one moving from back to front
      // 2. We lose (sum - nums[k]) from decreasing factors by 1
      // 3. The element nums[k] goes from having factor k to factor 0
      F = F + sum - n * nums[k];
      maxF = Math.max(maxF, F);
    }

    return maxF;
  }

  /**
   * You are given two integer arrays nums1 and nums2 sorted in non-decreasing order and an integer
   * k.
   *
   * <p>Define a pair (u, v) which consists of one element from the first array and one element from
   * the second array.
   *
   * <p>Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
   */
  public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
    List<List<Integer>> result = new ArrayList<>();
    if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
      return result;
    }

    // Min heap to store pairs sorted by sum
    PriorityQueue<int[]> minHeap =
        new PriorityQueue<>(Comparator.comparingInt(a -> (nums1[a[0]] + nums2[a[1]])));

    // Add initial pairs with first element from nums1
    for (int i = 0; i < nums1.length && i < k; i++) {
      minHeap.offer(new int[] {i, 0});
    }

    // Process k pairs
    while (k > 0 && !minHeap.isEmpty()) {
      int[] pair = minHeap.poll();
      int i = pair[0];
      int j = pair[1];

      result.add(Arrays.asList(nums1[i], nums2[j]));

      // If there are more elements in nums2, add the next pair
      if (j + 1 < nums2.length) {
        minHeap.offer(new int[] {i, j + 1});
      }

      k--;
    }

    return result;
  }

  public List<List<Integer>> kSmallestPairsOptimize(int[] nums1, int[] nums2, int k) {
    return new AbstractList<List<Integer>>() {

      private List<List<Integer>> pairs;

      @Override
      public List<Integer> get(int index) {
        init();
        return pairs.get(index);
      }

      @Override
      public int size() {
        init();
        return pairs.size();
      }

      private void load() {
        int n = nums1.length, m = nums2.length;
        int left = nums1[0] + nums2[0];
        int right = nums1[n - 1] + nums2[m - 1];
        int middle;

        while (left <= right) {

          middle = (left + right) / 2;

          long count = getCount(nums1, nums2, middle, k);

          if (count < k) {
            left = middle + 1;
          } else if (count > k) {
            right = middle - 1;
          } else {
            left = middle;
            break;
          }
        }
        getPairs(nums1, nums2, left, k);
      }

      private long getCount(int[] nums1, int[] nums2, int goal, int k) {
        int prevRight = nums2.length - 1, count = 0;

        for (int i = 0; i < nums1.length && nums1[i] + nums2[0] <= goal; i++) {
          int left = 0, right = prevRight, position = -1;

          while (left <= right) {
            int middle = (right + left) / 2;
            int sum = nums1[i] + nums2[middle];

            if (sum <= goal) {
              position = middle;
              left = middle + 1;
            } else {
              right = middle - 1;
            }
          }
          if (position >= 0) {
            count += position + 1;
            prevRight = position;
          }
          if (count > k) {
            return count;
          }
        }
        return count;
      }

      private void getPairs(int[] nums1, int[] nums2, int sum, int k) {
        pairs = new ArrayList<>();

        for (int value : nums1) {
          for (int j = 0; j < nums2.length && value + nums2[j] < sum; j++) {
            pairs.add(Arrays.asList(value, nums2[j]));
          }
        }

        for (int value : nums1) {
          for (int j = 0; j < nums2.length && value + nums2[j] <= sum && pairs.size() < k; j++) {
            if (value + nums2[j] == sum) {
              pairs.add(Arrays.asList(value, nums2[j]));
            }
          }
        }
      }

      public void init() {
        if (null == pairs) {
          load();
          System.gc();
        }
      }
    };
  }

  public ListNode partition(ListNode head, int x) {
    // Create dummy nodes for both lists
    ListNode smallerHead = new ListNode(0);
    ListNode greaterHead = new ListNode(0);

    // Create pointers for both lists
    ListNode smaller = smallerHead;
    ListNode greater = greaterHead;

    // Traverse original list
    ListNode current = head;
    while (current != null) {
      if (current.val < x) {
        smaller.next = current;
        smaller = smaller.next;
      } else {
        greater.next = current;
        greater = greater.next;
      }
      current = current.next;
    }

    // Connect the two lists
    greater.next = null; // Important to avoid cycles
    smaller.next = greaterHead.next;

    return smallerHead.next;
  }

  public int reverseBits(int n) {
    int result = 0;

    for (int i = 0; i < 32; i++) {
      // Shift result to the left to make room for the next bit
      result <<= 1;
      // Add the least significant bit of n to result
      result |= (n & 1);
      // Shift n to the right to process the next bit
      n >>= 1;
    }

    return result;
  }

  public int countNodes(TreeNode root) {
    if (root == null) {
      return 0;
    }

    // Get height of left and right subtrees
    int leftHeight = getLeftHeight(root);
    int rightHeight = getRightHeight(root);

    // If heights are equal, tree is perfect
    if (leftHeight == rightHeight) {
      // Perfect tree has 2^h - 1 nodes
      return (1 << leftHeight) - 1;
    }

    // Otherwise, recursively count nodes
    return 1 + countNodes(root.left) + countNodes(root.right);
  }

  private int getLeftHeight(TreeNode node) {
    int height = 0;
    while (node != null) {
      height++;
      node = node.left;
    }
    return height;
  }

  private int getRightHeight(TreeNode node) {
    int height = 0;
    while (node != null) {
      height++;
      node = node.right;
    }
    return height;
  }

  /**
   * You are given an array of strings tokens that represents an arithmetic expression in a Reverse
   * Polish Notation.
   *
   * <p>Evaluate the expression. Return an integer that represents the value of the expression.
   *
   * <p>Note that:
   *
   * <ul>
   *   <li>The valid operators are '+', '-', '*', and '/'.
   *   <li>Each operand may be an integer or another expression.
   *   <li>The division between two integers always truncates toward zero.
   *   <li>There will not be any division by zero.
   *   <li>The input represents a valid arithmetic expression in a reverse polish notation.
   *   <li>The answer and all the intermediate calculations can be represented in a 32-bit integer.
   * </ul>
   *
   * Example:
   *
   * <p>Input: tokens = ["4","13","5","/","+"]
   *
   * <p>Output: 6
   *
   * <p>Explanation: (4 + (13 / 5)) = 6
   */
  public int evalRPN(String[] tokens) {
    Stack<Integer> numbers = new Stack<>();
    for (String token : tokens) {
      if (isOperator(token)) {
        int left = numbers.pop();
        int right = numbers.pop();
        int res = performOperation(left, right, token);
        numbers.push(res);
      } else {
        numbers.push(Integer.parseInt(token));
      }
    }
    return numbers.pop();
  }

  private boolean isOperator(String token) {
    return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
  }

  private int performOperation(int left, int right, String operator) {
    return switch (operator) {
      case "+" -> left + right;
      case "-" -> left - right;
      case "*" -> left * right;
      case "/" -> left / right;
      default -> throw new IllegalArgumentException("Invalid operator: " + operator);
    };
  }

  /**
   * Given an integer array nums, return the length of the longest strictly increasing subsequence.
   */
  public int lengthOfLIS(int[] nums) {
    if (nums == null || nums.length == 0) return 0;

    // tails[i] represents the smallest number that ends an
    // increasing subsequence of length i+1
    int[] tails = new int[nums.length];
    int size = 0;
    for (int num : nums) {
      // Binary search to find the position to update
      int left = 0, right = size;
      while (left < right) {
        int mid = left + (right - left) / 2;
        if (tails[mid] < num) {
          left = mid + 1;
        } else {
          right = mid;
        }
      }

      // Update tails array
      tails[left] = num;
      // Increase size if we added to the end
      if (left == size) size++;
    }

    return size;
  }

  public int solution(String S) {
    // Implement your solution here
    int n = S.length();

    // the move up and down alway pass
    // the right and left need to check the nearby position
    int countMoveSucc = 0;
    for (int i = 0; i < n; i++) {
      char move = S.charAt(i);
      boolean ableMove = false;

      switch (move) {
        case '>':
          if (i == n - 1) {
            ableMove = true;
            break;
          }
          ableMove = (i + 1 < n) && noCollide(S, i, i + 1);
          break;
        case '<':
          if (i == 0) {
            ableMove = true;
            break;
          }
          ableMove = noCollide(S, i, i - 1);
          break;
        case '^':
        case 'v':
          ableMove = true;
          break;
      }
      if (ableMove) {
        countMoveSucc++;
      }
    }
    return countMoveSucc;
  }

  private boolean noCollide(String S, int currentPos, int nextPos) {
    char curr = S.charAt(currentPos);
    char next = S.charAt(nextPos);

    if (curr == '>') {
      return false;
    }

    if (curr == '<') {
      return next == '<' || next == '^' || next == 'v';
    }

    return false;
  }

  public List<String> summaryRanges(int[] nums) {
    List<String> res = new ArrayList<>();
    int start = nums[0];
    List<String> temp = new ArrayList<>();
    temp.add(String.valueOf(start));
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == start + 1) {
        temp.add(String.valueOf(start + 1));
      } else {
        pushData(temp, res);
        temp = new ArrayList<>();
        temp.add(String.valueOf(nums[i]));
      }
      start = nums[i];
    }
    if (!temp.isEmpty()) {
      pushData(temp, res);
    }
    return res;
  }

  private void pushData(List<String> temp, List<String> res) {
    String s;
    if (temp.size() > 1) {
      s = temp.get(0) + "->" + temp.get(temp.size() - 1);
    } else {
      s = temp.get(0);
    }
    res.add(s);
  }

  public List<String> summaryRanges2(int[] nums) {
    List<String> sol = new ArrayList<>();
    StringBuilder q = new StringBuilder();
    for (int i = 0; i < nums.length; i++) {
      q.append(nums[i]);
      int j = i;
      while (j + 1 < nums.length && nums[j + 1] == nums[j] + 1) {
        j++;
      }
      if (i == j) {
        sol.add(q.toString());
      } else {
        q.append("->");
        q.append(nums[j]);
        sol.add(q.toString());
      }
      i = j;
      q = new StringBuilder();
    }
    return sol;
  }

  /**
   * Given a pattern and a string s, find if s follows the same pattern.
   *
   * <p>Here follow means a full match, such that there is a bijection between a letter in pattern
   * and a non-empty word in s. Specifically:
   *
   * <ul>
   *   <li>Each letter in pattern maps to exactly one unique word in s.
   *   <li>Each unique word in s maps to exactly one letter in pattern.
   *   <li>No two letters map to the same word, and no two words map to the same letter.
   * </ul>
   */
  public boolean wordPattern(String pattern, String s) {
    String[] words = s.split(" ");
    if (pattern.length() != words.length) {
      return false;
    }

    Map<Character, String> map = new HashMap<>();
    for (int i = 0; i < pattern.length(); i++) {
      if (map.containsKey(pattern.charAt(i))) {
        if (!map.get(pattern.charAt(i)).equals(words[i])) {
          return false;
        }
      } else {
        map.put(pattern.charAt(i), words[i]);
      }
    }

    Set<String> value = new HashSet<>(map.values());
    Set<Character> key = new HashSet<>(map.keySet());
    return key.size() == value.size();
  }

  public boolean wordPattern2(String pattern, String s) {
    Map<String, Character> map = new HashMap<>();
    String[] words = s.split(" ");
    if (pattern.length() != words.length) {
      return false;
    }
    for (int i = 0; i < words.length; i++) {
      if (map.containsKey(words[i]) || map.containsValue(pattern.charAt(i))) {
        if (map.getOrDefault(words[i], '-') != pattern.charAt(i)) {
          return false;
        }
      } else {
        map.put(words[i], pattern.charAt(i));
      }
    }

    return true;
  }

  /** Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M. */
  public int romanToInt(String s) {
    char[] ch = s.toCharArray();
    int count = 0;
    for (int i = 0; i < ch.length - 1; i++) {
      int current = getRomanValue(ch[i]);
      int next = getRomanValue(ch[i + 1]);
      if (current < next) {
        count -= current;
      } else {
        count += current;
      }
    }
    count += getRomanValue(ch[ch.length - 1]);
    return count;
  }

  private int getRomanValue(char c) {
    return switch (c) {
      case 'I' -> 1;
      case 'V' -> 5;
      case 'X' -> 10;
      case 'L' -> 50;
      case 'C' -> 100;
      case 'D' -> 500;
      case 'M' -> 1000;
      default -> 0;
    };
  }

  /**
   * Given two version strings, version1 and version2, compare them. A version string consists of
   * revisions separated by dots '.'. The value of the revision is its integer conversion ignoring
   * leading zeros.
   *
   * <p>To compare version strings, compare their revision values in left-to-right order. If one of
   * the version strings has fewer revisions, treat the missing revision values as 0.
   *
   * <p>Return the following:
   *
   * <ul>
   *   <li>If version1 < version2, return -1.
   *   <li>If version1 > version2, return 1.
   *   <li>Otherwise, return 0.
   * </ul>
   */
  public int compareVersion(String version1, String version2) {
    int i = 0, j = 0;

    while (i < version1.length() || j < version2.length()) {

      int num1 = 0, num2 = 0;
      while (i < version1.length() && version1.charAt(i) != '.') {
        num1 = num1 * 10 + (version1.charAt(i++) - '0');
      }

      while (j < version2.length() && version2.charAt(j) != '.') {
        num2 = num2 * 10 + (version2.charAt(j++) - '0');
      }

      if (num1 < num2) {
        return -1;
      }
      if (num1 > num2) {
        return 1;
      }
      i++;
      j++;
    }
    return 0;
  }

  /**
   * Given an array of integers citations where citations[i] is the number of citations a researcher
   * received for their ith paper, return the researcher's h-index.
   *
   * <p>According to the definition of h-index on Wikipedia: The h-index is defined as the maximum
   * value of h such that the given researcher has published at least h papers that have each been
   * cited at least h times.
   */
  public int hIndex(int[] citations) {
    int n = citations.length;
    int[] count = new int[n + 1];

    // Count papers for each citation number
    for (int citation : citations) {
      if (citation >= n) {
        count[n]++;
      } else {
        count[citation]++;
      }
    }

    // Start from the highest possible h-index
    int papers = 0;
    for (int i = n; i >= 0; i--) {
      papers += count[i];
      if (papers >= i) {
        return i;
      }
    }

    return 0;
  }

  /**
   * Write an efficient algorithm that searches for a value target in an m x n integer matrix
   * matrix. This matrix has the following properties:
   *
   * <p>Integers in each row are sorted in ascending from left to right.
   *
   * <p>Integers in each column are sorted in ascending from top to bottom.
   */
  public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
      return false;
    }
    int row = 0;
    int col = matrix[0].length - 1;

    while (row < matrix.length && col >= 0) {
      if (matrix[row][col] == target) {
        return true;
      } else if (matrix[row][col] < target) {
        row++;
      } else {
        col--;
      }
    }
    return false;
  }

  /**
   * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
   *
   * <p>Boyer-Moore Majority Vote algorithm
   */
  public List<Integer> majorityElements(int[] nums) {
    List<Integer> result = new ArrayList<>();
    if (nums == null || nums.length == 0) {
      return result;
    }

    // At most two numbers can appear more than n/3 times
    // Initialize candidates and their counts
    int candidate1 = 0, candidate2 = 0;
    int count1 = 0, count2 = 0;

    // First pass: Find candidates
    for (int num : nums) {
      if (count1 > 0 && num == candidate1) {
        count1++;
      } else if (count2 > 0 && num == candidate2) {
        count2++;
      } else if (count1 == 0) {
        candidate1 = num;
        count1 = 1;
      } else if (count2 == 0) {
        candidate2 = num;
        count2 = 1;
      } else {
        // If we reach here, we have two candidates
        // and current number is different from both
        count1--;
        count2--;
      }
    }

    // Second pass: Count actual occurrences
    count1 = 0;
    count2 = 0;
    for (int num : nums) {
      if (num == candidate1) {
        count1++;
      } else if (num == candidate2) {
        count2++;
      }
    }

    // Check if candidates appear more than n/3 times
    int n = nums.length;
    if (count1 > n / 3) {
      result.add(candidate1);
    }
    if (count2 > n / 3) {
      result.add(candidate2);
    }

    return result;
  }

  /**
   * Given an integer array nums and an integer k, return the kth largest element in the array.
   *
   * <p>Note that it is the kth largest element in the sorted order, not the kth distinct element.
   *
   * <p>Can you solve it without sorting?
   */
  public int findKthLargest(int[] nums, int k) {
    Arrays.sort(nums);
    return nums[nums.length - k];
  }

  /**
   * Given an integer array nums, return the maximum difference between two successive elements in
   * its sorted form. If the array contains less than two elements, return 0.
   *
   * <p>You must write an algorithm that runs in linear time and uses linear extra space.
   */
  public int maximumGap(int[] nums) {
    int min = nums[0], max = nums[0], len = nums.length;
    for (int x : nums) {
      min = Math.min(min, x);
      max = Math.max(max, x);
    }
    // all equal
    if (min == max) return 0;

    // Array = [3, 9, 24, 25, 29, 37, 45]; Max = 45, Min = 3;
    // Bucket Size = (45-3)/6 = 7
    // Buckets = (3,9), (24,25,29), (37), (45)
    int bucketSize = (int) Math.ceil((double) (max - min) / (len - 1));
    // create buckets & initialize
    int[] minBucket = new int[len];
    int[] maxBucket = new int[len];
    Arrays.fill(minBucket, Integer.MAX_VALUE);
    Arrays.fill(maxBucket, Integer.MIN_VALUE);

    for (int x : nums) {
      int idx = (x - min) / bucketSize;
      minBucket[idx] = Math.min(x, minBucket[idx]);
      maxBucket[idx] = Math.max(x, maxBucket[idx]);
    }

    int maxGap = 0;
    int prev = maxBucket[0];
    for (int i = 1; i < len; i++) {
      if (minBucket[i] == Integer.MAX_VALUE) {
        continue;
      }
      maxGap = Math.max(maxGap, minBucket[i] - prev);
      prev = maxBucket[i];
    }
    return maxGap;
  }

  /**
   * Given two integers representing the numerator and denominator of a fraction, return the
   * fraction in string format.
   *
   * <p>If the fractional part is repeating, enclose the repeating part in parentheses.
   *
   * <p>If multiple answers are possible, return any of them.
   *
   * <p>It is guaranteed that the length of the answer string is less than 104 for all the given
   * inputs.
   */
  public String fractionToDecimal(int numerator, int denominator) {
    // Handle edge cases
    if (numerator == 0) return "0";

    StringBuilder result = new StringBuilder();

    // Handle sign
    if (numerator < 0 ^ denominator < 0) {
      result.append("-");
    }

    // Convert to long to handle MIN_VALUE cases
    long num = Math.abs((long) numerator);
    long den = Math.abs((long) denominator);

    // Handle integer part
    result.append(num / den);
    num %= den;

    // If no decimal part
    if (num == 0) {
      return result.toString();
    }

    // Handle decimal part
    result.append(".");

    // Map to store remainder and its position
    // Key: remainder, Value: position in result
    Map<Long, Integer> remainderMap = new HashMap<>();

    while (num != 0) {
      // If we've seen this remainder before, we found a cycle
      if (remainderMap.containsKey(num)) {
        // Insert opening parenthesis at the start of cycle
        result.insert(remainderMap.get(num), "(");
        result.append(")");
        break;
      }

      // Store current remainder and its position
      remainderMap.put(num, result.length());

      // Multiply by 10 and continue division
      num *= 10;
      result.append(num / den);
      num %= den;
    }

    return result.toString();
  }

  private boolean isRepeat(String num) {

    return false;
  }

  /**
   * Given an integer columnNumber, return its corresponding column title as it appears in an Excel
   * sheet.
   *
   * <p>For example:
   * <li>A -> 1
   * <li>B -> 2
   * <li>C -> 3
   *
   *     <p>...
   * <li>Z -> 26
   * <li>AA -> 27
   * <li>AB -> 28
   *
   *     <p>...
   */
  public String convertToTitle(int columnNumber) {
    StringBuilder result = new StringBuilder();

    while (columnNumber > 0) {
      // Subtract 1 from columnNumber because Excel column titles
      // start from 1 (A) instead of 0
      columnNumber--;

      // Get current character (remainder when divided by 26)
      // Add 'A' to get correct ASCII value
      char currentChar = (char) ('A' + columnNumber % 26);
      result.insert(0, currentChar);

      // Move to next digit
      columnNumber /= 26;
    }

    return result.toString();
  }

  /**
   * Given a string s, partition s such that every substring of the partition is a palindrome .
   *
   * <p>Return the minimum cuts needed for a palindrome partitioning of s.
   */
  public int minCut(String s) {
    if (s == null || s.length() <= 1) {
      return 0;
    }

    int n = s.length();
    int[] cut = new int[n];

    for (int i = 0; i < n; i++) {
      cut[i] = i; // Initialize with worst case
    }

    for (int center = 0; center < n; center++) {
      // Odd length palindromes
      expandAroundCenter(s, center, center, cut);
      // Even length palindromes
      expandAroundCenter(s, center, center + 1, cut);
    }

    return cut[n - 1];
  }

  private void expandAroundCenter(String s, int left, int right, int[] cut) {
    while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
      // If substring(left,right) is palindrome
      // Update minimum cuts needed at position right
      cut[right] = left == 0 ? 0 : Math.min(cut[right], cut[left - 1] + 1);
      left--;
      right++;
    }
  }

  /**
   * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
   * (you may want to display this pattern in a fixed font for better legibility)
   *
   * <p>P A H N
   *
   * <p>A P L S I I G
   *
   * <p>Y I R
   *
   * <p>And then read line by line: "PAHNAPLSIIGYIR"
   *
   * <p>Write the code that will take a string and make this conversion given a number of rows:
   *
   * <p>string convert(string s, int numRows);
   */
  public String convert(String s, int numRows) {
    if (numRows == 1) {
      return s;
    }

    StringBuilder result = new StringBuilder();
    int n = s.length();
    int cycleLen = 2 * numRows - 2;

    // Go through each row
    for (int i = 0; i < numRows; i++) {
      // Go through each cycle for current row
      for (int j = 0; j + i < n; j += cycleLen) {
        result.append(s.charAt(j + i));

        // Add middle character for non-first and non-last rows
        if (i != 0 && i != numRows - 1 && j + cycleLen - i < n) {
          result.append(s.charAt(j + cycleLen - i));
        }
      }
    }

    return result.toString();
  }

  public List<List<String>> partition(String s) {
    List<List<String>> result = new ArrayList<>();
    backtrack(s, 0, new ArrayList<>(), result);
    return result;
  }

  private void backtrack(
      String s, int start, List<String> currentPartition, List<List<String>> result) {
    // If we reach the end of the string, add the current partition to the result
    if (start == s.length()) {
      result.add(new ArrayList<>(currentPartition));
      return;
    }

    // Try all possible end indices for substrings starting from `start`
    for (int end = start; end < s.length(); end++) {
      // Check if the substring from `start` to `end` is a palindrome
      if (isPalindrome(s, start, end)) {
        // If it is, add it to the current partition
        currentPartition.add(s.substring(start, end + 1));
        // Recursively partition the remaining substring
        backtrack(s, end + 1, currentPartition, result);
        // Backtrack: remove the last added substring for the next iteration
        currentPartition.remove(currentPartition.size() - 1);
      }
    }
  }

  // Helper method to check if a substring is a palindrome
  private boolean isPalindrome(String s, int left, int right) {
    while (left < right) {
      if (s.charAt(left) != s.charAt(right)) {
        return false;
      }
      left++;
      right--;
    }
    return true;
  }

  public String largestNumber(int[] nums) {
    String[] numStrs = new String[nums.length];
    for (int i = 0; i < nums.length; i++) {
      numStrs[i] = String.valueOf(nums[i]);
    }

    Arrays.sort(numStrs, (a, b) -> (b + a).compareTo(a + b));

    // Edge case: if the largest number is 0, return "0"
    if (numStrs[0].equals("0")) {
      return "0";
    }

    StringBuilder result = new StringBuilder();
    for (String numStr : numStrs) {
      result.append(numStr);
    }

    return result.toString();
  }

  /**
   * There are n children standing in a line. Each child is assigned a rating value given in the
   * integer array ratings. You are giving candies to these children subjected to the following
   * requirements:
   *
   * <ul>
   *   <li>Each child must have at least one candy.
   *   <li>Children with a higher rating get more candies than their neighbors.
   * </ul>
   *
   * Return the minimum number of candies you need to have to distribute the candies to the
   * children.
   */
  public int candy(int[] ratings) {
    int n = ratings.length;
    int[] candies = new int[n];
    Arrays.fill(candies, 1);

    for (int i = 1; i < n; i++) {
      if (ratings[i] > ratings[i - 1]) {
        candies[i] = candies[i - 1] + 1;
      }
    }

    for (int i = n - 2; i >= 0; i--) {
      if (ratings[i] > ratings[i + 1]) {
        candies[i] = Math.max(candies[i], candies[i + 1] + 1);
      }
    }

    int totalCandies = 0;
    for (int candy : candies) {
      totalCandies += candy;
    }

    return totalCandies;
  }

  /**
   * Given two strings s and t, return the number of distinct subsequences of s which equals t.
   *
   * <p>The test cases are generated so that the answer fits on a 32-bit signed integer.
   */
  public int numDistinct(String source, String target) {
    int sourceLength = source.length();
    int targetLength = target.length();

    // DP array to store the number of ways to form the first 'j' characters of target from source
    int[] dp = new int[targetLength + 1];

    // There's exactly one way to form an empty target string (by doing nothing)
    dp[0] = 1;

    // Convert strings to character arrays for easier access
    char[] sourceArray = source.toCharArray();
    char[] targetArray = target.toCharArray();

    // Variable to track how far we've matched in the target string
    int matchIndex = 0;

    for (int i = 0; i < sourceLength; i++) {
      // Update matchIndex if the current character in source matches the current character in
      // target
      if (matchIndex < targetLength - 1 && sourceArray[i] == targetArray[matchIndex]) {
        matchIndex++;
      }

      // Calculate the lower bound for the inner loop to avoid out-of-bounds issues
      int lowerBound = Math.max(0, i - sourceLength + targetLength);

      // Iterate backward to avoid overwriting the DP array prematurely
      for (int j = matchIndex; j >= lowerBound; j--) {
        // If the characters match, update the DP array
        if (sourceArray[i] == targetArray[j]) {
          dp[j + 1] += dp[j];
        }
      }
    }

    // The result is the number of ways to form the entire target string from source
    return dp[targetLength];
  }

  /**
   * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
   *
   * <p>An interleaving of two strings s and t is a configuration where s and t are divided into n
   * and m substrings respectively, such that:
   *
   * <ul>
   *   <li>s = s1 + s2 + ... + sn
   *   <li>t = t1 + t2 + ... + tm
   *   <li>|n - m| <= 1
   *   <li>The interleaving is s1 + t1 + s2 * + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 +
   *       ...
   * </ul>
   */
  public boolean isInterleave(String s1, String s2, String s3) {
    int m = s1.length();
    int n = s2.length();
    int l = s3.length();

    // If the lengths don't add up, it's impossible to interleave
    if (m + n != l) {
      return false;
    }

    // Initialize memoization table
    Boolean[][] memo = new Boolean[m + 1][n + 1];

    // Start recursion
    return isInterleave(s1, s2, s3, 0, 0, 0, memo);
  }

  private boolean isInterleave(
      String s1, String s2, String s3, int i, int j, int k, Boolean[][] memo) {
    // If we have already solved this sub-problem, return the stored result
    if (memo[i][j] != null) {
      return memo[i][j];
    }

    // Base case: If all indices have reached the end, return true
    if (k == s3.length()) {
      return true;
    }

    // Check if we can take a character from s1
    if (i < s1.length() && s1.charAt(i) == s3.charAt(k)) {
      if (isInterleave(s1, s2, s3, i + 1, j, k + 1, memo)) {
        memo[i][j] = true;
        return true;
      }
    }

    // Check if we can take a character from s2
    if (j < s2.length() && s2.charAt(j) == s3.charAt(k)) {
      if (isInterleave(s1, s2, s3, i, j + 1, k + 1, memo)) {
        memo[i][j] = true;
        return true;
      }
    }

    // If neither option works, store false and return
    memo[i][j] = false;
    return false;
  }

  public int strStr(String haystack, String needle) {
    if (haystack.length() < needle.length()) {
      return -1;
    }

    if (haystack.equals(needle)) {
      return 0;
    }

    for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
      String tmp = haystack.substring(i, i + needle.length());
      if (tmp.equals(needle)) {
        return i;
      }
    }

    return -1;
  }

  /**
   * Given an integer array nums where every element appears three times except for one, which
   * appears exactly once. Find the single element and return it.
   *
   * <p>You must implement a solution with a linear runtime complexity and use only constant extra
   * space.
   */
  public int singleNumberWithTriple(int[] nums) {
    int ones = 0;
    int twos = 0;

    for (final int num : nums) {
      ones ^= (num & ~twos);
      twos ^= (num & ~ones);
    }

    return ones;
  }

  /**
   * Given an unsorted array of integers nums, return the length of the longest consecutive elements
   * sequence.
   *
   * <p>You must write an algorithm that runs in O(n) time.
   */
  public int longestConsecutive(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }

    int res = 0;
    int count = 0;
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] <= -999999999) {
        nums[i] = Integer.MIN_VALUE;
        continue;
      }
      if (nums[i] < min) {
        min = nums[i];
      }
      if (nums[i] >= 999999999) {
        nums[i] = Integer.MAX_VALUE;
        continue;
      }
      if (nums[i] > max) {
        max = nums[i];
      }
    }

    int index = -min;

    int[] arr = new int[max + 1 + index];
    for (int i : nums) {
      if (i == Integer.MIN_VALUE || i == Integer.MAX_VALUE) {
        continue;
      }
      arr[i + index] = 1;
    }

    for (int i = min + index; i < max + 1 + index; i++) {
      if (arr[i] == 1) {
        count++;
        res = Math.max(res, count);
      } else {
        count = 0;
      }
    }

    return res;
  }

  /**
   * You are given an integer array nums and an integer target.
   *
   * <p>You want to build an expression out of nums by adding one of the symbols '+' and '-' before
   * each integer in nums and then concatenate all the integers.
   *
   * <p>For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate
   * them to build the expression "+2-1".
   *
   * <p>Return the number of different expressions that you can build, which evaluates to target.
   */
  public int findTargetSumWays(int[] nums, int target) {
    int total = 0;

    for (int n : nums) {
      total += n;
    }

    target = Math.abs(target);

    if ((target + total) % 2 == 1) {
      return 0;
    }

    int s1 = (target + total) / 2;
    int[] dp = new int[s1 + 1];
    dp[0] = 1;
    find(nums, 0, s1, dp);
    return dp[s1];
  }

  private void find(int[] nums, int index, int target, int[] dp) {

    if (index == nums.length) {
      return;
    }

    for (int n = target; n >= nums[index]; n--) {
      dp[n] = dp[n] + dp[n - nums[index]];
    }

    find(nums, index + 1, target, dp);
  }

  /**
   * Given an integer n, return all the structurally unique BST's (binary search trees), which has
   * exactly n nodes of unique values from 1 to n. Return the answer in any order.
   */
  public List<TreeNode> generateTrees(int n) {
    if (n == 0) {
      return new ArrayList<>();
    }

    return generateTrees(1, n);
  }

  private List<TreeNode> generateTrees(int start, int end) {
    List<TreeNode> result = new ArrayList<>();
    if (start > end) {
      result.add(null); // base case: empty tree
      return result;
    }

    for (int i = start; i <= end; i++) {
      List<TreeNode> leftSubtrees = generateTrees(start, i - 1);
      List<TreeNode> rightSubtrees = generateTrees(i + 1, end);
      for (TreeNode left : leftSubtrees) {
        for (TreeNode right : rightSubtrees) {
          TreeNode root = new TreeNode(i);
          root.left = left;
          root.right = right;
          result.add(root);
        }
      }
    }
    return result;
  }

  /**
   * An n-bit gray code sequence is a sequence of 2n integers where:
   *
   * <ul>
   *   <li>Every integer is in the inclusive range [0, 2n - 1],
   *   <li>The first integer is 0,
   *   <li>An integer appears no more than once in the sequence,
   *   <li>The binary representation of every pair of adjacent integers differs by exactly one bit,
   *       and
   *   <li>The binary representation of the first and last integers differs by exactly one bit.
   * </ul>
   *
   * Given an integer n, return any valid n-bit gray code sequence.
   */
  public List<Integer> grayCode(int n) {
    int ansSize = (1 << n);

    Integer[] ansArr = new Integer[ansSize];

    for (int i = 0; i < ansSize; i++) {
      ansArr[i] = (i ^ (i >> 1));
    }

    return Arrays.asList(ansArr);
  }

  /**
   * A valid IP address consists of exactly four integers separated by single dots. Each integer is
   * between 0 and 255 (inclusive) and cannot have leading zeros.
   *
   * <p>For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245",
   * "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
   *
   * <p>Given a string s containing only digits, return all possible valid IP addresses that can be
   * formed by inserting dots into s. You are not allowed to reorder or remove any digits in s. You
   * may return the valid IP addresses in any order.
   */
  public List<String> restoreIpAddresses(String s) {
    List<String> result = new ArrayList<>();
    if (s == null || s.length() < 4 || s.length() > 12) {
      return result;
    }
    restoreIpAddresses(s, 0, new ArrayList<>(), result);
    return result;
  }

  private void restoreIpAddresses(String s, int start, List<String> current, List<String> result) {
    // Base case: If we have 4 segments and we've used all characters
    if (current.size() == 4) {
      if (start == s.length()) {
        result.add(String.join(".", current));
      }
      return;
    }

    // Try all possible segments (1 to 3 digits)
    for (int i = 1; i <= 3; i++) {
      if (start + i > s.length()) {
        break; // Out of bounds
      }
      String segment = s.substring(start, start + i);

      // Validate segment
      if (isValid(segment)) {
        current.add(segment);
        restoreIpAddresses(s, start + i, current, result);
        current.remove(current.size() - 1); // Backtrack
      }
    }
  }

  private boolean isValid(String segment) {
    // Check segment length and value range
    if (segment.length() > 1 && segment.startsWith("0")) {
      return false; // Leading zeros not allowed
    }
    int value = Integer.parseInt(segment);
    return value >= 0 && value <= 255;
  }

  /**
   * Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where
   * the sum of the node values in the path equals targetSum. Each path should be returned as a list
   * of the node values, not node references.
   *
   * <p>A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is
   * a node with no children.
   */
  public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) {
      return res;
    }
    pathSum(root, targetSum, new ArrayList<>(), res);
    return res;
  }

  private void pathSum(TreeNode node, int target, List<Integer> paths, List<List<Integer>> res) {
    if (node == null) {
      return;
    }

    paths.add(node.val);

    if (node.left == null && node.right == null && node.val == target) {
      res.add(new ArrayList<>(paths)); // Add a copy of the current path to the result
    }

    pathSum(node.left, target - node.val, paths, res);
    pathSum(node.right, target - node.val, paths, res);

    paths.remove(paths.size() - 1);
  }

  /**
   * Given the root of a binary tree, return all root-to-leaf paths in any order.
   *
   * <p>A leaf is a node with no children.
   *
   * <p>Input: root = [1,2,3,null,5]
   *
   * <p>Output: ["1->2->5","1->3"]
   */
  public List<String> binaryTreePaths(TreeNode root) {
    List<String> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    StringBuilder path = new StringBuilder();
    binaryTreePaths(root, path, result);
    return result;
  }

  private void binaryTreePaths(TreeNode node, StringBuilder path, List<String> result) {
    // Base case: if the node is null, return
    if (node == null) {
      return;
    }

    // Append the current node's value to the path
    int length = path.length();
    if (length > 0) {
      path.append("->");
    }
    path.append(node.val);

    // If it's a leaf node, add the path to the result list
    if (node.left == null && node.right == null) {
      result.add(path.toString());
    } else {
      // If it's not a leaf, recurse on the children
      if (node.left != null) {
        binaryTreePaths(node.left, path, result);
      }
      if (node.right != null) {
        binaryTreePaths(node.right, path, result);
      }
    }

    // Backtrack: remove the current node's value to explore other paths
    path.setLength(length);
  }

  /**
   * Given an integer array nums that may contain duplicates, return all possible subsets (the power
   * set).
   *
   * <p>The solution set must not contain duplicate subsets. Return the solution in any order.
   */
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(nums); // Sort the array to handle duplicates
    subsetsWithDup(nums, 0, new ArrayList<>(), result);
    return result;
  }

  private void subsetsWithDup(
      int[] nums, int start, List<Integer> tempList, List<List<Integer>> result) {
    result.add(new ArrayList<>(tempList)); // Add current subset to the result
    for (int i = start; i < nums.length; i++) {
      // Skip duplicates
      if (i > start && nums[i] == nums[i - 1]) {
        continue;
      }
      tempList.add(nums[i]);
      subsetsWithDup(nums, i + 1, tempList, result);
      tempList.remove(tempList.size() - 1); // Backtrack
    }
  }

  /**
   * Given an absolute path for a Unix-style file system, which begins with a slash '/', transform
   * this path into its simplified canonical path.
   *
   * <p>In Unix-style file system context, a single period '.' signifies the current directory, a
   * double period ".." denotes moving up one directory level, and multiple slashes such as "//" are
   * interpreted as a single slash. In this problem, treat sequences of periods not covered by the
   * previous rules (like "...") as valid names for files or directories.
   *
   * <p>The simplified canonical path should adhere to the following rules:
   *
   * <ul>
   *   <li>It must start with a single slash '/'.
   *   <li>Directories within the path should be separated by only one slash '/'.
   *   <li>It should not end with a slash '/', unless it's the root directory.
   *   <li>It should exclude any single or double periods used to denote current or parent
   *       directories.
   * </ul>
   *
   * Return the new path.
   */
  public String simplifyPath(String path) {
    String[] arr = path.split("/");
    Deque<String> stacker = new LinkedList<>();
    for (String s : arr) {
      if (!s.trim().isEmpty()) {
        stacker.push(s);
      }
    }
    StringBuilder str = new StringBuilder();
    int count = 0;

    if (!stacker.isEmpty()) {
      do {
        String next = stacker.pop();
        if (next.equals("..")) {
          count++;
          continue;
        }
        if (next.equals(".")) {
          continue;
        }
        if (count == 0) {
          str.insert(0, "/" + next);
        } else {
          count--;
        }
      } while (!stacker.isEmpty());
    }
    return str.toString().isEmpty() ? "/" : str.toString();
  }

  /** Implement pow(x, n), which calculates x raised to the power n (i.e., xn). */
  public double myPow(double x, int n) {
    if (n == 0) {
      return 1;
    }

    double half = myPow(x, n / 2);

    if (n % 2 == 0) {
      return half * half;
    } else {
      if (n > 0) {
        return half * half * x;
      } else {
        return half * half / x;
      }
    }
  }

  /**
   * Write a function to find the longest common prefix string amongst an array of strings.
   *
   * <p>If there is no common prefix, return an empty string "".
   */
  public String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0) {
      return "";
    }

    // Start with the first string as the prefix
    String prefix = strs[0];

    // Iterate over the rest of the strings
    for (int i = 1; i < strs.length; i++) {
      // Update the prefix by comparing it with the current string
      while (strs[i].indexOf(prefix) != 0) {
        // Shorten the prefix by one character
        prefix = prefix.substring(0, prefix.length() - 1);

        // If the prefix becomes empty, return an empty string
        if (prefix.isEmpty()) {
          return "";
        }
      }
    }

    return prefix;
  }

  /**
   * There is an integer array nums sorted in non-decreasing order (not necessarily with distinct
   * values).
   *
   * <p>Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k <
   * nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0],
   * nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at
   * pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].
   *
   * <p>Given the array nums after the rotation and an integer target, return true if target is in
   * nums, or false if it is not in nums.
   *
   * <p>You must decrease the overall operation steps as much as possible.
   */
  public boolean search(int[] nums, int k) {
    int low = 0;
    int high = nums.length - 1;

    while (low <= high) {

      int mid = low + (high - low) / 2;

      if (nums[mid] == k) {
        return true;
      }

      if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
        low++;
        high--;
      }

      if (nums[low] <= nums[mid]) {
        if (k >= nums[low] && k <= nums[mid]) {
          high = mid - 1;
        } else {
          low = mid + 1;
        }
      } else {
        if (k >= nums[mid] && k <= nums[high]) {
          low = mid + 1;
        } else {
          high = mid - 1;
        }
      }
    }
    return false;
  }

  /** Given an m x n matrix, return all elements of the matrix in spiral order. */
  public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> res = new ArrayList<>();
    int top = 0;
    int left = 0;

    int bottom = matrix[0].length - 1;
    int right = matrix.length - 1;

    while (right >= left && top <= bottom) {
      for (int i = left; i <= right; i++) {
        res.add(matrix[top][i]);
      }
      top++;
      for (int i = top; i <= bottom; i++) {
        res.add(matrix[i][right]);
      }
      right--;

      if (top <= bottom) {
        for (int i = right; i >= left; i--) {
          res.add(matrix[bottom][i]);
        }
        bottom--;
      }

      if (left <= right) {
        for (int i = bottom; i >= top; i--) {
          res.add(matrix[i][left]);
        }
        left++;
      }
    }
    return res;
  }

  /**
   * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in
   * spiral order.
   */
  public int[][] generateMatrix(int n) {
    int[][] matrix = new int[n][n];
    int top = 0;
    int bottom = n - 1;

    int left = 0;
    int right = n - 1;

    int temp = 1;

    while (right >= left && top <= bottom) {
      for (int i = left; i <= right; i++) {
        matrix[top][i] = temp++;
      }
      top++;
      for (int i = top; i <= bottom; i++) {
        matrix[i][right] = temp++;
      }
      right--;

      if (top <= bottom) {
        for (int i = right; i >= left; i--) {
          matrix[bottom][i] = temp++;
        }
        bottom--;
      }

      if (left <= right) {
        for (int i = bottom; i >= top; i--) {
          matrix[i][left] = temp++;
        }
        left++;
      }
    }
    return matrix;
  }

  /**
   * Given n pairs of parentheses, write a function to generate all combinations of well-formed
   * parentheses.
   */
  public List<String> generateParenthesis(int n) {
    List<String> res = new ArrayList<>();
    generateParenthesis(res, new StringBuilder(), 0, 0, n);
    return res;
  }

  private void generateParenthesis(
      List<String> result, StringBuilder current, int open, int close, int max) {
    if (current.length() == max * 2) {
      result.add(current.toString());
      return;
    }

    if (open < max) {
      generateParenthesis(result, current.append("("), open + 1, close, max);
      current.deleteCharAt(current.length() - 1);
    }
    if (close < open) {
      generateParenthesis(result, current.append(")"), open, close + 1, max);
      current.deleteCharAt(current.length() - 1);
    }
  }

  /**
   * The count-and-say sequence is a sequence of digit strings defined by the recursive formula:
   *
   * <ul>
   *   <li>countAndSay(1) = "1"
   *   <li>countAndSay(n) is the run-length encoding of countAndSay(n - 1).
   * </ul>
   *
   * Run-length encoding (RLE)is a string compression method that works by replacing consecutive
   * identical characters (repeated 2 or more times) with the concatenation of the character and the
   * number marking the count of the characters (length of the run). For example, to compress the
   * string "3322251" we replace "33" with "23", replace "222" with "32", replace "5" with "15" and
   * replace "1" with "11". Thus the compressed string becomes "23321511".
   *
   * <p>Given a positive integer n, return the nth element of the count-and-say sequence.
   */
  public String countAndSay(int n) {
    if (n <= 0) {
      return "";
    }

    String result = "1";
    for (int i = 1; i < n; i++) {
      result = getNext(result);
    }

    return result;
  }

  private String getNext(String s) {
    StringBuilder next = new StringBuilder();

    for (int i = 0; i < s.length(); i++) {
      int count = 1;
      while (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
        i++;
        count++;
      }
      next.append(count).append(s.charAt(i));
    }

    return next.toString();
  }

  /**
   * A permutation of an array of integers is an arrangement of its members into a sequence or
   * linear order.
   *
   * <ul>
   *   <li>For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], *
   *       [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
   * </ul>
   *
   * The next permutation of an array of integers is the next lexicographically greater permutation
   * of its integer. More formally, if all the permutations of the array are sorted in one container
   * according to their lexicographical order, then the next permutation of that array is the
   * permutation that follows it in the sorted container. If such arrangement is not possible, the
   * array must be rearranged as the lowest possible order (i.e., sorted in ascending order).
   *
   * <ul>
   *   <li>For example, the next permutation of arr = [1,2,3] is [1,3,2].
   *   <li>Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
   *   <li>While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a
   *       lexicographical larger rearrangement.
   * </ul>
   *
   * Given an array of integers nums, find the next permutation of nums.
   */
  public void nextPermutation(int[] nums) {
    int n = nums.length;
    int k = -1;

    // Step 1: Find the first decreasing element from the end
    for (int i = n - 2; i >= 0; i--) {
      if (nums[i] < nums[i + 1]) {
        k = i;
        break;
      }
    }

    if (k == -1) {
      // If no such element is found, reverse the array
      reverse(nums, 0, n - 1);
      return;
    }

    // Step 2: Find the next larger element to swap with
    int l = -1;
    for (int i = n - 1; i > k; i--) {
      if (nums[i] > nums[k]) {
        l = i;
        break;
      }
    }

    // Step 3: Swap the elements
    swap(nums, k, l);

    // Step 4: Reverse the sequence from k+1 to the end
    reverse(nums, k + 1, n - 1);
  }

  private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  private void reverse(int[] nums, int start, int end) {
    while (start < end) {
      swap(nums, start, end);
      start++;
      end--;
    }
  }

  /**
   * Given a string containing digits from 2-9 inclusive, return all possible letter combinations
   * that the number could represent. Return the answer in any order.
   *
   * <p>A mapping of digits to letters (just like on the telephone buttons) is given below. Note
   * that 1 does not map to any letters.
   */
  public List<String> letterCombinations(String digits) {
    List<String> res = new ArrayList<>();

    if (digits == null || digits.isEmpty()) {
      return res;
    }

    Map<Character, String> digitToLetters =
        Map.of(
            '2', "abc", '3', "def", '4', "ghi", '5', "jkl", '6', "mno", '7', "pqrs", '8', "tuv",
            '9', "wxyz");

    letterCombinations(digits, 0, new StringBuilder(), res, digitToLetters);

    return res;
  }

  private void letterCombinations(
      String digits,
      int index,
      StringBuilder stringBuilder,
      List<String> res,
      Map<Character, String> digitToLetters) {
    if (index == digits.length()) {
      res.add(stringBuilder.toString());
      return;
    }

    String letter = digitToLetters.get(digits.charAt(index));
    for (char c : letter.toCharArray()) {
      stringBuilder.append(c);
      letterCombinations(digits, index + 1, stringBuilder, res, digitToLetters);
      stringBuilder.deleteCharAt(stringBuilder.length() - 1);
    }
  }

  /**
   * Given a collection of numbers, nums, that might contain duplicates, return all possible unique
   * permutations in any order.
   */
  public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    permuteUnique(nums, new ArrayList<>(), res, new boolean[nums.length]);
    return res;
  }

  private void permuteUnique(
      int[] nums, List<Integer> tempList, List<List<Integer>> result, boolean[] used) {
    if (tempList.size() == nums.length) {
      result.add(new ArrayList<>(tempList));
      return;
    }
    for (int i = 0; i < nums.length; i++) {
      if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
        continue;
      }
      used[i] = true;
      tempList.add(nums[i]);
      permuteUnique(nums, tempList, result, used);
      used[i] = false;
      tempList.remove(tempList.size() - 1);
    }
  }

  /**
   * Given an array nums of distinct integers, return all the possible permutations. You can return
   * the answer in any order.
   */
  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    permute(nums, new ArrayList<>(), res);
    return res;
  }

  private void permute(int[] nums, List<Integer> temp, List<List<Integer>> res) {
    if (temp.size() == nums.length) {
      res.add(new ArrayList<>(temp));
      return;
    }
    for (int num : nums) {
      if (temp.contains(num)) {
        continue;
      }
      temp.add(num);
      permute(nums, temp, res);
      temp.remove(temp.size() - 1);
    }
  }

  /**
   * Given an array of strings strs, group the anagrams together. You can return the answer in any
   * order.
   *
   * <p>An Anagram is a word or phrase formed by rearranging the letters of a different word or
   * phrase, typically using all the original letters exactly once.
   */
  public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();
    for (String str : strs) {
      char[] chars = str.toCharArray();
      Arrays.sort(chars);
      if (!map.containsKey(String.valueOf(chars))) {
        List<String> list = new ArrayList<>();
        list.add(str);
        map.put(String.valueOf(chars), list);
      } else {
        map.get(String.valueOf(chars)).add(str);
      }
    }
    return new ArrayList<>(map.values());
  }

  /**
   * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping
   * intervals, and return an array of the non-overlapping intervals that cover all the intervals in
   * the input.
   */
  public int[][] merge(int[][] intervals) {
    if (intervals == null || intervals.length == 0) {
      return new int[0][0];
    }
    if (intervals.length == 1) {
      return intervals;
    }

    Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

    List<int[]> merged = new ArrayList<>();
    int[] currentInterval = intervals[0];
    merged.add(currentInterval);

    for (int[] interval : intervals) {
      int currentEnd = currentInterval[1];
      int nextStart = interval[0];
      int nextEnd = interval[1];

      if (currentEnd >= nextStart) {
        // Overlapping intervals, merge them.
        currentInterval[1] = Math.max(currentEnd, nextEnd);
      } else {
        // No overlap, add the new interval to the list and update the current interval.
        currentInterval = interval;
        merged.add(currentInterval);
      }
    }

    return merged.toArray(new int[merged.size()][]);
  }

  /**
   * Given an array nums with n objects colored red, white, or blue, sort them in-place so that
   * objects of the same color are adjacent, with the colors in the order red, white, and blue.
   *
   * <p>We will use the integers 0, 1, and 2 to represent the color red, white, and blue,
   * respectively.
   *
   * <p>You must solve this problem without using the library's sort function.
   */
  public void sortColors(int[] nums) {
    int n = nums.length;
    int countRed = 0; // 0
    int countWhite = 0; // 1
    for (int num : nums) {
      if (num == 0) {
        countRed++;
      }
      if (num == 1) {
        countWhite++;
      }
    }
    for (int i = 0; i < countRed; i++) {
      nums[i] = 0;
    }
    for (int i = countRed; i < countWhite + countRed; i++) {
      nums[i] = 1;
    }
    for (int i = countRed + countWhite; i < n; i++) {
      nums[i] = 2;
    }
  }

  /**
   * Given an integer array nums of unique elements, return all possible subsets (the power set).
   *
   * <p>The solution set must not contain duplicate subsets. Return the solution in any order.
   */
  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    subsets(nums, 0, new ArrayList<>(), res);
    return res;
  }

  private void subsets(int[] nums, int start, List<Integer> temp, List<List<Integer>> res) {
    res.add(new ArrayList<>(temp));
    for (int i = start; i < nums.length; i++) {
      temp.add(nums[i]);
      subsets(nums, i + 1, temp, res);
      temp.remove(temp.size() - 1);
    }
  }

  public String intToRoman(int num) {
    String[] one = new String[] {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    String[] ten = new String[] {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    String[] hund = new String[] {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    String[] thousand = new String[] {"", "M", "MM", "MMM"};
    return thousand[num / 1000] + hund[(num % 1000) / 100] + ten[(num % 100) / 10] + one[num % 10];
  }

  public String intToRoman2(int num) {
    StringBuilder str = new StringBuilder();

    process(str, 'M', '*', '*', num / 1000);
    num %= 1000;

    process(str, 'C', 'D', 'M', num / 100);
    num %= 100;

    process(str, 'X', 'L', 'C', num / 10);
    num %= 10;

    process(str, 'I', 'V', 'X', num);

    return str.toString();
  }

  public void process(StringBuilder str, char minor, char middle, char major, int value) {
    if (value <= 3) {
      str.append(String.valueOf(minor).repeat(Math.max(0, value)));
    } else if (value == 4) {
      str.append(minor);
      str.append(middle);
    } else if (value == 5) {
      str.append(middle);
    } else if (value <= 8) {
      str.append(middle);
      str.append(String.valueOf(minor).repeat(value - 5));
    } else if (value == 9) {
      str.append(minor);
      str.append(major);
    }
  }

  /**
   * Given a collection of candidate numbers (candidates) and a target number (target), find all
   * unique combinations in candidates where the candidate numbers sum to target.
   *
   * <p>Each number in candidates may only be used once in the combination.
   *
   * <p>Note: The solution set must not contain duplicate combinations.
   */
  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<List<Integer>> results = new ArrayList<>();
    Arrays.sort(candidates); // Sort to handle duplicates easily
    combinationSum2(candidates, target, 0, new ArrayList<>(), results);
    return results;
  }

  private void combinationSum2(
      int[] candidates, int target, int start, List<Integer> current, List<List<Integer>> results) {
    if (target == 0) {
      results.add(new ArrayList<>(current));
      return;
    }

    for (int i = start; i < candidates.length; i++) {
      if (i > start && candidates[i] == candidates[i - 1]) {
        continue; // Skip duplicates
      }

      if (candidates[i] > target) {
        break; // No point in exploring further if the candidate exceeds the target
      }

      current.add(candidates[i]);
      combinationSum2(candidates, target - candidates[i], i + 1, current, results);
      current.remove(current.size() - 1);
    }
  }

  /**
   * Given an m x n grid of characters board and a string word, return true if word exists in the
   * grid.
   *
   * <p>The word can be constructed from letters of sequentially adjacent cells, where adjacent
   * cells are horizontally or vertically neighboring. The same letter cell may not be used more
   * than once.
   */
  public boolean exist(char[][] board, String word) {
    int n = board.length; // Number of rows in the board
    int m = board[0].length; // Number of columns in the board

    boolean[][] visited = new boolean[n][m]; // Array to keep track of visited cells

    char[] wordChar = word.toCharArray(); // Convert the word into a character array

    // Quick check: If the length of the word exceeds the total number of cells on the board, it
    // can't exist
    if (wordChar.length > n * m) return false;

    int[] counts = new int[256]; // Array to store counts of each character

    // Count the occurrence of each character on the board
    for (char[] chars : board) {
      for (int j = 0; j < m; j++) {
        counts[chars[j]]++;
      }
    }

    // Adjust the order of characters in the wordChar array based on their frequency counts to
    // optimize search
    int len = wordChar.length;
    for (int i = 0; i < len / 2; i++) {
      if (counts[wordChar[i]] > counts[wordChar[len - 1 - i]]) {
        for (int j = 0; j < len / 2; j++) {
          char temp = wordChar[j];
          wordChar[j] = wordChar[len - 1 - j];
          wordChar[len - 1 - j] = temp;
        }
        break;
      }
    }

    // Decrease counts of characters in the word from the board
    for (char c : wordChar) {
      if (--counts[c] < 0)
        return false; // If there are more occurrences of a character in the word than on the board,
      // return false
    }

    // Iterate through each cell in the board and start searching for the word
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (visit(board, wordChar, 0, i, j, n, m, visited))
          return true; // If the word is found starting from this cell, return true
      }
    }
    return false; // If the loop completes without finding the word, return false
  }

  // Helper function to recursively search for the word starting from a given cell
  private boolean visit(
      char[][] board, char[] word, int start, int x, int y, int n, int m, boolean[][] visited) {
    // Base case: If all characters in the word are found, return true
    if (start == word.length) return true;

    // Check for out-of-bounds, already visited cells, and character mismatch
    if (x < 0 || x >= n || y < 0 || y >= m || visited[x][y]) return false;

    // If the current character in the word does not match the character on the board, return false
    if (word[start] != board[x][y]) return false;

    visited[x][y] = true; // Mark the current cell as visited

    // Recursively search in all four directions from the current cell
    boolean found =
        visit(board, word, start + 1, x + 1, y, n, m, visited)
            || visit(board, word, start + 1, x - 1, y, n, m, visited)
            || visit(board, word, start + 1, x, y + 1, n, m, visited)
            || visit(board, word, start + 1, x, y - 1, n, m, visited);

    visited[x][y] = false; // Backtrack: Unmark the current cell as visited

    return found; // Return whether the word was found starting from the current cell
  }

  /**
   * Given an array of distinct integers candidates and a target integer target, return a list of
   * all unique combinations of candidates where the chosen numbers sum to target. You may return
   * the combinations in any order.
   *
   * <p>The same number may be chosen from candidates an unlimited number of times. Two combinations
   * are unique if the frequency of at least one of the chosen numbers is different.
   *
   * <p>The test cases are generated such that the number of unique combinations that sum up to
   * target is less than 150 combinations for the given input.
   */
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(candidates); // Sort the candidates to help with pruning
    combinationSum(result, new ArrayList<>(), candidates, target, 0);
    return result;
  }

  private void combinationSum(
      List<List<Integer>> result, List<Integer> tempList, int[] candidates, int remain, int start) {
    if (remain < 0) {
      return; // If the remaining sum is less than 0, we return (pruning)
    }
    if (remain == 0) {
      result.add(
          new ArrayList<>(tempList)); // If the remaining sum is 0, we found a valid combination
    } else {
      for (int i = start; i < candidates.length; i++) {
        tempList.add(candidates[i]); // Choose the number
        combinationSum(
            result,
            tempList,
            candidates,
            remain - candidates[i],
            i); // Recur with the updated sum and same start index
        tempList.remove(tempList.size() - 1); // Backtrack by removing the last chosen number
      }
    }
  }

  /**
   * You are given a string s and an integer k. You can choose any character of the string and
   * change it to any other uppercase English character. You can perform this operation at most k
   * times.
   *
   * <p>Return the length of the longest substring containing the same letter you can get after
   * performing the above operations.
   */
  public int characterReplacement(String s, int k) {
    int[] count = new int[26]; // Frequency count of characters
    int maxCount = 0; // Max count of a single character in the current window
    int maxLength = 0;

    int left = 0;
    for (int right = 0; right < s.length(); right++) {
      char currentChar = s.charAt(right);
      count[currentChar - 'A']++;
      maxCount = Math.max(maxCount, count[currentChar - 'A']);

      // Check if we need to shrink the window
      while (right - left + 1 - maxCount > k) {
        char leftChar = s.charAt(left);
        count[leftChar - 'A']--;
        left++;
      }

      // Update maxLength
      maxLength = Math.max(maxLength, right - left + 1);
    }

    return maxLength;
  }

  public List<Integer> findClosestElements2(int[] arr, int k, int x) {
    int n = arr.length;
    int left = 0;
    int right = n - 1;

    // Find the position of x or the closest element to x
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (arr[mid] < x) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }

    // Initialize two pointers
    int low = left - 1;
    int high = left;
    List<Integer> result = new ArrayList<>();

    // Find the k closest elements
    while (k-- > 0) {
      if (low < 0) {
        result.add(arr[high++]);
      } else if (high >= n) {
        result.add(arr[low--]);
      } else if (Math.abs(arr[low] - x) <= Math.abs(arr[high] - x)) {
        result.add(arr[low--]);
      } else {
        result.add(arr[high++]);
      }
    }

    // Sort the result before returning
    Collections.sort(result);
    return result;
  }

  public List<Integer> findClosestElements(int[] arr, int k, int x) {
    List<Integer> result = new ArrayList<>();
    int left = 0;
    int right = arr.length - k;
    binarySearch(arr, k, x, left, right, result);
    return result;
  }

  private void binarySearch(int[] arr, int k, int x, int left, int right, List<Integer> result) {
    if (left == right) {
      for (int i = left; i < left + k; i++) {
        result.add(arr[i]);
      }
      return;
    }
    int mid = left + (right - left) / 2;
    if (x - arr[mid] > arr[mid + k] - x) {
      binarySearch(arr, k, x, mid + 1, right, result);
    } else {
      binarySearch(arr, k, x, left, mid, result);
    }
  }

  /**
   * Given a string s and an integer k, return the length of the longest substring of s such that
   * the frequency of each character in this substring is greater than or equal to k.
   *
   * <p>if no such substring exists, return 0.
   */
  public int longestSubstring(String s, int k) {
    return longestSubstringHelper(s, 0, s.length(), k);
  }

  private int longestSubstringHelper(String s, int start, int end, int k) {
    if (end - start < k) {
      return 0;
    }

    int[] count = new int[26];
    for (int i = start; i < end; i++) {
      count[s.charAt(i) - 'a']++;
    }

    for (int i = start; i < end; i++) {
      if (count[s.charAt(i) - 'a'] < k) {
        int leftPart = longestSubstringHelper(s, start, i, k);
        int rightPart = longestSubstringHelper(s, i + 1, end, k);
        return Math.max(leftPart, rightPart);
      }
    }

    return end - start;
  }

  /**
   * Given n cuboids where the dimensions of the ith cuboid is cuboids[i] = [widthi, lengthi,
   * heighti] (0-indexed). Choose a subset of cuboids and place them on each other.
   *
   * <p>You can place cuboid i on cuboid j if widthi <= widthj and lengthi <= lengthj and heighti <=
   * heightj. You can rearrange any cuboid's dimensions by rotating it to put it on another cuboid.
   *
   * <p>Return the maximum height of the stacked cuboids.
   */
  public int maxHeight(int[][] cuboids) {
    // Rotate
    for (int[] c : cuboids) {
      Arrays.sort(c);
    }

    Arrays.sort(
        cuboids,
        (a, b) -> {
          int firstSub = (a[0] - b[0]);
          if (firstSub == 0) {
            int secondSub = a[1] - b[1];
            if (secondSub == 0) {
              return a[2] - b[2];
            }
            return secondSub;
          }
          return firstSub;
        });

    int ans = 0;
    int[] memo = new int[cuboids.length];
    Arrays.fill(memo, -1);
    for (int i = 0; i < cuboids.length; ++i) {
      ans = Math.max(ans, helper(cuboids, i, memo));
    }
    return ans;
  }

  private int helper(int[][] cuboids, int index, int[] memo) {
    if (index == cuboids.length) {
      return 0;
    }
    if (memo[index] != -1) {
      return memo[index];
    }
    int ans = 0;
    // because the cuboids has sorted so don't need to check 0 position.
    for (int i = index + 1; i < cuboids.length; ++i) {
      if (cuboids[i][2] >= cuboids[index][2] && cuboids[i][1] >= cuboids[index][1]) {
        ans = Math.max(ans, helper(cuboids, i, memo));
      }
    }
    memo[index] = cuboids[index][2] + ans;
    return memo[index];
  }

  /**
   * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a
   * sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
   *
   * <p>Every adjacent pair of words differs by a single letter.
   *
   * <p>Every si for 1 <= i <= k is in wordList.
   *
   * <p>Note that beginWord does not need to be in wordList.
   *
   * <p>sk == endWord
   *
   * <p>Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest
   * transformation sequences from beginWord to endWord, or an empty list if no such sequence
   * exists.
   *
   * <p>Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].
   */
  public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    List<List<String>> result = new ArrayList<>();
    Set<String> wordSet = new HashSet<>(wordList);

    if (!wordSet.contains(endWord)) {
      return result; // endWord must be in the wordList
    }

    // BFS to build the graph
    Map<String, List<String>> graphs = new HashMap<>();
    bfs(beginWord, endWord, wordSet, graphs);

    // DFS to find all paths from endWord to beginWord
    List<String> path = new ArrayList<>();
    path.add(endWord);
    dfs(endWord, beginWord, graphs, path, result);
    return result;
  }

  /**
   * BFS to Build Graph:
   *
   * <p>We perform BFS from beginWord. For each word, we generate all possible next words by
   * changing one letter at a time.
   *
   * <p>If a generated word is in the wordSet and hasn’t been visited yet, we add it to the queue
   * and mark it as visited.
   *
   * <p>We also keep a record of the parent-child relationships in the parents map.
   */
  private void bfs(
      String beginWord, String endWord, Set<String> wordSet, Map<String, List<String>> graphs) {
    Queue<String> queue = new LinkedList<>();
    queue.offer(beginWord);

    Set<String> visited = new HashSet<>();
    visited.add(beginWord);

    boolean foundEnd = false;

    while (!queue.isEmpty() && !foundEnd) {
      int levelSize = queue.size();
      Set<String> currentLevelVisited = new HashSet<>();
      for (int i = 0; i < levelSize; i++) {
        String word = queue.poll();
        List<String> neighbors = getNeighbors(word, wordSet);
        for (String neighbor : neighbors) {
          if (neighbor.equals(endWord)) {
            foundEnd = true;
          }
          if (!visited.contains(neighbor)) {
            if (!currentLevelVisited.contains(neighbor)) {
              queue.offer(neighbor);
              currentLevelVisited.add(neighbor);
            }
            graphs.computeIfAbsent(neighbor, k -> new ArrayList<>()).add(word);
          }
        }
      }
      visited.addAll(currentLevelVisited);
    }
  }

  /** Get all the words only different one character with the current word. */
  private List<String> getNeighbors(String word, Set<String> wordSet) {
    List<String> neighbors = new ArrayList<>();
    char[] charArray = word.toCharArray();
    for (int i = 0; i < charArray.length; i++) {
      char originalChar = charArray[i];
      for (char c = 'a'; c <= 'z'; c++) {
        if (c == originalChar) {
          continue;
        }
        charArray[i] = c;
        String newWord = new String(charArray);
        if (wordSet.contains(newWord)) {
          neighbors.add(newWord);
        }
      }
      charArray[i] = originalChar;
    }
    return neighbors;
  }

  /**
   * DFS to Collect Paths:
   *
   * <p>Starting from endWord, we backtrack to beginWord using the parents map.
   *
   * <p>Each valid path found is added to the result list.
   */
  private void dfs(
      String currentWord,
      String beginWord,
      Map<String, List<String>> parents,
      List<String> paths,
      List<List<String>> result) {

    if (currentWord.equals(beginWord)) {
      List<String> validPath = new ArrayList<>(paths);
      Collections.reverse(validPath);
      result.add(validPath);
      return;
    }

    if (!parents.containsKey(currentWord)) {
      return;
    }

    for (String parent : parents.get(currentWord)) {
      paths.add(parent);
      dfs(parent, beginWord, parents, paths, result);
      paths.remove(paths.size() - 1);
    }
  }

  /**
   * You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are
   * surrounded:
   *
   * <p>Connect: A cell is connected to adjacent cells horizontally or vertically.
   *
   * <p>Region: To form a region connect every 'O' cell.
   *
   * <p>Surround: The region is surrounded with 'X' cells if you can connect the region with 'X'
   * cells and none of the region cells are on the edge of the board.
   *
   * <p>A surrounded region is captured by replacing all 'O's with 'X's in the input matrix board.
   */
  public void surroundedRegions(char[][] board) {
    if (board == null || board.length == 0 || board[0].length == 0) {
      return;
    }

    int m = board.length;
    int n = board[0].length;

    // Mark all 'O's on the border and their connected 'O's
    for (int i = 0; i < m; i++) {
      if (board[i][0] == 'O') {
        markBorderConnected(board, i, 0);
      }
      if (board[i][n - 1] == 'O') {
        markBorderConnected(board, i, n - 1);
      }
    }

    for (int j = 0; j < n; j++) {
      if (board[0][j] == 'O') {
        markBorderConnected(board, 0, j);
      }
      if (board[m - 1][j] == 'O') {
        markBorderConnected(board, m - 1, j);
      }
    }

    // Capture surrounded regions
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i][j] == 'O') {
          board[i][j] = 'X'; // This 'O' is surrounded, capture it
        } else if (board[i][j] == 'E') {
          board[i][j] = 'O'; // This 'O' was marked as non-surrounded, restore it
        }
      }
    }
  }

  private void markBorderConnected(char[][] board, int i, int j) {
    int row = board.length;
    int col = board[0].length;

    if (i < 0 || i >= row || j < 0 || j >= col || board[i][j] != 'O') {
      return;
    }

    board[i][j] = 'E'; // Mark this 'O' as visited (non-surrounded)

    // Recursively mark all connected 'O's
    markBorderConnected(board, i - 1, j); // Up
    markBorderConnected(board, i + 1, j); // Down
    markBorderConnected(board, i, j - 1); // Left
    markBorderConnected(board, i, j + 1); // Right
  }

  public String printBinary(double num) {
    if (num <= 0 || num >= 1) {
      return "ERROR";
    }
    StringBuilder binary = new StringBuilder();
    binary.append('.');

    while (num > 0) {
      /* Setting a limit on length: 32 characters */
      if (binary.length() >= 32) {
        return binary.toString();
      }

      double r = num * 2;
      if (r >= 1) {
        binary.append(1);
        num = r - 1;
      } else {
        binary.append(0);
        num = r;
      }
    }

    return binary.toString();
  }

  public boolean isPermutationOfPalindrome(String phrase) {
    int countOdd = 0;
    int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
    for (char c : phrase.toCharArray()) {
      int x = Character.getNumericValue(c);
      if (x != -1) {
        table[x]++;
        if (table[x] % 2 == 1) {
          countOdd++;
        } else {
          countOdd--;
        }
      }
    }
    return countOdd <= 1;
  }

  /**
   * Given n non-negative integers representing an elevation map where the width of each bar is 1,
   * compute how much water it can trap after raining.
   */
  public int trap(int[] heights) {
    if (heights.length == 0) {
      return 0;
    }

    int l = 0;
    int r = heights.length - 1;
    int leftMax = heights[l];
    int rightMax = heights[r];
    int res = 0;

    while (l < r) {
      if (leftMax < rightMax) {
        l++;
        leftMax = Math.max(leftMax, heights[l]);
        res += leftMax - heights[l];
      } else {
        r--;
        rightMax = Math.max(rightMax, heights[r]);
        res += rightMax - heights[r];
      }
    }

    return res;
  }

  /**
   * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a
   * sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
   *
   * <p>Every adjacent pair of words differs by a single letter. Every si for 1 <= i <= k is in
   * wordList. Note that beginWord does not need to be in wordList. sk == endWord Given two words,
   * beginWord and endWord, and a dictionary wordList, return the number of words in the shortest
   * transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
   */
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    Set<String> wordSet = new HashSet<>(wordList);
    if (!wordSet.contains(endWord)) {
      return 0;
    }

    Set<String> beginSet = new HashSet<>();
    Set<String> endSet = new HashSet<>();
    Set<String> visited = new HashSet<>();
    int length = 1;

    beginSet.add(beginWord);
    endSet.add(endWord);

    while (!beginSet.isEmpty()) {
      // Always expand the smaller set to keep the search balanced
      if (beginSet.size() > endSet.size()) {
        Set<String> temp = beginSet;
        beginSet = endSet;
        endSet = temp;
      }

      Set<String> nextLevelSet = new HashSet<>();
      for (String word : beginSet) {
        char[] wordArray = word.toCharArray();
        for (int i = 0; i < wordArray.length; i++) {
          char originalChar = wordArray[i];
          for (char c = 'a'; c <= 'z'; c++) {
            if (c == originalChar) continue;
            wordArray[i] = c;
            String newWord = new String(wordArray);
            if (endSet.contains(newWord)) {
              return length + 1;
            }
            if (wordSet.contains(newWord) && !visited.contains(newWord)) {
              visited.add(newWord);
              nextLevelSet.add(newWord);
            }
          }
          wordArray[i] = originalChar;
        }
      }

      beginSet = nextLevelSet;
      length++;
    }

    return 0;
  }

  public boolean containsNearbyDuplicate(int[] nums, int k) {
    if (nums.length <= k) {
      k = nums.length - 1;
    }

    int count = 0;

    for (int i = 0; i < nums.length - 1; i++) {
      int jPointer = i + 1;
      while (jPointer < nums.length && jPointer <= i + k) {
        if (nums[i] == nums[jPointer]) {
          return true;
        }
        jPointer++;
        count++;
        if (count > 9999) {
          return false;
        }
      }
    }

    return false;
  }

  public int lengthOfLongestSubstring(String s) {
    int n = s.length();
    if (n == 0) return 0; // Handle empty string case

    int[] lastIndex = new int[256]; // Array for ASCII characters
    Arrays.fill(lastIndex, -1); // Initialize all indices to -1

    int left = 0; // Left boundary of the window
    int maxLength = 0; // Maximum length found

    for (int right = 0; right < n; right++) {
      char c = s.charAt(right);
      // If character c appears in the current window
      if (lastIndex[c] >= left) {
        left = lastIndex[c] + 1; // Move left past the last occurrence
      }
      lastIndex[c] = right; // Update last seen index
      maxLength = Math.max(maxLength, right - left + 1); // Update max length
    }

    return maxLength;
  }

  /**
   * Given an integer array arr, return the length of a maximum size turbulent subarray of arr.
   *
   * <p>A subarray is turbulent if the comparison sign flips between each adjacent pair of elements
   * in the subarray.
   *
   * <p>More formally, a subarray [arr[i], arr[i + 1], ..., arr[j]] of arr is said to be turbulent
   * if and only if:
   *
   * <p>For i <= k < j: arr[k] > arr[k + 1] when k is odd, and arr[k] < arr[k + 1] when k is even.
   * Or, for i <= k < j: arr[k] > arr[k + 1] when k is even, and arr[k] < arr[k + 1] when k is odd.
   */
  public int maxTurbulenceSize(int[] arr) {
    if (arr.length == 1) {
      return 1;
    }

    int maxLen = 1;
    int inc = 1; // Length of turbulent subarray ending with a ">" comparison
    int dec = 1; // Length of turbulent subarray ending with a "<" comparison

    for (int i = 1; i < arr.length; i++) {
      if (arr[i] > arr[i - 1]) {
        inc = dec + 1;
        dec = 1;
      } else if (arr[i] < arr[i - 1]) {
        dec = inc + 1;
        inc = 1;
      } else {
        inc = 1;
        dec = 1;
      }
      maxLen = Math.max(maxLen, Math.max(inc, dec));
    }

    return maxLen;
  }

  /**
   * Given a circular integer array nums of length n, return the maximum possible sum of a non-empty
   * subarray of nums.
   *
   * <p>A circular array means the end of the array connects to the beginning of the array.
   * Formally, the next element of nums[i] is nums[(i + 1) % n] and the previous element of nums[i]
   * is nums[(i - 1 + n) % n].
   *
   * <p>A subarray may only include each element of the fixed buffer nums at most once. Formally,
   * for a subarray nums[i], nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with
   * k1 % n == k2 % n.
   */
  public int maxSubarraySumCircular(int[] nums) {
    int curMax = 0, curMin = 0;
    int globMax = nums[0], globMin = nums[0];
    int total = 0;
    for (int n : nums) {
      curMax = Math.max(curMax + n, n);
      curMin = Math.min(curMin + n, n);
      total += n;
      globMax = Math.max(curMax, globMax);
      globMin = Math.min(curMin, globMin);
    }
    return globMax > 0 ? Math.max(globMax, total - globMin) : globMax;
  }

  public int maxSubArray(int[] nums) {
    int max = nums[0];
    int sum = nums[0];
    for (int i = 1; i < nums.length; i++) {
      if (sum < 0) {
        sum = 0;
      }
      sum += nums[i];
      max = Math.max(sum, max);
    }
    return max;
  }

  /**
   * Given an array of strings words and a width maxWidth, format the text such that each line has
   * exactly maxWidth characters and is fully (left and right) justified.
   *
   * <p>You should pack your words in a greedy approach; that is, pack as many words as you can in
   * each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth
   * characters.
   *
   * <p>Extra spaces between words should be distributed as evenly as possible. If the number of
   * spaces on a line does not divide evenly between words, the empty slots on the left will be
   * assigned more spaces than the slots on the right.
   *
   * <p>For the last line of text, it should be left-justified, and no extra space is inserted
   * between words.
   *
   * <p>Note:
   *
   * <p>A word is defined as a character sequence consisting of non-space characters only. Each
   * word's length is guaranteed to be greater than 0 and not exceed maxWidth. The input array words
   * contains at least one word.
   */
  public List<String> fullJustify(String[] words, int maxWidth) {
    List<String> result = new ArrayList<>();
    List<String> currentLine = new ArrayList<>();
    int currentLength = 0;

    for (String word : words) {
      if (currentLength + word.length() + currentLine.size() > maxWidth) {
        result.add(justifyLine(currentLine, currentLength, maxWidth, false));
        currentLine.clear();
        currentLength = 0;
      }
      currentLine.add(word);
      currentLength += word.length();
    }

    result.add(justifyLine(currentLine, currentLength, maxWidth, true));
    return result;
  }

  private String justifyLine(
      List<String> line, int currentLength, int maxWidth, boolean isLastLine) {
    StringBuilder justifiedLine = new StringBuilder();
    int totalSpaces = maxWidth - currentLength;
    int numGaps = line.size() - 1;

    if (isLastLine || numGaps == 0) {
      lastLineOrNotGap(line, maxWidth, justifiedLine);
    } else {
      appendSpaceToLines(line, totalSpaces, numGaps, justifiedLine);
    }

    return justifiedLine.toString();
  }

  private void appendSpaceToLines(
      List<String> line, int totalSpaces, int numGaps, StringBuilder justifiedLine) {
    int spacesPerGap = totalSpaces / numGaps;
    int extraSpaces = totalSpaces % numGaps;

    for (int i = 0; i < line.size(); i++) {
      if (i > 0) {
        justifiedLine.append(" ".repeat(spacesPerGap + (i <= extraSpaces ? 1 : 0)));
      }
      justifiedLine.append(line.get(i));
    }
  }

  private void lastLineOrNotGap(List<String> line, int maxWidth, StringBuilder justifiedLine) {
    for (int i = 0; i < line.size(); i++) {
      if (i > 0) {
        justifiedLine.append(" ");
      }
      justifiedLine.append(line.get(i));
    }
    while (justifiedLine.length() < maxWidth) {
      justifiedLine.append(" ".repeat(maxWidth - justifiedLine.length()));
    }
  }

  public int gridPath(int n, int m) {
    int[][] dp = new int[n + 1][m + 1];

    for (int i = 1; i <= n; i++) {
      dp[i][1] = 1;
    }

    for (int j = 1; j <= m; j++) {
      dp[1][j] = 1;
    }

    for (int i = 2; i <= n; i++) {
      for (int j = 2; j <= m; j++) {
        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
      }
    }

    return dp[n][m];
  }

  public int howManyWays(int m, int[] coins) {
    Map<Integer, Integer> memo = new HashMap<>();

    memo.put(0, 1);
    for (int i = 1; i <= m; i++) {
      memo.put(i, 0);
    }

    for (int coin : coins) {
      for (int i = coin; i <= m; i++) {
        int subProblem = i - coin;
        if (subProblem < 0) {
          continue;
        }

        memo.put(i, memo.get(i) + memo.get(subProblem));
      }
    }

    return memo.get(m);
  }

  /**
   * You are given an integer array coins representing coins of different denominations and an
   * integer amount representing a total amount of money.
   *
   * <p>Return the fewest number of coins that you need to make up that amount. If that amount of
   * money cannot be made up by any combination of the coins, return -1.
   *
   * <p>You may assume that you have an infinite number of each kind of coin.
   */
  public int coinChange(int[] coins, int amount) {
    // Create an array to store the minimum coins required for each amount
    int[] dp = new int[amount + 1];
    // Initialize the dp array with a large value
    Arrays.fill(dp, amount + 1);
    // Base case: 0 amount requires 0 coins
    dp[0] = 0;

    // Iterate over each coin
    for (int coin : coins) {
      // Update the dp array for each amount from the coin value up to the target amount
      for (int i = coin; i <= amount; i++) {
        dp[i] = Math.min(dp[i], dp[i - coin] + 1);
      }
    }

    // If dp[amount] is still the large value, it means it's not possible to make the amount
    return dp[amount] > amount ? -1 : dp[amount];
  }

  /**
   * You are given a string s and an array of strings words. All the strings of words are of the
   * same length.
   *
   * <p>A concatenated string is a string that exactly contains all the strings of any permutation
   * of words concatenated.
   *
   * <p>For example, if words = ["ab","cd","ef"], then "abcdef", "abefcd", "cdabef", "cdefab",
   * "efabcd", and "efcdab" are all concatenated strings. "acdbef" is not a concatenated string
   * because it is not the concatenation of any permutation of words. Return an array of the
   * starting indices of all the concatenated substrings in s. You can return the answer in any
   * order.
   */
  public List<Integer> findSubstring(String s, String[] words) {
    List<Integer> result = new ArrayList<>();
    if (words == null || words.length == 0) {
      return result;
    }

    int wordLen = words[0].length();
    Map<String, Integer> wordCount = new HashMap<>();

    for (String word : words) {
      wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
    }

    for (int i = 0; i < wordLen; i++) {
      Map<String, Integer> currentCount = new HashMap<>();
      int count = 0;
      int left = i;

      for (int j = i; j <= s.length() - wordLen; j += wordLen) {
        String word = s.substring(j, j + wordLen);

        if (wordCount.containsKey(word)) {
          currentCount.put(word, currentCount.getOrDefault(word, 0) + 1);
          count++;

          while (currentCount.getOrDefault(word, 0) > wordCount.getOrDefault(word, 0)) {
            String leftWord = s.substring(left, left + wordLen);
            currentCount.put(leftWord, currentCount.get(leftWord) - 1);
            count--;
            left += wordLen;
          }

          if (count == words.length) {
            result.add(left);
            String leftWord = s.substring(left, left + wordLen);
            currentCount.put(leftWord, currentCount.get(leftWord) - 1);
            count--;
            left += wordLen;
          }
        } else {
          currentCount.clear();
          count = 0;
          left = j + wordLen;
        }
      }
    }

    return result;
  }

  /**
   * Given an input string s and a pattern p, implement regular expression matching with support for
   * '.' and '*' where:
   *
   * <p>'.' Matches any single character. '*' Matches zero or more of the preceding element. The
   * matching should cover the entire input string (not partial).
   */
  public boolean isMatch(String s, String p) {
    int m = s.length();
    int n = p.length();

    // DP table initialization
    boolean[][] dp = new boolean[m + 1][n + 1];

    // Empty pattern can match empty string
    dp[0][0] = true;

    // Deals with patterns like a* or a*b* or a*b*c*
    for (int j = 1; j <= n; j++) {
      if (p.charAt(j - 1) == '*') {
        dp[0][j] = dp[0][j - 2];
      }
    }

    // Fill the table
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1)) {
          dp[i][j] = dp[i - 1][j - 1];
        } else if (p.charAt(j - 1) == '*') {
          dp[i][j] = dp[i][j - 2];
          if (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1)) {
            dp[i][j] = dp[i][j] || dp[i - 1][j];
          }
        }
      }
    }

    return dp[m][n];
  }

  public int maxVowels(String s, int k) {
    boolean[] isVowel = new boolean[128];
    isVowel['a'] = isVowel['e'] = isVowel['i'] = isVowel['o'] = isVowel['u'] = true;

    char[] chars = s.toCharArray();
    int vowelCount = 0;
    int maxVowels = 0;

    // Count vowels in the first window and slide
    for (int i = 0; i < chars.length; i++) {
      if (isVowel[chars[i]]) {
        vowelCount++;
      }

      if (i >= k && isVowel[chars[i - k]]) {
        vowelCount--;
      }

      if (i >= k - 1) {
        maxVowels = Math.max(maxVowels, vowelCount);
        if (maxVowels == k) return k; // Early return if we reach maximum possible
      }
    }

    return maxVowels;
  }

  public double findMaxAverage(int[] nums, int k) {
    if (nums == null || nums.length < k) {
      return 0.0;
    }

    double sum = 0;
    for (int i = 0; i < k; i++) {
      sum += nums[i];
    }

    double maxSum = sum;
    for (int i = k; i < nums.length; i++) {
      sum += nums[i] - nums[i - k];
      maxSum = Math.max(maxSum, sum);
    }

    return maxSum / k;
  }

  public int maxOperations(int[] nums, int k) {
    Arrays.sort(nums);
    int i = 0;
    int j = nums.length - 1;

    int count = 0;

    while (i < j) {
      if (nums[i] + nums[j] == k) {
        count++;
        i++;
        j--;
      } else if (nums[i] + nums[j] < k) {
        i++;
      } else {
        j--;
      }
    }

    return count;
  }

  public String reverseVowels(String s) {
    char[] word = s.toCharArray();
    int start = 0;
    int end = s.length() - 1;
    String vowels = "aeiouAEIOU";

    while (start < end) {
      // Move start pointer until it points to a vowel
      while (start < end && vowels.indexOf(word[start]) == -1) {
        start++;
      }

      // Move end pointer until it points to a vowel
      while (start < end && vowels.indexOf(word[end]) == -1) {
        end--;
      }

      // Swap the vowels
      char temp = word[start];
      word[start] = word[end];
      word[end] = temp;

      // Move the pointers towards each other
      start++;
      end--;
    }

    String answer = new String(word);
    return answer;
  }

  public boolean canPlaceFlowers(int[] flowerbed, int n) {
    for (int i = 0; i < flowerbed.length; i = i + 2) {
      if (flowerbed[i] == 0) {
        if (i == flowerbed.length - 1 || flowerbed[i] == flowerbed[i + 1]) {
          n--;
        } else {
          i++;
        }
      }
    }
    return n <= 0;
  }

  public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
    List<Boolean> result = new ArrayList<>();
    int max = candies[0];
    for (int i = 1; i < candies.length; i++) {
      if (candies[i] > max) {
        max = candies[i];
      }
    }
    for (int candy : candies) {
      int temp = candy + extraCandies;
      result.add(temp >= max);
    }
    return result;
  }

  public String gcdOfStrings(String str1, String str2) {
    // Check if concatenated strings are equal or not, if not return ""
    if (!(str1 + str2).equals(str2 + str1)) {
      return "";
    }
    // If strings are equal than return the substring from 0 to gcd of size(str1), size(str2)
    int gcd = gcd(str1.length(), str2.length());
    return str1.substring(0, gcd);
  }

  public int minDistance(String word1, String word2) {
    int m = word1.length();
    int n = word2.length();

    // Create a DP table
    int[][] dp = new int[m + 1][n + 1];

    // Initialize the first row and column
    for (int i = 0; i <= m; i++) {
      dp[i][0] = i;
    }
    for (int j = 0; j <= n; j++) {
      dp[0][j] = j;
    }

    // Fill the DP table
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] =
              1
                  + Math.min(
                      dp[i - 1][j - 1], // Replace
                      Math.min(
                          dp[i - 1][j], // Delete
                          dp[i][j - 1])); // Insert
        }
      }
    }

    // Return the minimum number of operations
    return dp[m][n];
  }

  public String predictPartyVictory(String senate) {
    Queue<Integer> radiant = new LinkedList<>();
    Queue<Integer> dire = new LinkedList<>();

    // Populate the queues with the indices of the Radiant and Dire senators
    for (int i = 0; i < senate.length(); i++) {
      if (senate.charAt(i) == 'R') {
        radiant.offer(i);
      } else {
        dire.offer(i);
      }
    }

    int n = senate.length();

    // Simulate the voting rounds
    while (!radiant.isEmpty() && !dire.isEmpty()) {
      int radiantIndex = radiant.poll();
      int direIndex = dire.poll();

      if (radiantIndex < direIndex) {
        // Radiant bans Dire
        radiant.offer(radiantIndex + n);
      } else {
        // Dire bans Radiant
        dire.offer(direIndex + n);
      }
    }

    // Determine the winner
    return radiant.isEmpty() ? "Dire" : "Radiant";
  }

  public String decodeString(String s) {
    int position = 0;
    StringBuilder sb = new StringBuilder();
    int repeat = 0;
    String temp;
    while (position < s.length()) {
      char c = s.charAt(position);
      position++;
      if (c == '[') {
        temp = decodeString(s);
        int j = 0;
        while (j < repeat) {
          sb.append(temp);
          j++;
        }
        repeat = 0;
      } else if (c == ']') {
        break;
      } else if (Character.isAlphabetic(c)) {
        sb.append(c);
      } else {
        repeat = repeat * 10 + c - '0';
      }
    }
    return sb.toString();
  }

  /**
   * The key recurrence relation is: dp[i] = 2 * dp[i-1] + dp[i-3] This comes from the observation
   * that:
   * <li>We can add a vertical domino to any tiling of a 2x(n-1) board.
   * <li>We can add two horizontal dominos to any tiling of a 2x(n-2) board.
   * <li>We can add a tromino in two ways to any tiling of a 2x(n-2) board.
   * <li>We can add a tromino and a domino to any tiling of a 2x(n-3) board. The first three cases
   *     are covered by 2 * dp[i-1], and the last case is covered by dp[i-3].
   */
  public int numTilings(int n) {
    if (n <= 2) {
      return n;
    }

    int MOD = 1_000_000_007;
    long[] dp = new long[n + 1];

    // Base cases
    dp[1] = 1;
    dp[2] = 2;
    dp[3] = 5;

    for (int i = 4; i <= n; i++) {
      dp[i] = (2 * dp[i - 1] + dp[i - 3]) % MOD;
    }

    return (int) dp[n];
  }

  public int uniquePaths(int m, int n) {
    int[] dp = new int[n];

    // Initialize first row
    Arrays.fill(dp, 1);

    // Fill the dp array
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        dp[j] += dp[j - 1];
      }
    }

    return dp[n - 1];
  }

  public int maxProfit(int[] prices, int fee) {
    if (prices == null || prices.length == 0) {
      return 0;
    }
    int days = prices.length;
    int dp0 = 0;
    int dp1 = -prices[0];
    for (int i = 1; i < days; i++) {
      // dp0[i] = Math.max(dp0[i - 1], dp1[i - 1] + prices[i] - fee);
      // dp1[i] = Math.max(dp1[i - 1], dp0[i - 1] - prices[i]);
      int temp = dp0;
      dp0 = Math.max(dp0, dp1 + prices[i] - fee);
      dp1 = Math.max(dp1, temp - prices[i]);
    }
    // return dp0[days - 1];
    return dp0;
  }

  public int rob(int[] nums) {
    int a = 0; // house robbed
    int b = 0; // house not robbed
    for (int num : nums) {
      int curr =
          b + num; // not robbed earlier, so rob curr one i.e. i th one, as let i-1 is not robbed
      b = Math.max(a, b); // max of robed and not robbed house
      a = curr; // robbed curr house
    }
    return Math.max(a, b);
  }

  public int minCostClimbingStairs(int[] cost) {
    if (cost.length < 2) {
      return 0;
    }
    int one = cost[1], two = cost[0], tmp = 0, m = cost.length;
    for (int i = 2; i < m; i++) { // start from third stair
      tmp = one;
      one = Math.min(one, two) + cost[i];
      two = tmp;
    }
    return Math.min(one, two);
  }

  public int tribonacci(int n) {
    if (n == 0) {
      return 0;
    }
    if (n < 3) {
      return 1;
    }
    if (n < 4) {
      return 2;
    }

    int fib0 = 2;
    int fib1 = 1;
    int fib2 = 1;
    for (int i = 3; i < n; i++) {
      int temp = fib0;
      fib0 = fib0 + fib1 + fib2;
      fib2 = fib1;
      fib1 = temp;
    }

    return fib0;
  }

  public int[] countBits(int n) {
    int[] ans = new int[n + 1];

    for (int i = 1; i <= n; i++) {
      // ans[i] = ans[i / 2] + (i % 2);
      ans[i] = ans[i >> 1] + (i & 1);
    }

    return ans;
  }

  public int singleNumber(int[] nums) {
    int res = 0;
    for (int num : nums) {
      res ^= num;
    }
    return res;
  }

  public int minFlips(int a, int b, int c) {
    int flips = 0;

    while (a > 0 || b > 0 || c > 0) {
      int bitA = a & 1;
      int bitB = b & 1;
      int bitC = c & 1;

      if ((bitA | bitB) != bitC) {
        if (bitC == 0) {
          flips += (bitA == 1 ? 1 : 0) + (bitB == 1 ? 1 : 0);
        } else {
          flips += 1;
        }
      }

      a >>= 1;
      b >>= 1;
      c >>= 1;
    }

    return flips;
  }

  public List<List<String>> suggestedProducts(String[] products, String searchWord) {
    List<List<String>> ans = new ArrayList<>();
    Arrays.sort(products);
    for (int i = 1; i <= searchWord.length(); ++i) {
      String cur = searchWord.substring(0, i);
      int k = Arrays.binarySearch(products, cur);
      // while (k > 0 && cur.equals(products[k - 1])) // in case there are more than 1
      // cur in products.
      // --k; // find the first one.
      if (k < 0) { // no cur in products.
        // k = ~-k; // find the first one larger than cur.
        k = -(k + 1); // find the first one larger than cur.
      }
      List<String> suggestion = new ArrayList<>();
      for (int j = k + 3; k < products.length && k < j && products[k].startsWith(cur); ++k) {
        suggestion.add(products[k]);
      }
      ans.add(suggestion);
    }
    return ans;
  }

  public int eraseOverlapIntervals2(int[][] intervals) {
    int max = intervals[0][1];
    int min = max;

    for (int i = 1; i < intervals.length; i++) {
      max = Math.max(max, intervals[i][1]);
      min = Math.min(min, intervals[i][1]);
    }

    int shift = 1 - min;
    int maxIntervalRange = 2 + max - min;
    int[] rightEnds = new int[maxIntervalRange];

    for (int[] interval : intervals) {
      int left = interval[0] + shift;
      int right = interval[1] + shift;
      if (left > rightEnds[right]) {
        rightEnds[right] = left;
      }
    }

    int start = 1;
    int count = 1;

    for (int i = 2; i < maxIntervalRange; i++) {
      if (start <= rightEnds[i]) {
        count++;
        start = i;
      }
    }

    return intervals.length - count;
  }

  public int eraseOverlapIntervals(int[][] intervals) {
    if (intervals == null || intervals.length == 0) {
      return 0;
    }

    // Sort intervals based on end time
    Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

    int count = 0;
    int lastEnd = intervals[0][1];

    for (int i = 1; i < intervals.length; i++) {
      if (intervals[i][0] < lastEnd) {
        // Overlap found, increment count
        count++;
      } else {
        // No overlap, update lastEnd
        lastEnd = intervals[i][1];
      }
    }

    return count;
  }

  public int findMinArrowShots(int[][] points) {
    final int n = points.length;
    final long[] a = new long[n];
    for (int i = 0; i < n; i++) {
      a[i] = (((long) points[i][1]) << 32) | (points[i][0] & 0xFFFFFFFFL);
    }
    Arrays.sort(a);
    int prev = (int) (a[0] >>> 32);
    int count = 1;
    for (int i = 1; i < a.length; i++) {
      if ((int) a[i] > prev) {
        count++;
        prev = (int) (a[i] >>> 32);
      }
    }
    return count;
  }

  public int[] dailyTemperatures(int[] temperatures) {
    int[] ans = new int[temperatures.length];

    // USING STACK BASED APPROACH

    //    Stack<Integer> stack = new Stack<>();
    //    for (int i = 0; i < temperatures.length; i++) {
    //      if (stack.empty() || temperatures[stack.peek()] >= temperatures[i]) {
    //        stack.push(i);
    //      } else {
    //        while (!stack.empty() && temperatures[stack.peek()] < temperatures[i]) {
    //          int poppedIndex = stack.pop();
    //          ans[poppedIndex] = i - poppedIndex;
    //        }
    //        stack.push(i);
    //      }
    //    }
    //    while (!stack.empty()) {
    //      temperatures[stack.pop()] = 0;
    //    }

    // USING MAYBE GREEDY APPROACH

    int maxTemp = -1;
    for (int i = temperatures.length - 1; i >= 0; i--) {
      if (temperatures[i] >= maxTemp) {
        maxTemp = temperatures[i];
      } else {
        int days = 1;
        while (temperatures[i + days] <= temperatures[i]) {
          days += ans[i + days];
        }
        ans[i] = days;
      }
    }

    return ans;
  }

  public int compress(char[] chars) {
    int ans = 0; // keep track of current position in compressed array

    // iterate through input array using i pointer
    int i = 0;
    while (i < chars.length) {
      final char letter = chars[i]; // current character being compressed
      int count = 0; // count of consecutive occurrences of letter

      // count consecutive occurrences of letter in input array
      while (i < chars.length && chars[i] == letter) {
        ++count;
        ++i;
      }

      // write letter to compressed array
      chars[ans++] = letter;

      // if count is greater than 1, write count as string to compressed array
      if (count > 1) {
        // convert count to string and iterate over each character in string
        for (final char c : String.valueOf(count).toCharArray()) {
          chars[ans++] = c;
        }
      }
    }

    // return length of compressed array
    return ans;
  }

  public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] ans = new int[n];
    Arrays.fill(ans, 1);
    int curr = 1;
    for (int i = 0; i < n; i++) {
      ans[i] *= curr;
      curr *= nums[i];
    }
    curr = 1;
    for (int i = n - 1; i >= 0; i--) {
      ans[i] *= curr;
      curr *= nums[i];
    }
    return ans;
  }

  public boolean isIsomorphic(String s, String t) {
    char[] mapping = new char[256];
    boolean[] checkMap = new boolean[256];

    for (int i = 0; i < s.length(); i++) {
      char charS = s.charAt(i);
      char charT = t.charAt(i);
      if (mapping[charS] == 0) {
        if (checkMap[charT]) {
          return false;
        }
        mapping[charS] = charT;
        checkMap[charT] = true;
      } else if (mapping[charS] != charT) {
        return false;
      }
    }
    return true;
  }

  public int canCompleteCircuit(int[] gas, int[] cost) {
    int n = gas.length;
    int totalSurplus = 0;
    int surplus = 0;
    int start = 0;

    for (int i = 0; i < n; i++) {
      totalSurplus += gas[i] - cost[i];
      surplus += gas[i] - cost[i];
      if (surplus < 0) {
        surplus = 0;
        start = i + 1;
      }
    }
    return (totalSurplus < 0) ? -1 : start;
  }

  public TreeNode sortedArrayToBST(int[] nums) {
    return BST(nums, 0, nums.length - 1);
  }

  private TreeNode BST(int[] nums, int start, int end) {
    if (start > end) {
      return null;
    }
    int mid = start + (end - start) / 2;
    TreeNode node = new TreeNode(nums[mid]);
    node.left = BST(nums, start, mid - 1);
    node.right = BST(nums, mid + 1, end);
    return node;
  }

  public void setZeroes(int[][] matrix) {
    Set<Integer> rows = new HashSet<>();
    Set<Integer> cols = new HashSet<>();

    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        if (matrix[i][j] == 0) {
          rows.add(i);
          cols.add(j);
        }
      }
    }

    for (int i = 0; i < matrix.length; i++) {
      if (rows.contains(i)) {
        for (int j = 0; j < matrix[0].length; j++) {
          matrix[i][j] = 0;
        }
      }
    }

    for (int i = 0; i < matrix[0].length; i++) {
      if (cols.contains(i)) {
        for (int j = 0; j < matrix.length; j++) {
          matrix[j][i] = 0;
        }
      }
    }
  }

  public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> ret = new ArrayList<>();
    for (int i = 0; i < nums.length - 2; ++i) {
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      int target = -nums[i];
      int l = i + 1, r = nums.length - 1;
      while (l < r) {
        if (nums[l] + nums[r] > target) {
          r--;
        } else if (nums[l] + nums[r] < target) {
          l++;
        } else {
          ret.add(Arrays.asList(nums[i], nums[l++], nums[r--]));
          while (l < r && nums[l] == nums[l - 1]) {
            l++;
          }
          while (l < r && nums[r] == nums[r + 1]) {
            r--;
          }
        }
      }
    }
    return ret;
  }

  public int[] twoSum(int[] numbers, int target) {
    int left = 0;
    int end = numbers.length - 1;
    int[] res = new int[2];
    while (left < end) {
      if (numbers[end] + numbers[left] > target) {
        end--;
        continue;
      }
      if (numbers[end] + numbers[left] < target) {
        left++;
        continue;
      }
      if (numbers[end] + numbers[left] == target) {
        res[0] = left + 1;
        res[1] = end + 1;
        break;
      }
    }
    return res;
  }

  public String reverseWords(String s) {
    char[] str = s.toCharArray();
    char[] res = new char[s.length() + 1];
    int l = 0;
    int j = str.length - 1;
    while (j >= 0) {
      while (j >= 0 && str[j] == ' ') j--;
      int i = j - 1;
      while (i >= 0 && str[i] != ' ') i--;
      if (j >= 0) {
        for (int k = i + 1; k <= j; k++) {
          res[l++] = str[k];
        }
        res[l++] = ' ';
      }
      j = i - 1;
    }
    return new String(res, 0, l - 1);
  }

  public String reverseWords2(String s) {
    String[] words = Arrays.stream(s.trim().split(" ")).filter(t -> t != "").toArray(String[]::new);
    StringBuilder sb = new StringBuilder();
    for (int i = words.length - 1; i >= 0; i--) {
      sb.append(words[i]).append(" ");
    }
    return sb.toString().trim();
  }

  public String addBinary(String a, String b) {
    StringBuilder res = new StringBuilder();
    int i = a.length() - 1;
    int j = b.length() - 1;
    int carry = 0;
    while (i >= 0 || j >= 0) {
      int sum = carry;
      if (i >= 0) {
        sum += a.charAt(i--) - '0';
      }
      if (j >= 0) {
        sum += b.charAt(j--) - '0';
      }
      carry = sum > 1 ? 1 : 0;
      res.append(sum % 2);
    }
    if (carry != 0) {
      res.append(carry);
    }
    return res.reverse().toString();
  }

  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int n = nums1.length;
    int m = nums2.length;
    int[] merge = new int[n + m];
    int i = 0;
    int j = 0;
    int k = 0;
    while (i < n && j < m && k < n + m) {
      if (nums1[i] < nums2[j]) {
        merge[k] = nums1[i];
        i++;
      } else {
        merge[k] = nums2[j];
        j++;
      }
      k++;
    }

    if (i == nums1.length) {
      while (j < nums2.length) {
        merge[k] = nums2[j];
        j++;
        k++;
      }
    } else {
      while (i < nums1.length) {
        merge[k] = nums1[i];
        i++;
        k++;
      }
    }

    n = n + m;
    double ans;
    if (n % 2 != 0) {
      ans = (merge[n / 2]);
    } else {

      ans = (merge[n / 2] + merge[(n / 2) - 1]);
      ans = ans / 2;
    }
    return ans;
  }

  public double findMedianSortedArrays2(int[] arr1, int[] arr2) {
    int m = arr1.length;
    int n = arr2.length;
    int[] result = new int[m + n]; // Create a new array to hold the merged elements

    System.arraycopy(arr1, 0, result, 0, m);
    System.arraycopy(arr2, 0, result, m, n);
    Arrays.sort(result);
    if ((n + m) % 2 == 0) {
      return (result[(m + n) / 2 - 1] + result[(n + m) / 2]) / 2.0;
    }
    return result[(m + n) / 2];
  }

  public void rotate(int[][] matrix) {
    int row = matrix.length;
    for (int i = 0; i < row; i++) {
      for (int j = 0; j <= i; j++) {
        swap(matrix, i, j);
      }
    }
    for (int i = 0; i < row; i++) {
      int start = 0;
      int end = matrix[i].length - 1;
      while (end > start) {
        swap(matrix, i, start, end);
        end--;
        start++;
      }
    }
  }

  void swap(int[][] matrix, int i, int j) {
    int temp = matrix[i][j];
    matrix[i][j] = matrix[j][i];
    matrix[j][i] = temp;
  }

  void swap(int[][] matrix, int row, int i, int j) {
    int temp = matrix[row][i];
    matrix[row][i] = matrix[row][j];
    matrix[row][j] = temp;
  }

  public String minWindow(String source, String sub) {
    if (source == null
        || sub == null
        || source.isEmpty()
        || sub.isEmpty()
        || source.length() < sub.length()) {
      return "";
    }
    int[] map = new int[128];
    int count = sub.length();
    int start = 0;
    int end = 0;
    int minLen = Integer.MAX_VALUE;
    int startIndex = 0;

    for (char c : sub.toCharArray()) {
      map[c]++;
    }

    char[] chS = source.toCharArray();

    while (end < chS.length) {
      if (map[chS[end++]]-- > 0) {
        count--;
      }
      while (count == 0) {
        if (end - start < minLen) {
          startIndex = start;
          minLen = end - start;
        }

        if (map[chS[start++]]++ == 0) {
          count++;
        }
      }
    }

    return minLen == Integer.MAX_VALUE ? "" : new String(chS, startIndex, minLen);
  }

  /**
   * Given an array of positive integers nums and a positive integer target, return the minimal
   * length of a subarray whose sum is greater than or equal to target. If there is no such
   * subarray, return 0 instead.
   */
  public int minSubArrayLen(int target, int[] nums) {
    int n = nums.length;
    int left = 0;
    int curSum = 0;
    int minLen = Integer.MAX_VALUE;

    for (int right = 0; right < n; right++) {
      curSum += nums[right];

      while (curSum >= target) {
        minLen = Math.min(minLen, right - left + 1);
        curSum -= nums[left];
        left++;
      }
    }

    return minLen == Integer.MAX_VALUE ? 0 : minLen;
  }

  public boolean isPalindrome(String s) {
    StringBuilder filteredString = new StringBuilder();
    for (char c : s.toCharArray()) {
      if (Character.isLetterOrDigit(c)) {
        filteredString.append(Character.toLowerCase(c));
      }
    }

    String filtered = filteredString.toString();

    int l = 0;
    int r = filtered.length() - 1;
    while (l < r) {
      if (filtered.charAt(l) != filtered.charAt(r)) {
        return false;
      }
      l++;
      r--;
    }

    return true;
  }

  public boolean isSubsequence(String s, String t) {
    char[] sChar = s.toCharArray();
    char[] tChar = t.toCharArray();

    int index = 0;
    int count = 0;
    for (char c : sChar) {
      for (int j = index; j < tChar.length; j++) {
        if (c == tChar[j]) {
          index = j + 1;
          count++;
          break;
        }
      }
    }
    return count == sChar.length;
  }

  public int majorityElement1(int[] nums) {
    int count = 0;
    int candidate = 0;

    for (int num : nums) {
      if (count == 0) {
        candidate = num;
      }

      if (num == candidate) {
        count++;
      } else {
        count--;
      }
    }

    return candidate;
  }

  public int removeElement(int[] nums, int val) {
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == val) {
        for (int j = nums.length - 1; j > i; j--) {
          if (nums[j] != val) {
            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
            break;
          }
        }
      }
    }
    int count = 0;
    for (int num : nums) {
      if (num == val) {
        break;
      }
      count++;
    }
    return count;
  }

  public int kthFactor(int n, int k) {
    int count = 0;
    for (int i = 1; i <= n; i++) {
      if (n % i == 0) {
        count++;
      }
      if (count == k) {
        return i;
      }
    }
    return -1;
  }

  public long numberOfPairs(int[] nums1, int[] nums2, int k) {
    Map<Integer, Integer> nm = new HashMap<>();
    for (int i : nums2) {
      int val = i * k;
      nm.put(val, nm.getOrDefault(val, 0) + 1);
    }
    long ans = 0;
    for (int i : nums1) {
      for (int j = 1; j * j <= i; j++) {
        if (i % j == 0) {
          if (nm.containsKey(j)) {
            ans += nm.get(j);
          }
          int val = i / j;
          if (j != val && nm.containsKey(val)) {
            ans += nm.get(val);
          }
        }
      }
    }
    return ans;
  }

  public int countDays(int days, int[][] meetings) {
    Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));
    int freedays = 0;
    int busydays = 0;

    for (int[] meeting : meetings) {
      int start = meeting[0];
      int end = meeting[1];

      if (start > busydays) {
        freedays += start - busydays - 1;
      }

      busydays = Math.max(busydays, end);
    }

    freedays += days - busydays;

    return freedays;
  }

  public int[] sortJumbled(int[] mapping, int[] nums) {
    List<int[]> list = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      int num = nums[i];
      StringBuilder sb = new StringBuilder();
      for (char c : (num + "").toCharArray()) {
        sb.append(mapping[c - '0']);
      }
      list.add(new int[] {nums[i], Integer.parseInt(sb.toString()), i});
    }
    list.sort((a, b) -> (b[1] - a[1] == 0) ? a[2] - b[2] : a[1] - b[1]);
    List<Integer> resultList = new ArrayList<>();

    for (int[] val : list) {
      resultList.add(nums[val[2]]);
    }

    return resultList.stream().mapToInt(Integer::intValue).toArray();
  }

  public int numberOfBeams(String[] bank) {

    int prevCount = 0;
    int totalBeams = 0;

    for (String s : bank) {
      int currentCount = countOnes(s);

      if (currentCount > 0) {
        totalBeams += prevCount * currentCount;
        prevCount = currentCount;
      }
    }

    return totalBeams;
  }

  private int countOnes(String s) {
    int count = 0;
    for (char c : s.toCharArray()) {
      if (c == '1') {
        count++;
      }
    }
    return count;
  }

  public int nextBeautifulNumber(int n) {
    int[] beauts = {
      0, 1, 22, 122, 212, 221, 333, 1333, 3133, 3313, 3331, 4444, 14444, 22333, 23233, 23323, 23332,
      32233, 32323, 32332, 33223, 33232, 33322, 41444, 44144, 44414, 44441, 55555, 122333, 123233,
      123323, 123332, 132233, 132323, 132332, 133223, 133232, 133322, 155555, 212333, 213233,
      213323, 213332, 221333, 223133, 223313, 223331, 224444, 231233, 231323, 231332, 232133,
      232313, 232331, 233123, 233132, 233213, 233231, 233312, 233321, 242444, 244244, 244424,
      244442, 312233, 312323, 312332, 313223, 313232, 313322, 321233, 321323, 321332, 322133,
      322313, 322331, 323123, 323132, 323213, 323231, 323312, 323321, 331223, 331232, 331322,
      332123, 332132, 332213, 332231, 332312, 332321, 333122, 333212, 333221, 422444, 424244,
      424424, 424442, 442244, 442424, 442442, 444224, 444242, 444422, 515555, 551555, 555155,
      555515, 555551, 666666, 1224444
    };
    int pos = Arrays.binarySearch(beauts, n);

    if (pos >= 0) {
      return beauts[pos + 1];
    }

    return beauts[-pos - 1];
  }

  public int minAvgTwoSlice(int[] arr) {
    int pos = 0;
    double min = Integer.MAX_VALUE;

    for (int i = 0; i < arr.length - 2; i++) {
      double avg1 = (arr[i] + arr[i + 1]) / 2d;
      double avg2 = (arr[i] + arr[i + 1] + arr[i + 2]) / 3d;
      if (min > avg2 || min > avg1) {
        min = Math.min(avg1, avg2);
        pos = i;
      }
    }

    int length = arr.length;
    if (min > (arr[length - 1] + arr[length - 2]) / 2d) {
      return length - 2;
    }

    return pos;
  }

  public boolean isAlienSorted(String[] words, String order) {
    int[] dict = new int[26];
    for (int i = 0; i < dict.length; i++) {
      int idx = order.charAt(i) - 'a';
      dict[idx] = i;
    }
    for (int i = 0; i < words.length - 1; i++) {
      if (compare(words[i], words[i + 1], dict) > 0) {
        return false;
      }
    }

    return true;
  }

  private int compare(String word1, String word2, int[] dict) {
    int l1 = word1.length();
    int l2 = word2.length();
    int min = Math.min(l1, l2);
    for (int i = 0; i < min; i++) {
      int c1 = word1.charAt(i) - 'a';
      int c2 = word2.charAt(i) - 'a';
      if (c1 != c2) {
        return dict[c1] - dict[c2];
      }
    }
    return l1 == min ? -1 : 1;
  }

  public List<List<Integer>> fourSum(int[] nums, int target) {
    Arrays.sort(nums);
    return kSum(nums, target, 0, 4);
  }

  public List<List<Integer>> kSum(int[] nums, long target, int start, int k) {
    List<List<Integer>> res = new ArrayList<>();

    // If we have run out of numbers to add, return res.
    if (start == nums.length) {
      return res;
    }

    // There are k remaining values to add to the sum. The
    // average of these values is at least target / k.
    long averageValue = target / k;

    // We cannot obtain a sum of target if the smallest value
    // in nums is greater than target / k or if the largest
    // value in nums is smaller than target / k.
    if (nums[start] > averageValue || averageValue > nums[nums.length - 1]) {
      return res;
    }

    if (k == 2) {
      return twoSum(nums, target, start);
    }

    for (int i = start; i < nums.length; ++i) {
      if (i == start || nums[i - 1] != nums[i]) {
        for (List<Integer> subset : kSum(nums, target - nums[i], i + 1, k - 1)) {
          res.add(new ArrayList<>(List.of(nums[i])));
          res.get(res.size() - 1).addAll(subset);
        }
      }
    }

    return res;
  }

  public List<List<Integer>> twoSum(int[] nums, long target, int start) {
    List<List<Integer>> res = new ArrayList<>();
    int lo = start;
    int hi = nums.length - 1;

    while (lo < hi) {
      int currSum = nums[lo] + nums[hi];
      if (currSum < target || (lo > start && nums[lo] == nums[lo - 1])) {
        ++lo;
      } else if (currSum > target || (hi < nums.length - 1 && nums[hi] == nums[hi + 1])) {
        --hi;
      } else {
        res.add(Arrays.asList(nums[lo++], nums[hi--]));
      }
    }

    return res;
  }

  private ListNode mergeSort(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    // split list
    ListNode slow = head;
    ListNode fast = head.next;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }
    ListNode mid = slow.next;
    slow.next = null;

    // divide and get sorted lists
    ListNode left = mergeSort(head);
    ListNode right = mergeSort(mid);
    ListNode ans = new ListNode(-1);
    ListNode last = ans;

    // merge 2 lists
    while (left != null || right != null) {
      int n1 = left != null ? left.val : Integer.MAX_VALUE;
      int n2 = right != null ? right.val : Integer.MAX_VALUE;
      if (n1 < n2) {
        last.next = left;
        left = left.next;
      } else {
        last.next = right;
        right = right.next;
      }
      last = last.next;
    }
    return ans.next;
  }

  public ListNode sortList(ListNode head) {
    return mergeSort(head);
  }

  public ListNode mergeNodes(ListNode head) {
    if (head.next == null) {
      return head;
    }
    head = head.next;
    ListNode res = head;
    while (res != null && res.next != null) {
      ListNode cur = res;
      int sum = 0;
      while (cur.val != 0) {
        sum += cur.val;
        cur = cur.next;
      }
      res.val = sum;
      res.next = cur.next;
      res = res.next;
    }
    return head;
  }

  public ListNode swapPairs(ListNode head) {
    if (head.next == null) {
      return head;
    }
    ListNode dummy = new ListNode(0);
    ListNode pre = dummy, cur = head;
    dummy.next = head;
    while (cur != null && cur.next != null) {
      ListNode nextNode = cur.next;
      pre.next = nextNode;
      cur.next = nextNode.next;
      nextNode.next = cur;
      pre = cur;
      cur = cur.next;
    }
    return dummy.next;
  }

  public ListNode rotateRight(ListNode head, int k) {
    if (head.next == null) {
      return head;
    }

    int len = 1; // number of nodes
    ListNode newH;
    ListNode tail;
    tail = head;

    while (tail.next != null) // get the number of nodes in the list
    {
      tail = tail.next;
      len++;
    }
    tail.next = head; // circle the link
    k %= len;
    if (k > 0) {
      for (int i = 0; i < len - k; i++) {
        tail = tail.next; // the tail node is the (len-k)-th node (1st node is head)
      }
    }
    newH = tail.next;
    tail.next = null;
    return newH;
  }

  public int divide(int dividend, int divisor) {
    double res = (double) dividend / (double) divisor;
    return (int) res;
  }

  public int threeSumClosest(int[] nums, int target) {
    Arrays.sort(nums);
    int n = nums.length;

    int sum = nums[0] + nums[1] + nums[2];
    for (int i = 0; i < n - 2; i++) {

      int j = i + 1;
      int k = n - 1;
      while (j < k) {
        int temp = nums[i] + nums[j] + nums[k];
        if (Math.abs(temp - target) < Math.abs(sum - target)) {
          sum = temp;
        }
        if (temp > target) {
          k--;
        } else if (temp < target) {
          j++;
        } else {
          return target;
        }
      }
    }
    return sum;
  }

  public char findTheDifference(String s, String t) {
    char c = 0;
    for (char cs : s.toCharArray()) {
      c ^= cs;
    }
    for (char ct : t.toCharArray()) {
      c ^= ct;
    }
    return c;
  }

  public String interpret(String command) {
    StringBuilder stringBuilder = new StringBuilder();
    int theLast = 0;
    for (int i = 0; i < command.length(); i++) {
      char c = command.charAt(i);
      if (c != '(' && c != ')') {
        stringBuilder.append(c);
      } else {
        if (c == '(') {
          theLast = i;
          continue;
        }
        if (i - 1 == theLast) {
          stringBuilder.append('o');
        }
      }
    }
    return stringBuilder.toString();
  }

  public String mergeAlternately(String word1, String word2) {
    int total = word1.length() + word2.length();
    char[] res = new char[total];
    int lastCharWord1 = 0;
    int lastCharWord2 = 0;
    for (int i = 0; i < total; i++) {
      if (lastCharWord1 == word1.length() || lastCharWord2 == word2.length()) {
        if (lastCharWord1 == word1.length()) {
          res[i] = word2.charAt(lastCharWord2);
          lastCharWord2++;
        } else {
          res[i] = word1.charAt(lastCharWord1);
          lastCharWord1++;
        }
      } else {
        if (i % 2 == 0 && lastCharWord1 < word1.length()) {
          res[i] = word1.charAt(lastCharWord1);
          lastCharWord1++;
          continue;
        }
        if (i % 2 != 0 && lastCharWord2 < word2.length()) {
          res[i] = word2.charAt(lastCharWord2);
          lastCharWord2++;
        }
      }
    }
    return String.copyValueOf(res);
  }

  public int[][] matrixReshape(int[][] nums, int r, int c) {
    int n = nums.length, m = nums[0].length;
    if (r * c != n * m) return nums;
    int[][] res = new int[r][c];
    for (int i = 0; i < r * c; i++) {
      res[i / c][i % c] = nums[i / m][i % m];
    }
    return res;
  }

  public int diagonalSum(int[][] mat) {
    int res = 0;
    if (mat.length > 0) {
      res = mat[0][mat.length - 1] + mat[mat.length - 1][0];
    }
    for (int i = 0; i < mat.length; i++) {
      for (int j = 0; j < mat[i].length; j++) {
        if (i == j) {
          res += mat[i][j];
        }
        if (j == mat.length - i - 1 && i > 0 && i < mat.length - 1 && i != j) {
          res += mat[i][j];
        }
      }
    }
    return res;
  }

  public int maximumWealth(int[][] accounts) {
    int richest = 0;
    for (int[] account : accounts) {
      int total = 0;
      for (int i : account) {
        total += i;
      }
      if (richest <= total) {
        richest = total;
      }
    }
    return richest;
  }

  public void moveZeroes(int[] nums) {
    int left = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != 0) {
        nums[left] = nums[i];
        left++;
      }
    }

    while (left < nums.length) {
      nums[left] = 0;
      left++;
    }
  }

  public int sumOddLengthSubArrays(int[] arr) {
    int res = 0, n = arr.length;
    for (int i = 0; i < n; ++i) {
      res += ((i + 1) * (n - i) + 1) / 2 * arr[i];
    }
    return res;
  }

  public boolean checkStraightLine(int[][] coordinates) {
    // y = ax + b; a = (y2 -y1) / (x2 - x1)
    if ((coordinates[1][0] == coordinates[0][0])) {
      for (int[] coordinate : coordinates) {
        if (coordinate[0] != coordinates[0][0]) {
          return false;
        }
      }
    } else {
      double a =
          (double) (coordinates[1][1] - coordinates[0][1])
              / (double) (coordinates[1][0] - coordinates[0][0]);
      int b = coordinates[0][1] - (int) (a * coordinates[0][0]);
      for (int i = 2; i < coordinates.length; i++) {
        if (coordinates[i][1] != (int) (a * coordinates[i][0]) + b) {
          return false;
        }
      }
    }
    return true;
  }

  public int[] nextGreaterElement(int[] findNums, int[] nums) {
    Map<Integer, Integer> map = new HashMap<>(); // map from x to next greater element of x
    Deque<Integer> stack = new LinkedList<>();
    for (int num : nums) {
      while (!stack.isEmpty()
          && stack.peek() < num) { // catch the element[i + 1] > element[i] when pop all element
        map.put(stack.pop(), num);
      }
      stack.push(num);
    }
    for (int i = 0; i < findNums.length; i++) {
      findNums[i] = map.getOrDefault(findNums[i], -1);
    }
    return findNums;
  }

  public List<Integer> preorder(LeetNode root) {
    List<Integer> list = new ArrayList<>();
    if (root == null) {
      return list;
    }

    Deque<LeetNode> nodeStack = new LinkedList<>();
    nodeStack.add(root);

    while (!nodeStack.isEmpty()) {
      root = nodeStack.pop();
      list.add(root.val);
      for (int i = root.children.size() - 1; i >= 0; i--) {
        nodeStack.add(root.children.get(i));
      }
    }

    return list;
  }

  public boolean areAlmostEqual(String s1, String s2) {
    if (s1.length() != s2.length()) {
      return false;
    }
    if (s1.equals(s2)) {
      return true;
    }
    int countDiff = 0;
    int firstPos = 0;
    int lastPos = 0;
    for (int i = 0; i < s1.length(); i++) {
      if (s1.charAt(i) != s2.charAt(i)) {
        if (countDiff == 0) {
          firstPos = i;
        }
        if (countDiff == 1) {
          lastPos = i;
        }
        countDiff++;
      }
    }
    if (countDiff != 2) {
      return false;
    }
    return s1.charAt(firstPos) == s2.charAt(lastPos) && s1.charAt(lastPos) == s2.charAt(firstPos);
  }

  public boolean isHappy(int n) {
    if (n == 1 || n == 7) {
      return true;
    } else if (n < 10) {
      return false;
    }
    int m = 0;
    while (n != 0) {
      int tail = n % 10;
      m += tail * tail;
      n = n / 10;
    }
    return isHappy(m);
  }

  public boolean canMakeArithmeticProgression(int[] arr) {
    Arrays.sort(arr);
    int progress = Math.abs(arr[0] - arr[1]);
    for (int i = 1; i < arr.length - 1; i++) {
      if (progress != Math.abs(arr[i] - arr[i + 1])) {
        return false;
      }
    }
    return true;
  }

  public int arraySign(int[] nums) {
    int pro = 1;
    for (int num : nums) {
      pro *= num;
      if (pro > 0) {
        pro = 1;
      } else if (pro < 0) {
        pro = -1;
      } else {
        return 0;
      }
    }
    return pro > 0 ? 1 : -1;
  }

  public boolean firstCharIs(String s, char ch) {
    return (!s.isEmpty() && s.charAt(0) == ch);
  }

  public boolean isDigit(char ch) {
    return ch >= '0' && ch <= '9';
  }

  public int myAtoi(String s) {
    s = s.trim();
    int number = 0;
    int i = 0;
    int sign = firstCharIs(s, '-') ? -1 : 1;
    if (sign == -1 || firstCharIs(s, '+')) {
      i = 1;
    }
    for (; i < s.length() && isDigit(s.charAt(i)); i++) {
      long temp = (long) number * 10 + (s.charAt(i) - '0');
      if (temp > Integer.MAX_VALUE) {
        return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
      }
      number = (int) temp;
    }
    return sign * number;
  }

  public int reverse(int x) {
    int result = 0;

    while (x != 0) {
      int tail = x % 10;
      int newResult = result * 10 + tail;
      if ((newResult - tail) / 10 != result) {
        return 0;
      }
      result = newResult;
      x = x / 10;
    }

    return result;
  }

  public int maxArea(int[] heights) {
    int left = 0;
    int right = heights.length - 1;
    int maxArea = Integer.MIN_VALUE;
    while (left < right) {
      int height = Math.min(heights[left], heights[right]);
      int width = right - left;
      maxArea = Math.max(maxArea, (height * width));
      while (left < right && heights[left] <= height) {
        left++;
      }
      while (left < right && heights[right] <= height) {
        right--;
      }
    }
    return maxArea;
  }

  public Node findKthToLast(Node head, int k) {
    if (head == null || k <= 0) return null;

    Node first = head;
    Node second = head;

    // Move first k nodes ahead
    for (int i = 0; i < k; i++) {
      if (first == null) return null; // Out of bounds
      first = first.next;
    }

    // Move both pointers until first reaches the end
    while (first != null) {
      first = first.next;
      second = second.next;
    }

    return second;
  }

  public final int SIZE = 8;

  public void solveQueens(int row, int[] board, List<int[]> results) {
    if (row == SIZE) {
      results.add(board.clone());
    } else {
      for (int col = 0; col < SIZE; col++) {
        if (isSafe(row, col, board)) {
          board[row] = col;
          solveQueens(row + 1, board, results);
        }
      }
    }
  }

  public boolean isSafe(int row, int col, int[] board) {
    for (int r = 0; r < row; r++) {
      int c = board[r];
      if (c == col || r - c == row - col || r + c == row + col) {
        return false;
      }
    }
    return true;
  }

  public void printResults(List<int[]> results) {
    for (int[] solution : results) {
      for (int row = 0; row < SIZE; row++) {
        for (int col = 0; col < SIZE; col++) {
          if (solution[row] == col) {
            System.out.print("Q ");
          } else {
            System.out.print(". ");
          }
        }
        System.out.println();
      }
      System.out.println();
    }
  }

  public int minCoins(int amount) {
    int[] coins = {25, 10, 5, 1};
    // Initialize dp array with a large number (infinity)
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, amount + 1); // Use amount + 1 as infinity
    dp[0] = 0;

    // Iterate over each coin
    for (int coin : coins) {
      for (int i = coin; i <= amount; i++) {
        dp[i] = Math.min(dp[i], dp[i - coin] + 1);
      }
    }

    // If dp[amount] is still infinity, return -1 indicating no solution
    return dp[amount] > amount ? -1 : dp[amount];
  }

  public int makeChangeDp(int n) {
    int[] coins = {25, 10, 5, 1};

    // Create a dp array to store the number of ways to make change for each value
    int[] dp = new int[n + 1];

    // There's one way to make 0 cents: use no coins
    dp[0] = 1;

    // Iterate over each coin
    for (int coin : coins) {
      // Update the dp array for values from coin to n
      for (int i = coin; i <= n; i++) {
        dp[i] += dp[i - coin];
      }
    }

    // Return the number of ways to make n cents
    return dp[n];
  }

  public int makeChange(int n) {
    int[] coins = {25, 10, 5, 1};
    int[][] map = new int[n + 1][coins.length]; // precomputed vals
    return makeChange(n, coins, 0, map);
  }

  public int makeChange(int amount, int[] coins, int index, int[][] map) {
    if (map[amount][index] > 0) {
      return map[amount][index];
    }

    if (index >= coins.length - 1) {
      return 1;
    }

    int denomAmount = coins[index];
    int ways = 0;

    for (int i = 0; i * denomAmount <= amount; i++) {
      int amountRemaining = amount - i * denomAmount;
      ways += makeChange(amountRemaining, coins, index + 1, map);
    }

    map[amount][index] = ways;
    return ways;
  }

  public List<String> generateParens(int count) {
    char[] str = new char[count * 2];
    List<String> list = new ArrayList<>();
    addParens(list, count, count, str, 0);
    return list;
  }

  public void addParens(List<String> perms, int left, int right, char[] chars, int index) {
    if (left < 0 || right < left) {
      return;
    }

    if (left == 0 && right == 0) {
      perms.add(new String(chars));
    } else {
      chars[index] = '(';
      addParens(perms, left - 1, right, chars, index + 1);
      chars[index] = ')';
      addParens(perms, left, right - 1, chars, index + 1);
    }
  }

  public List<String> getPerms(String input) {
    List<String> result = new ArrayList<>();
    getPerms("", input, result);
    return result;
  }

  public void getPerms(String prefix, String remainder, List<String> result) {
    if (remainder.isEmpty()) {
      result.add(prefix);
    }

    int len = remainder.length();
    for (int i = 0; i < len; i++) {
      String before = remainder.substring(0, i);
      String after = remainder.substring(i + 1, len);
      char c = remainder.charAt(i);
      getPerms(prefix + c, before + after, result);
    }
  }

  public int countWays(int n) {
    int[] memo = new int[n + 1];
    Arrays.fill(memo, -1);
    return countWays(n, memo);
  }

  public int dpCountWays(int n) {
    if (n == 0) {
      return 0;
    }
    if (n == 1) {
      return 1;
    }
    if (n == 2) {
      return 2;
    }
    if (n == 3) {
      return 4;
    }

    int a = 1;
    int b = 2;
    int c = 4;
    for (int i = 4; i <= n; i++) {
      int temp = a + b + c;
      a = b;
      b = c;
      c = temp;
    }
    return c;
  }

  public int countWays(int n, int[] memo) {
    if (n < 0) {
      return 0;
    } else if (n == 0) {
      return 1;
    } else if (memo[n] > -1) {
      return memo[n];
    } else {
      memo[n] = countWays(n - 1, memo) + countWays(n - 2, memo) + countWays(n - 3, memo);
      return memo[n];
    }
  }
}
