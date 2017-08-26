package ru.job4j.convert;

import java.util.HashMap;
import java.util.List;

/**
 * Shows converting list into map.
 *
 * @author abondarev.
 * @since 19.08.2017.
 */
public class UserConvert {

    /**
     * Takes list of users and convert it into hash map where key is users id.
     *
     * @param list for converting.
     * @return HashMap that contains all elements from list of users.
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<>();
        for (User user : list) {
            result.put(user.getId(), user);
        }
        return result;
    }
}
