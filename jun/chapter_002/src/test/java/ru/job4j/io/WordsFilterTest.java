package ru.job4j.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class WordsFilterTest {
    private final String testString = "Здесь есть запрещенное слово.";
    private ByteArrayInputStream in;
    private ByteArrayOutputStream out;

    @Before
    public void setUp() {
        this.in = new ByteArrayInputStream(testString.getBytes());
        this.out = new ByteArrayOutputStream();
    }

    @After
    public void shotDown() throws IOException {
        this.in.close();
        this.out.close();
    }

    @Test
    public void whenDataNoContainsAbusesThenShouldOutputOriginalData() throws IOException {
        WordsFilter wf = new WordsFilter();

        wf.dropAbuses(this.in, this.out, new String[] {"тест"});

        assertThat(this.out.toString(), is(this.testString));
    }

    @Test
    public void whenDataContainsAbusesThenShouldOutputDataWithoutItAbuses() throws IOException {
        WordsFilter wf = new WordsFilter();
        String expected = "Здесь есть  слово.";

        wf.dropAbuses(this.in, this.out, new String[] {"запрещенное"});

        assertThat(this.out.toString(), is(expected));
    }

}