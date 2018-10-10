package ru.job4j.inheritance;

/**
 * The class describes a engineer.
 *
 * @author abondarev.
 * @since 21.07.2017.
 */
public class Engineer extends Profession {

	/**
	 * The constructor takes four parameter and based on them created a engineer
	 * object.
	 *
	 * @param name is engineer name.
	 * @param age is engineer age.
	 * @param job is engineer job.
	 * @param education is a engineer education.
	 */
	public Engineer(String name, int age, String job, String education) {
		super(name, age, job, education);
	}

	/**
	 * The constructor takes four parameter and based on them created a engineer
	 * object.
	 *
	 * @param name is engineer name.
	 * @param age is engineer age.
	 * @param job is engineer job.
	 * @param education is a engineer education.
	 * @param expirience is a engineer expirience.
	 */
	public Engineer(String name, int age, String job, String education, int expirience) {
		super(name, age, job, education, expirience);
	}

	/**
	 * The method describes a project develop.
	 *
	 * @return a string what a project is develop.
	 */
	public String develop() {
		return String.format("Engineer %s develop a project", this.getName());
	}
}