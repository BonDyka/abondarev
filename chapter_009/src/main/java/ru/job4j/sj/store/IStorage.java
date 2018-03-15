package ru.job4j.sj.store;

import ru.job4j.sj.models.Role;
import ru.job4j.sj.models.User;

import java.util.List;

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

    List<User> getAll();

    Role getRole(int id);

    List<Role> getRoles();

    void close();
}
