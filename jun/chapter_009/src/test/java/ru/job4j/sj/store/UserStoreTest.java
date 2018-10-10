package ru.job4j.sj.store;

import org.junit.After;
import org.junit.Test;
import ru.job4j.sj.models.Role;
import ru.job4j.sj.models.User;

import java.sql.Timestamp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserStoreTest {

    @After
    public void shutDown() {
        UserStore.getInstance().delete("test");
    }

    @Test
    public void whenGetUserThenShouldObtainIt() {
        User user = UserStore.getInstance().get("admin");

        assertThat(user.getRole().getName(), is("admin"));
    }

    @Test
    public void whenAddUserThenItShouldBeAdded() throws NotCompleteOperationException {

        UserStore store = UserStore.getInstance();

        store.add(createUser());
        User result = store.get("test");

        assertThat(result.getName(), is("test"));
    }

    @Test(expected = NotCompleteOperationException.class)
    public void whenAddExistingUserThenShouldGetException() throws NotCompleteOperationException {
        UserStore store = UserStore.getInstance();
        store.add(createUser());

        store.add(createUser());
    }

    @Test
    public void whenEditUserThenItShouldChanged() throws NotCompleteOperationException {
        UserStore store = UserStore.getInstance();
        User user = createUser();
        store.add(user);

        store.edit(new User("user", "test", "user", "test@mail.ru", user.getCountry(),
                user.getCity(), user.getCreateDate(), user.getRole()));

        assertThat(store.get("test").getName(), is("user"));
    }

    public User createUser() {
        Role role = new Role();
        role.setId(2);
        role.setName("user");
        return new User("test", "test", "test", "test@mail.ru", "Russia", "Moscow",
                new Timestamp(System.currentTimeMillis()), role);
    }
}