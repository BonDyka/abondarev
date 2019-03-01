package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.stream.Stream;

public class WordsFilter {
    private static final Logger LOG = LoggerFactory.getLogger(WordsFilter.class);

    public void dropAbuses(InputStream in, OutputStream out, String[] abuse) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        String line = reader.readLine();
        while (line != null) {
            for (String word : abuse) {
                if (line.contains(word)) {
                    line = line.replaceAll(word, "");
                }
            }
            writer.write(line);
            writer.flush();
            writer.newLine();
            line = reader.readLine();
        }
    }
}
