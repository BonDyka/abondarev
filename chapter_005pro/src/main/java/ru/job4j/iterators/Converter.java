package ru.job4j.iterators;

import java.util.Iterator;

/**
 * The class represent converting iterator of iterators of integers into
 * iterator integers.
 *
 * @author abondarev.
 * @since 27.08.2017.
 */
public class Converter {

    /**
     * Takes as parameter iterator of iterators of integers and convert it
     * into iterator of integer.
     *
     * @param it iterator for converting.
     * @return iterator of integers
     */
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {

            private Iterator<Integer> currentIterator = it.next();

            @Override
            public boolean hasNext() {
                return currentIterator.hasNext() && it.hasNext();
            }

            @Override
            public Integer next() {
                if (!currentIterator.hasNext()) {
                    currentIterator = it.next();
                }
                return currentIterator.next();
            }
        };
    }
}
