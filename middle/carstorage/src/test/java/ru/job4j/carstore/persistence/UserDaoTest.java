package ru.job4j.carstore.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.carstore.models.annotated.User;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserDaoTest {

    private static final Database DB = Database.INSTANCE;

    private IDao<User> dao;

    private User user;

    @Before
    public void setUp() {
        this.user = new User("test", "test", "test", "test", "89362358523");
        this.dao = new GenericDao<>(User.class);
    }

    @After
    public void shoutDown() {
        this.dao.delete(this.user);
        this.user = null;
        this.dao = null;
    }

    @Test
    public void whenReadAllFromDBThenReturnListExistingValues() {
        List<User> users = this.dao.readAll();

        int expected = 1;
        int result = users.size();

        assertThat(result, is(expected));
    }

    @Test
    public void whenTryAddNewUserThenItShouldBeAdded() {
        this.dao.saveOrUpdate(this.user);

        User result = this.dao.readById(this.user.getId());

        assertThat(result, is(this.user));
    }

    @Test
    public void whenUpdateExistingUserThenShouldUpdateIt() {
        this.dao.saveOrUpdate(this.user);

        this.user.setFname("test25");
        this.dao.saveOrUpdate(this.user);

        User result = this.dao.readById(this.user.getId());

        assertThat(result.getFname(), is(this.user.getFname()));
    }
}