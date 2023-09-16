import java.time.LocalDate;
import java.util.Stack;

class StockSpanner {
  private final Stack<int[]> stack;
  private int day;

  public StockSpanner() {
    stack = new Stack<>();
    day = 0;
  }

  public int next(int price) {
    day++;

    // Pop elements from stack while they have price <= current price
    while (!stack.isEmpty() && stack.peek()[0] <= price) {
      stack.pop();
    }

    int span;
    if (stack.isEmpty()) {
      span = day;
    } else {
      span = day - stack.peek()[1];
    }

    // Push current [price, day] pair onto the stack
    stack.push(new int[] {price, day});

    return span;
  }

  public static void main(String[] args) {
    LocalDate date = LocalDate.of(2012, 12, 31);
    System.out.println(date);
//    StockSpanner spanner = new StockSpanner();
//    System.out.println(spanner.next(100));
//    System.out.println(spanner.next(80));
//    System.out.println(spanner.next(60));
//    System.out.println(spanner.next(70));
//    System.out.println(spanner.next(60));
//    System.out.println(spanner.next(75));
//    System.out.println(spanner.next(85));
//    System.out.println(spanner.next(100));
  }
}
