package ru.job4j.semapfore.search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Represent processor for search text in file.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 07.11.2017.
 */
public class FileProcessor {

    private final List<String> files;

    private final String text;

    private final File file;

    public FileProcessor(List<String> files, String text, File file) {
        this.files = files;
        this.text = text;
        this.file = file;
    }

    /**
     * Execute search in file.
     *
     * @throws FileNotFoundException
     */
    public void process() throws FileNotFoundException {
        Scanner scn = new Scanner(file);
        while (scn.hasNextLine()) {
            String line = scn.nextLine();
            if (line.contains(text)) {
                files.add(file.getAbsolutePath());
                break;
            }
        }
    }
}
