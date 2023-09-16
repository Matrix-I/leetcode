import java.util.ArrayList;
import java.util.List;

public class LoopOptimizationDemo {
    public static void main(String[] args) {
        // Create a large list to demonstrate performance difference
        List<Integer> largeList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            largeList.add(i);
        }
        int max = largeList.size();

        // Method 3: More Efficient - Factoring out size before loop
        long startTime3 = System.nanoTime();
        int sum3 = 0;
        for (int i = 0; i < max; i++) {
            sum3 += largeList.get(i);
        }
        long endTime3 = System.nanoTime();

        // Method 4: More Efficient - Factoring out size before loop
        long startTime4 = System.nanoTime();
        int sum4 = 0;
        for (int i = max - 1; i >= 0; i--) {
            sum4 += largeList.get(i);
        }
        long endTime4 = System.nanoTime();

        // Method 1: Less Efficient - Calling size() in loop condition
        long startTime1 = System.nanoTime();
        int sum1 = 0;
        for (Integer integer : largeList) {
            sum1 += integer;
        }
        long endTime1 = System.nanoTime();

        long startTime2 = System.nanoTime();
        int sum2 = largeList.stream().mapToInt(integer -> integer).sum();
        long endTime2 = System.nanoTime();

        // Print results
        System.out.println("Method 1 (size() in loop):");
        System.out.println("Sum: " + sum1);
        System.out.println("Time taken: " + (endTime1 - startTime1) + " nanoseconds");

        System.out.println("\nMethod 2 (size factored out):");
        System.out.println("Sum: " + sum2);
        System.out.println("Time taken: " + (endTime2 - startTime2) + " nanoseconds");

        System.out.println("\nMethod 3 (size factored out):");
        System.out.println("Sum: " + sum3);
        System.out.println("Time taken: " + (endTime3 - startTime3) + " nanoseconds");

        System.out.println("\nMethod 4 (size factored out):");
        System.out.println("Sum: " + sum4);
        System.out.println("Time taken: " + (endTime4 - startTime4) + " nanoseconds");
    }
}