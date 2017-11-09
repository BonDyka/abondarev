package ru.job4j.wait_and_other.pc_patern;

/**
 * Shows work of design pattern Producer-Consumer.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 09.11.2017.
 */
public class Main {

    /**
     * App entry point.
     * @param args commandline arguments.
     */
    public static void main(String[] args) {
        ItemQueue queue = new ItemQueue();

        for (int i = 0; i < 2; i++) {
            new Thread(new Producer(queue, "Producer_" + i)).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(new Consumer(queue, "Consumer_" + i)).start();
        }
    }
}
