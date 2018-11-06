package ru.job4j.lambda;

import org.junit.Test;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MathFuncTest {

    @Test
    public void whenCalculateLinearFunctionThenShouldGetItaResult() {
        List<Double> expected = Arrays.asList(3D, 6D, 9D);

        List<Double> result = new MathFunc()
                .diapason(1, 4, (value) -> 3 * value);

        assertThat(result, is(expected));
    }

    @Test
    public void whenCalculateSquareFunctionThenShouldGetItsResult() {
        List<Double> expected = Arrays.asList(1D, 4D, 9D);

        List<Double> result = new MathFunc()
                .diapason(1, 4, (value) -> Math.pow(value, 2));

        assertThat(result, is(expected));
    }

    @Test
    public void whenCalculateLogarithmicFunctionThenShouldGetItsResult() {
        List<Double> expected = Arrays.asList(0D, 0.69D, 1.1D);

        List<Double> result = new MathFunc()
                .diapason(1, 4,
                        (value) -> (double) Math.round(Math.log(value) * 100) / 100
                );

        assertThat(result, is(expected));
    }
}