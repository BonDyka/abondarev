package ru.job4j.carstore.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.carstore.models.annotated.Announcement;
import ru.job4j.carstore.models.annotated.Car;
import ru.job4j.carstore.models.annotated.User;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class AnnouncementDaoTest {
    private static final Database DB = Database.INSTANCE;
    private static final User USER = new User();
    private static final Car CAR = new Car();

    private IDao<Announcement> dao;

    private Announcement announcement;

    @BeforeClass
    public static void classSetUp() {
        MigrationUtil.setMigration();
        USER.setId(1);
        CAR.setId(1);
    }

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

        assertThat(this.announcement.getId(), not(0));
        assertThat(result.getId(), is(this.announcement.getId()));
    }

    @Test
    public void whenUpdateExistingEntityThenItShouldBeUpdated() {
        this.dao.saveOrUpdate(this.announcement);
        long id = this.announcement.getId();
        this.announcement.setStatus(true);
        this.dao.saveOrUpdate(this.announcement);

        Announcement result = this.dao.readById(id);

        assertThat(result.isStatus(), is(this.announcement.isStatus()));
    }


}