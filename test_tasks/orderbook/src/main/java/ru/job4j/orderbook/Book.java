package ru.job4j.orderbook;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents order book model.
 *
 * @author abondarev.
 * @since 08.10.2017.
 */
public class Book {

    /**
     * Name of the book.
     */
    private final String name;

    /**
     * Stores all buy orders.
     */
    private Map<Double, Order> buyOrders = new TreeMap<>();

    /**
     * Stores all sell orders.
     */
    private Map<Double, Order> sellOrders = new TreeMap<>(Comparator.reverseOrder());

    /**
     * The constructor.
     *
     * @param name it's name for the book.
     */
    public Book(String name) {
        this.name = name;
    }

    /**
     * Return name of the book.
     *
     * @return name of the book.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Prints to console book content.
     */
    public void show() {
        StringBuilder builder = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        builder.append(String.format("%s%s", this.name.toUpperCase(), newLine));
        for (Order order : sellOrders.values()) {
            builder.append(String.format("\t\t%s%s", order, newLine));
        }
        for (Order order : buyOrders.values()) {
            builder.append(String.format("%s%s", order, newLine));
        }
        System.out.println(builder);
    }

    /**
     * Adds specified new order to the book.
     *
     * @param order order for adding.
     */
    public void addOrder(Order order) {
        if (("SELL".equalsIgnoreCase(order.getOperation().name()))) {
            this.addToOrders(order, this.sellOrders);
        } else {
            this.addToOrders(order, this.buyOrders);
        }
    }

    /**
     * Matches sell orders and buy orders with appropriated price.
     */
    public void matchOrders() {
        Iterator<Order> itBuy =  this.buyOrders.values().iterator();
        Iterator<Order> itSell = this.sellOrders.values().iterator();
        int newVol;
        Order newBuyOrder;
        Order newSellOrder;
        while (itBuy.hasNext() || itSell.hasNext()) {
            Order buy = itBuy.next();
            Order sell = itSell.next();
            Double buyPrice = buy.getPrice();
            Double sellPrice = sell.getPrice();
            if (buyPrice < sellPrice) {
                continue;
            }
            if (buyPrice > sellPrice) {
                if (buy.getVolume() > sell.getVolume()) {
                    newVol = buy.getVolume() - sell.getVolume();
                    newBuyOrder = new Order(buy.getBook(), buy.getOperation(),
                                            buy.getPrice(), newVol, buy.getId()
                    );
                    this.buyOrders.put(newBuyOrder.getPrice(), newBuyOrder);
                } else if (buy.getVolume() < sell.getVolume()) {
                    newVol = sell.getVolume() - buy.getVolume();
                    newSellOrder = new Order(buy.getBook(), buy.getOperation(),
                                             buy.getPrice(), newVol, buy.getId()
                    );
                    this.sellOrders.put(newSellOrder.getPrice(), newSellOrder);
                }

            }
            this.sellOrders.remove(sell.getPrice());
            this.buyOrders.remove(buy.getPrice());
            break;
        }
    }

    /**
     * Adds order to pointed map of orders.
     *
     * @param newOrder it's order for adding.
     * @param orders it's map of orders.
     */
    private void addToOrders(Order newOrder, Map<Double, Order> orders) {
        if (orders.containsKey(newOrder.getPrice())) {
            int newVolume = orders.get(newOrder.getPrice()).getVolume()
                            + newOrder.getVolume();
            orders.remove(newOrder.getPrice());
            orders.put(newOrder.getPrice(), new Order(newOrder.getBook(),
                                                      newOrder.getOperation(),
                                                      newOrder.getPrice(),
                                                      newVolume, newOrder.getId()
                                            ));
        } else {
            orders.put(newOrder.getPrice(), newOrder);
        }
    }
}
