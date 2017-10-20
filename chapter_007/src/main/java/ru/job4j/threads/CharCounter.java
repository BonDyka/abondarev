package ru.job4j.threads;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represent char counter for text.
 *
 * @author abondarev.
 * @since 19.10.2017.
 */
public class CharCounter implements Runnable {
    /**
     * The text for count chars.
     */
    private String text;


    /**
     * Amount of chars in text.
     */
    private int amount = 0;

    /**
     * Time of program execution.
     */
    private AtomicInteger time;

    /**
     * Limit of execution time.
     */
    private int timeLimit;

    /**
     * The constructor.
     *
     * @param time amount of time past from start program.
     * @param timeLimit time limit of program execution.
     * @param text the text for count chars.
     */
    public CharCounter(AtomicInteger time, int timeLimit, String text) {
        this.time = time;
        this.timeLimit = timeLimit;
        this.text = text;
    }

    /**
     * Executes counting in different thread.
     */
    @Override
    public void run() {
        for (char ch : text.toCharArray()) {
            System.out.print("Chars amount: ");
            System.out.println(++amount);
            if (time.get() > timeLimit) {
                break;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
