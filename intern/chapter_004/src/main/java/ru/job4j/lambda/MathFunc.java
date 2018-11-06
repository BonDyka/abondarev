package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Class represent function calculation in range.
 *
 * @author abondarev
 * @since 1.0
 */
public class MathFunc {

    /**
     * The method takes three parameters and calculate function,
     * specified as parameter <tt>func</tt>, from <tt>start</tt>
     * include to <tt>end</tt> excludem
     *
     * @param start included value of range.
     * @param end excluded value of range.
     * @param func the function for calculation
     * @return List of values of calculations result.
     */
    public List<Double> diapason(final int start, final int end, final Function<Double, Double> func) {
        final List<Double> result = new ArrayList<>();
        for (int idx = start; idx < end; idx++) {
            result.add(func.apply((double) idx));
        }
        return result;
    }
}
