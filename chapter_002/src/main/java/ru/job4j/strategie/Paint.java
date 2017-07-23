package ru.job4j.strategie;

/**
 * The class represent painter.
 *
 * @author abondarev.
 * @since 23.07.2017.
 */
public class Paint {

	/**
	 * The figure for painting.
	 */
	private Shape figure;

	/**
	 * The constructor.
	 *
	 * @param figure is shape for painting.
	 */
	public Paint(Shape figure) {
		this.figure = figure;
	}

	/**
	 * The method draws inner figure in pseudo-graphics.
	 */
	public void draw() {
		System.out.println(this.figure.pic());
	}
}