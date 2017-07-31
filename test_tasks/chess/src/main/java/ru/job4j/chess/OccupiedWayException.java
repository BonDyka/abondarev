package ru.job4j.chess;

/**
 * The class describes exception when on the figure ways stand other figure.
 *
 * @author abondarev.
 * @since 28.07.2017.
 */
public class OccupiedWayException extends Exception {

	/**
	 * The constroctor takes as argument a string that describes that exception.
	 *
	 * @param msg is message about exception.
	 */
	public OccupiedWayException(String msg) {
		super(msg);
	}
}