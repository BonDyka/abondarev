package ru.job4j.threads;

/**
 * Represent timer for counting general time of program executing.
 *
 * @author abondarev.
 * @since 19.10.2017.
 */
public class Timer implements Runnable {

    /**
     * Limit of execution time.
     */
    private long timeLimit;

    /**
     * The constructor.
     *
     * @param timeLimit time limit of program execution.
     */
    public Timer(long timeLimit) {
        this.timeLimit = timeLimit;
    }

    /**
     * Executes timer in different thread.
     */
    @Override
    public void run() {
        System.out.println("Start timer.");
        try {
            Thread.sleep(timeLimit);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Time out.");
    }
}
