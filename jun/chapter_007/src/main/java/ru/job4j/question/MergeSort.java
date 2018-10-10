package ru.job4j.question;

import java.util.LinkedList;

/**
 * Represents merge sort for LinkedList.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 15.12.2017.
 */
public class MergeSort {

    /**
     * Sort list specified as parameter.
     *
     * @param unsorted the list for sorting
     * @param <T> type of stored elements
     */
    public static <T extends Comparable<T>> void sort(LinkedList<T> unsorted) {
        if (unsorted.size() < 2) {
            return;
        }
        int middle = unsorted.size() / 2;
        LinkedList<T> first = new LinkedList<>();
        LinkedList<T> second = new LinkedList<>();
        LinkedList<T> sorted = new LinkedList<>();
        for (int i = 0; i < unsorted.size(); i++) {
            if (i < middle) {
                first.add(unsorted.get(i));
            } else {
                second.add(unsorted.get(i));
            }
        }
        sort(first);
        sort(second);
        merge(first, second, sorted);
        unsorted.clear();
        unsorted.addAll(sorted);
    }

    /**
     * Merges two sorted list into third.
     *
     * @param first the first sorted list.
     * @param second the second sorted list.
     * @param out list for merging.
     * @param <T> type of stored elements
     */
    public static  <T extends Comparable<T>> void merge(LinkedList<T> first, LinkedList<T> second, LinkedList<T> out) {
        while (first.size() > 0 && second.size() > 0) {
            T fElem = first.getFirst();
            T sElem = second.getFirst();
            out.add((fElem.compareTo(sElem) <= 0 ? first.removeFirst() : second.removeFirst()));
        }
        if (first.size() < 1) {
            out.addAll(second);
        }
        if (second.size() < 1) {
            out.addAll(first);
        }
    }
}
