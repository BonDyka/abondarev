package ru.job4j.blockingalgo;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Represent simple mechanism of thread locking.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 11.11.2017.
 */
@ThreadSafe
public class Lock {

    /**
     * Flag that signalised is resource locked or not.
     */
    @GuardedBy("this")
    private boolean locked = false;

    /**
     * Link on thread that locked this lock.
     */
    private Thread lockingThread = null;

    /**
     * Locks some resource action from other threads if one turn on lock.
     * Other threads will wait until called unlock().
     *
     * @throws InterruptedException if interrupted while wait.
     */
    public synchronized void lock() throws InterruptedException {
        while (locked) {
            wait();
        }
        locked = true;
        lockingThread = Thread.currentThread();
    }

    /**
     * Turn off lock and send signal someone threads awake.
     */
    public synchronized void unlock() {
        if (this.lockingThread != Thread.currentThread()) {
            throw new IllegalMonitorStateException("Called thread has not locked this lock");
        }
        locked = false;
        lockingThread = null;
        notify();
    }
}
