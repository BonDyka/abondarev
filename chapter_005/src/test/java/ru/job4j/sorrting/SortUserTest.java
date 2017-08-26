package ru.job4j.sorrting;

import org.junit.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;

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

    /**
     * Tests method sortByNameLength().
     */
    @Test
    public void sortByNameLength() {
        List<User> list = Arrays.asList(new User("Tom", 24), new User("Barry", 10),
                                        new User("Jim", 22), new User("John", 31)
        );
        SortUser filter = new SortUser();
        List<User> expect = new ArrayList<>();
        expect.add(list.get(0));
        expect.add(list.get(2));
        expect.add(list.get(3));
        expect.add(list.get(1));
        List<User> result = filter.sortByNameLength(list);
        assertThat(result, is(expect));
    }

    /**
     * Tests method sort().
     */
    @Test
    public void sortByAllFields() {
        List<User> list = Arrays.asList(new User("Tom", 24), new User("John", 10),
                new User("Jim", 22), new User("Tony", 31)
        );
        SortUser filter = new SortUser();
        List<User> expect = new ArrayList<>();
        expect.add(list.get(2));
        expect.add(list.get(0));
        expect.add(list.get(1));
        expect.add(list.get(3));
        List<User> result = filter.sortByAllFields(list);
        assertThat(result, is(expect));
    }
}