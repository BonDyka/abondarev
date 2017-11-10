package ru.job4j.wait_and_other.pc_patern;

/**
 * Class as a chanel for {@link Producer}-{@link Consumer} exchange.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 09.11.2017.
 */
public class ItemQueue {

    /**
     * Stores unit of work from {@link Producer}.
     */
    private final Item[] items;

    /**
     * Among of elements.
     */
    private int size = 0;

    /**
     * The constructor.
     */
    public ItemQueue() {
        items = new Item[10];
    }

    /**
     * Inserts specified as parameter instance of {@link Item} after last
     * added element.
     *
     * @param item element for adding.
     * @throws InterruptedException if interrupted while waiting.
     */
    public synchronized void put(final Item item) throws InterruptedException {
        while (this.size >= items.length) {
            wait();
        }
        this.items[size++] = item;
        notify();
    }

    /**
     * Retrieves and removes the head of this queue, waiting if necessary
     * until an element becomes available.
     *
     * @return the head of this queue.
     * @throws InterruptedException if interrupted while waiting.
     */
    public synchronized Item take() throws InterruptedException {
        while (size <= 0) {
            wait();
        }
        Item result = items[0];
        System.arraycopy(this.items, 1, this.items, 0, this.size - 1);
        items[size - 1] = null;
        size--;
        notify();
        return result;
    }
}
