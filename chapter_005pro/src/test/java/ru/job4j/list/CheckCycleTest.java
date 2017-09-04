package ru.job4j.list;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * The class created for testing CheckCycle class.
 *
 * @author abondarev.
 * @since 04.09.2017
 */
public class CheckCycleTest {

    /**
     * Test method hasCycle() when cycle exist.
     */
    @Test
    public void whenListHasCycleShouldReturnTrue() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);

        first.setNext(second);
        second.setNext(third);
        third.setNext(four);
        four.setNext(first);

        CheckCycle checker = new CheckCycle();

        boolean result = checker.hasCycle(first);
        assertTrue(result);
    }

    /**
     * Test method hasCycle() when there is no cycle.
     */
    @Test
    public void whenListHasNoCycleShouldReturnTrue() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);

        first.setNext(second);
        second.setNext(third);
        third.setNext(four);

        CheckCycle checker = new CheckCycle();

        boolean result = checker.hasCycle(first);
        assertFalse(result);
    }
}