package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.nio.ByteBuffer;

public class CheckByte {
    private static final Logger LOG = LoggerFactory.getLogger(CheckByte.class);

    public boolean isNumber(InputStream in) {
        boolean result;
        try (InputStream input = in) {
            int len = input.available();
            byte[] buff = new byte[len];
            input.read(buff, 0, len);
            int numb = ByteBuffer.wrap(buff).getInt();
            result = (numb % 2 == 0);
        } catch (Exception e) {
            LOG.error("Is not an integer!", e);
            result = false;
        }
        return result;
    }

}
