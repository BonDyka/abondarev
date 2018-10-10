package ru.job4j.generics;

/**
 * The abstract class is based for models classes.
 *
 * @author abondarev.
 * @since 31.08.2017.
 */
public abstract class Base {

    /**
     * Contains value of Id.
     */
    private String id;

    /**
     * Returns id.
     *
     * @return id.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     * @param id new value of id.
     */
    protected void setId(String id) {
        this.id = id;
    }
}
