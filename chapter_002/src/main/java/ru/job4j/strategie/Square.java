package ru.job4j.strategie;

/**
 * The class inlements concrete shape as square.
 *
 * @author abondarev.
 * @since 23.07.2017.
 */
public class Square implements Shape {

	/**
	 * The method return string that repesent the square.
	 *
	 * @return a string represents the square.
	 */
	public String pic() {
		String line = System.getProperty("line.separator");
		StringBuilder builder = new StringBuilder();
		builder.append("*****").append(line)
			   .append("*****").append(line)
			   .append("*****").append(line)
			   .append("*****").append(line)
			   .append("*****").append(line);
		return builder.toString();
	}
}