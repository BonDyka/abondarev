package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *	The class createdfor test point task.
 *
 * @author abondarev
 * @since 11.07.2017
 */
public class PointTest {

	/**
	 * The method tests method getX() of point class
	 */
	@Test
	public void whenGetXThenReturnX() {
		int x = 2;
		int y = 5;

		Point point = new Point(x, y);

		int result = point.getX();
		int expected = x;

		assertThat(result, is(expected));
	}

	/**
	 * The method tests method getY() of point class
	 */
	@Test
	public void whenGetYThenReturnY() {
		int x = 2;
		int y = 5;

		Point point = new Point(x, y);

		int result = point.getY();
		int expected = y;

		assertThat(result, is(expected));
	}

	/**
	 * The method tests method is() of point class when it sould return <i>true</i>.
	 */
	@Test
	public void whenPointLaidOnThenReturnTrue() {
		Point point = new Point(2, 7);
		int a = 2;
		int b = 3;

		boolean result = point.is(a, b);
		boolean expected = true;

		assertThat(result, is(expected));
	}

	/**
	 * The method tests method is() of point class when it sould return <i>false</i>.
	 */
	@Test
	public void whenPointLaidOutThenReturnFalse() {
		Point point = new Point(2, 7);
		int a = 2;
		int b = 4;

		boolean result = point.is(a, b);
		boolean expected = false;

		assertThat(result, is(expected));
	}
}