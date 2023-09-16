public class RotateMatrix {
  /**
   * Steps to Rotate the Matrix by 90 Degrees
   *
   * <p>1. Identify the Layers: • The matrix can be divided into layers. The outermost layer is from
   * index 0 to N-1, the next layer is from index 1 to N-2, and so on. 2. Rotate Each Layer: • For
   * each layer, perform a 4-way swap. Each element in the top row moves to the right column, the
   * right column element moves to the bottom row, the bottom row element moves to the left column,
   * and the left column element moves to the top row.
   *
   * <p>Detailed Steps
   *
   * <p>1. Loop through each layer, where the first layer starts at the outermost edge and the last
   * layer is the innermost. 2. For each layer, swap the elements in groups of four.
   */
  public static void rotate(int[][] matrix) {
    int n = matrix.length;
    for (int layer = 0; layer < n / 2; layer++) {
      int last = n - 1 - layer;
      for (int i = layer; i < last; i++) {
        int offset = i - layer;
        // save top
        int top = matrix[layer][i];

        // left -> top
        matrix[layer][i] = matrix[last - offset][layer];

        // bottom -> left
        matrix[last - offset][layer] = matrix[last][last - offset];

        // right -> bottom
        matrix[last][last - offset] = matrix[i][last];

        // top -> right
        matrix[i][last] = top;
      }
    }
  }

  public static void main(String[] args) {
    int[][] matrix = {
      {1, 2, 3, 4, 20},
      {5, 6, 7, 8, 21},
      {9, 10, 11, 12, 22},
      {13, 14, 15, 16, 23},
      {19, 24, 25, 26, 27}
    };

    rotate(matrix);

    for (int[] ints : matrix) {
      for (int j = 0; j < matrix[0].length; j++) {
        System.out.print(ints[j] + " ");
      }
      System.out.println();
    }
  }
}
