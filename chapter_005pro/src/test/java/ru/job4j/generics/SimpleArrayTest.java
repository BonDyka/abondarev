package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created for testing SimpleArray class.
 *
 * @author abondarev.
 * @since 29.08.2017.
 */
public class SimpleArrayTest {

    /**
     * Tests method add().
     */
    @Test
    public void whenAddElementThenSizeGrowByOne() {
        SimpleArray<Integer> sa = new SimpleArray<>(5);
        sa.add(2);
        int result = sa.size();
        assertThat(result, is(1));
    }

    /**
     * Tests method get().
     */
    @Test
    public void whenGetElementFromPositionShouldReturnThisElement() {
        SimpleArray<String> sa = new SimpleArray<>(3);
        sa.add("a");
        sa.add("b");
        sa.add("c");
        String result = sa.get(1);
        assertThat(result, is("b"));
    }

    /**
     * Tests method delete().
     */
    @Test
    public void whenDeleteElementThenSizeDecrees() {
        SimpleArray<Integer> sa = new SimpleArray<>(3);
        sa.add(5);
        sa.add(8);
        sa.add(4);
        int startSize = sa.size();
        sa.delete(0);
        int result = sa.size();
        assertThat(result, is(startSize - 1));
    }

    /**
     * Tests method update().
     */
    @Test
    public void whenUpdatedNonNullElementItShouldBeUpdated() {
        SimpleArray<String> sa = new SimpleArray<>(3);
        sa.add("a");
        sa.add("b");
        sa.add("c");
        sa.update(1, "new");
        String result = sa.get(1);
        assertThat(result, is("new"));
    }
}