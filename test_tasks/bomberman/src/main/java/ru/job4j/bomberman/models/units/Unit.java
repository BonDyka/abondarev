package ru.job4j.bomberman.models.units;

import ru.job4j.bomberman.models.Board;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 04.12.2017.
 */
public abstract class Unit implements Runnable {

    private Direction direction;

    private int x;

    private int y;

    private final int tryingTime;

    private final Board board;

    private final String name;

    public Unit(String name, Board board, Direction direction, int x, int y, int tryingTime) {
        this.name = name;
        this.board = board;
        this.direction = direction;
        this.x = x;
        this.y = y;
        this.tryingTime = tryingTime;
    }

    // Getters and setters

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    protected void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    protected void setY(int y) {
        this.y = y;
    }

    /**
     * Returns the board.
     *
     * @return the board
     */
    protected Board getBoard() {
        return board;
    }

    /**
     * Returns next step coordinates.
     *
     * @return next step coordinates.
     */
    public int[] getNextStep() {
        int nextX = this.x;
        int nextY = this.y;
        if (direction == Direction.RIGHT) {
            nextX += 1;
        } else if (direction == Direction.LEFT) {
            nextX -= 1;
        } else if (direction == Direction.UP) {
            nextY += 1;
        } else if (direction == Direction.DOWN) {
            nextY -= 1;
        }
        return new int[]{nextX, nextY};
    }

    // logic

    /**
     * Move unit across the board.
     *
     * @throws InterruptedException if current thread is interrupted.
     */
    public abstract void move() throws InterruptedException;

    @Override
    public void run() {
        board.getCell(this.x, this.y).lock();
        this.board.setCellOwner(this.x, this.y, this.name.charAt(0));
        while (true) {
            try {
                Thread.sleep(1000);
                move();
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%s [%s : %s].", this.name, this.x, this.y);
    }

    protected void moveRight() throws InterruptedException {
        int newX = this.x + 1;
        if (tryCaptureOrChangeDirection(newX, this.y)) {
            this.board.getCell(this.x, this.y).unlock();
            this.board.setCellOwner(this.x, this.y, ' ');
            this.x = newX;
            this.board.setCellOwner(this.x, this.y, this.name.charAt(0));
        }
    }

    protected void moveLeft() throws InterruptedException {
        int newX = this.x - 1;
        if (tryCaptureOrChangeDirection(newX, this.y)) {
            this.board.getCell(this.x, this.y).unlock();
            this.board.setCellOwner(this.x, this.y, ' ');
            this.x = newX;
            this.board.setCellOwner(this.x, this.y, this.name.charAt(0));
        }
    }

    protected void moveUp() throws InterruptedException {
        int newY = this.y + 1;
        if (tryCaptureOrChangeDirection(this.x, newY)) {
            this.board.getCell(this.x, this.y).unlock();
            this.board.setCellOwner(this.x, this.y, ' ');
            this.y = newY;
            this.board.setCellOwner(this.x, this.y, this.name.charAt(0));
        }
    }

    protected void moveDown() throws InterruptedException {
        int newY = this.y - 1;
        if (tryCaptureOrChangeDirection(this.x, newY)) {
            this.board.getCell(this.x, this.y).unlock();
            this.board.setCellOwner(this.x, this.y, ' ');
            this.y = newY;
            this.board.setCellOwner(this.x, this.y, this.name.charAt(0));
        }
    }

    private boolean tryCaptureOrChangeDirection(final int x, final int y) throws InterruptedException {
        boolean result = false;
        ReentrantLock nextCell = board.getCell(x, y);
        if (nextCell.tryLock(this.tryingTime, TimeUnit.MILLISECONDS)) {
            result = true;
        } else {
            this.direction = Direction.getRandomDirection();
        }
        return result;
    }
}
