package ru.job4j.condition;

import static java.lang.Math.abs;

/**
 * The class describes a triangle in two-dimension space.
 *
 * @author abondarev.
 * @since 11.07.2017.
 */
public class Triangle {

	/**
	 * The variable holds values of point that represent the a vertice
	 * of the triangle.
	 */
	private Point a;

	/**
	 * The variable holds values of point that represent the b vertice
	 * of the triangle.
	 */
	private Point b;

	/**
	 * The variable holds values of point that represent the c vertice
	 * of the triangle.
	 */
	private Point c;

	/**
	 * The constructor that creates object of concrete triangle with specified
	 * as parameters points of vertices.
	 *
	 * @param a the a vertice of triangle.
	 * @param b the b vertice of triangle.
	 * @param c the c vertice of triangle.
	 */
	public Triangle(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	/**
	 * The method calculates the triangle area.
	 *
	 * @return value of the triangle area.
	 */
	public double area() {
		double aX = (double) this.a.getX();
		double aY = (double) this.a.getY();
		double bX = (double) this.b.getX();
		double bY = (double) this.b.getY();
		double cX = (double) this.c.getX();
		double cY = (double) this.c.getY();
		double result = 0.5 * abs((aX - cX) * (bY - cY) - (bX - cX) * (aY - cY));
		return result;
	}
}