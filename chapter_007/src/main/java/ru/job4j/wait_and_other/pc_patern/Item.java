package ru.job4j.wait_and_other.pc_patern;

/**
 * Class takes a part of an {@link Producer}-{@link Consumer} exchange.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 09.11.2017.
 */
public class Item {

    private final int id;

    private String producer;

    public Item(int id, String producer) {
        this.id = id;
        this.producer = producer;
    }

    public int getId() {
        return id;
    }

    public String getProducer() {
        return producer;
    }
}
