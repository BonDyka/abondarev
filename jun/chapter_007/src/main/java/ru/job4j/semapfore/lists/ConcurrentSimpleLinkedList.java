package ru.job4j.semapfore.lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represent simply threadsafe LinkedList
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 03.11.2017.
 */
public class ConcurrentSimpleLinkedList<E> implements IList<E> {

    /**
     * Pointer on first element of the list.
     */
    private Node<E> head;

    /**
     * Pointer on the last element of the list
     */
    private Node<E> tail;

    /**
     * Amount of elements of the list.
     */
    private int size;

    /**
     * Lock object for the list threadsafe using.
     */
    private final Object lock = new Object();

    /**
     * Adds element to the list if it not null and the list have place for
     * storing.
     *
     * @param elem it's element for adding to the list.
     */
    @Override
    public void add(E elem) {
        if (elem == null) {
            throw new IllegalArgumentException("Element can't be null");
        }
        synchronized (this.lock) {
            if (this.head == null) {
                this.head = new Node<>(elem, null, null);
                this.tail = this.head;
                this.size++;
            } else {
                Node<E> newNode = new Node<>(elem, null, this.tail);
                this.tail.next = newNode;
                this.tail = newNode;
            }
        }
    }

    /**
     * Returns element at the pointed position from the list.
     *
     * @param index it's index of element.
     * @return element at the pointed position from the list.
     */
    @Override
    public E get(final int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index out of current bounds.");
        }
        if (this.head == null) {
            throw new NoSuchElementException("List is empty;");
        }
        Iterator it = this.iterator();
        int counter = index;
        while (counter-- > 0) {
            it.next();
        }
        return (E) it.next();
    }

    /**
     * Returns an iterator instance for the list.
     *
     * @return an iterator instance for the list.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Node<E> pointer = head;

            @Override
            public boolean hasNext() {
                return this.pointer != null;
            }

            @Override
            public E next() {
                E res = this.pointer.value;
                this.pointer = this.pointer.next;
                return res;
            }
        };
    }

    private static class Node<E> {

        E value;

        Node<E> next;

        Node<E> prev;

        public Node(E value, Node<E> next, Node<E> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }
}
