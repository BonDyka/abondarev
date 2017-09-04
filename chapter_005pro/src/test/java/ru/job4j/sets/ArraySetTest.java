package ru.job4j.sets;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created for testing ArraySet class.
 *
 * @author abondarev.
 * @since 04.09.2017.
 */
public class ArraySetTest {

    /**
     * Tests adding unique element.
     */
    @Test
    public void whenElementUniqueShouldBeAdded() {
        ArraySet<String> as = new ArraySet<>(1);
        as.add("test");
        String expect = "test";
        Iterator<String> it = as.iterator();
        String result = it.next();
        assertThat(result, is(expect));
    }

    /**
     * Tests adding element that already exist.
     */
    @Test
    public void whenElementNitUniqueThenItNotAdded() {
        ArraySet<String> as = new ArraySet<>(1);
        as.add("test");
        as.add("test");
        Iterator<String> it = as.iterator();
        it.next();
        boolean result = it.hasNext();
        assertThat(result, is(false));
    }
}