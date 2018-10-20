package ru.job4j.hiber.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.hiber.models.Item;

import java.sql.Timestamp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ItemDaoTest {
    private Item expected;
    private Item theSameData;
    private ItemDao dao;

    @Before
    public void setUp() {
        expected = new Item();
        expected.setDescription("New test description");
        expected.setCreated(new Timestamp(System.currentTimeMillis()));
        dao = new ItemDao();
        theSameData = new Item();
        theSameData.setCreated(expected.getCreated());
        theSameData.setDescription(expected.getDescription());
    }

    @After
    public void shoutDown() {
        dao.delete(expected);
        dao.delete(theSameData);
        expected = null;
        theSameData = null;
        dao = null;
    }

    @Test
    public void whenReadExistingEntityByIdThenShouldGetIt() throws PersistException {
        Item item = dao.read(1L);

        assertThat(item.getDescription(), is("Test description"));
    }

    @Test(expected = PersistException.class)
    public void whenTryGetItemByInvalidId() throws PersistException {
        Item item = dao.read(5L);
    }

    @Test
    public void whenTrySaveNewItemThenShouldSaveIt() {
        dao.saveOrUpdate(expected);

        Item result = dao.readAll().get(1);

        assertThat(result, is(expected));
    }

    @Test
    public void whenTrySaveItemWithExistingDataThenShouldSaveItAsNewItemData() {

        dao.saveOrUpdate(expected);
        int expectSize = dao.readAll().size();
        dao.saveOrUpdate(theSameData);
        int resultSize = dao.readAll().size();

        assertTrue(resultSize > expectSize);
    }

    @Test
    public void whenTryUpdateExistingItemThenShouldUpdateIt () throws PersistException {
        dao.saveOrUpdate(expected);
        expected.setDone(true);

        dao.saveOrUpdate(expected);

        Item result = dao.read(expected.getId());
        assertThat(result, is(expected));
        assertTrue(result.isDone() == expected.isDone());
    }

    @Test
    public void whenTryReadUndoneItemsThenShouldGetListOfIt() {
        expected.setDone(true);
        dao.saveOrUpdate(expected);

        int sise = dao.readUndone().size();

        assertTrue(sise == 1);
    }
}