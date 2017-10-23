package ru.job4j.jmm;

/**
 * Represent object for demonstration visibility problem in JMM.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 23.10.2017.
 */
public class VisibilityObj implements Runnable {

    /**
     * Holds bounds condition for exit from loop in run().
     */
    public static final int BOUND_OF_EXECUTION = 1_000_000;

    /**
     * Common variable for increment.
     */
    private int counter;

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

        int bound = BOUND_OF_EXECUTION;
        while (bound-- > 0) {
            increment();
        }
    }
}
