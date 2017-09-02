package ru.job4j.generics;

/**
 * The class represents Role model that extends Base class.
 *
 * @author abondarev.
 * @since 01.09.2017.
 */
public class Role extends Base {

    /**
     * The constructor.
     *
     * @param id it's <tt>id</tt> of role.
     */
    public Role(String id) {
        setId(id);
    }
}
