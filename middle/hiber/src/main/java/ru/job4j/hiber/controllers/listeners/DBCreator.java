package ru.job4j.hiber.controllers.listeners;

import ru.job4j.hiber.persistence.Database;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DBCreator implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Database.INSTANCE.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Database.INSTANCE.destroy();
    }
}
