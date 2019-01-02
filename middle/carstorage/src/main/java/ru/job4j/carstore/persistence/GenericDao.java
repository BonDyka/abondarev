package ru.job4j.carstore.persistence;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GenericDao<T> implements IDao<T> {
    private static final Logger LOG = LoggerFactory.getLogger(GenericDao.class);

    private final Class<T> type;

    public GenericDao(Class<T> type) {
        this.type = type;
    }

    @Override
    public void saveOrUpdate(T entity) {
        Session session = Database.INSTANCE.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(entity);
        } catch (Exception e) {
            LOG.error("Operation not complete!", e);
            throw e;
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public void delete(T entity) {
        Session session = Database.INSTANCE.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(entity);
        } catch (Exception e) {
            LOG.error("Operation not complete!", e);
            throw e;
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public T readById(long id) {
        Session session = Database.INSTANCE.openSession();
        Transaction tx = session.beginTransaction();
        try {
            return session.get(this.type, id);
        } catch (Exception e) {
            LOG.error("Operation not complete!", e);
            throw e;
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public List<T> readAll() {
        Session session = Database.INSTANCE.openSession();
        Transaction tx = session.beginTransaction();
        try {
            String sql = String.format("SELECT * FROM %ss", this.type.getSimpleName());
            return session.createSQLQuery(sql).addEntity(this.type).list();
        } catch (Exception e) {
            LOG.error("Operation not complete!", e);
            throw e;
        } finally {
            tx.commit();
            session.close();
        }
    }
}
