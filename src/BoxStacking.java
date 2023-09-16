import java.util.*;

public class BoxStacking {

  static class Box implements Comparable<Box> {
    int width;
    int height;
    int depth;

    Box(int width, int height, int depth) {
      this.width = width;
      this.height = height;
      this.depth = depth;
    }

    @Override
    public int compareTo(Box other) {
      if (this.width != other.width) {
        return this.width - other.width;
      } else if (this.height != other.height) {
        return this.height - other.height;
      } else {
        return this.depth - other.depth;
      }
    }
  }

  /**
   * Stack of Boxes: You have a stack of n boxes, with widths w1 , heights hi , and depths di . The
   * boxes cannot be rotated and can only be stacked on top of one another if each box in the stack
   * is strictly larger than the box above it in width, height, and depth. Implement a method to
   * compute the height of the tallest possible stack. The height of a stack is the sum of the
   * heights of each box.
   */
  public static int maxHeight(Box[] boxes) {
    Arrays.sort(boxes);

    int n = boxes.length;
    int[] dp = new int[n];

    for (int i = 0; i < n; i++) {
      dp[i] = boxes[i].height;
    }

    for (int i = 1; i < n; i++) {
      for (int j = 0; j < i; j++) {
        if (boxes[i].width > boxes[j].width
            && boxes[i].height > boxes[j].height
            && boxes[i].depth > boxes[j].depth) {
          dp[i] = Math.max(dp[i], dp[j] + boxes[i].height);
        }
      }
    }

    int maxHeight = 0;
    for (int height : dp) {
      maxHeight = Math.max(maxHeight, height);
    }

    return maxHeight;
  }

  public static void main(String[] args) {
    Box[] boxes = {new Box(4, 6, 7), new Box(1, 2, 3), new Box(4, 5, 6), new Box(10, 12, 32)};

    System.out.println("The height of the tallest stack is " + maxHeight(boxes));
  }
}
