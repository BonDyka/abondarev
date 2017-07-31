package ru.job4j.chess;

import org.junit.Test;

import ru.job4j.chess.models.Cell;
import ru.job4j.chess.models.Bishop;
import ru.job4j.chess.models.Figure;
import ru.job4j.chess.models.ImpossibleMoveException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The class represent some tests for board class.
 *
 * @author abondarev.
 * @since 30.07.2017.
 */
public class BoardTest {

	/**
	 * The method tests board when North-West way of a figure is clear.
	 *
	 * @throws ImpossibleMoveException may be throwed.
	 * @throws OccupiedWayException if on the figure way stand other figure.
	 * @throws FigureNotFoundException if figure is not on the board.
	 */
	@Test
	public void whenNorthWestWayIsClearThenFigureMoved() throws ImpossibleMoveException,
														OccupiedWayException,
														FigureNotFoundException {
		Cell source = new Cell(4, 5);
		Cell dist = new Cell(1, 2);
		Figure[] figures = new Figure[] {new Bishop(source)};
		Board board = new Board(figures);
		board.move(source, dist);
		Cell result = figures[0].position();
		assertThat(result, is(dist));
	}

	/**
	 * The method tests board when North-Eastway of a figure is clear.
	 *
	 * @throws ImpossibleMoveException may be throwed.
	 * @throws OccupiedWayException if on the figure way stand other figure.
	 * @throws FigureNotFoundException if figure is not on the board.
	 */
	@Test
	public void whenNorthEastWayIsClearThenFigureMoved() throws ImpossibleMoveException,
														OccupiedWayException,
														FigureNotFoundException {
		Cell source = new Cell(4, 5);
		Cell dist = new Cell(1, 8);
		Figure[] figures = new Figure[] {new Bishop(source)};
		Board board = new Board(figures);
		board.move(source, dist);
		Cell result = figures[0].position();
		assertThat(result, is(dist));
	}

	/**
	 * The method tests board when North-Eastway of a figure is clear.
	 *
	 * @throws ImpossibleMoveException may be throwed.
	 * @throws OccupiedWayException if on the figure way stand other figure.
	 * @throws FigureNotFoundException if figure is not on the board.
	 */
	@Test
	public void whenSouthEastWayIsClearThenFigureMoved() throws ImpossibleMoveException,
														OccupiedWayException,
														FigureNotFoundException {
		Cell source = new Cell(4, 5);
		Cell dist = new Cell(7, 8);
		Figure[] figures = new Figure[] {new Bishop(source)};
		Board board = new Board(figures);
		board.move(source, dist);
		Cell result = figures[0].position();
		assertThat(result, is(dist));
	}

	/**
	 * The method tests board when North-Eastway of a figure is clear.
	 *
	 * @throws ImpossibleMoveException may be throwed.
	 * @throws OccupiedWayException if on the figure way stand other figure.
	 * @throws FigureNotFoundException if figure is not on the board.
	 */
	@Test
	public void whenSouthWestWayIsClearThenFigureMoved() throws ImpossibleMoveException,
														OccupiedWayException,
														FigureNotFoundException {
		Cell source = new Cell(4, 5);
		Cell dist = new Cell(7, 2);
		Figure[] figures = new Figure[] {new Bishop(source)};
		Board board = new Board(figures);
		board.move(source, dist);
		Cell result = figures[0].position();
		assertThat(result, is(dist));
	}

	/**
	 * The method tests board move is impossible.
	 *
	 * @throws ImpossibleMoveException may be throwed.
	 * @throws OccupiedWayException if on the figure way stand other figure.
	 * @throws FigureNotFoundException if figure is not on the board.
	 */
	@Test(expected = ImpossibleMoveException.class)
	public void whenWayIpossibleThenIWEThrowed() throws ImpossibleMoveException,
														OccupiedWayException,
														FigureNotFoundException {
		Cell source = new Cell(4, 5);
		Cell dist = new Cell(2, 8);
		Figure[] figures = new Figure[] {new Bishop(source)};
		Board board = new Board(figures);
		board.move(source, dist);
	}

	/**
	 * The method tests board move is impossible.
	 *
	 * @throws ImpossibleMoveException may be throwed.
	 * @throws OccupiedWayException if on the figure way stand other figure.
	 * @throws FigureNotFoundException if figure is not on the board.
	 */
	@Test(expected = FigureNotFoundException.class)
	public void whenFigureNotFoundThenFNFEThrowed() throws ImpossibleMoveException,
														OccupiedWayException,
														FigureNotFoundException {
		Cell source = new Cell(4, 5);
		Cell dist = new Cell(2, 8);
		Figure[] figures = new Figure[] {new Bishop(source)};
		Board board = new Board(figures);
		board.move(dist, source);
	}

	/**
	 * The method tests board move is impossible.
	 *
	 * @throws ImpossibleMoveException may be throwed.
	 * @throws OccupiedWayException if on the figure way stand other figure.
	 * @throws FigureNotFoundException if figure is not on the board.
	 */
	@Test(expected = OccupiedWayException.class)
	public void whenWayOccupiedThenOWEThrowed() throws ImpossibleMoveException,
														OccupiedWayException,
														FigureNotFoundException {
		Cell source = new Cell(4, 5);
		Cell dist = new Cell(1, 2);
		Figure[] figures = new Figure[] {new Bishop(source), new Bishop(new Cell(2, 3))};
		Board board = new Board(figures);
		board.move(source, dist);
	}
}