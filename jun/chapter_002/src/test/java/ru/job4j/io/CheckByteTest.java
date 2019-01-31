package ru.job4j.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CheckByteTest {
    @Test
    public void whenReadPositiveEvenDigitThenReturnTrue() {
        InputStream in = new ByteArrayInputStream(ByteBuffer.allocate(4).putInt(2).array());
        CheckByte cb = new CheckByte();

        boolean result = cb.isNumber(in);

        assertThat(result, is(true));
    }

    @Test
    public void whenReadNegativeEvenDigitThenReturnTrue() {
        InputStream in = new ByteArrayInputStream(ByteBuffer.allocate(4).putInt(-2).array());
        CheckByte cb = new CheckByte();

        boolean result = cb.isNumber(in);

        assertThat(result, is(true));
    }

    @Test
    public void whenReadPositiveOddDigitThenReturnFalse() {
        InputStream in = new ByteArrayInputStream(ByteBuffer.allocate(4).putInt(7).array());
        CheckByte cb = new CheckByte();

        boolean result = cb.isNumber(in);

        assertThat(result, is(false));
    }

    @Test
    public void whenReadNegativeOddDigitThenReturnFalse() {
        InputStream in = new ByteArrayInputStream(ByteBuffer.allocate(4).putInt(-7).array());
        CheckByte cb = new CheckByte();

        boolean result = cb.isNumber(in);

        assertThat(result, is(false));
    }

    @Test
    public void whenReadPositiveMaxIntegerThenReturnFalse() {
        InputStream in = new ByteArrayInputStream(ByteBuffer.allocate(4).putInt(Integer.MAX_VALUE).array());
        CheckByte cb = new CheckByte();

        boolean result = cb.isNumber(in);

        assertThat(result, is(false));
    }

    @Test
    public void whenReadNegativeMinIntegerThenReturnTrue() {
        InputStream in = new ByteArrayInputStream(ByteBuffer.allocate(4).putInt(Integer.MIN_VALUE).array());
        CheckByte cb = new CheckByte();

        boolean result = cb.isNumber(in);

        assertThat(result, is(true));
    }


}