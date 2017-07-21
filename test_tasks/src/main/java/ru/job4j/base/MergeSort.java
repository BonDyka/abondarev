package ru.job4j.base;

/**
 * The class show a merging of two sorted arrays.
 *
 * @author abondarev.
 * @since 21.07.2017.
 */
public class MergeSort {

	/**
	 * The method takes two sorted arrays and merge them in one new sorted array.
	 *
	 * @param first is first sorted array.
	 * @param second is second sorted array.
	 * @return new sorted array contained all elemets from parameters arrays.
	 */
	public int[] sort(int[] first, int[] second) {
		int[] result = new int[first.length + second.length];
		// This pointers on element from parameters array.
		int fPointer = 0;
		int sPointer = 0;
		for (int index = 0; index < result.length; index++) {
			if (fPointer == first.length) {
				System.arraycopy(second, sPointer, result, index, second.length - sPointer);
				break;
			}
			if (sPointer == second.length) {
				System.arraycopy(first, fPointer, result, index, first.length - fPointer);
				break;
			}
			if (first[fPointer] < second[sPointer]) {
				result[index] = first[fPointer];
				fPointer++;
			} else {
				result[index] = second[sPointer];
				sPointer++;
			}
		}
		return result;
	}
}