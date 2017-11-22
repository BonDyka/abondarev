package ru.job4j.nbalgo;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Represent concurrent storage for models of type {@link User} associated
 * with some key.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 21.11.2017.
 */
public class ConcurrentCash<K, V extends User> {

    private ConcurrentHashMap<K, V> container = new ConcurrentHashMap<>();

    /**
     * Adds new element of type {@link User} if its associated key doesn't
     * exist.
     *
     * @param key associated key.
     * @param value element for adding.
     */
    public void add(K key, V value) {
        if (!container.containsKey(key)) {
            container.put(key, value);
        }
    }

    /**
     * Returns element associated with specified key.
     *
     * @param key it's key for access to element.
     * @return element associated with key.
     */
    public V get(K key) {
        return container.get(key);
    }

    /**
     * Update value of type {@link User} associated with specified key if it
     * exist. If any other thread try update the value at the same time throws
     * @link OptimisticException}.
     *
     * @param key it's key of updating value.
     * @param value new value.
     * @throws OptimisticException throws if more than one thread try update
     * value at the same time.
     */
    public void update(K key, V value) throws OptimisticException {
        container.computeIfPresent(key, (k, v) -> {
            if (v.getVersion() != value.getVersion() - 1) {
                throw new OptimisticException("Attempt change updated");
            }
            return value;
        });
    }

    /**
     * Delete element associated with specified key.
     *
     * @param key of element for deletion.
     */
    public void delete(K key) {
        container.remove(key);
    }
}
