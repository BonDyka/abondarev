package ru.job4j.sj.models;

/**
 * User roles for web app.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 12.03.2018.
 */
public class Role {

    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
