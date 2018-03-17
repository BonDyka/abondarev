package ru.job4j.sj.servlets;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.sj.models.Role;
import ru.job4j.sj.models.User;
import ru.job4j.sj.store.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class CRUDControllersTest {
    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpServletResponse response = mock(HttpServletResponse.class);

    @Before
    public void setUp() {
        when(request.getParameter("name")).thenReturn("test");
        when(request.getParameter("login")).thenReturn("test");
        when(request.getParameter("password")).thenReturn("test");
        when(request.getParameter("role_id")).thenReturn("2");
    }
    @After
    public void shutDown() {
        this.request = null;
        this.response = null;
    }

    @Test
    public void whenAddUserThenItShouldBeAdded() throws ServletException, IOException {
        AddUserController controller = new AddUserController();
        when(request.getParameter("email")).thenReturn("test@email.ru");

        controller.doPost(request, response);
        String result = UserStore.getInstance().getAll().get(1).getLogin();

        verify(request, atLeastOnce()).getParameter("login");

        assertThat(result, is("test"));
    }

    @Test
    public void whenEditUserThanItShouldBeChanged() throws ServletException, IOException {
        Role role = new Role();
        role.setId(2);
        UserStore.getInstance().add(new User("test", "test", "test", "test", new Timestamp(1L), role));
        EditUserController controller = new EditUserController();
        String expected = "test2@email.ru";
        when(request.getParameter("email")).thenReturn(expected);
        when(request.getParameter("create")).thenReturn(UserStore.getInstance().get("test").getCreateDate().toString());

        controller.doPost(this.request, this.response);
        String result = UserStore.getInstance().get("test").getEmail();

        assertThat(result, is(expected));
    }

    @Test
    public void whenDeleteUserThanItShouldBeRemoved() throws ServletException, IOException {
        Role role = new Role();
        role.setId(2);
        UserStore.getInstance().add(new User("test", "test", "test", "test", new Timestamp(1L), role));
        DeleteUserController controller = new DeleteUserController();
        int expected = UserStore.getInstance().getAll().size() - 1;

        controller.doGet(request, response);
        int result = UserStore.getInstance().getAll().size();

        assertThat(result, is(expected));
    }
}