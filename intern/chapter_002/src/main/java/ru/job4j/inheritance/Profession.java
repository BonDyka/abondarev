package ru.job4j.inheritance;

/**
 * The class represent gnerenal shape for all profession.
 *
 * @author abondarev.
 * @since 21.07.2017.
 */
public class Profession {

	/**
	 * The variable holds value of a name of a people in profession.
	 */
	private String name;

	/**
	 * The variable holds value of a age of a people in profession.
	 */
	private int age;

	/**
	 * The variable holds value of a job of a people in profession.
	 */
	private String job;

	/**
	 * The variable holds value of a education of a people in profession.
	 */
	private String education;

	/**
	 * The variable holds value of a expirience of a people in profession.
	 */
	private int expirience;

	/**
	 * The constructor takes four parameter and based on them created a profession
	 * object.
	 *
	 * @param name is profession name.
	 * @param age is profession age.
	 * @param job is profession job.
	 * @param education is a profession education.
	 */
	public Profession(String name, int age, String job, String education) {
		this.name = name;
		this.age = age;
		this.job = job;
		this.education = education;
		this.expirience = 0;
	}

	/**
	 * The constructor takes four parameter and based on them created a profession
	 * object.
	 *
	 * @param name is profession name.
	 * @param age is profession age.
	 * @param job is profession job.
	 * @param education is a profession education.
	 * @param expirience is a profession expirience.
	 */
	public Profession(String name, int age, String job, String education, int expirience) {
		this.name = name;
		this.age = age;
		this.job = job;
		this.education = education;
		this.expirience = expirience;
	}

	/**
	 * The method return value of a name of the professional.
	 *
	 * @return value of name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * The method return value of an age of the professional.
	 *
	 * @return value of age.
	 */
	public int getAge() {
		return this.age;
	}

	/**
	 * The method return value of an job of the professional.
	 *
	 * @return value of job.
	 */
	public String getJob() {
		return this.job;
	}

	/**
	 * The method return value of an education of the professional.
	 *
	 * @return value of education.
	 */
	public String getEducation() {
		return this.education;
	}

	/**
	 * The method return value of an expirience of the professional.
	 *
	 * @return value of expirience.
	 */
	public int getExpirience() {
		return this.expirience;
	}
}