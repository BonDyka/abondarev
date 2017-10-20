package ru.job4j.threads;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represent timer for counting general time of program executing.
 *
 * @author abondarev.
 * @since 19.10.2017.
 */
public class Timer implements Runnable {

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
     */
    public Timer(AtomicInteger time, int timeLimit) {
        this.time = time;
        this.timeLimit = timeLimit;
    }

    /**
     * Executes timer in different thread.
     */
    @Override
    public void run() {
        int pastTime;
        while ((pastTime = time.getAndIncrement()) < timeLimit) {
            if (pastTime % 10 == 1) {
                System.out.println(String.format("Time: %s ms", pastTime));
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
