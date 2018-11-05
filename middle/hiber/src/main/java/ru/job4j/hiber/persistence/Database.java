package ru.job4j.hiber.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Database {
    public static final Database INSTANCE = new Database();

    private static SessionFactory factory;

    private Database() {}

    public void init() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    public Session openSession() {
        return factory.openSession();
    }

    public void destroy() {
        factory.close();
    }
}
