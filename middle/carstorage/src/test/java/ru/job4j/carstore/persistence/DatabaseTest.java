package ru.job4j.carstore.persistence;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseTest {

    @Test
    public void whenTryGetConnectionThenShouldObtainIt() {
        Session session = Database.INSTANCE.openSession();

        assertTrue(session != null);
    }

}