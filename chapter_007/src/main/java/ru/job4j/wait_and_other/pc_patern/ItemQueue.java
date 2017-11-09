package ru.job4j.wait_and_other.pc_patern;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Class as a chanel for {@link Producer}-{@link Consumer} exchange.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 09.11.2017.
 */
public class ItemQueue {

    private BlockingQueue<Item> queue;

    public ItemQueue() {
        queue = new LinkedBlockingDeque<>();
    }

    public void put(final Item item) throws InterruptedException {
        this.queue.put(item);
    }

    public Item take() throws InterruptedException {
        return this.queue.take();
    }
}
