package ru.job4j.loop;

/**
 * The class shows loop in action for factorial calculate.
 *
 * @author abondarev.
 * @since 13.07.2017.
 */
public class Factorial {

	/**
	 * The method calculates factorial of n number. 6 min
	 *
	 * @param n is the number for which calculates factorial.
	 * @return value of the factorial.
	 */
	public int calc(int n) {
		int result = 1;
		for (int i = 1; i <= n; i++) {
			result *= i;
		}
		return result;
	}
}