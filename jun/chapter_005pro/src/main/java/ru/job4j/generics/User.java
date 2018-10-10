package ru.job4j.generics;

/**
 * The class represents User model that extends Base class.
 *
 * @author abondarev.
 * @since 01.09.2017.
 */
public class User extends Base {

    /**
     * The constructor.
     *
     * @param id it's <tt>id</tt> of role.
     */
    public User(String id) {
        setId(id);
    }
}
