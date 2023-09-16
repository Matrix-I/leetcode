import java.util.LinkedList;
import java.util.Queue;

public class RecentCounter {
  Queue<Integer> integerQueue;

  public RecentCounter() {
    integerQueue = new LinkedList<>();
  }

  public int ping(int t) {
    integerQueue.add(t);
    while (!integerQueue.isEmpty() && integerQueue.peek() < t - 3000) {
      integerQueue.poll();
    }
    return integerQueue.size();
  }
}
