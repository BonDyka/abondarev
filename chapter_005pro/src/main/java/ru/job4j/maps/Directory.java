package ru.job4j.maps;

import java.util.Iterator;

/**
 * Represent my implementation of map. Holds elements as pair key - value.
 * Access to values executes by key.
 *
 * @param <K> type of key of stored elements.
 * @param <V> type of value of sored elements.
 * @author abondarev.
 * @since 10.09.2017.
 */
public class Directory<K, V> {

    /**
     * The default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 1 << 4;

    /**
     * The default initial load factor.
     */
    private static final double DEFAULT_LOAD_FACTOR = 0.75d;

    /**
     * The array represent inner container for storing elements.
     */
    private Node<K, V>[] table;

    /**
     * Amount of stored elements.
     */
    private int size;

    /**
     * Value of current capacity.
     */
    private int capacity;

    /**
     * Value of load factor that represent percent of load the the table.
     */
    private double loadFactor;

    /**
     * The threshold of the container after that inner container change itself
     * capacity (threshold = capacity * loadFactor).
     */
    private int threshold;

    /**
     * The default constructor.
     */
    public Directory() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    /**
     * The constructor takes value of capacity as parameter.
     *
     * @param capacity it's value of capacity.
     */
    public Directory(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * The constructor takes two parameters(capacity & loadFactor) and based
     * on it values construct instance of directory.
     *
     * @param capacity it's value of capacity.
     * @param loadFactor it's value of loadFactor.
     */
    public Directory(int capacity, double loadFactor) {
        if (capacity > Integer.MAX_VALUE / 2 || capacity < 0) {
            throw new IllegalArgumentException("Illegal value of capacity");
        }
        if (loadFactor > 1d || loadFactor < 0.1d) {
            throw new IllegalArgumentException("Illegal value of loadFactor.");
        }
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(this.capacity);
        this.table = new Node[this.capacity];
    }

    /**
     * Insert element into inner container.
     *
     * @param key for access to value.
     * @param value of element.
     * @return return true if element added.
     */
    public boolean insert(K key, V value) {
        boolean result = false;
        if (key != null) {
            checkSize();
            int h = hash(key);
            int index = hashToIndex(h);
            if (this.table[index] == null) {
                this.table[index] = new Node<>(h, key, value);
                size++;
                result = true;
            }
        }
        return result;
    }

    /**
     * Returns value of element associated with key.
     *
     * @param key for access to value.
     * @return value of element or null if element associated with key not found.
     */
    public V get(K key) {
        V result = null;
        if (key == null) {
            throw new IllegalArgumentException("Invalid argument");
        }
        int index = hashToIndex(hash(key));
        if (this.table[index] != null && this.table[index].key.equals(key)) {
            result = this.table[index].value;
        }
        return result;
    }

    /**
     * Deletes element from inner container by its key.
     *
     * @param key of element.
     * @return true if element deleted.
     */
    public boolean delete(K key) {
        boolean result = false;
        if (key != null) {
            int index = hashToIndex(hash(key));
            if (this.table[index] != null) {
                this.table[index] = null;
                size--;
                result = true;
            }
        }
        return false;
    }

    /**
     * Returns amount of stored elements.
     *
     * @return amount of stored elements.
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns iterator for the Directory elements.
     *
     * @return iterator for the Directory elements.
     */
    public Iterator<Node<K, V>> iterator() {
        return new Iterator<Node<K, V>>() {

            private int pointer = 0;

            @Override
            public boolean hasNext() {

                boolean result = false;

                while (pointer < capacity) {
                    if (table[pointer++] != null) {
                        result = true;
                        break;
                    }
                }
                return result;
            }

            @Override
            public Node<K, V> next() {
                return table[pointer++];
            }
        };
    }

    /**
     * Calculate max amount elements hold in table after that table will be
     * resize.
     *
     * @param cap capacity of table.
     * @return threshold.
     */
    private int tableSizeFor(int cap) {
        double result = (double) cap * loadFactor;
        return (int) result;
    }

    /**
     * Check size of the table and resize it if need.
     */
    private void checkSize() {
        if (this.size + 1 >= threshold) {
            Node<K, V>[] oldTable = this.table;
            this.table = new Node[this.capacity << 1];
            System.arraycopy(oldTable, 0, this.table, 0, oldTable.length);
            this.capacity = this.table.length;
            this.threshold = tableSizeFor(this.capacity);
        }
    }

    /**
     * The class represents bucket for storing pair key - value.
     *
     * @param <K> type of key.
     * @param <V> type of value.
     */
    private class Node<K, V> {

        /**
         * The hash of bucket.
         */
        private final int hash;

        /**
         * The key for access to associated value.
         */
        private final K key;

        /**
         * The value associated with key.
         */
        private V value;

        /**
         * The constructor.
         *
         * @param hash for node.
         * @param key key for node.
         * @param value value for node.
         */
        Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Calculate hash-function for key specified as parameter.
     *
     * @param key instance for whose calculating hash-function.
     * @param <K> type of key.
     * @return result calculation hash-function.
     */
    private static <K> int hash(K key) {
        int h;
        return  (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * Calculate index by hash of key.
     *
     * @param hash of key.
     * @return index for element.
     */
    private int hashToIndex(int hash) {
        return hash & (this.capacity - 1);
    }
}