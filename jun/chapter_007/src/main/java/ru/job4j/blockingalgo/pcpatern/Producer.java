package ru.job4j.blockingalgo.pcpatern;

import java.util.Random;

/**
 * Class responsible for producing unit of work that can be expressed as
 * {@link Item} and submitted it ot queue.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 09.11.2017.
 */
public class Producer implements Runnable {

    private ItemQueue queue;

    private String name;

    private int itemId;

    public Producer(ItemQueue queue, String name) {
        this.queue = queue;
        this.name = name;
    }

    /**
     * Produce unit of work and send it to Consumer through queue.
     *
     * @throws InterruptedException exception.
     */
    public void produce() throws InterruptedException {
        Item item = new Item(this.itemId++, this.name);
        this.queue.put(item);
        Thread.sleep(new Random().nextInt(1000));
    }

    @Override
    public void run() {
        while (true) {
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
