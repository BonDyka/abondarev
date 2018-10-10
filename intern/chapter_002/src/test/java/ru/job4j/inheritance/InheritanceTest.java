package ru.job4j.inheritance;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The class created to test all classes from inheritance task.
 *
 * @author abondarev.
 * @since 21.07.2017.
 */
public class InheritanceTest {

	/**
	 * The method tests treat() method from Doctor class.
	 */
	@Test
	public void whenCalledTreatThenTreatThePacient() {
		Doctor doc = new Doctor("Steeve", 28, "Dantist", "High");
		String result = doc.treat(new Human("Tom", 12));
		String expected = "Doctor Steeve treats Tom";
		assertThat(result, is(expected));
	}

	/**
	 * The method tests teache() method from Teacher class.
	 */
	@Test
	public void whenCalledTeacheThenTeacheTheStudent() {
		Teacher teacher = new Teacher("Steeve", 28, "Teacher", "High");
		String result = teacher.teache(new Human("Tom", 12));
		String expected = "Teacher Steeve teache Tom";
		assertThat(result, is(expected));
	}

	/**
	 * The method tests develop() method from Engineer class.
	 */
	@Test
	public void whenCalledDevelopThenDevelop() {
		Engineer engineer = new Engineer("Steeve", 28, "Engineer", "High");
		String result = engineer.develop();
		String expected = "Engineer Steeve develop a project";
		assertThat(result, is(expected));
	}
}