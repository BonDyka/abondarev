package ru.job4j.sj.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.sj.models.User;

import java.io.IOException;
import java.sql.*;
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

    private Connection conn;

    private UserStore() {
        try {
            this.setDBProperties();
            this.connect();
            this.createTable();
        } catch (IOException | SQLException | ClassNotFoundException e) {
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
        try (PreparedStatement ps = this.conn.prepareStatement(
                "INSERT INTO users(name, login, email, create_date) VALUES (?, ?, ?, ?)")) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getEmail());
            ps.setTimestamp(4, user.getCreateDate());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            try {
                this.conn.rollback();
            } catch (SQLException e1) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public void edit(User user) {
        try (PreparedStatement ps = this.conn.prepareStatement(
                "UPDATE users SET email = ? WHERE login = '?';")) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getLogin());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            try {
                this.conn.rollback();
            } catch (SQLException e1) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public void delete(String login) {
        try (PreparedStatement ps = this.conn.prepareStatement("DELETE FROM users WHERE login = '?';")) {
            ps.setString(1, login);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            try {
                this.conn.rollback();
            } catch (SQLException e1) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public User get(String login) {
        User result = null;
        try (Statement st = this.conn.createStatement();
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
    public void close() {
        if (this.conn != null) {
            try {
                this.conn.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    private void setDBProperties() throws IOException {
        this.prs.load(getClass().getClassLoader().getResourceAsStream(DB_PROPERTIES_FILE));
    }

    private void connect() throws SQLException, ClassNotFoundException {
        Class.forName(this.prs.getProperty("jdbc.driver"));
        this.conn = DriverManager.getConnection(this.prs.getProperty("jdbc.url"),
                this.prs.getProperty("jdbc.username"), this.prs.getProperty("jdbc.password"));
    }

    private void createTable() {
        try (Statement st = this.conn.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS users(name TEXT NOT NULL, login TEXT NOT NULL UNIQUE,"
                    + " email TEXT NOT NULL UNIQUE, create_date TIMESTAMP NOT NULL);");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
