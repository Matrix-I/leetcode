public class TriePredixTree {
  private static class Trie {
    Trie[] children;
    boolean isWordEnd;

    public Trie() {
      children = new Trie[26];
      isWordEnd = false;
    }

    public boolean helper(String str, boolean insert, boolean search) {
      Trie curr = this;
      for (int i = 0; i < str.length(); i++) {
        int idx = str.charAt(i) - 'a';
        if (curr.children[idx] == null) {
          if (insert) {
            curr.children[idx] = new Trie();
          } else {
            return false;
          }
        }
        curr = curr.children[idx];
      }
      if (insert) {
        curr.isWordEnd = true;
      }
      return !search || curr.isWordEnd;
    }

    public void insert(String word) {
      helper(word, true, false);
    }

    public boolean search(String word) {
      return helper(word, false, true);
    }

    public boolean startsWith(String prefix) {
      return helper(prefix, false, false);
    }
  }
}
