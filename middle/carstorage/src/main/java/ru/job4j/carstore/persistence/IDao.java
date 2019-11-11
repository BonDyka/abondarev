package ru.job4j.carstore.persistence;

import ru.job4j.carstore.persistence.criterias.Criteria;

import java.util.List;

/**
 * Main interface for dao classes.
 *
 * @param <T> type-parameter.
 * @author abondarev.
 * @since 18.11.2018.
 */
public interface IDao<T> {

    /**
     * The method take an entitu as parameter and if it does not contains <tt>id</tt>
     * the method save the entity and update it otherwise.
     *
     * @param entity an object for saving or updating.
     */
    void saveOrUpdate(T entity);

    /**
     * The method delete entity specified as parameter from database.
     *
     * @param entity the entity for deleting.
     */
    void delete(T entity);

    /**
     * The method take as parameter <tt>id</tt> and returns entity from database if it
     * exists or stub object with id -1 otherwise.
     *
     * @param id the id searched entity.
     * @return searched entity if it exists or stab object with id -1 otherwise.
     */
    T readById(long id);

    /**
     * The method returns <code>{@link List}</code> of objects of type <tt>T</tt>
     * if database is not empty or empty {@link List} otherwise.
     *
     * @return <code>{@link List}</code> of objects of type <tt>T</tt>
     *         if database is not empty or empty {@link List} otherwise.
     */
    List<T> readAll();

    /**
     * Reads a <code>{@link List<T>}</code> of current type objects that's
     * appropriate for specified as parameter <code>{@link Criteria<T>}</code>
     *
     * @param criteria is restriction for selection.
     * @return <code>{@link List<T>}</code> appropriate to <code>{@link Criteria<T>}</code>
     */
    List<T> readByCriteria(Criteria<T> criteria);
}
