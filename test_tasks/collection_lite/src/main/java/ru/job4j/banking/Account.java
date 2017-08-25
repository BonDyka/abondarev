package ru.job4j.banking;

/**
 * The class represents model of bank account.
 *
 * @author abondarev.
 * @since 23.08.2017.
 */
public class Account {

    /**
     * The value of the account. The Amount of money.
     */
    private int value;

    /**
     * Account requisites.
     */
    private String requisites;

    /**
     * The constructor that takes two parameters, <tt>value</tt> and <tt>requisites</tt>
     * and based on it construct an instance of the account. <tt>value</tt> can't be negative,
     * <tt>passport</tt> can't be null
     *
     * @param value of the account.
     * @param requisites of the account.
     */
    public Account(int value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    /**
     * Returns value of account.
     *
     * @return value of account.
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns requisite of account.
     *
     * @return requisite of account.
     */
    public String getRequisites() {
        return requisites;
    }

    /**
     * Sets new value for the account.
     *
     * @param value for the account.
     */
    public void setValue(int value) {
        this.value = value;
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

        Account account = (Account) o;

        if (value != account.value) {
            return false;
        }
        return requisites.equals(account.requisites);
    }

    /**
     * <@inheritedDoc>.
     *
     * @return an integer that represent hash code of the object.
     */
    @Override
    public int hashCode() {
        int result = value;
        result = 31 * result + requisites.hashCode();
        return result;
    }
}
