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
    private final Object guarder = new Object();

    /**
     * Value of counter.
     */
    @GuardedBy("guarder")
    private int counter;

    @SuppressWarnings("checkstyle")
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread threadA = new Thread(counter);
        Thread threadB = new Thread(counter);
        threadA.start();
        threadA.join();
        threadB.start();
        threadB.join();
        System.out.println("Expected: 3");
        System.out.printf("Actually: %s \r\n", counter.increment());
    }

    /**
     * Increment value of counter.
     */
    public int increment() {
        synchronized(guarder) {
            return ++this.counter;
        }
    }

    /**
     * Executing in different thread.
     */
    @Override
    public void run() {
        this.increment();
    }
}
