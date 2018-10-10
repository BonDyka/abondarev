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

    private String country;

    private String city;

    private final Timestamp createDate;

    private final Role role;

    public User(String name, String login, String password, String email, String country, String city,
                Timestamp createDate, Role role) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.createDate = createDate;
        this.role = role;
        this.country = country;
        this.city = city;
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

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public Role getRole() {
        return role;
    }
}
