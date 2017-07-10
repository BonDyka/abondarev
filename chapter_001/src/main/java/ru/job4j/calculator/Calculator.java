package ru.job4j.calculator;

/**
 * The class represents symply calculator that can execute next arityhmetical
 * operations: addition, subtraction, multiplying and division.
 *
 * @author abondarev
 * @since 10.07.2017
 */
public class Calculator {

	/**
	 * The variable holds value of result of a calculation.
	 */
	private double result;

	/**
	 *	The method returns value of result of a calculation.
	 *
	 * @return value of result of a calculation.
	 */
	public double getResult() {
		return this.result;
	}

	/**
	 * The method execute addition aritmetical operation with two arguments.
	 *
	 * @param first is first parameter for calculation.
	 * @param second is second parameter for calculation.
	 */
	public void add(double first, double second) {
		this.result = first + second;
	}

	/**
	 * The method execute subtraction aritmetical operation with two arguments.
	 *
	 * @param first is first parameter for calculation.
	 * @param second is second parameter for calculation.
	 */
	public void sub(double first, double second) {
		this.result = first - second;
	}

	/**
	 * The method execute multiplication aritmetical operation with two arguments.
	 *
	 * @param first is first parameter for calculation.
	 * @param second is second parameter for calculation.
	 */
	public void mult(double first, double second) {
		this.result = first * second;
	}

	/**
	 * The method execute division aritmetical operation with two arguments.
	 *
	 * @param first is first parameter for calculation.
	 * @param second is second parameter for calculation.
	 */
	public void div(double first, double second) {
		this.result = first / second;
	}
}