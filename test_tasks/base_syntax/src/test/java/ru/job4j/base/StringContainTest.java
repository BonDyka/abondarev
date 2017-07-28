package ru.job4j.base;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The class created for testing StringContain class behaviour.
 *
 * @author abondarev.
 * @since 16.07.2017.
 */
public class StringContainTest {

	/**
	 * The method tests contains() method when the origin string contains substring.
	 */
	@Test
	public void whenOriginContainsSubThenTrue() {
		StringContain sc = new StringContain();
		boolean result = sc.contains("Привет", "иве");
		boolean expected = true;
		assertThat(result, is(expected));
	}

	/**
	 * The method tests contains() method when the origin string do not contains
	 * substring.
	 */
	@Test
	public void whenOriginNotContainsSubThenFalse() {
		StringContain sc = new StringContain();
		boolean result = sc.contains("Привет", "иtе");
		boolean expected = false;
		assertThat(result, is(expected));
	}
}