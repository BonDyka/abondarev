package ru.job4j.carstore.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.carstore.models.annotated.Announcement;
import ru.job4j.carstore.models.annotated.Photo;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PhotoDaoTest {
    private static final Database DB = Database.INSTANCE;
    private static final Announcement ANNOUNCEMENT = new GenericDao<>(Announcement.class).readById(1);

    private IDao<Photo> dao;
    private Photo photo;

    @BeforeClass
    public static void classSetUp() {
        MigrationUtil.setMigration();
    }

    @Before
    public void setUp() {
        this.dao = new GenericDao<>(Photo.class);
        this.photo = new Photo("/photos/test/img_001.jpg");
        this.photo.setAnnouncement(ANNOUNCEMENT);
    }

    @After
    public void shoutDown() {
        this.dao.delete(this.photo);
        this.photo = null;
        this.dao = null;
    }

    @Test
    public void whenSaveNewEntityThenItShouldBeSaved() {
        this.dao.saveOrUpdate(this.photo);

        Photo result = this.dao.readById(this.photo.getId());

        assertThat(result, is(this.photo));
    }

    @Test
    public void whenUpdateExistingEntityThenShouldUpdateIt() {
        this.dao.saveOrUpdate(this.photo);
        this.photo.setPath("/photo/test/img_002.jpg");
        this.dao.saveOrUpdate(this.photo);
        Photo result = this.dao.readById(this.photo.getId());

        assertThat(result, is(this.photo));
    }

    @Test
    public void whenReadAllDataThenShouldGetListOfTheseData() {
        this.dao.saveOrUpdate(this.photo);
        int result = this.dao.readAll().size();

        assertThat(result, is(1));
    }
}