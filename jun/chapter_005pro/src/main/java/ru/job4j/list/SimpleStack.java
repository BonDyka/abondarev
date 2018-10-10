package ru.job4j.list;

/**
 * The class represent simple stack.
 *
 * @param <T> type of stored elements.
 * @author abondarev.
 * @since 03.09.2017.
 */
public class SimpleStack<T> {

    /**
     * Inner container for storing elements.
     */
    private SimpleLinkedList<T> container;

    /**
     * The default constructor.
     */
    public SimpleStack() {
        this.container = new SimpleLinkedList<>();
    }

    /**
     * Adds element into stack.
     *
     * @param element for adding.
     */
    public void push(T element) {
        this.container.add(element);
    }

    /**
     * Returns element from stack tails and delete it from stack.
     *
     * @return element from tail of the stack.
     */
    public T poll() {
        T result = this.container.get(this.size() - 1);
        this.container.delete(this.size() - 1);
        return result;
    }

    /**
     * Returns size of the stack.
     *
     * @return size off the stack.
     */
    public int size() {
        return this.container.size();
    }
}
