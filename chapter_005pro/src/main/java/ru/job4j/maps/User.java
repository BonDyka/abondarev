package ru.job4j.maps;

import java.util.Calendar;

/**
 * The class represent model of user for map tasks.
 *
 * @author abondarev.
 * @since 08.09.2017.
 */
public class User {

    /**
     * The name of the user.
     */
    private final String name;

    /**
     * The amount of children of the user.
     */
    private int children = 0;

    /**
     * Holds birth day value of the user.
     */
    private final Calendar birthDay;

    /**
     * Takes two parameters for user and based on them create instance.
     *
     * @param name of the user.
     * @param birthDay birth day of the user.
     */
    public User(String name, Calendar birthDay) {
        this.name = name;
        this.birthDay = birthDay;
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
     * Returns amount of children of the user.
     *
     * @return amount of children of the user.
     */

    public int getChildren() {
        return children;
    }

    /**
     * Sets amount of children for the user.
     *
     * @param children amount of children for the user. Can not be less than
     *                 zero.
     */
    public void setChildren(int children) {
        if (children < 0) {
            throw new IllegalArgumentException(
                    "Argument can not be less than zero.");
        }
        this.children = children;
    }

    /**
     * Returns birth day of the user.
     *
     * @return birth day of the user.
     */
    public Calendar getBirthDay() {
        return birthDay;
    }

    /**
     * Compares <tt>this</tt> with object <tt>o</tt> specified as parameter.
     * Returns <tt>true</tt> if they are equals.
     *
     * @param o object for comparision.
     * @return <tt>true</tt> if they are equals.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return name.equals(user.name) && birthDay.equals(user.birthDay);
    }

//    /**
//     * Returns integer value of hash code for the User.
//     *
//     * @return integer value of hash code for the User.
//     */
//    @Override
//    public int hashCode() {
//        int result = name.hashCode();
//        result = 31 * result + birthDay.hashCode();
//        return result;
//    }
}
