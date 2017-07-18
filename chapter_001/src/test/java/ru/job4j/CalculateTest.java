package ru.job4j;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The class for testing Calculate class methods.
 *
 * @author abondarev
 * @since 09.07.2017
 */
public class CalculateTest {
	/**
	 * Test for main method from Calculate class.
	 */
	@Test
	public void whenAddOneToOneThenTwo() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Calculate.main(null);
		assertThat(out.toString(), is("Hello, world!\r\n"));
	}

	/**
	 * Test for method echo when it takes string as parameter.
	 */
	@Test
	public void whenSetValueThenReturnItThreeTimeThroughSpace() {
		String value = "stop";
		Calculate calc = new Calculate();
		String result = calc.echo(value);
		assertThat(result, is("stop stop stop"));
	}

	/**
	 * Test for method echo when it takes null as parameter.
	 */
	@Test
	public void whenSetNullThenReturnItThreeTimeThroughSpace() {
		Calculate calc = new Calculate();
		String result = calc.echo(null);
		assertThat(result, is("null null null"));
	}


}