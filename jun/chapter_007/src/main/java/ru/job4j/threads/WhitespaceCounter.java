package ru.job4j.threads;

/**
 * Represent whitespace counter for text specified as parameter to constructor.
 *
 * @author abondarev.
 * @since 15.10.2017.
 */
public class WhitespaceCounter implements Runnable {

    /**
     * Text for counting.
     */
    private String text;

    /**
     * Constructor.
     *
     * @param text it's text for counting.
     */
    public WhitespaceCounter(String text) {
        this.text = text;
    }

    /**
     * Created for executing counts in thread.
     */
    @Override
    public void run() {
        System.out.println(this.count());
    }

    /**
     * Counter instance count amount of spaces.
     *
     * @return amount spaces.
     */
    public int count() {
        int result = 0;
        for (int i = 0; i < this.text.length(); i++) {
            if (' ' == this.text.charAt(i)) {
                result++;
            }
        }
        return result;
    }
}
