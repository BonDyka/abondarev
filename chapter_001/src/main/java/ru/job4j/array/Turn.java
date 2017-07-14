package ru.job4j.array;

/**
 * The class shows actions with array.
 *
 * @author abondarev.
 * @since 14.07.2017.
 */
public class Turn {

	/**
	 * The method turn back an array that specified as parameter.
	 *
	 * @param array an array for changes.
	 * @return turned back array.
	 */
	public int[] back(int[] array) {
		for (int index = 0; index < array.length / 2; index++) {
			//A temporaly variable for save value arrae[index];
			int tmp = array[index];
			array[index] = array[array.length - index - 1];
			array[array.length - index - 1] = tmp;
		}
		return array;
	}
}