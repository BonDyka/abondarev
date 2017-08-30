package ru.job4j.coverage;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created for testing MaxCoverage class.
 *
 * @author abondarev.
 * @since 30.08.2017.
 */
public class MaxCoverageTest {

    /**
     * Test method areaCoverage() when matrix has two areas.
     */
    @Test
    public void whenMatrixHasTwoAreasShouldReturnGreatestAreaBetweenThem() {
        int[][] matrix = new int[][] {
                                        new int[] {0, 1, 0, 0, 0, 0},
                                        new int[] {0, 1, 1, 0, 1, 1},
                                        new int[] {0, 0, 0, 1, 0, 1},
                                        new int[] {0, 1, 1, 1, 1, 1},
                                        new int[] {0, 0, 0, 1, 0, 0},
                                        new int[] {0, 0, 0, 0, 0, 0}
                        };
        MaxCoverage max = new MaxCoverage(matrix);
        int result = max.areaCoverage();
        int expect = 10;
        assertThat(result, is(expect));
    }
}