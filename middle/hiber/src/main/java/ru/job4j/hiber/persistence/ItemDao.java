package ru.job4j.hiber.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.hiber.models.Item;

import java.util.List;

public class ItemDao implements GenericDao<Item> {

    @Override
    public void saveOrUpdate(Item entity) {
        try (Session session = Database.INSTANCE.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Item entity) {
        try (Session session = Database.INSTANCE.openSession()) {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public Item read(long id) throws PersistException {
        Item result;
        try (Session session = Database.INSTANCE.openSession()) {
            session.beginTransaction();
            result = session.get(Item.class, id);
        }
        if (result == null) {
            throw new PersistException(String.format("User with id: %s doesn't exist!", id));
        }
        return result;
    }

    @Override
    public List<Item> readUndone() {
        List<Item> result;
        try (Session session = Database.INSTANCE.openSession()) {
            session.beginTransaction();
            result = session.createQuery("from Item I where I.done = false", Item.class).list();
        }
        return result;
    }

    @Override
    public List<Item> readAll() {
        List<Item> result;
        try (Session session = Database.INSTANCE.openSession()) {
            session.beginTransaction();
            result = session.createQuery("from Item", Item.class).list();
        }
        return result;
    }
}
