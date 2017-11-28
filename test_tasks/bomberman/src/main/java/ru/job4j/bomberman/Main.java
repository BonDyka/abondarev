package ru.job4j.bomberman;

import ru.job4j.bomberman.models.Board;
import ru.job4j.bomberman.models.Bomberman;

/**
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 27.11.2017.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Board board = new Board();
        board.init();
        Thread bomber = new Thread(new Bomberman(board));
        bomber.start();
        bomber.join();
        System.out.println("end");
    }
}
