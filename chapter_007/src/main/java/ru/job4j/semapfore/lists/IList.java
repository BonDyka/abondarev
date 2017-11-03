package ru.job4j.semapfore.lists;

import java.util.Iterator;

/**
 * Represent main interface of list need for task.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 03.11.2017.
 */
public interface IList<E> extends Iterable<E> {

    /**
     * Add element specified as parameter to the list.
     *
     * @param elem it's element for adding to the list.
     */
    void add(E elem);

    /**
     * Returns element at the index position specified as parameter.
     *
     * @param index it's index of element.
     * @return element at the index position specified as parameter.
     */
    E get(int index);

    /**
     * Returns an iterator instance for the list instance.
     *
     * @return an iterator instance for the list instance.
     */
    Iterator<E> iterator();
}
