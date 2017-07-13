package ru.job4j.loop;

/**
 * The class shows a loop in action.
 *
 * @author abondarev.
 * @since 13.07.2017.
 */
public class Counter {

	/**
	 * The method calculate sum all even numbers in range.
	 *
	 * @param start is start limit of the range
	 * @param end is top limit of the range.
	 * @return value of sum of all even numbers in range.
	 */
	public int sumEven(int start, int end) {
		int result = 0;
		for (int index = 0; index <= end; index++) {
			if (index % 2 == 0) {
				result += index;
			}
		}
		return result;
	}
}