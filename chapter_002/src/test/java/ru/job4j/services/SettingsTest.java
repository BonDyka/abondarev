package ru.job4j.services;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test class for {@link Settings}.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 05.01.2018.
 */
public class SettingsTest {

    @Test
    public void whenLoadFileThenGotIt() {
        Settings settings = Settings.getInstance();

        String expected = "postgres";
        String result = settings.getValue("jdbc.username");

        assertThat(result, is(expected));
    }

}