package ru.job4j.chess.models;

import ru.job4j.chess.Board;

import static java.lang.Math.abs;

/**
 * The class describes a chess bishop.
 *
 * @author abondarev.
 * @since 29.07.2017.
 */
public class Bishop extends Figure {

	/**
	 * The based constructor for Bishop.
	 *
	 * @param position is the position of the figure.
	 */
	public Bishop(Cell position) {
		super(position);
	}

	/**
	 * <@inheritedDoc>.
	 *
	 * @param dist is destination Cell.
	 * @return array of cells from figure way.
	 * @throws ImpossibleMoveException when move is not allowed.
	 */
	public Cell[] way(Cell dist) throws ImpossibleMoveException {
		Cell[] result = null;
		int rowDir = this.position().getRow() - dist.getRow();
	 	int colDir = this.position().getCol() - dist.getCol();

	 	if ((abs(rowDir) != abs(colDir)) || dist.getRow() < Board.START
	 		|| dist.getRow() > Board.SIZE || dist.getRow() < Board.START
	 		|| dist.getRow() > Board.SIZE) {
	 		throw new ImpossibleMoveException("Move is impossible.");
	 	}
	 	if (rowDir > 0 && colDir > 0) {
	 		result = this.goNW(dist);
	 	} else if (rowDir > 0 && colDir < 0) {
	 		result = this.goNE(dist);
	 	} else if (rowDir < 0 && colDir < 0) {
	 		result = this.goSE(dist);
	 	} else {
	 		result = this.goSW(dist);
	 	}
		return result;
	}

	/**
	 * <inheritedDoc>.
	 *
	 * @param cell is position for the returned figure.
	 * @return figure with specified as parameter cell.
	 */
	public Figure clone(Cell cell) {
		return new Bishop(cell);
	}

	/**
	 * The method return an array of cell that reprasent way of figure to Nort-West.
	 *
	 * @param dist is cell of destination.
	 * @return way of figure.
	 */
	private Cell[] goNW(Cell dist) {
		Cell[] result = new Cell[this.position().getRow() - dist.getRow()];
		int row = dist.getRow();
		int col = dist.getCol();
		for (int index = 0; index < result.length; index++) {
			result[index] = new Cell(row + index, col + index);
		}

		return result;
	}

	/**
	 * The method return an array of cell that reprasent way of figure to Nort-East.
	 *
	 * @param dist is cell of destination.
	 * @return way of figure.
	 */
	private Cell[] goNE(Cell dist) {
		Cell[] result = new Cell[this.position().getRow() - dist.getRow()];
		int row = dist.getRow();
		int col = dist.getCol();
		for (int index = 0; index < result.length; index++) {
			result[index] = new Cell(row + index, col - index);
		}
		return result;
	}

	/**
	 * The method return an array of cell that reprasent way of figure to Nort-East.
	 *
	 * @param dist is cell of destination.
	 * @return way of figure.
	 */
	private Cell[] goSE(Cell dist) {
		Cell[] result = new Cell[abs(this.position().getRow() - dist.getRow())];
		int row = dist.getRow();
		int col = dist.getCol();
		for (int index = 0; index < result.length; index++) {
			result[index] = new Cell(row - index, col - index);
		}
		return result;
	}

	/**
	 * The method return an array of cell that reprasent way of figure to Nort-East.
	 *
	 * @param dist is cell of destination.
	 * @return way of figure.
	 */
	private Cell[] goSW(Cell dist) {
		Cell[] result = new Cell[abs(this.position().getRow() - dist.getRow())];
		int row = dist.getRow();
		int col = dist.getCol();
		for (int index = 0; index < result.length; index++) {
			result[index] = new Cell(row - index, col + index);
		}
		return result;
	}
}