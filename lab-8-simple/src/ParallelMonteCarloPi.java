import java.util.Random;
import java.util.Scanner;

public class ParallelMonteCarloPi {
    private static final long ITERATIONS = 1_000_000L;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Введіть к-сть потоків: ");

        int numThreads = Integer.parseInt(sc.nextLine());
        long pointsPerThread = ITERATIONS / numThreads;

        Thread[] threads = new Thread[numThreads];
        MonteCarloWorker[] workers = new MonteCarloWorker[numThreads];
        long totalInsideCircle = 0;

        long startTime = System.nanoTime();

        for (int i = 0; i < numThreads; i++) {
            workers[i] = new MonteCarloWorker(pointsPerThread);
            threads[i] = new Thread(workers[i]);
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
                totalInsideCircle += workers[i].getInsideCircle();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        double pi = 4.0 * totalInsideCircle / ITERATIONS;

        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1_000_000f;

        System.out.printf("PI is %.5f\n", pi);
        System.out.printf("THREADS %d\n", numThreads);
        System.out.printf("ITERATIONS %,d\n", ITERATIONS);
        System.out.printf("TIME %.2fms\n", duration);
    }

    static class MonteCarloWorker implements Runnable {
        private long points;
        private long insideCircle = 0;

        public MonteCarloWorker(long points) {
            this.points = points;
        }

        @Override
        public void run() {
            Random rand = new Random();
            for (long i = 0; i < points; i++) {
                double x = rand.nextDouble();
                double y = rand.nextDouble();
                if (x * x + y * y <= 1) {
                    insideCircle++;
                }
            }
        }

        public long getInsideCircle() {
            return insideCircle;
        }
    }
}
