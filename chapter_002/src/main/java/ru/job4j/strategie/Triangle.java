package ru.job4j.strategie;

/**
 * The class inlements concrete shape as triangle.
 *
 * @author abondarev.
 * @since 23.07.2017.
 */
public class Triangle implements Shape {

	/**
	 * The method return string that repesent the triangle.
	 *
	 * @return a string represents the triangle.
	 */
	public String pic() {
		String line = System.getProperty("line.separator");
		StringBuilder builder = new StringBuilder();
		builder.append("    *").append(line)
			   .append("   **").append(line)
			   .append("  ***").append(line)
			   .append(" ****").append(line)
			   .append("*****").append(line);
		return builder.toString();
	}
}