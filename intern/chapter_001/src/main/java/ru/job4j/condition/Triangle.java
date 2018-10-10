package ru.job4j.condition;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static java.lang.Math.pow;

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
	 * The method calculates the triangle area. If it is not exist return -1D.
	 *
	 * @return value of the triangle area.
	 */
	public double area() {
		if (isExist()) {
			double aX = (double) this.a.getX();
			double aY = (double) this.a.getY();
			double bX = (double) this.b.getX();
			double bY = (double) this.b.getY();
			double cX = (double) this.c.getX();
			double cY = (double) this.c.getY();
			return 0.5 * abs((aX - cX) * (bY - cY) - (bX - cX) * (aY - cY));
		} else {
			return -1D;
		}
	}

	/**
	 *	The method determines is exist the triangle or not.
	 *
	 * @return <i>true</i> if the triangle is exist and <i>false</i> otherwise.
	 */
	public boolean isExist() {
		double sideAB = this.getLongSide(a, b);
		double sideAC = this.getLongSide(a, c);
		double sideBC = this.getLongSide(b, c);
		return ((sideAC + sideAB == sideBC)
				|| (sideAB + sideBC == sideAC)
				|| (sideAC + sideBC == sideAB)) ? false : true;
	}

	/**
	 * The method calculate the long side pointed two point.
	 *
	 * @param start is the start point of the side.
	 * @param end is the end point of the side.
	 * @return value of the long side.
	 */
	public double getLongSide(Point start, Point end) {
		return sqrt(pow((double) (end.getX() - start.getX()), 2)
				+ pow((double) (end.getY() - start.getY()), 2));
	}
}