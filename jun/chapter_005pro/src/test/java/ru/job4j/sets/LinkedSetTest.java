package ru.job4j.sets;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created for testing LinkedSet class.
 *
 * @author abondarev.
 * @since 06.09.2017.
 */
public class LinkedSetTest {

    /**
     * Test method add() that adds unique elements.
     */
    @Test
    public void whenAddTwoTheSameElementsShouldBeAddedOne() {
        Set<String> set = new LinkedSet<>();
        set.add("test");
        set.add("test");
        Iterator<String> it = set.iterator();
        it.next();
        boolean result = it.hasNext();

        assertThat(result, is(false));
    }
}