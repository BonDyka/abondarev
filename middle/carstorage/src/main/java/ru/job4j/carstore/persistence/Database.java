package ru.job4j.carstore.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Database {
    public static final Database INSTANCE = new Database();

    private static final Logger LOG = LoggerFactory.getLogger(Database.class);

    private static final SessionFactory FACTORY;

    static {
        try {
            FACTORY = new Configuration()
                    .configure()
                    .addAnnotatedClass(ru.job4j.carstore.models.annotated.Transmission.class)
                    .addAnnotatedClass(ru.job4j.carstore.models.annotated.Engine.class)
                    .addAnnotatedClass(ru.job4j.carstore.models.annotated.CarBody.class)
                    .addAnnotatedClass(ru.job4j.carstore.models.annotated.Car.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            LOG.error("Initialization error", e);
            throw new InstantiationError("Initialization error");
        }
    }

    private Database() { }

    public Session openSession() {
        return FACTORY.openSession();
    }

    public void destroy() {
        if (FACTORY != null) {
            FACTORY.close();
        }
    }
}
