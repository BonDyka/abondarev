package ru.job4j.semapfore;

/**
 * Represent user model for multithreading.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 28.10.2017.
 */
public class User {

    /**
     * User id.
     */
    private final int id;

    /**
     * Amount of something of the user.
     */
    private int amount;

    /**
     * The constructor.
     *
     * @param id user id.
     * @param amount amount of something of the user.
     */
    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    /**
     * Returns user id.
     *
     * @return user id.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns amount of something of the user.
     *
     * @return amount of something of the user.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets amount of something of the user.
     *
     * @param amount of something of the user.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", amount=" + amount + '}';
    }
}
