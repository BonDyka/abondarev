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

    public static final int MAX_WIDTH = 8; //(1 << 5) + 1; // aka 33

    public static final int MAX_HEIGHT = 6; //(1 << 4) + 1; // aka 17

    private int xDoorCoordinate;

    private int yDoorCoordinate;

    private final ReentrantLock[][] board;

    public Board() {
        board = new ReentrantLock[MAX_WIDTH][MAX_HEIGHT];
    }

    public void init() {
        fillBoard();
        setDoorCoordinates();
        installBounds();
        //installObstacles();
    }

    public int[] getExitCoordinates() {
        return new int[]{this.xDoorCoordinate, this.yDoorCoordinate};
    }

    public ReentrantLock getCell(final int x, final int y) {
        return this.board[x][y];
    }

    private void fillBoard() {
        for (int i = 0; i < MAX_WIDTH; i++) {
            for (int j = 0; j < MAX_HEIGHT; j++) {
                this.board[i][j] = new ReentrantLock();
            }
        }
    }

    private void setDoorCoordinates() {
        Random rn = new Random();
        this.xDoorCoordinate = (rn.nextInt(MAX_WIDTH >> 1) * 2) - 1;
        this.yDoorCoordinate = (rn.nextInt(MAX_HEIGHT >> 1) * 2) - 1;
    }

    private void installBounds() {
        for (int i = 0; i < MAX_WIDTH; i++) {
            for (int j = 0; j < MAX_HEIGHT; j++) {
                if (i == 0 || i == MAX_WIDTH - 1 || j == 0 || j == MAX_HEIGHT - 1) {
                    if (i != this.xDoorCoordinate || j != this.yDoorCoordinate) {
                        board[i][j].lock();
                    }
                }
            }
        }
    }

//    private void installObstacles() {
//        for (int i = 1; i < MAX_WIDTH - 2; i++) {
//            for (int j = 1; j < MAX_HEIGHT - 2; j++) {
//                if (i%2 != 0 && j%2 != 0) {
//                    this.board[i][j].lock();
//                }
//            }
//        }
//    }
}
