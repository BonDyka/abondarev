package ru.job4j.convert;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests all methods of ConvertList class.
 *
 * @author abondarev.
 * @since 19.08.2017.
 */
public class ConvertListTest {

    /**
     * Test converting two-dimensional array into list.
     */
    @Test
    public void convertToList() {
        int[][] array = new int[][]{
                new int[]{1, 2, 3},
                new int[]{4, 5, 6}
        };
        ConvertList converter = new ConvertList();
        List<Integer> result = converter.toList(array);
        assertThat(result, is(asList(1, 2, 3, 4, 5, 6)));
    }

    /**
     * Test conversion list to two-dimension array.
     */
    @Test
    public void convertToArray() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        ConvertList converter = new ConvertList();
        int[][] result = converter.toArray(list);

        int[][] expected = new int[][]{
                new int[]{1, 2, 3},
                new int[]{4, 5, 6}
        };
        assertThat(result, is(expected));
    }
}