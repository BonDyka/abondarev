package ru.job4j.carstore.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.carstore.models.annotated.Announcement;
import ru.job4j.carstore.models.annotated.Car;
import ru.job4j.carstore.models.annotated.User;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AnnouncementDaoTest {
    private static final Database DB = Database.INSTANCE;
    private static final User USER = new GenericDao<>(User.class).readById(1);
    private static final Car CAR = new GenericDao<>(Car.class).readById(1);

    private IDao<Announcement> dao;

    private Announcement announcement;

    @Before
    public void setUp() {
        this.announcement = new Announcement(
                USER, CAR, 130_000_00, false, new ArrayList<>()
        );

        this.dao = new GenericDao<>(Announcement.class);
    }

    @After
    public void shoutDown() {
        this.dao.delete(this.announcement);
        this.announcement = null;
        this.dao = null;
    }

    @Test
    public void whenReadAllDataThenShouldReturnListOfIt() {
        List<Announcement> result = this.dao.readAll();

        assertThat(result.size(), is(1));
    }

    @Test
    public void whenAddNewEntityThenShouldAddIt() {
        this.dao.saveOrUpdate(this.announcement);

        Announcement result = this.dao.readById(this.announcement.getId());

        assertThat(result, is(this.announcement));
    }

    @Test
    public void whenUpdateExistingEntityThenItShouldBeUpdated() {
        this.dao.saveOrUpdate(this.announcement);
        long id = this.announcement.getId();
        this.announcement.setStatus(true);
        this.dao.saveOrUpdate(this.announcement);

        Announcement result = this.dao.readById(id);

        assertThat(result, is(this.announcement));
    }


}