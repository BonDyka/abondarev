package ru.job4j.trees;

/**
 * Interface represent simple structure of tree.
 *
 * @param <E> type of stored elements.
 * @author abondarev.
 * @since 25.09.2017.
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {

    /**
     * Adds element child to parent. Parent can have list of child.
     *
     * @param parent parent.
     * @param child child.
     * @return true if child was add.
     */
    boolean add(E parent, E child);
}
