public class ListNode {
  int val;
  ListNode next;

  ListNode() {}

  ListNode(int val) {
    this.val = val;
  }

  ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);
    reorderList(head);
  }

  /**
   * You are given the head of a singly linked-list. The list can be represented as:
   *
   * <p>L0 → L1 → … → Ln - 1 → Ln
   *
   * <p>Reorder the list to be on the following form:
   *
   * <p>L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
   *
   * <p>You may not modify the values in the list's nodes. Only nodes themselves may be changed.
   *
   * <p>Explain the algorithm: Example [1,2,3,4,5]
   *
   * <ul>
   *   <li>Find Middle: 3
   *   <li>The second half is [3,4,5]
   *   <li>Revert the second half: [5,4,3] and head: [1,2,3]
   *   <li>Merge 2 halves: 1 -> 5 -> 2 -> 4 -> 3
   * </ul>
   */
  public static void reorderList(ListNode head) {
    if (head == null || head.next == null) {
      return;
    }

    // Step 1: Find the middle of the list
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    // Step 2: Reverse the second half of the list
    ListNode prev = null;
    ListNode curr = slow;
    while (curr != null) {
      ListNode next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }

    // Step 3: Merge the two halves
    ListNode first = head; // [1,2,3]
    ListNode second = prev; // [5,4,3]
    while (second.next != null) {
      ListNode temp1 = first.next;
      ListNode temp2 = second.next;

      first.next = second; // 1 -> 5 -> 4 -> 3 | 1 -> 5 -> 2 -> 4 -> 3
      second.next = temp1;

      first = temp1;
      second = temp2;
    }
  }

  /**
   * Given the head of a singly linked list, return true if it is a <b><i>palindrome</i></b> or
   * false otherwise.
   */
  public boolean isPalindrome(ListNode head) {
    ListNode slow = head, fast = head;

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    ListNode tail = slow;
    ListNode next;
    slow = slow.next;
    tail.next = null;

    while (slow != null) {
      next = slow.next;
      slow.next = tail;
      tail = slow;
      slow = next;
    }

    while (tail != null) {
      if (head.val != tail.val) {
        return false;
      }

      head = head.next;
      tail = tail.next;
    }

    return true;
  }

  public ListNode reverseBetween(ListNode head, int left, int right) {
    // If nothing to reverse
    if (head == null || left == right) {
      return head;
    }

    // Dummy node to handle case where left = 1
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode prev = dummy;

    // Move to position before reversal starts
    for (int i = 1; i < left; i++) {
      prev = prev.next;
    }

    // Start node of the part to be reversed
    ListNode start = prev.next;
    ListNode then = start.next;

    // Reverse required nodes
    for (int i = 0; i < right - left; i++) {
      start.next = then.next;
      then.next = prev.next;
      prev.next = then;
      then = start.next;
    }

    return dummy.next;
  }

  public ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;

    while (curr != null) {
      // Store next node
      ListNode next = curr.next;
      // Reverse current node's pointer
      curr.next = prev;
      // Move prev and curr one step forward
      prev = curr;
      curr = next;
    }

    return prev;
  }

  /**
   * Given the head of a linked list, return the node where the cycle begins. If there is no cycle,
   * return null.
   *
   * <p>There is a cycle in a linked list if there is some node in the list that can be reached
   * again by continuously following the next pointer. Internally, pos is used to denote the index
   * of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no
   * cycle. Note that pos is not passed as a parameter.
   *
   * <p>Do not modify the linked list.
   */
  public ListNode detectCycle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) {
        break;
      }
    }

    if (fast == null || fast.next == null) {
      return null;
    }

    while (head != slow) {
      head = head.next;
      slow = slow.next;
    }
    return head;
  }

  /**
   * Given the head of a linked list, reverse the nodes of the list k at a time, and return the
   * modified list.
   *
   * <p>k is a positive integer and is less than or equal to the length of the linked list. If the
   * number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
   *
   * <p>You may not alter the values in the list's nodes, only nodes themselves may be changed.
   */
  public ListNode reverseKGroup(ListNode head, int k) {
    if (head == null || k == 1) return head;

    // Dummy node initialization
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode cur = dummy, nex = dummy, pre = dummy;

    // Count the number of nodes in the list
    int count = 0;
    while (cur.next != null) {
      cur = cur.next;
      count++;
    }

    // Loop to reverse every group of k nodes
    while (count >= k) {
      cur = pre.next;
      nex = cur.next;
      for (int i = 1; i < k; i++) {
        cur.next = nex.next;
        nex.next = pre.next;
        pre.next = nex;
        nex = cur.next;
      }
      pre = cur;
      count -= k;
    }

    return dummy.next;
  }

  /**
   * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
   *
   * <p>Merge all the linked-lists into one sorted linked-list and return it.
   */
  public ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0) {
      return null;
    }
    return mergeKListsHelper(lists, 0, lists.length - 1);
  }

  private ListNode mergeKListsHelper(ListNode[] lists, int start, int end) {
    if (start == end) {
      return lists[start];
    }
    if (start + 1 == end) {
      return merge(lists[start], lists[end]);
    }
    int mid = start + (end - start) / 2;
    ListNode left = mergeKListsHelper(lists, start, mid);
    ListNode right = mergeKListsHelper(lists, mid + 1, end);
    return merge(left, right);
  }

  private ListNode merge(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode curr = dummy;

    while (l1 != null && l2 != null) {
      if (l1.val < l2.val) {
        curr.next = l1;
        l1 = l1.next;
      } else {
        curr.next = l2;
        l2 = l2.next;
      }
      curr = curr.next;
    }

    curr.next = (l1 != null) ? l1 : l2;

    return dummy.next;
  }
}
