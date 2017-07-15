package ru.job4j.loop;

/**
 * The class represent cell-board.
 *
 * @author abondarev
 * @since 13.07.2017.
 */
public class Board {

	/**
	 * The method paints cell board in pseudo-graphics.
	 *
	 * @param width is width of the board.
	 * @param height is height of the board.
	 * @return a string that represent the board.
	 */
	public String paint(int width, int height) {
		StringBuilder result = new StringBuilder();
		String newLine = System.getProperty("line.separator");
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if ((i + j) % 2 == 0) {
					result.append("X");
				} else {
					result.append(" ");
				}
			}
			result.append(newLine);
		}
		return result.toString();
	}
}