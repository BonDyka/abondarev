package ru.job4j.list;

/**
 * The class represent simple queue.
 *
 * @param <T> type of stored elements.
 * @author abondarev.
 * @since 03.09.2017.
 */
public class SimpleQueue<T> {
    /**
     * Inner container for storing elements.
     */
    private SimpleLinkedList<T> container;

    /**
     * The default constructor.
     */
    public SimpleQueue() {
        this.container = new SimpleLinkedList<>();
    }

    /**
     * Adds element into queue.
     *
     * @param element for adding.
     */
    public void push(T element) {
        this.container.add(element);
    }

    /**
     * Returns element from queue heads and delete it from queue.
     *
     * @return element from head of the queue.
     */
    public T poll() {
        T result = this.container.get(0);
        this.container.delete(0);
        return result;
    }

    /**
     * Returns size of the queue.
     *
     * @return size off the queue.
     */
    public int size() {
        return this.container.size();
    }
}
