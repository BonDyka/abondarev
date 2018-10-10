package ru.job4j.sj.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.sj.models.Role;
import ru.job4j.sj.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implements storage for storing users data into Database.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 03.03.2018.
 */
public class UserStore {

    private static final Logger LOG = LoggerFactory.getLogger(UserStore.class);

    private static final UserStore INSTANCE = new UserStore();

    private UserStore() { }

    public static UserStore getInstance() {
        return INSTANCE;
    }

    public void add(User user) throws NotCompleteOperationException {
        LOG.info("Trying add user", user);
        String query = String.format(
                "%s %s %s", "INSERT INTO users(name, login, password, email, create_date, role_id, city_id)",
                "SELECT ?, ?, ?, ?, ?, ?, (SELECT id FROM cities WHERE name = ?)",
                "WHERE NOT EXISTS (SELECT login, email FROM users WHERE login=? AND email=?);"
        );
        try (Connection conn = ConnectionsPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getLogin());
            ps.setString(4, user.getEmail());
            ps.setTimestamp(5, user.getCreateDate());
            ps.setInt(6, user.getRole().getId());
            ps.setString(7, user.getCity());
            ps.setString(8, user.getLogin());
            ps.setString(9, user.getPassword());
            ps.executeUpdate();
            LOG.info("User added", user);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new NotCompleteOperationException("User not added", e);
        }
    }

    public void edit(User user) {
        String query = String.format("%s %s", "UPDATE users SET name=?, password=?, email=?,",
                "role_id=?, city_id=(SELECT id FROM cities WHERE name=?) WHERE login=?;");
        LOG.info("Try edit user", user);
        try (Connection conn = ConnectionsPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setInt(4, user.getRole().getId());
            ps.setString(5, user.getCity());
            ps.setString(6, user.getLogin());
            ps.executeUpdate();
            LOG.info("User changed", user);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void delete(String login) {
        try (Connection conn = ConnectionsPool.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE login = ?;")) {
            ps.setString(1, login);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public User get(String login) {
        User result = null;
        String query = String.format(
                "%s %s%s%s %s %s",
                "SELECT u.name, u.login, u.password, u.email, u.create_date, u.role_id, r.name as role,",
                "p.city, p.country FROM (SELECT * FROM users WHERE login = '", login,
                "') AS u INNER JOIN roles as r ON r.id = u.role_id ",
                "INNER JOIN (SELECT cities.id, cities.name as city, country.name as country FROM cities",
                "INNER JOIN country ON cities.country_id = country.id) as p ON u.city_id = p.id;"
        );
        try (Connection conn = ConnectionsPool.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("role_id"));
                role.setName(rs.getString("role"));
                result = new User(rs.getString("name"), rs.getString("login"), rs.getString("password"),
                        rs.getString("email"), rs.getString("country"), rs.getString("city"),
                        rs.getTimestamp("create_date"), role
                );
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public List<User> getAll() {
        String query = String.format(
                "%s %s %s", "SELECT u.name, u.login, u.password, u.email, u.create_date, u.role_id, r.name AS role,",
                "t.name AS city, c.name AS country FROM users AS u INNER JOIN roles AS r ON u.role_id=r.id",
                "INNER JOIN cities AS t ON u.city_id=t.id INNER JOIN country AS c ON t.country_id=c.id;"
        );
        List<User> result = new ArrayList<>();
        try (Connection conn = ConnectionsPool.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                result.add(
                        new User(rs.getString("name"), rs.getString("login"), rs.getString("password"),
                        rs.getString("email"), rs.getString("country"), rs.getString("city"),
                        rs.getTimestamp("create_date"), getRole(rs.getInt("role_id")))
                );
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public Role getRole(int id) {
        Role result = new Role();
        try (Connection conn = ConnectionsPool.getConnection();
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

    public List<Role> getRoles() {
        List<Role> result = new ArrayList<>();
        try (Connection conn = ConnectionsPool.getConnection();
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

    public Map<Integer, String> getCountries() {
        Map<Integer, String> result = new HashMap<>();
        try (Connection conn = ConnectionsPool.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM country;")) {
            while (rs.next()) {
                result.put(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public Map<Integer, String> getCitiesOfCountry(int countryId) {
        Map<Integer, String> result = new HashMap<>();
        try (Connection conn = ConnectionsPool.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM cities WHERE country_id=" + countryId)) {
            while (rs.next()) {
                result.put(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
}
