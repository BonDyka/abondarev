package ru.job4j.bomberman.models;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Represent board model.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 26.11.2017.
 */
public class Board {

    /**
     * Default board width.
     */
    public static final int DEFAULT_WIDTH = (1 << 5) + 1; // aka 33

    /**
     * Default board height.
     */
    public static final int DEFAULT_HEIGHT = (1 << 4) + 1; // aka 17

    private final int width;

    private final int height;

    /*
     * Exit coordinates for win level.
     */
    private int xDoorCoordinate;

    private int yDoorCoordinate;

    /*
     * The board.
     */
    private final ReentrantLock[][] board;

    /*
     * the board map for graphic representation.
     */
    private final char[][] boardMap;

    public Board() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        board = new ReentrantLock[this.width][this.height];
        boardMap = new char[this.width][this.height];
    }

    /**
     * Init the board. Use immediately after the board creation.
     */
    public void init() {
        fillBoard();
        installBounds();
        installObstacles();
        setDoorCoordinates();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[] getExitCoordinates() {
        return new int[]{this.xDoorCoordinate, this.yDoorCoordinate};
    }

    public ReentrantLock getCell(final int x, final int y) {
        return this.board[x][y];
    }

    /**
     * Returns chars representation of the cell owner.
     *
     * @param x cell coordinate.
     * @param y cell coordinate.
     * @return chars representation of the cell owner.
     */
    public char getCellOwner(final int x, final int y) {
        return boardMap[x][y];
    }

    public void setCellOwner(final int x, final int y, final char owner) {
        boardMap[x][y] = owner;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (char[] chars : boardMap) {
            for (char ch : chars) {
                builder.append(ch).append(" ");
            }
            builder.append("\r\n");
        }
        return builder.toString();
    }

    private void fillBoard() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.board[i][j] = new ReentrantLock();
                boardMap[i][j] = ' ';
            }
        }
    }

    private void setDoorCoordinates() {
        Random rn = new Random();
        int x = (rn.nextInt(width / 2) + (width / 2));
        int y = (rn.nextInt(height / 2) + (height / 2));
        while (this.getCell(x, y).isLocked()) {
            x = (rn.nextInt(width / 2) + (width / 2));
            y = (rn.nextInt(height / 2) + (height / 2));
        }
        xDoorCoordinate = x;
        yDoorCoordinate = y;
   }

    private void installBounds() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (i == 0 || i == width - 1 || j == 0 || j == height - 1) {
                    board[i][j].lock();
                    boardMap[i][j] = '*';
                }
            }
        }
    }

    private void installObstacles() {
        for (int i = 1; i < width - 2; i++) {
            for (int j = 1; j < height - 2; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    this.board[i][j].lock();
                    boardMap[i][j] = '*';
                }
            }
        }
    }
}
