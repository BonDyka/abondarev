package ru.job4j.start;

import java.util.Scanner;

/**
 * The class created for reading user input from console.
 *
 * @author abondarev.
 * @since 22.07.2017.
 */
public class ConsoleInput implements Input {

	/**
	 * The instance of input from standard java tools.
	 */
	private Scanner scanner = new Scanner(System.in);

	/**
	 * <@inheriteDoc>.
	 *
	 * @param question is a string for output for user.
	 * @return a string that represent input to console from user.
	 */
	public String ask(String question) {
		System.out.print(question);
		return scanner.nextLine();
	}
}