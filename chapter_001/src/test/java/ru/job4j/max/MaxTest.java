package ru.job4j.max;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The class created for testing a behaviour of Max class.
 *
 * @author abondarev
 * @since 11.07.2017
 */
public class MaxTest {

	/**
	 * The method tests method max() when first argument biger than second.
	 */
	@Test
	public void whenFirstBigerThanSecondThenReturnFirst() {
		Max max = new Max();
		int first = 5;
		int second = 3;
		int result = max.max(first, second);
		int expected = first;
		assertThat(result, is(expected));
	}

	/**
	 * The method tests method max() when first argument lesser than second.
	 */
	@Test
	public void whenFirstlesserThanSecondThenReturnSecond() {
		Max max = new Max();
		int first = 3;
		int second = 5;
		int result = max.max(first, second);
		int expected = second;
		assertThat(result, is(expected));
	}
}