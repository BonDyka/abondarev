package ru.job4j.carstore.models.annotated;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "fname", nullable = false)
    private String fname;

    @Column(name = "lname")
    private String lname;

    @Column(name = "phone", nullable = false)
    private String phone;

    public User() { }

    public User(String login, String password, String fname, String lname, String phone) {
        this.login = login;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return login.equals(user.login)
                && password.equals(user.password)
                && fname.equals(user.fname)
                && Objects.equals(lname, user.lname)
                && phone.equals(user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, fname, lname, phone);
    }

    @Override
    public String toString() {
        return String.format(
                "User{login = %s, password = %s, fname = %s, lname = %s, phone = %s};",
                this.login, this.password, this.fname, (this.lname != null) ? this.lname : "____",
                this.phone
        );
    }
}
