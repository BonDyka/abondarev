package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The class tests behaviour of the paint class.
 *
 * @author abondarev.
 * @since 14.07.2017.
 */
public class PaintTest {

	/**
	 * The method test piramid() method with specified height 3.
	 */
	@Test
	public void whenHeightOfPiramidTwoThenReturnStringTwoRows() {
		Paint paint = new Paint();
		String line = System.getProperty("line.separator");
		String result = paint.piramid(2);
		String expected = String.format(" ^ %s^^^%s", line, line);
		assertThat(result, is(expected));
	}

	/**
	 * The method test piramid() method with specified height 3.
	 */
    @Test
    public void whenPiramidWithHeightThreeThenStringWithThreeRows() {
		Paint paint = new Paint();
		String line = System.getProperty("line.separator");
		String result = paint.piramid(3);
		String expected = String.format("  ^  %s ^^^ %s^^^^^%s", line, line, line);
		assertThat(result, is(expected));
    }
}