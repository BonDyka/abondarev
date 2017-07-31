package ru.job4j.chess;

import ru.job4j.chess.models.Cell;
import ru.job4j.chess.models.Figure;
import ru.job4j.chess.models.ImpossibleMoveException;

/**
 * The class represent chess board.
 *
 * @author abondarev.
 * @since 29.07.2017.
 */
public class Board {

	/**
	 * The Started position for columns and rows on the board.
	 */
	public static final int START = 1;

	/**
	 * The size of the board.
	 */
	public static final int SIZE = 8;

	/**
	 * The array of figures that contains the board.
	 */
	private Figure[] figures;

	/**
	 * The constructor that takes as parameter list of figures on the board.
	 *
	 * @param figures is an array of figures on the board.
	 */
	public Board(Figure[] figures) {
		this.figures = figures;
	}

	/**
	 * The method moves figure. The method takes as parameter source and dist
	 * cells. If move is imposible throws <b>ImpossibleMoveException</b>. If
	 * figure in source cell is not exist throws <b>FigureNotFoundException</b>.
	 * If there is oter figures on the way throws <b>OccupiedWayException</b>.
	 * If there is no Exceptions, The figure moved and the method return <i>true</i>.
	 *
	 * @param source is sourse cell.
	 * @param dist is destination cell.
	 * @return <i>true</i> if and only if figure was move.
	 * @throws ImpossibleMoveException if move is imposible.
	 * @throws OccupiedWayException if on the figure way stand other figure.
	 * @throws FigureNotFoundException if figure is not on the board.
	 */
	public boolean move(Cell source, Cell dist) throws ImpossibleMoveException,
														OccupiedWayException,
														FigureNotFoundException {
		// Checking that the figure is exist.
		int selected = this.select(source);
		// checking that move is possible.
		Cell[] way = this.figures[selected].way(dist);
		// Checking that on the way of figure do not stand other figure.
		this.isWayClear(way);
		this.figures[selected] = this.figures[selected].clone(dist);
		return true;
	}

	/**
	 * The method checks the figure in cell is exist. If the figure exists return
	 * the position of its position in this figures array otherwise throws
	 * <b>FigureNotFoundException</b>.
	 *
	 * @param cell is cell of selected figure.
	 * @return position in this array of figures if that figure exists.
	 * @throws FigureNotFoundException if the figure do not stand on the board.
	 */
	private int select(Cell cell) throws FigureNotFoundException {
		int result = -1;
		for (int index = 0; index < this.figures.length; index++) {
			if (cell.equals(this.figures[index].position())) {
				result = index;
				break;
			}
		}
		if (result < 0) {
			throw new FigureNotFoundException("Figure does not exist");
		}
		return result;
	}

	/**
	 * The method checks is way clear. If is not clear throws OccupiedWayException.
	 *
	 * @param way is array of cells that represent the figure way.
	 * @throws OccupiedWayException if the way contain other figure.
	 */
	private void isWayClear(Cell[] way) throws OccupiedWayException {
		for (Cell cell : way) {
			for (Figure figure : this.figures) {
				if (cell.equals(figure.position())) {
					throw new OccupiedWayException("Way is occupied.");
				}
			}
		}
	}
}