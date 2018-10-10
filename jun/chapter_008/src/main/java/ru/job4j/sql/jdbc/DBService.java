package ru.job4j.sql.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for work with database.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 30.12.2017.
 */
public class DBService {

    private final static Logger LOG = LoggerFactory.getLogger(DBService.class);

    private String url;

    private String login;

    private String password;

    public DBService(String url, String login, String password) {
        this.url = url;
        this.login = login;
        this.password = password;
    }

    /**
     * Creates a table or if  the table already exist clear it.
     */
    public void createTable() {
        try (Connection conn = DriverManager.getConnection(url, login, password);
             Statement st = conn.createStatement()) {
            if (!st.execute("CREATE TABLE IF NOT EXISTS test (field INT NOT NULL)")) {
                LOG.info("table exist");
                st.execute("DELETE FROM test");
                LOG.info("table clear");
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Fills the table with values amount that specified as parameter.
     *
     * @param barrier the amount of values.
     */
    public void fillTable(int barrier) {
        try (Connection conn = DriverManager.getConnection(url, login, password)) {
            String sql = "INSERT INTO test VALUES (?);";
            conn.setAutoCommit(false);
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                for (int i = 1; i <= barrier; i++) {
                    st.setInt(1, i);
                    st.executeUpdate();
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                LOG.error(e.getMessage(), e);
            }
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public List<Entry> getAll() {
        List<Entry> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, login, password);
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT field FROM test;")) {
            while (rs.next()) {
                Entry entry = new Entry();
                entry.setField(rs.getInt("field"));
                result.add(entry);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
}
