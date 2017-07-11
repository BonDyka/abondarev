package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.number.IsCloseTo.closeTo;
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
}