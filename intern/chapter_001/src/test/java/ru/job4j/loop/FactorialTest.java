package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The class created for testing the Factorial class.
 *
 * @author abondarev.
 * @since 13.07.2017.
 */
public class FactorialTest {

	/**
	 * The method tests calc() method when parameter is five.
	 */
	@Test
	public void whenNumberFiveThenReturnOneHundertTwenty() {
		Factorial factorial = new Factorial();
		int result = factorial.calc(5);
		int expected = 120;
		assertThat(result, is(expected));
	}

	/**
	 * The method tests calc() method when parameter is zero.
	 */
	@Test
	public void whenNumberIsZeroThenReturnOne() {
		Factorial factorial = new Factorial();
		int result = factorial.calc(0);
		int expected = 1;
		assertThat(result, is(expected));
	}
}