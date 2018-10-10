package ru.job4j.iterators;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The class created for testing all methods from TDAIterator class.
 *
 * @author abondarev.
 * @since 26.08.2017.
 */
public class TDAIteratorTest {

    /**
     * Test method next().
     */
    @Test
    public void whenCalledNextFourTimesShouldReturnElementAndPointersForvard() {
        TDAIterator<Integer> it = new TDAIterator<Integer>(
                                        new Integer[][] {
                                                        new Integer[] {1, 2},
                                                        new Integer[] {3, 4}
                                        });
        int[] expected = new int[] {1, 2, 3, 4};
        int[] result = new int[4];
        for (int index = 0; index < 4; index++) {
            result[index] = it.next();
        }
        assertThat(result, is(expected));
    }

    /**
     * Tests method hasNext() when next elements is exist.
     */
    @Test
    public void whenCalledHasNextAtStartShouldReturnTrue() {
        TDAIterator<Integer> it = new TDAIterator<>(new Integer[][]{{1, 2}});

        boolean result = it.hasNext();

        assertThat(result, is(true));
    }

    /**
     * Tests method hasNext() when next element isn't exist.
     */
    @Test
    public void whenCalledHasNextInEndShouldReturnFalse() {
        TDAIterator<Integer> it = new TDAIterator<>(new Integer[][] {{1}});

        it.next();
        boolean result = it.hasNext();

        assertThat(result, is(false));
    }
}