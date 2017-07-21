package ru.job4j.base;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The class created for testing MergeSort class.
 *
 * @author abondarev.
 * @since 21.07.2017.
 */
public class MergeSortTest {

	/**
	 * The method tests sort() method from the MergeSort class.
	 */
	@Test
	public void whenTwoSortedArrayPassedThenMergeThemInSortedOrder() {
		MergeSort ms = new MergeSort();
		int[] result = ms.sort(new int[] {1, 3, 6}, new int[] {2, 4, 5});
		int[] expected = new int[] {1, 2, 3, 4, 5, 6};
		assertThat(result, is(expected));
	}
}