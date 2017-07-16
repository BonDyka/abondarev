package ru.job4j.array;

import java.util.Arrays;

/**
 * The class show removing duplicates from array.
 *
 * @author abondarev.
 * @since 16.07.2017.
 */
public class ArrayDuplicate {

	/**
	 * The method takes an array, checks it and if it have any duplicates remove
	 * them and return an array without duplicates.
	 *
	 * @param array original array.
	 * @return array without duplicates.
	 */
	public String[] remove(String[] array) {
		// The variable counts how many duplicates in the array.
		int counter = 0;
		for (int i = 0; i < array.length - counter - 1; i++) {
			String origin = array[i];
			for (int j = i + 1; j < array.length - counter; j++) {
				if (array[j].equals(origin)) {
					System.arraycopy(array, j + 1, array, j, array.length - j - 1);
					counter++;
					array[array.length - 1] = origin;
				}
			}
		}
		return Arrays.copyOf(array, array.length - counter);
	}
}