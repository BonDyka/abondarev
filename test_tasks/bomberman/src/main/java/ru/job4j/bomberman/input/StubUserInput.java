package ru.job4j.bomberman.input;

import ru.job4j.bomberman.models.units.Direction;

/**
 * Stub for user input.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 05.12.2017.
 */
public class StubUserInput implements IUserInput {

    private final static long TIME_FOR_CHANGING = 5793L;

    private long time = System.currentTimeMillis();

    private Direction direction = Direction.getRandomDirection();

    @Override
    public Direction getMoveDirection() {
        if (System.currentTimeMillis() - time >= TIME_FOR_CHANGING) {
            Direction newDir = direction;
            while (newDir == direction) {
                newDir = Direction.getRandomDirection();
            }
            time = System.currentTimeMillis();
            direction = newDir;
        }
        return direction;
    }
}
