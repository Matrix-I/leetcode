public class PartitionList {
  public static Node partition(Node head, int x) {
    if (head == null) return null;

    Node beforeStart = new Node(0); // Dummy node for the start of the 'before' list
    Node afterStart = new Node(0); // Dummy node for the start of the 'after' list

    Node before = beforeStart; // Pointer to the last node in the 'before' list
    Node after = afterStart; // Pointer to the last node in the 'after' list

    Node current = head; // Pointer to traverse the original list

    while (current != null) {
      if (current.data < x) {
        before.next = current;
        before = before.next;
      } else {
        after.next = current;
        after = after.next;
      }
      current = current.next;
    }

    after.next = null; // End the 'after' list
    before.next = afterStart.next; // Connect 'before' list with 'after' list

    return beforeStart.next; // Return the head of the partitioned list
  }

  public static void printList(Node head) {
    Node current = head;
    while (current != null) {
      System.out.print(current.data + " ");
      current = current.next;
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Node head = new Node(3);
    head.next = new Node(5);
    head.next.next = new Node(8);
    head.next.next.next = new Node(5);
    head.next.next.next.next = new Node(10);
    head.next.next.next.next.next = new Node(2);
    head.next.next.next.next.next.next = new Node(1);

    System.out.println("Original List:");
    printList(head);

    int x = 5;
    Node partitionedHead = partition(head, x);

    System.out.println("Partitioned List around " + x + ":");
    printList(partitionedHead);
  }
}
