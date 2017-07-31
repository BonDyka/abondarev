package ru.job4j.chess.models;

/**
 * The class represent exception for situation when move selected user is
 * not avaliable.
 *
 * @author abondarev.
 * @since 28.07.2017.
 */
public class ImpossibleMoveException extends Exception {

	/**
	 * The constructor for the exception.
	 *
	 * @param msg is a string that describes the exception.
	 */
	public ImpossibleMoveException(String msg) {
		super(msg);
	}
}