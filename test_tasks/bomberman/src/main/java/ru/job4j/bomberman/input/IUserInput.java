package ru.job4j.bomberman.input;

import ru.job4j.bomberman.models.units.Direction;

/**
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 05.12.2017.
 */
public interface IUserInput {

    /**
     * Returns move direction.
     *
     * @return move direction.
     */
    Direction getMoveDirection();
}
