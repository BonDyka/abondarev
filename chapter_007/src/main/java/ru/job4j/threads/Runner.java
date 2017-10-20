package ru.job4j.threads;

import java.util.concurrent.atomic.AtomicInteger;

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
    private static int timeLimit = 200;

    /**
     * Main.
     *
     * @param args array of arguments.
     */
    public static void main(String[] args) {
        AtomicInteger time = new AtomicInteger(0);
        String text = "Hello, I am Alex. It's my thread.";
        Thread timer = new Thread(new Timer(time, timeLimit));
        Thread counter = new Thread(new CharCounter(time, timeLimit, text));
        System.out.println("Timer thread is started.");
        timer.start();
        System.out.println("Counter thread is started.");
        counter.start();
        try {
            timer.join(timeLimit);
            counter.join(timeLimit);
            System.out.println("Execution...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End of execution.");
    }
}
