package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The class tests a behaviour of ArrayDuplicate class.
 *
 * @author abondarev.
 * @since 16.07.2017.
 */
public class ArrayDuplicateTest {

	/**
	 *
	 */
	@Test
	public void whenArrayHasDuplicatesThenRemoveThem() {
		ArrayDuplicate ad = new ArrayDuplicate();
		String[] result = ad.remove(new String[] {"Hi", "Hello", "Hello", "Peace", "Hi"});
		String[] expected = new String[] {"Hi", "Hello", "Peace"};
		assertThat(result, is(expected));
	}
}