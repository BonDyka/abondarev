package ru.job4j.orderbook;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Represent processor for converting xml file to order books.
 *
 * @author abondarev.
 * @since 09.10.2017.
 */
public class Processor {

    /**
     * Contains orders book.
     */
    private Map<String, Book> books = new HashMap<>();

    /**
     * Contains parsed unsorted orders.
     */
    private Map<Integer, Order> unsortedOrders = new HashMap<>();

    /**
     * Initiate work the processor for processing file.
     *
     * @param fileName full file name.
     */
    public void init(String fileName) {
        this.parse(fileName);
        this.sortOrders();
        this.matchOrders();
    }

    /**
     * Shows all books content.
     */
    public void showBooks() {
        for (Book book : books.values()) {
            book.show();
        }
    }

    /**
     * Sorts orders to its book.
     */
    private void sortOrders() {
        for (Order order : unsortedOrders.values()) {
            String bookName = order.getBook();
            if (this.books.containsKey(bookName)) {
                this.books.get(bookName).addOrder(order);
            } else {
                Book book = new Book(bookName);
                book.addOrder(order);
                this.books.put(bookName, book);
            }
        }
        this.unsortedOrders.clear();
    }

    /**
     * Match orders from books.
     */
    private void matchOrders() {
        for (Book book : this.books.values()) {
            book.matchOrders();
        }
    }

    /**
     * Parses XML file from path pointed as parameter.
     *
     * @param fileName full file name.
     */
    private void parse(String fileName) {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        InputStream in;
        XMLStreamReader reader;
        try {
            in = new FileInputStream(fileName);
            reader = factory.createXMLStreamReader(in);
            while (reader.hasNext()) {
                reader.next();
                if (reader.isStartElement()) {
                    this.handle(reader);
                }
            }
            reader.close();
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks nod add or delete order.
     *
     * @param reader stream from parsed XML.
     */
    private void handle(XMLStreamReader reader) {
        if ("AddOrder".equals(reader.getLocalName())) {
            addToUnsorted(reader);
        }
        if ("DeleteOrder".equals(reader.getLocalName())) {
            deleteFromUnsorted(reader);
        }
    }

    /**
     * Adds orders read from XML to unsorted orders.
     *
     * @param reader stream from parsed XML.
     */
    private void addToUnsorted(XMLStreamReader reader) {
        String bookName = reader.getAttributeValue(0);
        Order.Type type = "SELL".equals(reader.getAttributeValue(1))
                ? Order.Type.SELL
                : Order.Type.BUY;
        Double price = Double.valueOf(reader.getAttributeValue(2));
        int volume = Integer.valueOf(reader.getAttributeValue(3));
        int id = Integer.valueOf(reader.getAttributeValue(4));
        this.unsortedOrders.put(id, new Order(bookName, type, price, volume, id));
    }

    /**
     * Deletes orders read from XML from unsorted orders.
     *
     * @param reader stream from parsed XML.
     */
    private void deleteFromUnsorted(XMLStreamReader reader) {
        int id = Integer.valueOf(reader.getAttributeValue(1));
        this.unsortedOrders.remove(id);
    }

}
