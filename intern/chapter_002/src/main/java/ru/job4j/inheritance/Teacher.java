package ru.job4j.inheritance;

/**
 * The class describes a teacher.
 *
 * @author abondarev.
 * @since 21.07.2017.
 */
public class Teacher extends Profession {

	/**
	 * The constructor takes four parameter and based on them created a teacher
	 * object.
	 *
	 * @param name is teacher name.
	 * @param age is teacher age.
	 * @param job is teacher job.
	 * @param education is a teacher education.
	 */
	public Teacher(String name, int age, String job, String education) {
		super(name, age, job, education);
	}

	/**
	 * The constructor takes four parameter and based on them created a teacher
	 * object.
	 *
	 * @param name is teacher name.
	 * @param age is teacher age.
	 * @param job is teacher job.
	 * @param education is a teacher education.
	 * @param expirience is a teacher expirience.
	 */
	public Teacher(String name, int age, String job, String education, int expirience) {
		super(name, age, job, education, expirience);
	}

	/**
	 * The method describes a treat a pacient.
	 *
	 * @param student is student for studying.
	 * @return a string shows what a teacher teache what a student.
	 */
	public String teache(Human student) {
		return String.format("Teacher %s teache %s", this.getName(), student.getName());
	}
}