package ru.job4j.max;

/**
 * The class created for maximum of two numbers.
 *
 * @author abondarev
 * @since 11.07.2017
 */
public class Max {
	/**
	 * The method calculate maximum of two numbers.
	 *
	 * @param first is the first argument.
	 * @param second is the second argument.
	 * @return max of two numbers.
	 */
	public int max(int first, int second) {
		return (first > second) ? first : second;
	}

	/**
	 * The method calculates maximum of three numbers.
	 *
	 * @param first is the first argument.
	 * @param second is the second argument.
	 * @param third is the second argument.
	 * @return max of three numbers.
	 */
	public int max(int first, int second, int third) {
		return this.max(first, this.max(second, third));
	}
}