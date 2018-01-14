package ru.job4j.start.store;


import org.junit.Test;
import ru.job4j.models.Item;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 06.01.2018.
 */
public class DBStorageTest {

    @Test
    public void whenAddItemShouldBeAdded() {
        String id = "as2583";
        Item item = new Item("name", "test", System.currentTimeMillis());
        item.setId(id);
        Storage storage = new DBStorage();
        storage.add(item);
        Item result = storage.findById(id);

        storage.delete(result);

        assertThat(result.getId(), is(id));
    }
}