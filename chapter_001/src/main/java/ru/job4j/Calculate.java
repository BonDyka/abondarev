package ru.job4j;
/**
 * Class Calculate. The solution task from chapter_001 lesson 1(JVM. Byte code).
 * 
 * @author aondarev
 * @since 08.07.2017
 */
public class Calculate {
	/**
	 * The method takes a string parameter and return new string where value of the parameter
	 * is represent three times through spaces.
	 *
	 * @param value a string for changing.
	 * @return String represent value three times throug spaces.
	 */
	public String echo(String value) {
		return String.format("%s %s %s", value,  value, value);
	}
}