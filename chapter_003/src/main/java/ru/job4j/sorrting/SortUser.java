package ru.job4j.sorrting;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Shows sort of collection.
 *
 * @author abondarev.
 * @since 19.08.2017.
 */
public class SortUser {

    /**
     * Takes list of users and returns set of users sorted in order growing
     * ages of users.
     *
     * @param list of users.
     * @return set of users.
     */
    public Set<User> sort(List<User> list) {
        Set<User> result = new TreeSet<>();
        for (User user : list) {
            result.add(user);
        }
        return result;
    }
}
