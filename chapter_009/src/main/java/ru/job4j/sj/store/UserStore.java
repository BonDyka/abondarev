package ru.job4j.sj.store;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.sj.models.Role;
import ru.job4j.sj.models.User;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Implements {@link IStorage} for storing users data into Database.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 03.03.2018.
 */
public class UserStore implements IStorage {

    private static final Logger LOG = LoggerFactory.getLogger(UserStore.class);

    private static final String DB_PROPERTIES_FILE = "db.properties";

    private static final UserStore INSTANCE = new UserStore();

    private Properties prs = new Properties();

//    private Connection conn;

    private ComboPooledDataSource dataSource;

    private UserStore() {
        try {
            this.setDBProperties();
            this.connect();
            this.createTables();
            this.fillTables();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            this.close();
            throw new IllegalStateException(e.getMessage());
        }
    }

    public static UserStore getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(User user) {
        try (Connection conn = this.dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO users(name, login, password, email, create_date, role_id) SELECT ?, ?, ?, ?, ?, ? "
                        + "WHERE NOT EXISTS (SELECT * FROM users WHERE login = ?)")) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getLogin());
            ps.setString(4, user.getEmail());
            ps.setTimestamp(5, user.getCreateDate());
            ps.setInt(6, user.getRole().getId());
            ps.setString(7, user.getLogin());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void edit(User user) {
        LOG.info("edit user", user);
        try (Connection conn = this.dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                "UPDATE users SET email = ?, password = ?, role_id = ? WHERE login = ?;")) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getRole().getId());
            ps.setString(4, user.getLogin());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void delete(String login) {
        try (Connection conn = this.dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE login = ?;")) {
            ps.setString(1, login);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public User get(String login) {
        User result = null;
        try (Connection conn = this.dataSource.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM users WHERE login = '" + login + "';")) {
            if (rs.next()) {
                result = new User(rs.getString("name"), rs.getString("login"), rs.getString("password"),
                        rs.getString("email"), rs.getTimestamp("create_date"), getRole(rs.getInt("role_id")));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try (Connection conn = this.dataSource.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM users;")) {
            while (rs.next()) {
                result.add(new User(rs.getString("name"), rs.getString("login"), rs.getString("password"),
                        rs.getString("email"), rs.getTimestamp("create_date"), getRole(rs.getInt("role_id"))));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Role getRole(int id) {
        Role result = new Role();
        try (Connection conn = this.dataSource.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM roles WHERE id=" + id + ";")) {
            if (rs.next()) {
                result.setId(id);
                result.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<Role> getRoles() {
        List<Role> result = new ArrayList<>();
        try (Connection conn = this.dataSource.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM roles;")) {
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
                result.add(role);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public void close() {
        this.dataSource.close();
    }

    private void setDBProperties() throws IOException {
        this.prs.load(getClass().getClassLoader().getResourceAsStream(DB_PROPERTIES_FILE));
    }

    private void connect() throws Exception {
        this.dataSource = new ComboPooledDataSource();
        try {
            this.dataSource.setDriverClass(this.prs.getProperty("jdbc.driver"));
            this.dataSource.setJdbcUrl(this.prs.getProperty("jdbc.url"));
            this.dataSource.setUser(this.prs.getProperty("jdbc.username"));
            this.dataSource.setPassword(this.prs.getProperty("jdbc.password"));

            this.dataSource.setMinPoolSize(10);
            this.dataSource.setAcquireIncrement(5);
            this.dataSource.setMaxPoolSize(100);

        } catch (PropertyVetoException e) {
            LOG.error("Properties for DB not found", e);
            throw new Exception(e);
        }
    }

    private void createTables() {
        try (Connection conn = this.dataSource.getConnection();
             Statement st = conn.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS roles (id SERIAL PRIMARY KEY, name TEXT);");
            st.execute("CREATE TABLE IF NOT EXISTS users(name TEXT NOT NULL, login TEXT NOT NULL UNIQUE,"
                    + "password TEXT NOT NULL, email TEXT NOT NULL UNIQUE, create_date TIMESTAMP NOT NULL,"
                    + "role_id INTEGER REFERENCES roles(id));");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private void fillTables() {
        try (Connection connection = this.dataSource.getConnection();
             Statement st = connection.createStatement()) {
            st.execute("INSERT INTO roles(name) SELECT 'admin' WHERE NOT EXISTS (SELECT name FROM roles WHERE name = 'admin');");
            st.execute("INSERT INTO roles(name) SELECT 'user' WHERE NOT EXISTS (SELECT name FROM roles WHERE name = 'user');");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        this.add(new User("admin", "admin", "admin", "admin@mail.ru", new Timestamp(System.currentTimeMillis()), this.getRole(1)));
    }
}
