public class NQueen {

  private final int size;
  private final int[][] chessboard;

  public NQueen(int size) {
    this.size = size;
    chessboard = new int[size][size];
  }

  private boolean isSafe(int row, int col) {
    // Check for queens in the same row
    for (int i = 0; i < col; i++) {
      if (chessboard[row][i] == 1) {
        return false;
      }
    }

    // Check for queens in the upper diagonal
    for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
      if (chessboard[i][j] == 1) {
        return false;
      }
    }

    // Check for queens in the lower diagonal
    for (int i = row, j = col; i < size && j >= 0; i++, j--) {
      if (chessboard[i][j] == 1) {
        return false;
      }
    }

    return true;
  }

  private boolean solveNQueens(int col) {
    if (col >= size) {
      return true; // All queens placed successfully
    }

    for (int row = 0; row < size; row++) {
      if (isSafe(row, col)) {
        chessboard[row][col] = 1; // Place queen at current position

        if (solveNQueens(col + 1)) { // Recursively check next column
          return true;
        }

        chessboard[row][col] = 0; // Backtrack: Remove queen if placement doesn't lead to a solution
      }
    }

    return false; // No valid position found in this column
  }

  public void solve() {
    if (solveNQueens(0)) {
      System.out.println("Solution exists:");
      printChessboard();
    } else {
      System.out.println("No solution exists");
    }
  }

  private void printChessboard() {
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        System.out.print(chessboard[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    int size = 10; // Change this to solve for a different board size
    NQueen nQueens = new NQueen(size);
    nQueens.solve();
  }
}
