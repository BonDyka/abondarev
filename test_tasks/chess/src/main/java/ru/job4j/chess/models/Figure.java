package ru.job4j.chess.models;

/**
 * The class describes a figure for chess game.
 *
 * @author abondarev.
 * @since 28.07.2017.
 */
public abstract class Figure {

	/**
	 * The current position of the figure.
	 */
	private final Cell position;

	/**
	 * The based constructor for Figure.
	 *
	 * @param position is the position of the figure.
	 */
	public Figure(Cell position) {
		this.position = position;
	}

	/**
	 * The method returns Cell that represents figure positions.
	 *
	 * @return Cell that represents figure positions.
	 */
	public Cell position() {
		return this.position;
	}

	/**
	 * The method take as parameter Cell of distination and return an array of cells
	 * that represent way of the figure.
	 *
	 * @param dist is destination Cell.
	 * @return array of cells from figure way.
	 * @throws ImpossibleMoveException when move is not allowed.
	 */
	public abstract Cell[] way(Cell dist) throws ImpossibleMoveException;

	/**
	 * The method returns Figure with specified as parameter cell.
	 *
	 * @param cell is position for the returned figure.
	 * @return figure with specified as parameter cell.
	 */
	public abstract Figure clone(Cell cell);
}