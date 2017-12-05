package ru.job4j.bomberman.models.units;

import ru.job4j.bomberman.input.IUserInput;
import ru.job4j.bomberman.models.Board;

/**
 * Represent Bomberman model.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 27.11.2017.
 */
public class Bomberman extends Unit {

    /**
     * Default name of bomber man.
     */
    public static final String BM_NAME = "B";

    /*
     * Start cell coordinates.
     */
    private static final int START_X = 1, START_Y = 1;

    /*
     * Start bomber man direction.
     */
    private static final Direction START_DIRECTION = Direction.RIGHT;

    /*
     * time for trying nove on a locked cell.
     */
    private static final int TRYING_TIME = 500;

    /*
     * Instance of user input.
     */
    private final IUserInput input;

    public Bomberman(Board board, IUserInput input) {
        super(BM_NAME, board, START_DIRECTION, START_X, START_Y, TRYING_TIME);
        this.input = input;
    }

    @Override
    public void move() throws InterruptedException {
        Direction oldDir = getDirection();
        Direction newDir = input.getMoveDirection();
        if (oldDir != newDir) {
            setDirection(newDir);
        }
        if (getDirection() == Direction.RIGHT) {
            moveRight();
        } else if (getDirection() == Direction.LEFT) {
            moveLeft();
        } else if (getDirection() == Direction.UP) {
            moveUp();
        } else if (getDirection() == Direction.DOWN) {
            moveDown();
        }
    }
}
