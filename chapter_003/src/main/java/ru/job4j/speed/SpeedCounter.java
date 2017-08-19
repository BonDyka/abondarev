package ru.job4j.speed;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * The class tests work speed an collection for inserting and removing big
 * amount of element.
 *
 * @author abondarev.
 * @since 19.08.2017.
 */
public class SpeedCounter {

    /**
     * Counts time to add <tt>amount</tt> of element into <tt>collection</tt>.
     *
     * @param collection for adding elements.
     * @param amount of elements for adding.
     * @return time of work the collection.
     */
    public long add(Collection<String> collection, int amount) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < amount; i++) {
            collection.add(String.format("%s",i));
        }
        long end = System.currentTimeMillis();
        return end - start;
    }
    /**
     * Counts time to remove <tt>amount</tt> of element from <tt>collection</tt>.
     *
     * @param collection for removing elements.
     * @param amount of elements for removing.
     * @return time of work the collection.
     */
    public long delete(Collection<String> collection, int amount) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < amount; i++) {
            collection.remove(String.format("%s", i));
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static void main(String[] args) {
        long timeAdd, timeRemove;
        Collection<String> collection;
        SpeedCounter counter = new SpeedCounter();
        // ArrayList
        collection = new ArrayList<>();

        timeAdd = counter.add(collection, 100_000);
        timeRemove = counter.delete(collection, 10_000);

        System.out.println(String.format("Time add: %s; time remove: %s.", timeAdd, timeRemove));

        // LinkedList
        collection = new LinkedList<>();

        timeAdd = counter.add(collection, 100_000);
        timeRemove = counter.delete(collection, 10_000);

        System.out.println(String.format("Time add: %s; time remove: %s.", timeAdd, timeRemove));

        // TreeSet
        collection = new TreeSet<>();

        timeAdd = counter.add(collection, 100_000);
        timeRemove = counter.delete(collection, 10_000);

        System.out.println(String.format("Time add: %s; time remove: %s.", timeAdd, timeRemove));
    }
}
