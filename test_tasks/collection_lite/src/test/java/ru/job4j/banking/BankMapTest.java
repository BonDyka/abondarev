package ru.job4j.banking;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The class created for testing BankMap class.
 *
 * @author abondarev.
 * @since 25.08.2017
 */
public class BankMapTest {
    /**
     * Tests transfer operation when all accounts are found.
     */
    @Test
    public void whenAllAccountsFoundThenExecuteTransfer() {
        User firstUser = new User("Alex", "SV254683");
        Account firstUserAccount = new Account(12000, "243985610");
        User secondUser = new User("John", "BW327651");
        Account secondUserAccount = new Account(3000, "927630254");

        BankMap map = new BankMap();
        map.addUser(firstUser);
        map.addAccountToUser(firstUser, firstUserAccount);
        map.addUser(secondUser);
        map.addAccountToUser(secondUser, secondUserAccount);

        boolean result = map.transferMoney(firstUser, firstUserAccount, secondUser,
                                            secondUserAccount, 2000);
        assertThat(result, is(true));
    }

    /**
     * Tests transfer operation when amount of money aren't enough.
     */
    @Test
    public void whenAmountOfMoneyNotEnoughThenTransferNotExecuted() {
        User firstUser = new User("Alex", "SV254683");
        Account firstUserAccount = new Account(100, "243985610");
        User secondUser = new User("John", "BW327651");
        Account secondUserAccount = new Account(3000, "927630254");

        BankMap map = new BankMap();
        map.addUser(firstUser);
        map.addAccountToUser(firstUser, firstUserAccount);
        map.addUser(secondUser);
        map.addAccountToUser(secondUser, secondUserAccount);

        boolean result = map.transferMoney(firstUser, firstUserAccount, secondUser,
                                            secondUserAccount, 2000);
        assertThat(result, is(false));
    }

    /**
     * Tests deleting user.
     */
    @Test
    public void whenDeleteUserThenSizeOfMapLesserOnOne() {
        User user = new User("Alex", "SV254683");
        Account userAccount = new Account(100, "243985610");

        BankMap map = new BankMap();
        map.addUser(user);
        map.addAccountToUser(user, userAccount);
        int startSize = map.size();

        map.deleteUser(user);
        int result = map.size();

        assertThat(result, is(startSize - 1));
    }

    /**
     * Tests deleting account of user.
     */
    @Test
    public void whenDeleteUserAccountThenSizeOfListAccountsLesserOnOne() {
        User user = new User("Alex", "SV254683");
        Account firstAccount = new Account(1000, "243985610");
        Account secondAccount = new Account(5000, "246585610");

        BankMap map = new BankMap();
        map.addUser(user);
        map.addAccountToUser(user, firstAccount);
        map.addAccountToUser(user, secondAccount);
        int startSize = map.getUserAccounts(user).size();

        map.deleteUser(user);
        int result = map.size();

        assertThat(result, is(startSize - 1));
    }

}