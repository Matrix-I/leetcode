import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode() {}

  TreeNode(int val) {
    this.val = val;
  }

  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }

  /**
   * Given the root of a binary search tree, and an integer k, return the kth smallest value
   * (1-indexed) of all the values of the nodes in the tree.
   */
  public int kthSmallest(TreeNode root, int k) {
    Set<Integer> set = new HashSet<>();
    dfs(root, set);
    return (int) set.toArray()[k-1];
  }

  private void dfs(TreeNode root, Set<Integer> set) {
    if (root == null) {
      return;
    }
    set.add(root.val);
    if (root.left != null) {
      dfs(root.left, set);
    }
  }

  public int kthSmallestOptimized(TreeNode root, int k) {
    Stack<TreeNode> stack = new Stack<>();
    TreeNode curr = root;

    while (curr != null || !stack.isEmpty()) {
      // Push all left nodes
      while (curr != null) {
        stack.push(curr);
        curr = curr.left;
      }

      // Pop and process
      curr = stack.pop();
      k--;

      if (k == 0) {
        return curr.val;
      }

      // Move to right subtree
      curr = curr.right;
    }

    return -1;
  }

  /**
   * You are given the root of a binary search tree (BST), where the values of exactly two nodes of
   * the tree were swapped by mistake. Recover the tree without changing its structure.
   */
  public void recoverTree(TreeNode root) {}

  public int sumNumbers(TreeNode root) {
    return dfs(root, 0);
  }

  private int dfs(TreeNode node, int currentSum) {
    if (node == null) {
      return 0;
    }

    // Update the current sum to include the current node's value
    currentSum = currentSum * 10 + node.val;

    // If it's a leaf node, return the current sum
    if (node.left == null && node.right == null) {
      return currentSum;
    }

    // Recursively calculate the sum for left and right subtrees
    int leftSum = dfs(node.left, currentSum);
    int rightSum = dfs(node.right, currentSum);

    // Return the total sum from both subtrees
    return leftSum + rightSum;
  }

  public int goodNodes(TreeNode root) {
    return countGoodNodes(root, Integer.MIN_VALUE);
  }

  private int countGoodNodes(TreeNode node, int maxSoFar) {
    if (node == null) {
      return 0;
    }

    int count = 0;
    if (node.val >= maxSoFar) {
      count = 1;
    }

    int newMax = Math.max(maxSoFar, node.val);

    count += countGoodNodes(node.left, newMax);
    count += countGoodNodes(node.right, newMax);

    return count;
  }
}
