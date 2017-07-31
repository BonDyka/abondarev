package ru.job4j.chess;

/**
 * The class describes exception when figure no found on the board.
 *
 * @author abondarev.
 * @since 28.07.2017.
 */
public class FigureNotFoundException extends Exception {

	/**
	 * The constroctor takes as argument a string that describes that exception.
	 *
	 * @param msg is message about exception.
	 */
	public FigureNotFoundException(String msg) {
		super(msg);
	}
}