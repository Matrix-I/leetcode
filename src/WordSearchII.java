import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordSearchII {
  private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // Right -> Down -> Left -> Up

  public List<String> findWords(char[][] board, String[] words) {
    Trie trie = new Trie();
    for (String word : words) {
      trie.insert(word);
    }

    Set<String> result = new HashSet<>();
    TrieNode root = trie.getRoot();
    int m = board.length;
    int n = board[0].length;
    boolean[][] visited = new boolean[m][n];

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        dfs(board, i, j, root, visited, result);
      }
    }

    return new ArrayList<>(result);
  }

  private void dfs(
      char[][] board, int i, int j, TrieNode node, boolean[][] visited, Set<String> result) {
    char c = board[i][j];
    if (c == '#' || node.children.get(c) == null) {
      return;
    }

    node = node.children.get(c);
    if (node.word != null) {
      result.add(node.word);
      node.word = null; // Avoid duplicate entries
    }

    visited[i][j] = true;

    for (int[] dir : DIRECTIONS) {
      int newI = i + dir[0];
      int newJ = j + dir[1];
      if (newI >= 0
          && newI < board.length
          && newJ >= 0
          && newJ < board[0].length
          && !visited[newI][newJ]) {
        dfs(board, newI, newJ, node, visited, result);
      }
    }

    visited[i][j] = false;
  }

  public static class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    String word = null; // Only store complete words here
  }

  public static class Trie {
    private final TrieNode root;

    public Trie() {
      root = new TrieNode();
    }

    public void insert(String word) {
      TrieNode node = root;
      for (char c : word.toCharArray()) {
        node = node.children.computeIfAbsent(c, k -> new TrieNode());
      }
      node.word = word;
    }

    public TrieNode getRoot() {
      return root;
    }
  }
}
