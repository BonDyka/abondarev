package ru.job4j.wait_and_other.pc_patern;

/**
 * Class responsible for consume the {@link Item} produced by {@link Producer}.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 09.11.2017.
 */
public class Consumer implements Runnable {

    private ItemQueue queue;

    private String name;

    public Consumer(ItemQueue queue, String name) {
        this.queue = queue;
        this.name = name;
    }

    /**
     * Consume work from Producer through queue. (Just print to console).
     *
     * @throws InterruptedException exception.
     */
    public void consume() throws InterruptedException {
        Item item = queue.take();
        System.out.println(String.format("%S consume item %s produced by %s.",
                                         this.name, item.getId(), item.getProducer()
                           ));
    }

    @Override
    public void run() {
        while (true) {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
