package ru.job4j.sets;

import java.util.Iterator;

/**
 * Represents main interface for all implementation for set task.
 *
 * @param <T> type of stored element.
 * @author abondarev.
 * @since 04.09.2017.
 */
public interface Set<T> {

    /**
     * Adds element to the set if element is unique.
     *
     * @param element for adding.
     */
    void add(T element);

    /**
     * Returns an iterator instance for the set.
     *
     * @return an iterator instance for the set.
     */
    Iterator<T> iterator();
}
