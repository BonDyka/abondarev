package ru.job4j.bomberman.models;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Represent Bomberman model.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 27.11.2017.
 */
public class Bomberman implements Runnable {

    private Direction direction;

    private int x = 1;

    private int y = 1;

    private Board board;

    public Bomberman(Board board) {
        direction = Direction.RIGHT;
        this.board = board;
    }

    public void move() throws InterruptedException {
        if (direction == Direction.RIGHT) {
            moveRight();
        } else if (direction == Direction.LEFT) {
            moveLeft();
        } else if (direction == Direction.UP) {
            moveUp();
        } else if (direction == Direction.DOWN) {
            moveDown();
        }
    }

    @Override
    public void run() {
        board.getCell(this.x, this.y).lock();
        int[] exit = this.board.getExitCoordinates();
        while (this.x != exit[0] || this.y != exit[1]) {
            try {
                System.out.println(this);
                move();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
        System.out.println("You win");
    }

    @Override
    public String toString() {
        return String.format("Bomberman [%s : %s].", this.x, this.y);
    }

    private void moveRight() throws InterruptedException {
        int newX = this.x + 1;
        if (tryCaptureOrChangeDirection(newX, this.y)) {
            this.board.getCell(this.x, this.y).unlock();
            this.x = newX;
        }
    }

    private void moveLeft() throws InterruptedException {
        int newX = this.x - 1;
        if (tryCaptureOrChangeDirection(newX, this.y)) {
            this.board.getCell(this.x, this.y).unlock();
            this.x = newX;
        }
    }

    private void moveUp() throws InterruptedException {
        int newY = this.y + 1;
        if (tryCaptureOrChangeDirection(this.x, newY)) {
            this.board.getCell(this.x, this.y).unlock();
            this.y = newY;
        }
    }

    private void moveDown() throws InterruptedException {
        int newY = this.y - 1;
        if (tryCaptureOrChangeDirection(this.x, newY)) {
            this.board.getCell(this.x, this.y).unlock();
            this.y = newY;
        }
    }

    private boolean tryCaptureOrChangeDirection(final int x, final int y) throws InterruptedException {
        boolean result = false;
        ReentrantLock nextCell = board.getCell(x, y);
        if (nextCell.tryLock(500, TimeUnit.MILLISECONDS)) {
            result = true;
        } else {
            this.direction = Direction.getRandomDirection();
        }
        return result;
    }
}
