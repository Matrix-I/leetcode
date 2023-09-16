import java.util.Arrays;

public class Interview1 {
  public static void main(String[] args) {
    int[] first = {1, 3, 15, 11, 2, 12};
    int[] second = {23, 127, 235, 19, 8};
    System.out.println(smallest(first, second));
  }

  static int smallest(int[] first, int[] second) {
    Arrays.sort(first);
    Arrays.sort(second);

    int min = Integer.MAX_VALUE;
    int i = 0;
    int j = 0;

    while (i < first.length && j < second.length) {
      min = Math.min(min, Math.abs(second[j] - first[i]));
      if (first[i] < second[j]) {
        i++;
      } else {
        j++;
      }
    }

    return min;
  }

  // Given two arrays of integers, compute the pair of values (one value in each array) with the
  // smallest (non-negative) difference. Return the difference.
  //
  // Example
  // Input:
  // {1,3,15,11,2 12},
  // {23,127,235,19,8},
  // Output: 3 (pair (11,8))
  //

  // (1 ,2 , 11 ,12, 15)
  // (8 , 23 , 127)
  // smallest
  // for (int a : first)
  // for (int b : second)
  //  smallest  = b - a < smallest ? b -a : smallest
  //  O(n^2)

  // sort(a) sort(b)
  // {1 ,2 ,3,11,12, 15},
  // (8 , 9, 23 , 127, 235)
  // 15 - 8 -> 12 - 8 -> 11 -8
  // 15 - 9 -> ...
  // 23 > 15 stop. 25 - 9 compare min
  // 1 -> 2 -> 3 -> 11-> 12 -> 15
  // 8 -> 9 -> 23

}
