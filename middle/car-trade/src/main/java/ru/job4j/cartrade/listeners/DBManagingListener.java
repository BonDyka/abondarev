package ru.job4j.cartrade.listeners;

import ru.job4j.carstore.persistence.Database;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DBManagingListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Database db = Database.INSTANCE;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Database.INSTANCE.destroy();
    }
}
