package ru.job4j.convert;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests class UserConvert.
 *
 * @author abondarev.
 * @since 19.08.2017.
 */
public class UserConvertTest {

    /**
     * Tests converting list to map.
     */
    @Test
    public void convertIntoMap() {
        User user1 = new User(5241, "Ivan", "Moscow");
        User user2 = new User(5648, "Ivan", "Moscow");
        List<User> list = Arrays.asList(user1, user2);
        UserConvert converter = new UserConvert();

        HashMap<Integer, User> expect = new HashMap<>();
        expect.put(user1.getId(), user1);
        expect.put(user2.getId(), user2);
        HashMap<Integer, User> result = converter.process(list);

        assertThat(result, is(expect));
    }
}