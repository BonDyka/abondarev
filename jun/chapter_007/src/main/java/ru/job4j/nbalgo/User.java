package ru.job4j.nbalgo;

/**
 * Represent model for storing in {@link ConcurrentCash}.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 22.11.2017.
 */
public class User {

    private String name;

    private volatile int version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (this.name != null) {
            this.version++;
        }
        this.name = name;
    }

    public int getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        User user = (User) o;

        if (version != user.version) {
            return false;
        }
        return name.equals(user.name);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + version;
        return result;
    }
}
