package ru.job4j.semapfore.search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * Represent processor for search text in file.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 07.11.2017.
 */
public class FileProcessor implements Runnable {

    private static Lock locker = new Lock();

    private final List<String> foundFiles;

    private final String text;

    private final Queue<File> files;

    public FileProcessor(List<String> foundFiles, String text, Queue<File> files) {
        this.foundFiles = foundFiles;
        this.text = text;
        this.files = files;
    }

    /**
     * Execute search in file.
     *
     * @throws FileNotFoundException
     */
    public void process() throws FileNotFoundException, InterruptedException {
        locker.lock();
        File file = files.poll();
        Scanner scn = new Scanner(file);
        while (scn.hasNextLine()) {
            String line = scn.nextLine();
            if (line.contains(text)) {
                foundFiles.add(file.getAbsolutePath());
                break;
            }
        }
        locker.unlock();
    }

    @Override
    public void run() {
        try {
            process();
        } catch (FileNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * The custom locker that let four threads are executing simultanously.
     */
    private static class Lock {

        private int counter = 4;

        private synchronized void lock() throws InterruptedException {
            if (counter <= 0) {
                wait();
            }
            counter--;
        }

        private synchronized void unlock() {
            notify();
            counter++;
        }
    }
}
