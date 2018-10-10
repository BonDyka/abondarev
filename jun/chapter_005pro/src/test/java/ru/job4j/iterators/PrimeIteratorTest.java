package ru.job4j.iterators;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Created for testing PrimeIterator class.
 *
 * @author abondarev.
 * @since 27.08.2017.
 */
public class PrimeIteratorTest {

    /**
     * Tests method hasNext() when next number is prime.
     */
    @Test
    public void whenCalledHasNextIfNextIsPrimeNumberShouldReturnTrue() {
        Iterator it = new PrimeIterator(new int[] {2});

        boolean result = it.hasNext();

        assertThat(result, is(true));
    }

    /**
     * Tests method hasNext() when next number is not prime.
     */
    @Test
    public void whenCalledHasNextIfNextIsNotPrimeNumberShouldReturnFalse() {
        Iterator it = new PrimeIterator(new int[] {6});

        boolean result = it.hasNext();

        assertThat(result, is(false));
    }

    /**
     * Tests method next() when there is prime number.
     */
    @Test
    public void whenCalledNextIfPrimeThereIsShouldReturnFirstPrimeElement() {
        Iterator it = new PrimeIterator(new int[] {4, 7, 5});

        int result = (Integer) it.next();

        assertThat(result, is(7));
    }

    /**
     * Tests method next() when there isn't prime number.
     */
    @Test
    public void whenCalledNextIfPrimeThereIsNotShouldReturnNull() {
        Iterator it = new PrimeIterator(new int[] {4});

        assertNull(it.next());
    }
}