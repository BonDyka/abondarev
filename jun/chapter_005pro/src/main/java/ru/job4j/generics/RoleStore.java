package ru.job4j.generics;

/**
 * The class represent store for User class objects.
 *
 * @author abondarev.
 * @since 01.09.2017.
 */
public class RoleStore extends AbstractStore<Role> {

    /**
     * The constructor. Takes as parameter size of inner store.
     *
     * @param size size of store.
     */
    public RoleStore(int size) {
        super(size);
    }
}
