package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created for testing SimpleLinkedList class.
 *
 * @author abondarev.
 * @since 03.09.2017.
 */
public class SimpleLinkedListTest {

    /**
     * Tests method add()
     */
    @Test
    public void whenCalledAddSizeShouldBeIncreeseByOne() {
        SimpleLinkedList<Integer> sll = new SimpleLinkedList<>();
        int startSize = sll.size();
        sll.add(234);
        int result = sll.size();
        assertThat(result, is(startSize + 1));
    }

    /**
     * Tests method get() when index of searching element in bounds of the list.
     */
    @Test
    public void whenCalledGetShouldReturnElementValue() {
        String expected = "test";
        SimpleLinkedList<String> sll = new SimpleLinkedList<>();
        sll.add(expected);
        String result = sll.get(0);
        assertThat(result, is(expected));
    }

    /**
     * Tests method get() when index out of bounds of the list.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenCalledGedWithIndexOutOfBoundsShouldThrowsException() {
        SimpleLinkedList sll = new SimpleLinkedList();
        Object o = sll.get(1);
    }
}