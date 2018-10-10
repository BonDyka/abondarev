package ru.job4j.nbalgo;

/**
 * Represent exception that is generated if two threads try change
 * the same {@link User} in {@link ConcurrentCash} at the same time.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 21.11.2017.
 */
public class OptimisticException extends RuntimeException {
    public OptimisticException(String msg) {
        super(msg);
    }
}
