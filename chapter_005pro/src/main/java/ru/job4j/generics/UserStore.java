package ru.job4j.generics;

/**
 * The class represent store for User class objects.
 *
 * @author abondarev.
 * @since 01.09.2017.
 */
public class UserStore extends AbstractStore<User> {

    /**
     * The constructor. Takes as parameter size of inner store.
     *
     * @param size size of store.
     */
    public UserStore(int size) {
        super(size);
    }
}
