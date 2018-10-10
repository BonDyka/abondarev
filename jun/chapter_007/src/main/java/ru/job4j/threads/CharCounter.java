package ru.job4j.threads;

/**
 * Represent char counter for text.
 *
 * @author abondarev.
 * @since 19.10.2017.
 */
public class CharCounter implements Runnable {
    /**
     * The text for count chars.
     */
    private String text;

    /**
     * Amount of chars in text.
     */
    private int amount = 0;

    /**
     * The constructor.
     *
     * @param text the text for count chars.
     */
    public CharCounter(String text) {
        this.text = text;
    }

    /**
     * Executes counting in different thread.
     */
    @Override
    public void run() {
        System.out.println("Counter start.");
        for (char ch : text.toCharArray()) {
            if (Thread.interrupted()) {
                break;
            }
            System.out.println(amount++);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Count time out. \r\nEnd.");
                break;
            }
        }
    }
}
