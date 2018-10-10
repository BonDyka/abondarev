package ru.job4j.sj.store;

/**
 * Exception describes situation when operation not complete.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 25.03.2018.
 */
public class NotCompleteOperationException extends Exception {

    public NotCompleteOperationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
