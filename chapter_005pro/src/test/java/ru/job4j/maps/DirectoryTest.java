package ru.job4j.maps;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests functionality of Directory class.
 *
 * @author abondarev.
 * @since 24.09.2017.
 */
public class DirectoryTest {

    /**
     * Tests method insert() and get().
     */
    @Test
    public void whenInsertKeyAndValueShouldBeAdded() {
        String key = "task";
        String value = "test";
        Directory<String, String> directory = new Directory<>();
        directory.insert(key, value);
        String result = directory.get(key);
        assertThat(result, is(value));
    }

    /**
     * Tests method insert() and delete().
     */
    @Test
    public void whenDeleteElementByItKeyShouldDelete() {
        String key = "task";
        String value = "test";
        Directory<String, String> directory = new Directory<>();
        directory.insert(key, value);
        int size = directory.size();
        directory.delete(key);
        int result = directory.size();
        assertThat(result, is(size - 1));
    }

    /**
     * Test time of insert.
     */
    @Test
    public void whenInsertNumberOfElementsTimeOfInsertShouldBeConstant() {
        String key = "task";
        String key2 = "two";
        String value = "test";
        Directory<String, String> directory = new Directory<>();
        long start = System.currentTimeMillis();
        directory.insert(key, value);
        long end = System.currentTimeMillis();
        long firstT = end - start;
        start = System.currentTimeMillis();
        directory.insert(key2, value);
        end = System.currentTimeMillis();
        long secondT = end - start;
        assertThat(firstT, is(secondT));
    }

    /**
     * Test time of insert.
     */
    @Test
    public void whenDeleteNumberOfElementsTimeOfDeleteShouldBeConstant() {
        String key = "task";
        String key2 = "two";
        String value = "test";
        Directory<String, String> directory = new Directory<>();
        directory.insert(key, value);
        directory.insert(key2, value);
        long start = System.currentTimeMillis();
        directory.delete(key);
        long end = System.currentTimeMillis();
        long firstT = end - start;
        start = System.currentTimeMillis();
        directory.delete(key2);
        end = System.currentTimeMillis();
        long secondT = end - start;
        assertThat(firstT, is(secondT));
    }
}