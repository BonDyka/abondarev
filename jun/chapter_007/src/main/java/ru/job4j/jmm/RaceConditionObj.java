package ru.job4j.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represent object for race condition situation.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 23.10.2017.
 */
public class RaceConditionObj implements Runnable {

    /**
     * Holds bounds condition for exit from loop in run().
     */
    public static final int BOUND_OF_EXECUTION = 1_000_000;

    /**
     * Checker of condition in loop.
     */
    private final AtomicInteger checker = new AtomicInteger();

    /**
     * Common variable for increment.
     */
    private static int counter;

    /**
     * Increment counter.
     */
    public void increment() {
        counter++;
    }

    /**
     * Returns current value of counter.
     *
     * @return current value of counter.
     */
    public int get() {
        return counter;
    }

    /**
     * For execution in different threads.
     */
    @Override
    public void run() {
        while (checker.getAndIncrement() < BOUND_OF_EXECUTION) {
            increment();
        }
    }
}
