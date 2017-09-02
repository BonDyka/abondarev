package ru.job4j.generics;

/**
 * The interface represent store general interface for storing Base classes
 * and its heirs.
 *
 * @param <T> type of storing elements.
 * @author abondarev.
 * @since 31.08.2017.
 */
public interface Store<T extends Base> {

    /**
     * Adds element to the store.
     *
     * @param element for adding.
     */
    void add(T element);

    /**
     * Updates element from store if it exist.
     *
     * @param id element that need update.
     * @param newElement updated element.
     */
    void update(String id, T newElement);

    /**
     * Deletes element from the store if it exist.
     *
     * @param id of element that need delete.
     */
    void delete(String id);

    /**
     * Returns elements with id specified as parameter. If element not found
     * returns null.
     *
     * @param id searched element.
     * @return element with specified id or null.
     */
    T get(String id);
}
