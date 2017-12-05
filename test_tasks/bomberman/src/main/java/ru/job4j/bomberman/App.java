package ru.job4j.bomberman;

import ru.job4j.bomberman.input.StubUserInput;
import ru.job4j.bomberman.models.Board;
import ru.job4j.bomberman.models.units.Bomberman;
import ru.job4j.bomberman.models.units.Monster;
import ru.job4j.bomberman.models.units.Unit;

import java.util.Arrays;

/**
 * App start class.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 27.11.2017.
 */
public class App {

    private final static int DEFAULT_POOL_SIZE = 5;

    private Unit[] units;

    private Thread[] pool;

    private int poolSize;

    private Board board;

    /*
     * App entry point.
     */
    public static void main(String[] args) throws InterruptedException {
        App game = new App(7, 7, 1);
        game.init();
        game.start();
    }

    public App() {
        this.board = new Board();
        this.poolSize = DEFAULT_POOL_SIZE;
        this.units = new Unit[poolSize];
        this.pool = new Thread[poolSize];
    }

    public App(int boardWidth, int boardHeight, int monsterAmount) {
        this.board = new Board(boardWidth, boardHeight);
        this.poolSize = monsterAmount + 1;
        this.units = new Unit[this.poolSize];
        this.pool = new Thread[poolSize];
    }

    /**
     * Initializes game.
     */
    public void init() {
        this.board.init();
        this.fillUnitsAndPool();
    }

    /**
     * Starts game execution. Use only after calling {@link App#init()}
     */
    public void start() throws InterruptedException {
        System.out.println("exit " + board.getExitCoordinates()[0] + " " + board.getExitCoordinates()[1]);
        this.activateUnits();
        while (true) {
            Thread.sleep(1000);
            System.out.println(this.board);
            if (isOver() || isWin()) {
                break;
            }
        }
        this.stopUnits();
        if (isWin()) {
            System.out.println("You Win");
        }
        if (isOver()) {
            System.out.println("You lose");
        }
    }


    /*
     * fill units and thread pool
     */
    private void fillUnitsAndPool() {
        for (int i = 0; i < poolSize; i++) {
            if (i != 0) {
                units[i] = new Monster(this.board);
            } else {
                units[i] = new Bomberman(this.board, new StubUserInput());
            }
            pool[i] = new Thread(units[i]);
        }
    }

    /*
     * start all units executing
     */
    private void activateUnits() {
        for (Thread t : this.pool) {
            t.start();
        }
    }

    /*
     * stop all units executing
     */
    private void stopUnits() {
        for (Thread t : this.pool) {
            t.interrupt();
        }
    }

    /*
     * Checks winning
     */
    private boolean isWin() {
        int[] exit = this.board.getExitCoordinates();
        return units[0].getX() == exit[0] && units[0].getY() == exit[1];
    }

    /*
     * Checks losing
     */
    private boolean isOver() {
        boolean result = false;
        int[] nextStep;
        for (int i = 1; i < this.poolSize; i++) {
            nextStep = units[i].getNextStep();
            if (this.board.getCellOwner(nextStep[0], nextStep[1]) == 'B'
                    || Arrays.equals(units[0].getNextStep(),
                                    new int[]{units[i].getX(), units[i].getY()})
                    ) {
                result = true;
                break;
            }
        }
        return result;
    }


}
