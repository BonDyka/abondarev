package ru.job4j.sorrting;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.Collections;

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

    /**
     * Sorts list of users by name length of user.
     *
     * @param list for sorting.
     * @return sorted list.
     */
    public List<User> sortByNameLength(List<User> list) {
        Comparator<User> comparator = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().length() - o2.getName().length();
            }
        };
        Collections.sort(list, comparator);
        return list;
    }

    /**
     * Sorts list of users by all fields.
     *
     * @param list for sorting.
     * @return sorted list.
     */
    public List<User> sortByAllFields(List<User> list) {
        Comparator<User> comparator = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int firstLength = o1.getName().length();
                int secondLength = o2.getName().length();
                return (firstLength - secondLength == 0) ? o1.getAge() - o2.getAge()
                                                         : firstLength - secondLength;
            }
        };
        Collections.sort(list, comparator);
        return list;
    }
}
