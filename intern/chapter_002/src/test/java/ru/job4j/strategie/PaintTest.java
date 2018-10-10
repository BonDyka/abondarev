package ru.job4j.strategie;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The class created for testing Paint class.
 *
 * @author abondarev.
 * @since 23.07.2017.
 */
public class PaintTest {

	/**
	 * The method tests draw() method when paint created with Triangle.
	 */
	@Test
	public void whenPaintCreatedWithTriangleThenDrawTriangle() {
		String line = System.getProperty("line.separator");
		StringBuilder builder = new StringBuilder();
		String triangle = builder.append("    *").append(line)
								 .append("   **").append(line)
								 .append("  ***").append(line)
								 .append(" ****").append(line)
								 .append("*****").append(line)
								 .append(line).toString();


		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		new Paint(new Triangle()).draw();
		assertThat(out.toString(), is(triangle));
	}

	/**
	 * The method tests draw() method when paint created with Square.
	 */
	@Test
	public void whenPaintCreatedWithSquareThenDrawSquare() {
		String line = System.getProperty("line.separator");
		StringBuilder builder = new StringBuilder();
		String square = builder.append("*****").append(line)
								 .append("*****").append(line)
								 .append("*****").append(line)
								 .append("*****").append(line)
								 .append("*****").append(line)
								 .append(line).toString();


		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		new Paint(new Square()).draw();
		assertThat(out.toString(), is(square));
	}
}