package ru.job4j.sj.store;

import ru.job4j.sj.models.User;

/**
 * Main interface for user storage.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 04.03.2018.
 */
public interface IStorage {

    void add(User user);

    void edit(User user);

    void delete(String login);

    User get(String login);

    void close();
}
