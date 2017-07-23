package ru.job4j.start;

/**
 * The class represent stub for ConsoleInput that uses for testing cslasses
 * where need impur system.
 *
 * @author abondarev.
 * @since 23.07.2017.
 */
public class StubInput implements Input {

	/**
	 * The list of answers.
	 */
	private String[] answers;

	/**
	 * The position current returning answer.
	 */
	private int position = 0;

	/**
	 * The constructor takes as parameter list of answers needed for test.
	 *
	 * @param answers list of answers.
	 */
	public StubInput(String[] answers) {
		this.answers = answers;
	}

	/**
	 * <@inheritedDoc>.
	 *
	 * @param question is a string outputs for user.
	 * @return a string represents user input.
	 */
	public String ask(String question) {
		return answers[position++];
	}
}