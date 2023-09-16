public class PerformanceTest {
    private static final int ITERATIONS = 10_000_000;

    public static void main(String[] args) {
        // Test with boxing
        long start = System.nanoTime();
        Integer sum1 = 0;
        for (Integer i = 0; i < ITERATIONS; i++) {
            sum1 += i;
        }
        long boxingTime = System.nanoTime() - start;

        // Test without boxing
        start = System.nanoTime();
        int sum2 = 0;
        for (int i = 0; i < ITERATIONS; i++) {
            sum2 += i;
        }
        long primitiveTime = System.nanoTime() - start;

        System.out.printf("Boxing time: %d ms%n", boxingTime / 1_000_000);
        System.out.printf("Primitive time: %d ms%n", primitiveTime / 1_000_000);
        System.out.printf("Boxing overhead: %.2fx%n",
                (double) boxingTime / primitiveTime);
    }
}

