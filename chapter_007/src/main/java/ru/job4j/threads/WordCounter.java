package ru.job4j.threads;

/**
 * Represent word counter for text specified as parameter to constructor.
 *
 * @author abondarev.
 * @since 14.10.2017.
 */
public class WordCounter implements Runnable {

    /**
     * Text for counting.
     */
    private String text;

    /**
     * Constructor.
     *
     * @param text it's text for counting.
     */
    public WordCounter(String text) {
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
     * Counter instance count amount of word in the text.
     *
     * @return amount world.
     */
    public int count() {
        return (this.text.split(" ")).length;
    }
}
