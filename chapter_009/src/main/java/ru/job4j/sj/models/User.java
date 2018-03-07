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

    private final String email;

    private final Timestamp createDate;

    public User(final String name, final String login, final String email, final Timestamp createDate) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    @Override
    public String toString() {
        return String.format("User[name:%s, login:%s, email:%s, created: %s]", this.name, this.login,
                this.email, this.createDate.toString());
    }
}
