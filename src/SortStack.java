import java.util.Deque;
import java.util.LinkedList;

public class SortStack {

  // Method to sort a stack
  public static void sortStack(Deque<Integer> input) {
    Deque<Integer> temp = new LinkedList<>();

    while (!input.isEmpty()) {
      // Pop the top element from the original stack
      int current = input.pop();

      // Move elements from tempStack to stack if they are greater than the current element
      while (!temp.isEmpty() && temp.peek() > current) {
        input.push(temp.pop());
      }

      // Push the current element onto tempStack
      temp.push(current);
    }

    // Move the sorted elements from tempStack back to the original stack
    while (!temp.isEmpty()) {
      input.push(temp.pop());
    }
  }

  // Method to print the stack
  public static void printStack(Deque<Integer> stack) {
    Deque<Integer> tempStack = new LinkedList<>();
    while (!stack.isEmpty()) {
      int value = stack.pop();
      System.out.print(value + " ");
      tempStack.push(value);
    }
    System.out.println();

    // Restore the original stack
    while (!tempStack.isEmpty()) {
      stack.push(tempStack.pop());
    }
  }

  public static void main(String[] args) {
    Deque<Integer> stack = new LinkedList<>();
    stack.push(34);
    stack.push(3);
    stack.push(31);
    stack.push(98);
    stack.push(92);
    stack.push(23);

    System.out.println("Original Stack:");
    printStack(stack);

    sortStack(stack);

    System.out.println("Sorted Stack:");
    printStack(stack);
  }
}
