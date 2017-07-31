package ru.job4j.chess.models;

/**
 * The class describes a cell on chess board.
 *
 * @author abondarev.
 * @since 28.07.2017.
 */
public class Cell {

	/**
	 * The variable holds value of cell rows.
	 */
	private int row;

	/**
	 * The variable holds value of cell cols.
	 */
	private int col;

	/**
	 * The constructor.
	 *
	 * @param row is integer value of cell rows.
	 * @param col is integer value of cell cols.
	 */
	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	 * The method return value of the cell rows.
	 *
	 * @return value of the cell rows.
	 */
	public int getRow() {
		return this.row;
	}

	/**
	 * The method return value of the cell cols.
	 *
	 * @return value of the cell cols.
	 */
	public int getCol() {
		return this.col;
	}

	/**
	 * <@inheritedDoc>.
	 *
	 * @param o object for equality.
	 * @return <i>true</i> if objects are equals.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Cell cell = (Cell) o;

		if (row != cell.row) {
			return false;
		}
		return col == cell.col;
	}

	/**
	 * <@inheritedDoc>.
	 *
	 * @return hash code for the cell.
	 */
	@Override
	public int hashCode() {
		int result = row;
		result = 31 * result + col;
		return result;
	}
}