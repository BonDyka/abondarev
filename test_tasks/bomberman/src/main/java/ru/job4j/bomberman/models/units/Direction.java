package ru.job4j.bomberman.models.units;

import java.util.Random;

/**
 * Represent driving direction for unit.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 27.11.2017.
 */
public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    /**
     * Returns random direction value.
     *
     * @return random direction value.
     */
    public static Direction getRandomDirection() {
        Random rn = new Random();
        return values()[rn.nextInt(values().length)];
    }
}
