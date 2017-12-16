package ru.job4j.question;


import org.junit.Test;

import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests methods from{@link MergeSort}.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 16.12.2017.
 */
public class MergeSortTest {

    @Test
    public void whenMergeTwoSortedListShouldGetOneSorted() {
        LinkedList<Integer> expected = new LinkedList<>();
        LinkedList<Integer> first = new LinkedList<>();
        LinkedList<Integer> second = new LinkedList<>();
        LinkedList<Integer> out = new LinkedList<>();
        for (int i = 0; i < 7; i++) {
            expected.add(i);
            if (i % 2 == 0) {
                first.add(i);
            } else {
                second.add(i);
            }
        }
        MergeSort.merge(first, second, out);
        assertThat(out, is(expected));
    }

    @Test
    public void whenSortListShouldBeSorted() {
        LinkedList<Integer> expected = new LinkedList<>();
        LinkedList<Integer> unsorted = new LinkedList<>();
        for (int i = 0; i < 7; i++) {
            expected.add(i);
            if (i % 2 == 0) {
                unsorted.addFirst(i);
            } else {
                unsorted.add(i / 2, i);
            }
        }
        MergeSort.sort(unsorted);
        assertThat(unsorted, is(expected));
    }
}