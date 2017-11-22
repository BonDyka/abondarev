package ru.job4j.blockingalgo.pool;

/**
 * Shows work of {@link ThreadPool}.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 10.11.2017.
 */
public class WorkSample {

    /**
     * App entry point.
     *
     * @param args commandline args.
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadPool pool = new ThreadPool();
        for (int i = 0; i < 1000; i++) {
            pool.execute(new Printer(i));
        }

        Thread.sleep(3000);
        pool.shutDown();
    }

    /**
     * Class represent task for execution.
     */
    private static class Printer implements Runnable {
        private final int id;

        public Printer(int id) {
            this.id = id;
        }

        @Override
        public void run() {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("Thread #%s execute task #%d",
                    Thread.currentThread().getName(), id));
        }
    }
}
