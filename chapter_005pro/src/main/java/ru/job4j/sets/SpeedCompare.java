package ru.job4j.sets;

/**
 * The class compare insertion speed for two implementation of Set.
 *
 * @author abondarev
 * @since 07.09.2017
 */
public class SpeedCompare {

    /**
     * Returns difference between first and second inserting speed for first
     * and second implementation of Set.
     *
     * @param first instance of implementation of Set.
     * @param second instance of implementation of Set.
     * @param amount it's amount of elements for inserting.
     * @return difference between first and second inserting speed for first
     *         and second implementation of Set.
     */
    public int compare(Set<Integer> first, Set<Integer> second, int amount) {
        return (int) (countTime(first, amount) - countTime(second, amount));
    }

    /**
     * Count time of inserting specified amount of elements to concrete
     * implementation of Set.
     *
     * @param set instance of Set.
     * @param amount of elements.
     * @return time of addition.
     */
    private long countTime(Set<Integer> set, int amount) {
        long start = System.nanoTime();
        for (int i = 0; i < amount; i++) {
            set.add(i);
        }
        long end = System.nanoTime();
        return end - start;
    }
}
