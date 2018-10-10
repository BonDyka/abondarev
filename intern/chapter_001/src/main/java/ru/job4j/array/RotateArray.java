package ru.job4j.array;

/**
 * The class shows work with arrays on exersize rotate.
 *
 * @author abondarev.
 * @since 15.07.2017.
 */
public class RotateArray {

	/**
	 * The method takes a two-dimention array and execute clockwise rotation.
	 *
	 * @param array is array for rotation.
	 * @return rotated array.
	 */
	public int[][] rotate(int[][] array) {
		int barier = array.length;
		for (int i = 0; i < barier / 2; i++) {
			for (int j = 0; j < barier - 1; j++) {
				//the temporally varyable for rotating values of cell of the array.
				int tmp = array[i][j];
				array[i][j] = array[barier - j - 1][i];
				array[barier - j - 1][i] = array[barier - i - 1][barier - j - 1];
				array[barier - i - 1][barier - j - 1] = array[j][barier - i - 1];
				array[j][barier - i - 1] = tmp;
			}
		}
		return array;
	}
}