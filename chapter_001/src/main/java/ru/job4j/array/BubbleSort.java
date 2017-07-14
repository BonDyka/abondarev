package ru.job4j.array;

/**
 * The class shows sorting arrays.
 *
 * @author abondarev.
 * @since 14.07.2017.
 */
public class BubbleSort {

	/**
	 * The method sorts an array specified as parameter.
	 *
	 * @param array unsorted array.
	 * @return sorted array.
	 */
	public int[] sort(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length - 1; j++) {
				if (array[j] > array[j + 1]) {
					int tmp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = tmp;
				}
			}
		}
		return array;
	}
}