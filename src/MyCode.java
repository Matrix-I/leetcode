import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyCode {
  private enum Demo {
    OK,
    CANCELLED
  }

  public static void main(String[] args) {
    List<? super Number> listSuper = new ArrayList<>(List.of(10, 20, 30));
    listSuper.add(10); // ✅ Can add Integer (subclass of Number)
    listSuper.add(10L);
    Object n = listSuper.getFirst(); // ❌ Compile error (returns Object)

    // ---
    List<? extends Number> listExtends = List.of(10, 20, 30);
    Number num = listExtends.getFirst(); // ✅ Safe to read as Number
    //    listExtends.add(213); // ❌ Compile error
  }

  private static boolean isCancelled(Demo demo) {
    return demo == Demo.CANCELLED;
  }
}
