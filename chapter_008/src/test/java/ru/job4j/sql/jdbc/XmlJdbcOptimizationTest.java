package ru.job4j.sql.jdbc;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Tests {@link XmlJdbcOptimization} for large values of n.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 02.01.2018.
 */
public class XmlJdbcOptimizationTest {

    @Test
    public void whenExecuteOptimizationTimeShouldBeLessFiveMinutes() {
        long expected = 5 * 60 * 1000;

        XmlJdbcOptimization optimization = new XmlJdbcOptimization();
        long result = optimization.execute();

        assertTrue(result < expected);
    }

}