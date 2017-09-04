package ru.job4j.list;

/**
 * The class is model for creating linked list.
 *
 * @param <T> it's type of stored value;
 * @author abondarev.
 * @since 04.09.2017
 */
public class Node<T> {

    /**
     * The value of concrete node.
     */
    private T value;

    /**
     * Link to next node.
     */
    private Node<T> next;

    /**
     * The constructor.
     *
     * @param value it's sored value into the node.
     */
    public Node(T value) {
        this.value = value;
    }

    /**
     * Returns value of the next node.
     *
     * @return value of the next node.
     */
    public T getValue() {
        return value;
    }

    /**
     * Returns link of the next node.
     *
     * @return link of the next node.
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Sets next node for the node.
     *
     * @param next instance of next node.
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }
}
