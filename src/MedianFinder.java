import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {
  private final PriorityQueue<Integer> maxHeap; // to store the smaller half of the numbers
  private final PriorityQueue<Integer> minHeap; // to store the larger half of the numbers

  public MedianFinder() {
    maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    minHeap = new PriorityQueue<>();
  }

  public void addNum(int num) {
    if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
      maxHeap.offer(num);
    } else {
      minHeap.offer(num);
    }

    // Balance the heaps
    if (maxHeap.size() > minHeap.size() + 1) {
      minHeap.offer(maxHeap.poll());
    } else if (minHeap.size() > maxHeap.size()) {
      maxHeap.offer(minHeap.poll());
    }
  }

  public double findMedian() {
    if (maxHeap.size() == minHeap.size()) {
      return (maxHeap.peek() + minHeap.peek()) / 2.0;
    } else {
      return maxHeap.peek();
    }
  }

  public static void main(String[] args) {
    MedianFinder mf = new MedianFinder();
    mf.addNum(2);
    System.out.println(mf.findMedian()); // 2.0
    mf.addNum(1);
    System.out.println(mf.findMedian()); // 1.5
    mf.addNum(4);
    System.out.println(mf.findMedian()); // 2.0
    mf.addNum(3);
    System.out.println(mf.findMedian()); // 2.5
  }
}
