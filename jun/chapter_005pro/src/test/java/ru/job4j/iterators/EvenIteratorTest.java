package ru.job4j.iterators;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Tests EvenIterator class methods.
 *
 * @author abondarev.
 * @since 26.08.2017.
 */
public class EvenIteratorTest {

    /**
     * Test method next() when even numbers are exist.
     */
    @Test
    public void whenCalledNextIfEvenNumberShouldReturnEvenNumber() {
        Iterator it = new EvenIterator(new int[] {1, 2, 3});

        int result = (Integer) it.next();

        assertThat(result, is(2));
    }

    /**
     * Tests method next() when in array no even number.
     */
    @Test
    public void whenCalledNextIfNoEvenNumberShouldReturnNull() {
        Iterator it = new EvenIterator(new int[] {1});

        assertNull(it.next());
    }

    /**
     * Test method hasNext() if next number not even.
     */
    @Test
    public void whenCalledHasNextIfNoEvenNumbersShouldReturnFalse() {
        Iterator it = new EvenIterator(new int[] {1});

        boolean result = it.hasNext();

        assertThat(result, is(false));
    }
}