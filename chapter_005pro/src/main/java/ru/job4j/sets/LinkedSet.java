package ru.job4j.sets;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Represent Set based on linked list.
 *
 * @param <T> type of stored element.
 * @author abondarev.
 * @since 06.09.2017.
 */
public class LinkedSet<T> implements Set<T> {

    /**
     * Inner container for storing elements.
     */
    private LinkedList<T> values;

    /**
     * The constructor.
     */
    public LinkedSet() {
        this.values = new LinkedList<>();
    }

    /**
     * Adds element to the set if element is unique.
     *
     * @param element for adding.
     */
    @Override
    public void add(T element) {
        if (!this.values.contains(element)) {
            this.values.add(element);
        }
    }

    /**
     * Returns an iterator instance for the set.
     *
     * @return an iterator instance for the set.
     */
    @Override
    public Iterator<T> iterator() {
        return values.iterator();
    }
}
