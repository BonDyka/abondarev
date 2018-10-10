package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The class created for testing the Turn class.
 *
 * @author abondarev.
 * @since 14.07.2017.
 */
public class TurnTest {

	/**
	 * The method tests the back() method when array has odd amount of elements.
	 */
	@Test
	public void whenTurnedArrayWithOddAmountOfElementsThenThenTurnedArray() {
		Turn turn = new Turn();
		int[] result = turn.back(new int[] {1, 3, 5, 2, 4, 6, 9});
		int[] expected = new int[] {9, 6, 4, 2, 5, 3, 1};
		assertThat(result, is(expected));
	}

	/**
	 * The method tests the back() method when array has even amount of elements.
	 */
	@Test
	public void whenTurnedArrayWithEvenAmountOfElementsThenThenTurnedArray() {
		Turn turn = new Turn();
		int[] result = turn.back(new int[] {1, 3, 5, 2, 4, 6});
		int[] expected = new int[] {6, 4, 2, 5, 3, 1};
		assertThat(result, is(expected));
	}
}