package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The class tests the triangle class.
 *
 * @author abondarev.
 * @since 11.07.2017.
 */
public class TriangleTest {

	/**
	 * The method tests area() method of the triangle class.
	 */
	@Test
	public void whenCallAreaThenMustReturnRightValueOfArea() {
		Point a = new Point(1, -2);
		Point b = new Point(-3, -2);
		Point c = new Point(1, 5);
		Triangle triangle = new Triangle(a, b, c);
		double result = triangle.area();
		double expected = 14D;
		assertThat(result, closeTo(expected, 0.1));
	}

	/**
	 * The method test area() method when the triangle doesen't exist.
	 */
	@Test
	public void whenTriangleIsNotExistThenAreaReturnNegativeOne() {
		Triangle triangle = new Triangle(new Point(1, 1), new Point(2, 2),
										new Point(3, 3));
		double result = triangle.area();
		double expected = -1D;
		assertThat(result, closeTo(expected, 0.1));
	}

	/**
	 * The method test isExist() method of the triangle when it exist.
	 */
	@Test
	public void whenTriangleIsExistThenReturnTrue() {
		Triangle triangle = new Triangle(new Point(1, 2), new Point(4, 2),
										new Point(3, -3));
		boolean result = triangle.isExist();
		boolean expected = true;
		assertThat(result, is(expected));
	}

	/**
	 * The method test isExist() method of the triangle when it exist.
	 */
	@Test
	public void whenTriangleIsNotExistThenReturnFalse() {
		Triangle triangle = new Triangle(new Point(1, 1), new Point(2, 2),
										new Point(3, 3));
		boolean result = triangle.isExist();
		boolean expected = false;
		assertThat(result, is(expected));
	}

	/**
	 * The method tests getLongSide() method of the triangle.
	 */
	@Test
	public void whenCallGetLongSideThenReturnItsActualSize() {
		Point a = new Point(1, -2);
		Point b = new Point(-3, -2);
		Point c = new Point(1, 5);
		Triangle triangle = new Triangle(a, b, c);
		double result = triangle.getLongSide(a, b);
		double expected = 4D;
		assertThat(result, closeTo(expected, 0.1));
	}
}