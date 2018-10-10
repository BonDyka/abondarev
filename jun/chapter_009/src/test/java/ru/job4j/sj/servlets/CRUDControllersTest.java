package ru.job4j.sj.servlets;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.sj.models.Role;
import ru.job4j.sj.models.User;
import ru.job4j.sj.store.NotCompleteOperationException;
import ru.job4j.sj.store.UserStore;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Timestamp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class CRUDControllersTest {
    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpServletResponse response = mock(HttpServletResponse.class);
    private ServletOutputStream outputStream = mock(ServletOutputStream.class);
    private HttpSession session = mock(HttpSession.class);

    @Before
    public void setUp() throws IOException {
        when(request.getParameter("name")).thenReturn("test");
        when(request.getParameter("login")).thenReturn("test");
        when(request.getParameter("password")).thenReturn("test");
        when(request.getParameter("role_id")).thenReturn("2");
        when(request.getParameter("country")).thenReturn("1");
        when(request.getParameter("city")).thenReturn("2");
        when(response.getOutputStream()).thenReturn(outputStream);
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("login")).thenReturn("test");
    }
    @After
    public void shutDown() {
        UserStore.getInstance().delete("test");
        this.request = null;
        this.response = null;
    }

    @Test
    public void whenAddUserThenItShouldBeAdded() throws ServletException, IOException {
        AddUserController controller = new AddUserController();
        when(request.getParameter("email")).thenReturn("test@email.ru");

        controller.doPost(request, response);

        verify(request, atLeastOnce()).getParameter("login");
        verify(request, atLeastOnce()).getParameter("name");
        verify(request, atLeastOnce()).getParameter("password");
        verify(request, atLeastOnce()).getParameter("email");
        verify(request, atLeastOnce()).getParameter("city");
        verify(request, atLeastOnce()).getParameter("country");
    }

    @Test
    public void whenEditUserThanItShouldBeChanged() throws ServletException, IOException {
        Role role = new Role();
        role.setId(2);
        try {
            UserStore.getInstance().add(new User("test", "test", "test", "test", "Russia", "Moscow", new Timestamp(1L), role));
        } catch (NotCompleteOperationException e) {
            e.printStackTrace();
        }
        EditUserController controller = new EditUserController();
        String expected = "test2@email.ru";
        when(request.getParameter("email")).thenReturn(expected);
        when(request.getParameter("create")).thenReturn(UserStore.getInstance().get("test").getCreateDate().toString());

        controller.doPost(this.request, this.response);
        String result = UserStore.getInstance().get("test").getEmail();

        verify(request, atLeastOnce()).getParameter("login");
        verify(request, atLeastOnce()).getParameter("name");
        verify(request, atLeastOnce()).getParameter("password");
        verify(request, atLeastOnce()).getParameter("email");
        verify(request, atLeastOnce()).getParameter("city");
        verify(request, atLeastOnce()).getParameter("country");

        assertThat(result, is(expected));
    }

    @Test
    public void whenDeleteUserThanItShouldBeRemoved() throws ServletException, IOException {
        Role role = new Role();
        role.setId(2);
        try {
            UserStore.getInstance().add(new User("test", "test", "test", "test", "Russia", "Moscow", new Timestamp(1L), role));
        } catch (NotCompleteOperationException e) {
            e.printStackTrace();
        }
        DeleteUserController controller = new DeleteUserController();

        controller.doGet(request, response);

        verify(request, atLeastOnce()).getParameter("login");
    }
}