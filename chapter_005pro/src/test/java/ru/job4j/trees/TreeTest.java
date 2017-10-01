package ru.job4j.trees;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created for testing the Tree class.
 *
 * @author abondarev.
 * @since 01.10.2017.
 */
public class TreeTest {

    /**
     * Test method add() with empty tree.
     */
    @Test
    public void whenAddUniqueElementItShouldBeAdded() {
        Tree<Integer> itree = new Tree<>();
        int startSize = itree.size();
        itree.add(1, 2);
        int result = itree.size();

        assertThat(result, is(startSize + 2));
    }

    /**
     * Test add() non-unique element.
     */
    @Test
    public void whenAddNonUniqueElementItShouldNotAdded() {
        Tree<Integer> itree = new Tree<>();
        int startSize = itree.size();
        itree.add(1, 1);
        int result = itree.size();

        assertThat(result, is(startSize + 1));
    }
}