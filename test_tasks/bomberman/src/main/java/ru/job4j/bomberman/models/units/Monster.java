package ru.job4j.bomberman.models.units;

import ru.job4j.bomberman.models.Board;

import java.util.Random;

/**
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 04.12.2017.
 */
public class Monster extends Unit {

    public static final String M_NAME = "M";

    private static final int TRYING_TIME = 5000;

    private static final Direction START_DIRECTION = Direction.getRandomDirection();

    private static int count = 0;

    public Monster(Board board) {
        super(M_NAME + ++count, board, START_DIRECTION, 0, 0, TRYING_TIME);
        setRandomCoordinates(board.getWidth(), board.getHeight());
    }

    public void move() throws InterruptedException {
        Direction dir = getDirection();
        if (dir == Direction.RIGHT) {
            moveRight();
        } else if (dir == Direction.LEFT) {
            moveLeft();
        } else if (dir == Direction.UP) {
            moveUp();
        } else if (dir == Direction.DOWN) {
            moveDown();
        }
    }

    // utils

    private void setRandomCoordinates(int upBoundX, int upBoundY) {
        Random rn = new Random();
        int x = (rn.nextInt(upBoundX / 2) + (upBoundX / 2));
        int y = (rn.nextInt(upBoundY / 2) + (upBoundY / 2));
        while (getBoard().getCell(x, y).isLocked()) {
            x = (rn.nextInt(upBoundX / 2) + (upBoundX / 2));
            y = (rn.nextInt(upBoundY / 2) + (upBoundY / 2));
        }
        setX(x);
        setY(y);
    }
}
