package ru.job4j.carstore.persistence.criterias;

/**
 * Represent interface for obtaining part of query that restrict selection.
 *
 * @param <E> an entity type for that is doing selection.
 */
public interface Criteria<E> {

    /**
     * Returns restricted part of query.
     *
     * @return restricted part of query.
     */
    String queryRestriction();
}
