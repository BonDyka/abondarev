package ru.job4j.convert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Converts two-dimension array to list and back side.
 *
 * @author abondarev.
 * @since 19.08.2017
 */
public class ConvertList {

    /**
     * Converts array specified as parameter into list.
     *
     * @param array of integers
     * @return List<Integer>
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> result = new ArrayList<>();
        for (int[] part : array) {
            for (int item : part) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Converts list into a two-dimension array.
     *
     * @param list for conversion.
     * @return array.
     */
    public int[][] toArray(List<Integer> list) {
        int sideLen = (int) (Math.sqrt(list.size()) + 1);
        int[][] result = new int[sideLen][sideLen];
        int row = 0;
        int col = 0;
        for (int item : list) {
            result[row][col++] = item;
            if (col >= sideLen) {
                col = 0;
                row++;
            }
        }
        if (result[sideLen - 1][0] == 0) {
            result = Arrays.copyOf(result, sideLen - 1);
        }
        return result;
    }

    /**
     * Convert list of array of integer into list of integer.
     *
     * @param list for converting.
     * @return converted list.
     */
    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] part : list) {
            if (part != null) {
                for (int item : part) {
                    result.add(item);
                }
            }
        }
        return result;
    }
}
