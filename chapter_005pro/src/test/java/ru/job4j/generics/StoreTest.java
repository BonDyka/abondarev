package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * The class created for testing all implementation of Store interface.
 *
 * @author abondarev.
 * @since 02.09.2017.
 */
public class StoreTest {

    //UserStore implementation.

    /**
     * Test methods add() and get().
     */
    @Test
    public void whenAddElementThenItMustBeAdded() {
        String id = "re34ho2";
        User user = new User(id);
        Store<User> store = new UserStore(1);
        store.add(user);
        User result = store.get(id);
        assertThat(result, is(user));
    }

    /**
     * Test method get() if element not exist.
     */
    @Test
    public void whenSearchedElementNotExistShouldReturnNull() {
        Store<User> store = new UserStore(2);
        User result = store.get("ret");
        assertNull(result);
    }

    // RoleStore implementation

    /**
     * Tests method update().
     */
    @Test
    public void whenCalledUpdateThenElementShouldBeUpdated() {
        String oldId = "45u2kj";
        String newId = "kj348ds";
        Role newElement = new Role(newId);
        Store<Role> store = new RoleStore(1);
        store.add(new Role(oldId));
        store.update(oldId, newElement);
        Role result = store.get(newId);
        assertThat(result, is(newElement));
    }

    /**
     * Tests method delete().
     */
    @Test
    public void whenCalledDeleteThenElementShouldBeDeleted() {
        String id = "kr4po09";
        Store<Role> store = new RoleStore(1);
        store.add(new Role(id));
        store.delete(id);
        Role result = store.get(id);
        assertNull(result);
    }
}