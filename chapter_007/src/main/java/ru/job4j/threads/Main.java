package ru.job4j.threads;

/**
 * Created for showing work Counter class in different threads.
 *
 * @author abondarev.
 * @since 14.10.2017.
 */
public class Main {

    /**
     * Main method.
     *
     * @param args array of arguments.
     */
    public static void main(String[] args) {
        new Thread(new WordCounter("Let's dive in and see a variable in the wild.")).start();
        new Thread(new WhitespaceCounter("Let's dive in and see a variable in the wild.")).start();

    }
}
