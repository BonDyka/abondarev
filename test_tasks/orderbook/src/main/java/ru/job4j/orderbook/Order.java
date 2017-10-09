package ru.job4j.orderbook;

/**
 * Represent model of order.
 *
 * @author abondarev.
 * @since 08.10.2017.
 */
public class Order {

    /**
     * Enumeration of operations type.
     */
    public enum Type {
        /**
         * Means that order for sell.
         */
        SELL,

        /**
         * Means that order for buy.
         */
        BUY
    }

    /**
     * Name of book for the order.
     */
    private String book;

    /**
     * Type of operation.
     */
    private Type operation;

    /**
     * Price of order.
     */
    private double price;

    /**
     * It's volume of order.
     */
    private int volume;

    /**
     * Id of order.
     */
    private int id;

    /**
     * The constructor.
     *
     * @param book name of book.
     * @param operation type of operation.
     * @param price it's orders price.
     * @param volume orders volume.
     * @param id orders id.
     */
    public Order(String book, Type operation, double price, int volume, int id) {
        this.book = book;
        this.operation = operation;
        this.price = price;
        this.volume = volume;
        this.id = id;
    }

    /**
     * Returns name of book for the order.
     *
     * @return name of book for the order.
     */
    public String getBook() {
        return book;
    }

    /**
     * Returns operations type for the order.
     *
     * @return operations type for the order.
     */
    public Type getOperation() {
        return operation;
    }

    /**
     * Returns price of the order.
     *
     * @return price of the order.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns volume of the order.
     *
     * @return volume of the order.
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Returns id of the order.
     *
     * @return id of the order.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns true if object o are equal this.
     *
     * @param o object for comparision.
     * @return true if o are equal this.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }

        Order order = (Order) o;

        if (Double.compare(order.price, price) != 0) {
            return false;
        }
        if (volume != order.volume) {
            return false;
        }
        return id == order.id;
    }

    /**
     * Return hash code for the order.
     *
     * @return hash code for the order.
     */
    @Override
    public int hashCode() {
        return this.id;
    }

    /**
     * Returns a string representation of the order.
     *
     * @return a string representation of the order.
     */
    @Override
    public String toString() {
        return String.format("%s%s%s", this.price, "@", this.volume);
    }
}
