public class SumLists {
  public static Node addLists(Node l1, Node l2) {
    Node dummyHead = new Node(0); // Dummy node to simplify list operations
    Node current = dummyHead; // Pointer to build the new list
    int carry = 0; // Initialize carry to 0

    // Traverse both lists
    while (l1 != null || l2 != null || carry != 0) {
      int sum = carry; // Start with carry

      if (l1 != null) {
        sum += l1.data;
        l1 = l1.next;
      }

      if (l2 != null) {
        sum += l2.data;
        l2 = l2.next;
      }

      // Calculate new carry and the digit to store in the result list
      carry = sum / 10;
      int digit = sum % 10;

      // Create a new node with the calculated digit and attach it to the result list
      current.next = new Node(digit);
      current = current.next;
    }

    return dummyHead.next; // Return the actual head of the result list
  }

  public static void printList(Node head) {
    Node current = head;
    while (current != null) {
      System.out.print(current.data + " ");
      current = current.next;
    }
    System.out.println();
  }

  public static Node reverseList(Node head) {
    Node prev = null;
    Node current = head;

    while (current != null) {
      Node next = current.next; // Store next node
      current.next = prev; // Reverse current node's pointer
      prev = current; // Move prev to current node
      current = next; // Move to next node
    }

    return prev; // New head of the reversed list
  }

  public static void main(String[] args) {
    // Create first number: 7 -> 1 -> 6 (represents 617)
    Node l1 = new Node(7);
    l1.next = new Node(1);
    l1.next.next = new Node(6);

    // Create second number: 5 -> 9 -> 2 (represents 295)
    Node l2 = new Node(5);
    l2.next = new Node(9);
    l2.next.next = new Node(2);

    System.out.println("First List:");
    printList(l1);

    System.out.println("Second List:");
    printList(l2);

    Node result = addLists(l1, l2);

    System.out.println("Sum List:");
    printList(reverseList(result));
  }
}
