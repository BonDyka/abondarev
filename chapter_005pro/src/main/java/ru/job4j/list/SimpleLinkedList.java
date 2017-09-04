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
    private Node<T> first;

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
            this.first = newNode;
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
        Node<T> result = this.node(index);
        return result.value;
    }

    /**
     * Deletes element from index position.
     *
     * @param index it's element position.
     */
    public void delete(int index) {
        Node<T> node = this.node(index);
        final Node<T> n = node.next;
        final Node<T> p = node.prev;

        if (p == null) {
            this.first = n;
        } else {
            p.next = n;
            node.prev = null;
        }

        if (n == null) {
            this.last = p;
        } else {
            n.prev = p;
            node.next = null;
        }

        node.value = null;
        this.size--;
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
            private Node<T> current = first;

            @Override
            public boolean hasNext() {
                return first.next != null;
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
     * Returns a node at index position.
     *
     * @param index of node position.
     * @return node instance from index position.
     */
    private Node<T> node(int index) {
        checkIndex(index);
        Node<T> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result;
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
