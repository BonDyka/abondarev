package ru.job4j.maps;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Tests adding two object of User to Map with overriding methods hashCode()
 * and methods equals() for them.
 *
 * @author abondarev.
 * @since 08.09.2017.
 */
public class UserTest {

    /**
     * Tests adding to HashMap.
     */
    @Test
    public void whenAddsUsersShouldBeAdded() {
        User first = new User("Alex", new GregorianCalendar(1985, 6, 25));
        User second = new User("Alex", new GregorianCalendar(1985, 6, 25));
        Map<User, Object> map = new HashMap<>();

        map.put(first, new Object());
        map.put(second, new Object());

        System.out.println(map);
    }
}