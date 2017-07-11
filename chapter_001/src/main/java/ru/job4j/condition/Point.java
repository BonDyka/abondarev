package ru.job4j.condition;

/**
 * The class describes point in two-dimensional space.
 *
 * @author abondarev
 * @since 11.07.2017
 */
public class Point {
	/**
	 * The variable hold value of x coordinate of point in two-dimentional space.
	 */
	private int x;

	/**
	 * The variable hold value of y coordinate of point in two-dimentional space.
	 */
	private int y;

	/**
	 * The constructor that creates object of point with pointed as parameter
	 * coordinates.
	 *
	 * @param x value of x coordinate.
	 * @param y value of y coordinate.
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * The method return value of x coordinate.
	 *
	 * @return value of x coordinate.
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * The method return value of y coordinate.
	 *
	 * @return value of y coordinate.
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * The method determines wheter a point is on function y(x) = a *x + b or not.
	 * Takes as parameters a and b function coefficients. Return <i>true</i> if
	 * the point laid on the function and false otherwise.
	 *
	 * @param a is the a coefficient of function.
	 * @param b is the a coefficient of function.
	 * @return <i>true</i> if the point laid on the function and <i>false</i>
	 *		   otherwise.
	 */
	public boolean is(int a, int b) {
		return this.y == a * this.x + b;
	}
}