package ru.job4j.sets;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Represents more speed set than ArraySet.
 *
 * @param <T>
 * @author abondarev.
 * @since 07.09.2017.
 */
public class SpeedArraySet<T> implements Set<T> {

    /**
     * The default capacity of the set.
     */
    private static final int DEFAULT_CAPACITY = 16;

    /**
     * Amount of hold element.
     */
    private int size = 0;

    /**
     * Current value of capacity.
     */
    private int capacity;

    /**
     * Container for storing element.
     */
    private T[] values;

    /**
     * The default constructor.
     */
    public SpeedArraySet() {
        this.values = (T[]) new Object[DEFAULT_CAPACITY];
        this.capacity = DEFAULT_CAPACITY;
    }

    /**
     * The constructor create an instance of the set with capacity specified as
     * parameter.
     *
     * @param capacity of the set.
     */
    public SpeedArraySet(int capacity) {
        this.values = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    /**
     * <@inheritedDoc>.
     *
     * @param element for adding.
     */
    @Override
    public void add(T element) {
        if (element != null && this.isUnique(element)) {
            this.checkCapacity();
            this.add(this.getAdditionIndex(element), element);
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
                return values[pointer++];
            }
        };
    }

    /**
     * Inserts element into position specified as parameter.
     *
     * @param index position for insertion.
     * @param element element for inserting.
     */
    private void add(int index, T element) {
        System.arraycopy(this.values, index, this.values, index + 1, size - index);
        this.values[index] = element;
        size++;
    }

    /**
     * Returns index for inserting element.
     *
     * @param element for inserting.
     * @return index for inserting element.
     */
    private int getAdditionIndex(T element) {
        int hash = element.hashCode();
        int leftBorder = -1;
        int rightBorder = this.size;
        int middle;
        while (leftBorder < rightBorder - 1) {
            middle = (leftBorder + rightBorder) >> 1;
            if (this.values[middle].hashCode() < hash) {
                leftBorder = middle;
            } else {
                rightBorder = middle;
            }
        }
        return rightBorder;
    }

    /**
     * Check is element contains in the set.
     *
     * @param element element for checking.
     * @return true if element is unique.
     */
    private boolean isUnique(T element) {
        boolean result = true;
        int hash = element.hashCode();
        int leftBorder = -1;
        int rightBorder = this.size;
        int middle;
        while (leftBorder < rightBorder - 1) {
            middle = (leftBorder + rightBorder) >> 1;
            if (this.values[middle].hashCode() == hash) {
                result = false;
                break;
            }
            if (this.values[middle].hashCode() < hash) {
                leftBorder = middle;
            } else {
                rightBorder = middle;
            }
        }
        return result;
    }

    /**
     * Checks are capacity of inner array enough for adding one more element.
     */
    private void checkCapacity() {
        if (this.capacity - size <= 0) {
            this.grow();
        }
    }

    /**
     * Increase inner array capacity.
     */
    private void grow() {
        this.capacity += this.capacity >> 2;
        this.values = Arrays.copyOf(this.values, this.capacity);
    }
}
