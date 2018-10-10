package ru.job4j.iterators;

import java.util.Iterator;

/**
 * Implements iterator for even numbers.
 *
 * @author abondarev.
 * @since 26.08.2017.
 */
public class EvenIterator implements Iterator {

    /**
     * The array for iteration.
     */
    private int[] values;

    /**
     * The pointer on position of element.
     */
    private int position;

    /**
     * The constructor takes as parameter an array for iteration.
     *
     * @param values an array for iteration.
     */
    public EvenIterator(int[] values) {
        this.values = values;
    }

    /**
     * Returns true if next element exist and it is even.
     *
     * @return true if next element exist and it is even.
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = position; i < this.values.length; i++) {
            if (this.values[i] % 2 == 0) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Returns element from array in wrapper or null if element not found.
     *
     * @return element from array in wrapper or null if element not found.
     */
    @Override
    public Integer next() {
        Integer result = null;
        for (; this.position < this.values.length; this.position++) {
            if (values[position] % 2 == 0) {
                result = values[position];
                break;
            }
        }
        return result;
    }
}
