package ru.job4j.sj.store;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
            this.createTable();
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
                "INSERT INTO users(name, login, email, create_date) SELECT ?, ?, ?, ? WHERE NOT EXISTS"
                        + " (SELECT * FROM users WHERE login = ?)")) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getEmail());
            ps.setTimestamp(4, user.getCreateDate());
            ps.setString(5, user.getLogin());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
//            try {
//                this.conn.rollback();
//            } catch (SQLException e1) {
//                LOG.error(e.getMessage(), e);
//            }
        }
    }

    @Override
    public void edit(User user) {
        LOG.info("edit user", user);
        try (Connection conn = this.dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                "UPDATE users SET email = ? WHERE login = ?;")) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getLogin());
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
                result = new User(rs.getString("name"), rs.getString("login"), rs.getString("email"),
                        rs.getTimestamp("create_date"));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<User> getAll() {
        List <User> result = new ArrayList<>();
        try (Connection conn = this.dataSource.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM users;")) {
            while (rs.next()) {
                result.add(new User(rs.getString("name"), rs.getString("login"), rs.getString("email"),
                        rs.getTimestamp("create_date")));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public void close() {
//        if (this.conn != null) {
//            try {
//                this.conn.close();
//            } catch (SQLException e) {
//                LOG.error(e.getMessage(), e);
//            }
//        }
        this.dataSource.close();
    }

    private void setDBProperties() throws IOException {
        this.prs.load(getClass().getClassLoader().getResourceAsStream(DB_PROPERTIES_FILE));
    }

    private void connect() throws Exception {
//        Class.forName(this.prs.getProperty("jdbc.driver"));
//        this.conn = DriverManager.getConnection(this.prs.getProperty("jdbc.url"),
//                this.prs.getProperty("jdbc.username"), this.prs.getProperty("jdbc.password"));

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

    private void createTable() {

        try (Connection conn = this.dataSource.getConnection();
             Statement st = conn.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS users(name TEXT NOT NULL, login TEXT NOT NULL UNIQUE,"
                    + " email TEXT NOT NULL UNIQUE, create_date TIMESTAMP NOT NULL);");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
