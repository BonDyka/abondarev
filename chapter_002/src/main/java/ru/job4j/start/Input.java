package ru.job4j.start;

/**
 * The interface represent main contract for all input for the application.
 *
 * @author abondarev.
 * @since 22.07.2017.
 */
public interface Input {

	/**
	 * The method outputs question, specified as parameter, for user and return
	 * a string that represent answer.
	 *
	 * @param question is string for output.
	 * @return a string thet represent the input data.
	 */
	String ask(String question);
}