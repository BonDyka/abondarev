package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created for testing SimpleStack class.
 *
 * @author abondarev.
 * @since 04.09.2017.
 */
public class SimpleStackTest {

    /**
     * Tests method push().
     */
    @Test
    public void whenCalledPushThenSizeIncrease() {
        SimpleStack<Integer> ss = new SimpleStack<>();
        ss.push(3);
        int result = ss.size();
        assertThat(result, is(1));
    }

    /**
     * Test method poll().
     */
    @Test
    public void whenCalledPollShouldBeReturnedElementAndSizeDecrease() {
        SimpleStack<Integer> ss = new SimpleStack<>();
        ss.push(3);
        int resultOne = ss.poll();
        assertThat(resultOne, is(3));
        int resultTwo = ss.size();
        assertThat(resultTwo, is(0));
    }
}