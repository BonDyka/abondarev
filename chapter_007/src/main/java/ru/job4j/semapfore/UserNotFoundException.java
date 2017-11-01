package ru.job4j.semapfore;

/**
 * Represent signal that user not found.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 01.11.2017.
 */
@SuppressWarnings("checkstyle")
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
