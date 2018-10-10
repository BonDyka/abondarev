package ru.job4j.sets;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Test Compare class.
 */
public class SpeedCompareTest {

    /**
     * Test method compare().
     */
    @Test
    public void whenCompareTwoImplementationSetThenOneMustBeSpeeder() {
        SpeedCompare comparer = new SpeedCompare();

        int result = comparer.compare(new ArraySet<>(), new SpeedArraySet<>(), 10_000);
        System.out.println(result);
        assertTrue(result > 0);
    }
}