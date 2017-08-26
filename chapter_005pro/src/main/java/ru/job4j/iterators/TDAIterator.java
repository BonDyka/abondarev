package ru.job4j.iterators;

import java.util.Iterator;

/**
 * The class implements an iterator for two-dimension array.
 *
 * @author abondarev.
 * @since 26.08.2017.
 */
public class TDAIterator<E> implements Iterator<E>{

    /**
     * Contains elements for iteration.
     */
    private final E[][] values;

    /**
     * Row pointer for values.
     */
    private int rPointer = 0;

    /**
     * Column pointer for values.
     */
    private int cPointer = 0;

    /**
     * The constructor. Takes as parameter two-dimension array for iteration.
     *
     * @param values two-dimension array for iteration.
     */
    public TDAIterator(E[][] values) {
        this.values = values;
    }

    /**
     * Returns true if there is next element.
     *
     * @return true if there is next element.
     */
    @Override
    public boolean hasNext() {
        return (this.cPointer < this.values[rPointer].length
                && this.rPointer < this.values.length);
    }

    /**
     * Returns next element from <tt>values</tt> while <tt>rPointer</tt> lesser
     * than <tt>values.length</tt>.
     *
     * @return next element from <tt>values</tt>.
     */
    @Override
    public E next() {
        if (this.cPointer >= this.values[this.rPointer].length) {
            this.cPointer = 0;
            this.rPointer++;
        }
        return this.values[this.rPointer][this.cPointer++];
    }
}
