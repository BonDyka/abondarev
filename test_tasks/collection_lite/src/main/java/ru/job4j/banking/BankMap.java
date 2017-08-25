package ru.job4j.banking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The class represent a wrapper for HashMap for working with users and his
 * bank accounts.
 *
 * @author abondarev.
 * @since 23.08.2017.
 */
public class BankMap {

    /**
     * Holds all users with them accounts.
     */
    private Map<User, List<Account>> usersAccounts;

    /**
     * The constructor.
     */
    public BankMap() {
        this.usersAccounts = new HashMap<>();
    }

    /**
     * Adds user instance into inner map as key with value an empty list of accounts
     * of the user. User can't be <tt>null</tt>.
     *
     * @param user instance for adding as key.
     */
    public void addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Bad user instance.");
        }
        this.usersAccounts.put(user, new ArrayList<Account>());
    }

    /**
     * Deletes user from inner map.
     *
     * @param user for removing.
     */
    public void deleteUser(User user) {
        this.usersAccounts.remove(user);
    }

    /**
     * Takes as parameter user(as key for map) and account for adding to list
     * of accounts of the user.
     *
     * @param user as key.
     * @param account for adding.
     */
    public void addAccountToUser(User user, Account account) {
        this.usersAccounts.get(user).add(account);
    }

    /**
     * Takes as parameter user(as key for map) and account for removing from list
     * of accounts of the user.
     *
     * @param user as key.
     * @param account for removing.
     */
    public void deleteAccountFromUser(User user, Account account) {
        this.usersAccounts.get(user).remove(account);
    }

    /**
     * Returns list of accounts for the user.
     *
     * @param user as key.
     * @return list of accounts.
     */
    public List<Account> getUserAccounts(User user) {
        return new ArrayList<Account>(this.usersAccounts.get(user));
    }

    /**
     * Executes transfer of money from <tt>srcAccount</tt> of <tt>srcUser</tt> to
     * <tt>dstAccount</tt> of <tt>dstUser</tt>. Transfer can not be executed if
     * <tt>srcUser</tt>, <tt>srcAccount</tt>, <tt>dstUser</tt> or <tt>dstAccount</tt>
     * aren't found. Return <tt>true</tt> only if transfer operation executed.
     *
     * @param srcUser it's user from whose account executes transfer operation.
     * @param srcAccount it's account from which executes transfer operation.
     * @param dstUser it's user to whose account executes transfer operation.
     * @param dstAccount it's account to which executes transfer operation.
     * @param amount it's amount of money for transfer operation.
     * @return <tt>true</tt> if and only if transfer  operation was executing.
     */
    public boolean transferMoney(User srcUser, Account srcAccount, User dstUser,
                                 Account dstAccount, int amount) {
        boolean result = false;
        Account srcFound = this.findAccount(srcUser, srcAccount);
        Account dstFound = this.findAccount(dstUser, dstAccount);
        if (srcAccount.equals(srcFound) && dstAccount.equals(dstFound)
                && srcFound.getValue() >= amount) {
            srcFound.setValue(srcFound.getValue() - amount);
            dstFound.setValue(dstFound.getValue() + amount);
            result = true;
        }
        return result;
    }

    /**
     * Search the account in list accounts of user specified as parameter.
     *
     * @param user is a key for the usersAccounts.
     * @param account account for comparision.
     * @return account instance if it exist otherwise return <tt>null</tt>.
     */
    private Account findAccount(User user, Account account) {
        Account result = new Account(0, null);
        if (user != null) {
            for (Account item : this.usersAccounts.get(user)) {
                if (item != null && account.equals(item)) {
                    result = item;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Returns amount of elements into inner map.
     *
     * @return amount of elements into inner map.
     */
    public int size() {
        return this.usersAccounts.size();
    }
}
