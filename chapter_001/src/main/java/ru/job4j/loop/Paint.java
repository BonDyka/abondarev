package ru.job4j.loop;

/**
 * The class represent pseudo-graphical painter.
 *
 * @author abondarev.
 * @since 14.07.2017.
 */
public class Paint {

	/**
	 * The method paint a piramid with specified as parameter height.
	 *
	 * @param h is height of the piramid.
	 * @return a string that rapresent the piramid.
	 */
	public String piramid(int h) {
		StringBuilder result = new StringBuilder();
		String line = System.getProperty("line.separator");
		// The dependency ratio width of piramid from height.
		int k = h * 2 - 1;
		for (int i = 0; i < h; i++) {
			for (int j = k - 1; j >= 0; j--) {
				if (i != h - 1 && j < k / 2 - i || j > k / 2 + i) {
					result.append(" ");
				} else {
					result.append("^");
				}
			}
			result.append(line);
		}
		return result.toString();
	}
}