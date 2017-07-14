package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *
 */
public class BubbleSortTest {

	/**
	 *
	 */
	@Test
	public void whenSortUnsortedArrayThenReturnSortedArray() {
		BubbleSort sort = new BubbleSort();
		int[] result = sort.sort(new int[] {2, 9, 5, 7, 3, 8});
		int[] expected = new int[] {2, 3, 5, 7, 8, 9};
		assertThat(result, is(expected));
	}
}