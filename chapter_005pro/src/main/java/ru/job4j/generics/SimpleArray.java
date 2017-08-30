package ru.job4j.generics;

/**
 * The class represent wrapper for array. It implements methods
 * add(), delete(), update() and get() for array that adds, deletes,
 * updates and gets element of array.
 *
 * @param <T> type of elements stored into inner array.
 * @author abondarev.
 * @since 29.08.2017.
 */
public class SimpleArray<T> {

    /**
     * The inner array for executing operations.
     */
    private Object[] values;

    /**
     * Contains value of index of last empty cell.
     */
    private int position = 0;

    /**
     * The constructor. Takes as parameter an integer value of <tt>capacity</tt>
     * of inner array.
     *
     * @param capacity an integer value of <tt>capacity</tt> of inner array.
     */
    public SimpleArray(int capacity) {
        this.values = new Object[capacity];
    }

    /**
     * Adds element into inner array on the last empty position. If inner array
     * is filled <tt>throws ArrayIndexOutOfBoundsException</tt>.
     *
     * @param element it's element that need add into inner array.
     */
    public void add(T element) {
        this.values[position++] = element;
    }

    /**
     * Updates element at index position if it exist otherwise throws
     * <tt>IllegalStateException</tt>.
     *
     * @param index it's index of element for updating.
     * @param newElement it's element with updated data.
     * @throws IllegalStateException if element at index position == null.
     */
    public void update(int index, T newElement) {
        if (this.values[index] == null) {
            throw new IllegalStateException("Nothing to update");
        }
        this.values[index] = newElement;
    }

    /**
     * Returns element from index position or null.
     *
     * @param index it's index of element position.
     * @return element from index position.
     */
    public T get(int index) {
        return (T) this.values[index];
    }

    /**
     * Deletes element from index position.
     *
     * @param index it's index deleting element positions
     */
    public void delete(int index) {
        System.arraycopy(this.values, index + 1, this.values, index,
                         this.position - index - 1);
        this.values[--position] = null;
    }

    /**
     * Returns integer value filled size of inner array.
     *
     * @return integer value filled size of inner array.
     */
    public int size() {
        return this.position;
    }
}
