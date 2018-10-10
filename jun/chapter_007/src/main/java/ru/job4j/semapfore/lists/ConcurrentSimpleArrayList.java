package ru.job4j.semapfore.lists;

import net.jcip.annotations.ThreadSafe;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Represent threadsafe simple ArrayList.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 03.11.2017.
 */
@ThreadSafe
public class ConcurrentSimpleArrayList<E> implements IList<E> {

    /**
     * Value of default capacity of the list.
     */
    private static final int DEFAULT_CAPACITY = 16;

    /**
     * Value of max capacity of the list.
     */
    private final static int MAX_CAPACITY = Integer.MAX_VALUE;

    /**
     * Lock object for threadsafe using the list.
     */
    private final Object lock = new Object();

    /**
     * Current value fothe list capacity.
     */
    private volatile int capacity;

    /**
     * Current value amount of elements of the list.
     */
    private volatile int size;

    /**
     * Inner storage of the list elements.
     */
    private E[] arr;

    /**
     * The default constructor.
     */
    public ConcurrentSimpleArrayList() {
        this.arr = (E[]) new Object[DEFAULT_CAPACITY];
        this.capacity = DEFAULT_CAPACITY;
    }

    /**
     * The constructor that takes as parameter value of start capacity for
     * the list.
     *
     * @param capacity value of start capacity for the list.
     */
    public ConcurrentSimpleArrayList(int capacity) {
        this.capacity = capacity;
        this.arr = (E[]) new Object[this.capacity];
    }

    /**
     * Adds element to the list if it not null and the list have place for
     * storing.
     *
     * @param elem it's element for adding to the list.
     */
    @Override
    public void add(E elem) {
        if (elem == null) {
            throw new IllegalArgumentException("Element can't be a null");
        }
        synchronized (lock) {
            if (this.size == MAX_CAPACITY - 1) {
                throw new IllegalStateException("List are filled.");
            }
            checkAndGrow();
            this.arr[size++] = elem;
        }
    }

    /**
     * Returns element at the pointed position from the list.
     *
     * @param index it's index of element.
     * @return element at the pointed position from the list.
     */
    @Override
    public E get(int index) {
        return this.arr[index];
    }

    /**
     * Returns an iterator instance for the list.
     *
     * @return an iterator instance for the list.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int pointer = 0;

            @Override
            public boolean hasNext() {
                return this.pointer < size;
            }

            @Override
            public E next() {
                return arr[this.pointer++];
            }
        };
    }

    /**
     * Checks can the list add one more element for current capacity.
     * If it can't increase capacity of the list.
     */
    private void checkAndGrow() {
        if (this.size + 1 >= this.capacity) {
            if (this.capacity >= 1 << 30) {
                capacity = MAX_CAPACITY;
            } else {
                capacity <<= 1;
            }
            this.arr = Arrays.copyOf(arr, capacity);
        }
    }
}
