package ru.job4j.semapfore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Random;

/**
 * Represent thread safe storage for users.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 28.10.2017.
 */
@ThreadSafe
public class UserStorage {
    /**
     * The inner container.
     */
    @GuardedBy("this")
    private final HashMap<Integer, User> users;

    /**
     * The constructor.
     */
    public UserStorage() {
        users = new HashMap<>();
    }

    /**
     * Adds user specified as parameter in UserStore.
     *
     * @param user for adding.
     */
    public synchronized void add(final User user) {
        this.users.put(user.getId(), user);
    }

    /**
     * Update user with id like user specified as parameter have. If User not
     * found throws exception.
     *
     * @param user for updating.
     * @throws UserNotFoundException if user not found.
     */
    public synchronized void update(final User user) throws UserNotFoundException {
        if (this.users.containsKey(user.getId())) {
            this.users.get(user.getId()).setAmount(user.getAmount());
        } else {
            throw new UserNotFoundException("There is no that user.");
        }
    }

    /**
     * Deletes user with id specified as parameter.
     *
     * @param id fo user that need remove.
     */
    public synchronized void delete(final int id) {
        this.users.remove(id);
    }

    /**
     * Transfer amount from user with id - <tt>fromId</tt> to user with id
     * <tt>told</tt>. If some of users not found throws exception.
     *
     * @param fromId it's user id from who transfer amount.
     * @param told it's user id to who transfer amount.
     * @param amount it value of amount for transfer.
     * @throws UserNotFoundException if some of users not found.
     * @throws IllegalStateException if from user have no enough amount.
     */
    public synchronized void transfer(final int fromId, final int told, final int amount) throws UserNotFoundException {
        if (!this.users.containsKey(fromId)) {
            throw new UserNotFoundException("Bad value fromId");
        }
        if (!this.users.containsKey(told)) {
            throw new UserNotFoundException("Bad value told");
        }
        User fromUser = this.users.get(fromId);
        int newFromAmount = fromUser.getAmount() - amount;
        if (newFromAmount < 0) {
            throw new IllegalStateException("User with fromId have not enough amount");
        }
        User toldUser = this.users.get(told);
        fromUser.setAmount(newFromAmount);
        toldUser.setAmount(toldUser.getAmount() + amount);
    }

    @SuppressWarnings("ceckstyle")
    public static void main(String[] args) throws InterruptedException {
        Random rnd = new Random();
        UserStorage storage = new UserStorage();
        for (int i = 0; i < 10; i++) {
            storage.add(new User(i, rnd.nextInt(1000)));
        }
        System.out.println(storage);
        Thread first = new Thread(new MyRunnable(storage, rnd.nextInt(10), rnd.nextInt(10), 20));
        Thread second = new Thread(new MyRunnable(storage, rnd.nextInt(10), rnd.nextInt(10), 37));
        first.start();
        first.join();
        second.start();
        second.join();
        System.out.println(storage);
    }

    @Override
    @SuppressWarnings("ceckstyle")
    public String toString() {
        return users.toString();
    }

    @SuppressWarnings("ceckstyle")
    private static class MyRunnable implements Runnable {

        private UserStorage storage;
        private int fromId;
        private int told;
        private int amount;

        public MyRunnable(UserStorage storage, int fromId, int told, int amount) {
            this.storage = storage;
            this.fromId = fromId;
            this.told = told;
            this.amount = amount;
        }

        @Override
        public void run() {
            try {
                storage.transfer(fromId, told, amount);
            } catch (UserNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
