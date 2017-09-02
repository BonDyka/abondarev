package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created for testing class DynamicArray.
 *
 * @author abondarev.
 * @since 02.09.2017.
 */
public class DynamicArrayTest {

    /**
     * Test method add().
     */
    @Test
    public void whenAddElementThenSizeIncreaseByOne() {
        DynamicArray<Integer> da = new DynamicArray<>();
        int startSize = da.size();
        da.add(4);
        int result = da.size();
        assertThat(result, is(startSize + 1));
    }

    /**
     * Test method get() when element index in bounds of container.
     */
    @Test
    public void whenCalledGetInBoundsOfContainerShouldReturnElement() {
        DynamicArray<String> da = new DynamicArray<>();
        da.add("element");
        String result = da.get(0);
        assertThat(result, is("element"));
    }

    /**
     * Tests method get() when index out of bounds of the container.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenIndexOurOfBoundsShouldThrowsException() {
        DynamicArray<String> da = new DynamicArray<>();
        da.add("element");
        String result = da.get(1);
    }

    /**
     * Test dynamics of container. If capacity not enough then capacity
     * should be increased.
     */
    @Test
    public void testDynamicsOfContainer() {
        DynamicArray<Integer> da = new DynamicArray<>(1);
        da.add(3);
        int size = da.size();
        da.add(53);
        int result = da.size();
        assertThat(result, is(size + 1));
    }
}