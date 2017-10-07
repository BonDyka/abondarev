package ru.job4j.trees;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created for testing SearchBinaryTree class.
 *
 * @author abondarev.
 * @since 07.10.2017.
 */
public class SearchBynaryTreeTest {

    /**
     * Tests method add().
     */
    @Test
    public void whenAddElementSizeShouldBeIncrease() {
        SearchBinaryTree<Integer> tree = new SearchBinaryTree<>();
        int startSise = tree.size();
        tree.add(5);
        int result = tree.size();

        assertThat(result, is(startSise + 1));
    }

    /**
     * Tests iterator().
     */
    @Test
    public void whenCalledIteratorShouldBeReturnedIteratorForTheTree() {
        String elem = "Test";
        SearchBinaryTree<String> tree = new SearchBinaryTree<>();
        tree.add(elem);
        Iterator<String> it = tree.iterator();
        String result = it.next();
        assertThat(result, is(elem));
    }
}