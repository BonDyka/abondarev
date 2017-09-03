package ru.job4j.list;

import java.util.Iterator;

/**
 * The class represent LinkedList in his lite form.
 *
 * @param <T> type of holding elements.
 * @author abondarev.
 * @since 03.09.2017.
 */
public class SimpleLinkedList<T> implements Iterable<T> {

    /**
     * Links on first element of list.
     */
    private Node<T> firs;

    /**
     * Links on last element of list.
     */
    private Node<T> last;

    /**
     * Holds amount of list elements.
     */
    private int size = 0;

    /**
     * Add element to the list at last position.
     *
     * @param value it's element for adding.
     */
    public void add(T value) {
        final Node<T> l = this.last;
        final Node<T> newNode = new Node<T>(l, value, null);
        this.last = newNode;
        if (l == null) {
            this.firs = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    /**
     * Returns element from index position.
     *
     * @param index it's position of searched element.
     * @return element from index position.
     */
    public T get(int index) {
        checkIndex(index);
        Node<T> pointer = this.firs;
        for (int i = 0; i < index; i++) {
            pointer = pointer.next;
        }
        return pointer.value;
    }

    /**
     * Returns size of the list.
     *
     * @return size of the list.
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns an iterator instance for the list.
     *
     * @return an iterator instance for the list.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            // Links on node after last calling next().
            private Node<T> current = firs;

            @Override
            public boolean hasNext() {
                return firs.next != null;
            }

            @Override
            public T next() {
                T result = current.value;
                current = current.next;
                return result;
            }
        };
    }

    /**
     * Check index entering in bound range. If index out of bounds throws
     * IndexOutOfBoundException.
     *
     * @param index for checking.
     */
    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    /**
     * Represent node of the list that have connect with next node and
     * last node of the list.
     *
     * @param <T> type of value hold in node.
     */
    private static class Node<T> {

        /**
         * Links on previews node of the list.
         */
        private Node<T> prev;

        /**
         * Holds value of the node.
         */
        private T value;

        /**
         * Links on next node of the list.
         */
        private Node<T> next;

        /**
         * The constructor.
         *
         * @param prev instance of preview node.
         * @param value instance of value of the node.
         * @param next instance of next node.
         */
        Node(Node<T> prev, T value, Node<T> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }
}
