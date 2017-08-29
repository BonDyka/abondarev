package ru.job4j.iterators;

import java.util.Iterator;

import static java.lang.Math.sqrt;

/**
 * The class implements iterator for prime numbers.
 *
 * @author abondarev.
 * @since 27.08.2017.
 */
public class PrimeIterator implements Iterator {

    /**
     * Contains values for iteration.
     */
    private int[] values;

    /**
     * Next position pointer.
     */
    private int pointer;

    /**
     * The constructor. Takes as parameter an array for iteration.
     *
     * @param values an array for iteration.
     */
    public PrimeIterator(int[] values) {
        this.values = values;
    }

    /**
     * Returns <tt>true</tt> if next element is prime number.
     *
     * @return <tt>true</tt> if next element is prime number.
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = pointer; i < this.values.length; i++) {
            if (isPrime(this.values[i])) {
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
        for (; pointer < values.length; pointer++) {
            if (isPrime(values[pointer])) {
                result = values[pointer];
                break;
            }
        }
        return result;
    }

    /**
     * Checks are number prime or not.
     *
     * @param number it's number for checking.
     * @return <tt>true</tt> if number is prime.
     */
    private boolean isPrime(int number) {
        int counter = 0; // Count amount of found dividers.
        if (number > 2 || number % 2 != 0) {
            for (int i = 2; i <= sqrt(number) + 1; i++) {
                if (number % i == 0) {
                    counter++;
                }
            }
        }
        return counter < 1;
    }
}
