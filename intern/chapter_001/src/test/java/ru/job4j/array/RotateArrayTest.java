package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The class created for testing RotateArray class.
 *
 * @author abondarev.
 * @since 15.07.2017.
 */
public class RotateArrayTest {

	/**
	 * The method tests the rotate() method that takes as parameter two-dimention
	 * array with size two on two.
	 */
	@Test
	public void whenRotateArrayWithTwoOnTwoSizeThenRotatedArray() {
		RotateArray ra = new RotateArray();
		int[][] result = ra.rotate(new int[][] {new int[] {1, 2},
												new int[] {3, 4}});
		int[][] expected = new int[][] {new int[] {3, 1},
										new int[] {4, 2}};
		assertThat(result, is(expected));
	}

	/**
	 * The method tests the rotate() method that takes as parameter two-dimention
	 * array with size three on thre.
	 */
	@Test
	public void whenRotateArrayWithThreeOnThreeSizeThenRotatedArray() {
		RotateArray ra = new RotateArray();
		int[][] result = ra.rotate(new int[][] {new int[] {1, 2, 3},
												new int[] {4, 5, 6},
												new int[] {7, 8, 9}});
		int[][] expected = new int[][] {new int[] {7, 4, 1},
										new int[] {8, 5, 2},
										new int[] {9, 6, 3}};
		assertThat(result, is(expected));
	}
}