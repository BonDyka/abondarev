package ru.job4j.convert;

/**
 * Represent model of user.
 *
 * @author abondarev.
 * @since 19.08.2017.
 */
public class User {

    /**
     * Holds id of the user.
     */
    private int id;

    /**
     * Holds name of the user.
     */
    private String name;

    /**
     * Holds name of the city of the user.
     */
    private String city;

    /**
     * Constructor.
     *
     * @param id of the user.
     * @param name of the user.
     * @param city name of the city fo the user.
     */
    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    /**
     * Returns id of the user.
     *
     * @return id of the user.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns name of the user.
     *
     * @return name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns name of the city of the user.
     *
     * @return name of the city of the user.
     */
    public String getCity() {
        return city;
    }

    /**
     * <@inheritedDoc>.
     *
     * @param o object for equals.
     * @return <tt>true</tt> if objects are equal.
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

        if (id != user.id) {
            return false;
        }
        if (!name.equals(user.name)) {
            return false;
        }
        return city != null ? city.equals(user.city) : user.city == null;
    }

    /**
     * <@inheritedDoc>.
     *
     * @return integer that represent hashcode of the object;
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }
}
