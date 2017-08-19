package ru.job4j.sorrting;

/**
 * Represent users model.
 *
 * @author abondarev.
 * @since 19.08.2017.
 */
public class User implements Comparable<User> {

    /**
     * Holds name of user.
     */
    private String name;

    /**
     * Holds age of user.
     */
    private int age;

    /**
     * Constructor.
     *
     * @param name of the user
     * @param age of the user
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Returns the name of the user.
     *
     * @return name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the age of the user.
     *
     * @return age of the user.
     */
    public int getAge() {
        return age;
    }

    /**
     * <@inheritedDoc>.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     *          is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(User o) {
        return (this.age == o.age) ? 0 : (this.age > o.age) ? 1 : -1;
    }
}
