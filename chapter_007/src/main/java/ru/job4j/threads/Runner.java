package ru.job4j.threads;

/**
 * Runner for test two threads Timer and CharCounter.
 *
 * @author abondarev.
 * @since 20.10.2017.
 */
public class Runner {

    /**
     * Limit of execution time.
     */
    private static long timeLimit = 100;

    /**
     * Main.
     *
     * @param args array of arguments.
     */
    public static void main(String[] args) {
        String text = "Hello, I am Alex. It's my thread.";
        Thread timer = new Thread(new Timer(timeLimit));
        Thread counter = new Thread(new CharCounter(text));
        System.out.println("Program started.");
        timer.start();
        counter.start();
        try {
            System.out.println("Execution...");
            timer.join();
            counter.join(timeLimit);
            if (!timer.isAlive()) {
                counter.interrupt();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End of execution.");
    }
}
