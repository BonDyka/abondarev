package ru.job4j.inheritance;

/**
 * The class describes a doctor.
 *
 * @author abondarev.
 * @since 21.07.2017.
 */
public class Doctor extends Profession {

	/**
	 * The constructor takes four parameter and based on them created a doctor
	 * object.
	 *
	 * @param name is doctors name.
	 * @param age is doctors age.
	 * @param job is doctors job.
	 * @param education is a doctors education.
	 */
	public Doctor(String name, int age, String job, String education) {
		super(name, age, job, education);
	}

	/**
	 * The constructor takes four parameter and based on them created a doctor
	 * object.
	 *
	 * @param name is doctors name.
	 * @param age is doctors age.
	 * @param job is doctors job.
	 * @param education is a doctors education.
	 * @param expirience is a doctors expirience.
	 */
	public Doctor(String name, int age, String job, String education, int expirience) {
		super(name, age, job, education, expirience);
	}

	/**
	 * The method describes a treat a pacient.
	 *
	 * @param pacient is pacient for treating.
	 * @return a string shows what a doctor treats what a pacient.
	 */
	public String treat(Human pacient) {
		return String.format("Doctor %s treats %s", this.getName(), pacient.getName());
	}
}