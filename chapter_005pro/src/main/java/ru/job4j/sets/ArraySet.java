package ru.job4j.sets;

import java.util.Iterator;

/**
 * The class represent Set based on array.
 *
 * @param <T> type of stored elements
 * @author abondarev.
 * @since 04.09.2017.
 */
public class ArraySet<T> implements Set<T> {

    /**
     * The default capacity of the set.
     */
    private static final int DEFAULT_CAPACITY = 16;

    /**
     * Amount of hold element.
     */
    private int size = 0;

    /**
     * Container for storing element.
     */
    private T[] elements;

    /**
     * The default constructor.
     */
    public ArraySet() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * The contructor create an instance of the set with capacity specified as
     * parameter.
     *
     * @param capacity of the set.
     */
    public ArraySet(int capacity) {
        elements = (T[]) new Object[capacity];
    }

    /**
     * <@inheritedDoc>.
     *
     * @param element for adding.
     */
    @Override
    public void add(T element) {
        if (this.isUnique(element)) {
            this.checkCapacity();
            this.elements[size++] = element;
        }
    }

    /**
     * Returns an iterator instance for the set.
     *
     * @return an iterator instance for the set.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int pointer = 0;

            @Override
            public boolean hasNext() {
                return this.pointer < size;
            }

            @Override
            public T next() {
                return elements[pointer++];
            }
        };
    }

    /**
     * Checks are capacity of inner array enough for adding one more element.
     */
    private void checkCapacity() {
        if (this.size + 1 >= elements.length) {
            this.grow();
        }
    }

    /**
     * Increase inner array capacity.
     */
    private void grow() {
        int newLength = this.elements.length << 2;
        Object[] oldContainer = this.elements;
        this.elements = (T[]) new Object[newLength];
        System.arraycopy(oldContainer, 0, this.elements, 0, this.size);
    }

    /**
     * Check is adding element unique or not.
     *
     * @param element element for checking.
     * @return <tt>true</tt> if element is unique.
     */
    private boolean isUnique(T element) {
        boolean result = true;
        for (T elem : this.elements) {
            if (element.equals(elem)) {
                result = false;
                break;
            }
        }
        return result;
    }
}
