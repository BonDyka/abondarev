package ru.job4j.sj.models;

import java.sql.Timestamp;

/**
 * Model of user for  servlet jsp chapter.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 03.03.2018.
 */
public class User {

    private final String name;

    private final String login;

    private final String password;

    private final String email;

    private final Timestamp createDate;

    private final Role role;

    public User(final String name, final String login, final String password,
                final String email, final Timestamp createDate, final Role role) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.createDate = createDate;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public Role getRole() {
        return role;
    }
}
