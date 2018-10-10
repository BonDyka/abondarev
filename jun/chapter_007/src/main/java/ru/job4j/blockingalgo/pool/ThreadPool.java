package ru.job4j.blockingalgo.pool;

import java.util.LinkedList;
import java.util.Queue;

/**
 * My implementation of ThreadPool.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 10.11.2017.
 */
public class ThreadPool {

    private final static int DEFAULT_AMONG_THREADS = 3;

    private final int nThreads;

    private final PoolWorker[] threads;

    private final Queue<Runnable> tasks;

    private boolean stopped;

    /**
     * Default constructor. Create ThreadPool instance with three active
     * threads.
     */
    public ThreadPool() {
        this(DEFAULT_AMONG_THREADS);
    }


    /**
     * The constructor creates ThreadPool instance with <tt>nThreads</tt>
     * active threads.
     *
     * @param nThreads number of active threads.
     */
    public ThreadPool(int nThreads) {
        this.nThreads = nThreads;
        this.tasks = new LinkedList<>();
        this.threads = new PoolWorker[this.nThreads];
        for (int i = 0; i < nThreads; i++) {
            this.threads[i] = new PoolWorker();
            this.threads[i].start();
        }
        stopped = false;
    }

    /**
     * Send task to the queue of execution and if there is waiting thread
     * told that thread start execute task.
     *
     * @param task task for executing.
     */
    public void execute(Runnable task) {
        synchronized (this.tasks) {
            this.tasks.add(task);
            this.tasks.notify();
        }
    }

    /**
     * Response signal for all threads end up their work.
     */
    public void shutDown() {
        this.stopped = true;
        tasks.clear();
    }

    /**
     * Inner unit of TreadPool that executes tasks.
     */
    private class PoolWorker extends Thread {

        @Override
        public void run() {
            Runnable r;

            while (!stopped) {
                synchronized (tasks) {
                    while (tasks.isEmpty()) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            System.out.println("End thread " + this.getName());
                        }
                    }
                    r = tasks.poll();
                }

                r.run();
            }
        }
    }
}
