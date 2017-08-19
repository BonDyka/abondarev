package ru.job4j.sorrting;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests SortUser class.
 *
 * @author abondarev.
 * @since 19.08.2017.
 */
public class SortUserTest {

    /**
     * Tests method sort().
     */
    @Test
    public void sortedSetFromList() {
        List<User> list = Arrays.asList(new User("tom", 24), new User("jim", 10));
        SortUser filter = new SortUser();
        Set<User> result = filter.sort(list);
        Set<User> expect = new TreeSet<>();
        expect.add(list.get(1));
        expect.add(list.get(0));
        assertThat(result, is(expect));
    }
}