package ru.job4j.banking;

/**
 * The class represent model of bank user.
 *
 * @author abondarev.
 * @since 23.08.2017.
 */
public class User {

    /**
     * The name of the user.
     */
    private String name;

    /**
     * The passport info of the user.
     */
    private String passport;

    /**
     * The constructor that takes two parameters, <tt>name</tt> and <tt>passport</tt>,
     * and based on it construct an instance of the user. No one can be null.
     *
     * @param name of the user
     * @param passport info of the user.
     */
    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    /**
     * Returns the name of the user.
     *
     * @return the name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the passport info of the user.
     *
     * @return the passport info of the user.
     */
    public String getPassport() {
        return passport;
    }

    /**
     * <@inheritedDoc>.
     *
     * @param o instance for comparison.
     * @return true if this objects are equals.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (!name.equals(user.name)) {
            return false;
        }
        return passport.equals(user.passport);
    }

    /**
     * <@inheritedDoc>.
     *
     * @return an integer that represent hash code of the object.
     */
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + passport.hashCode();
        return result;
    }
}
