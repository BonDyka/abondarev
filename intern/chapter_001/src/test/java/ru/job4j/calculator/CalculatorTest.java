package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The class created for testing behaviour of Calculator class.
 *
 * @author abondarev
 * @since 10.07.2017
 */
public class CalculatorTest {

	/**
	 * The method tests action getResult without any arithmetical calculation.
	 */
	@Test
	public void whenCallGetResultWithoutCalculationThenReturnZero() {
		Calculator calc = new Calculator();
		double expected = 0D;
		double result = calc.getResult();
		assertThat(result, is(expected));
	}

	/**
	 * The method test operation addition for calculator class.
	 */
	@Test
	public void whenAddOneAndOneThenResultTwo() {
		Calculator calc = new Calculator();
		calc.add(1D, 1D);
		double result = calc.getResult();
		double expected = 2D;
		assertThat(result, is(expected));
	}

	/**
	 * The method test operation subtraction for calculator class.
	 */
	@Test
	public void whenSubOneFromThreeThenResultTwo() {
		Calculator calc = new Calculator();
		calc.sub(3D, 1D);
		double result = calc.getResult();
		double expected = 2D;
		assertThat(result, is(expected));
	}

	/**
	 * The method test operation multiplication for calculator class.
	 */
	@Test
	public void whenMultOneAndOneThenresultOne() {
		Calculator calc = new Calculator();
		calc.mult(1D, 1D);
		double result = calc.getResult();
		double expected = 1D;
		assertThat(result, is(expected));
	}

	/**
	 * The method test operation division for calculator class.
	 */
	@Test
	public void whenDivForOnTwoThenresultTwo() {
		Calculator calc = new Calculator();
		calc.div(4D, 2D);
		double result = calc.getResult();
		double expected = 2D;
		assertThat(result, is(expected));
	}
}