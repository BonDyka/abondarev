package ru.job4j.semapfore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Multithreading counter.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 25.10.2017.
 */
@ThreadSafe
public class Counter implements Runnable {

    /**
     * Guard object.
     */
    private Object guarder = new Object();

    /**
     * Value of counter.
     */
    @GuardedBy("guarder")
    private int counter;

    /**
     * Increment value of counter.
     */
    public void increment() {
        this.counter++;
    }

    /**
     * Returns value of count.
     *
     * @return value of count.
     */
    public int getCount() {
        return this.counter;
    }

    /**
     * Executing in different thread.
     */
    @Override
    public void run() {
        this.increment();
    }
}
