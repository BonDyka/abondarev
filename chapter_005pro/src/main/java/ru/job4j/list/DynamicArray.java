package ru.job4j.list;

import java.util.Iterator;

/**
 * The class represent DynamicArray.
 *
 * @param <T> type of stored  elements.
 * @author abondarev.
 * @since 02.09.2017.
 */
public class DynamicArray<T> implements Iterable<T> {

    /**
     * Default capacity of inner array.
     */
    private static final int DEFAULT_CAPACITY = 16;

    /**
     * Array for storing.
     */
    private Object[] container;

    /**
     * Holds index empty cell after last added element.
     */
    private int pointer = 0;

    /**
     * The default constructor.
     */
    public DynamicArray() {
        this.container = new Object[DEFAULT_CAPACITY];
    }

    /**
     * The constructor takes as parameter a capacity of the container.
     *
     * @param capacity a capacity of the container.
     */
    public DynamicArray(int capacity) {
        this.container = new Object[capacity];
    }

    /**
     * Adds element into container.
     *
     * @param value it's object for adding.
     */
    public void add(T value) {
        checkAndIncreaseCapacity();
        this.container[this.pointer++] = value;
    }

    /**
     * Checks capacity of the container. If capacity not enough for adding
     * one more element increase capacity.
     */
    private void checkAndIncreaseCapacity() {
        if (this.pointer == this.container.length) {
            grow();
        }
    }

    /**
     * Increase capacity of container.
     */
    private void grow() {
        int newLength = this.container.length << 2;
        Object[] oldContainer = this.container;
        this.container = new Object[newLength];
        System.arraycopy(oldContainer, 0, this.container, 0, this.pointer);
    }

    /**
     * Return element from container at position specified as parameter.
     *
     * @param index index of searched element.
     * @return element from container at position specified as parameter.
     */
    public T get(int index) {
        if (index >= this.pointer) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        return (T) this.container[index];
    }

    /**
     * Returns amount filled cells of the container.
     *
     * @return amount filled cells of the container.
     */
    public int size() {
        return this.pointer;
    }

    /**
     * Returns an Iterator instance for the container.
     *
     * @return an Iterator instance.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int index;

            @Override
            public boolean hasNext() {
                return index < pointer;
            }

            @Override
            public T next() {
                if (index >= pointer) {
                    throw new IndexOutOfBoundsException("Index out of bounds.");
                }
                return (T) container[pointer++];
            }
        };
    }
}
