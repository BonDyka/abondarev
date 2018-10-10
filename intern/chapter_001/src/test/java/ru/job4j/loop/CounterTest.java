package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The class created for testing the Counter class.
 *
 * @author abondarev.
 * @since 13.07.2017.
 */
public class CounterTest {

	/**
	 * The method tests sumEven() method of the Counter class.
	 */
	@Test
	public void whenRangeLimitTenThenReturnThirty() {
		Counter counter = new Counter();
		int result = counter.sumEven(0, 10);
		int expected = 30;
		assertThat(result, is(expected));
	}
}