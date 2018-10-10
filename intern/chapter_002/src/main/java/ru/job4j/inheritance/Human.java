package ru.job4j.inheritance;

/**
 * The class represent a human.
 *
 * @author abondarev.
 * @since 21.07.2017
 */
public class Human {

	/**
	 * The variable holds name of human.
	 */
	private String name;

	/**
	 * The variable holds name of human.
	 */
	private int age;

	/**
	 * The constructor takes two parameters and based on it creates an instance
	 * of a human.
	 *
	 * @param name is name of the human.
	 * @param age is age of the human.
	 */
	public Human(String name, int age) {
		this.name = name;
		this.age = age;
	}

	/**
	 * The method return value of the name of the human.
	 *
	 * @return name of the human.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * The method return value of the age of the human.
	 *
	 * @return age of the human.
	 */
	public int getAge() {
		return this.age;
	}
}