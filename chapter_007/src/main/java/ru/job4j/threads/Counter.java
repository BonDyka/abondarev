package ru.job4j.threads;

/**
 * Represent counter for text specified as parameter to a constructor
 * of the class. Instance of the class counts amount words or spaces
 * in the text if flag variable are true or false appropriates.
 *
 * @author abondarev.
 * @since 14.10.2017.
 */
public class Counter implements Runnable {

    /**
     * Text for counting.
     */
    private String text;

    /**
     * Flag that points what is it need count.
     */
    private boolean flag;

    /**
     * Constructor.
     *
     * @param text it's text for counting.
     * @param flag that points what is it need count.
     */
    public Counter(String text, boolean flag) {
        this.text = text;
        this.flag = flag;
    }


    /**
     * Created for executing counts in thread.
     */
    @Override
    public void run() {
        System.out.println(this.count());
    }

    /**
     *  If it true Counter instance count amount of word in the text otherwise
     *  it counts amount of spaces.
     *
     * @return amount world or spaces depends from flag value.
     */
    public int count() {
        int result;
        if (!this.flag) {
            result = this.countWords();
        } else {
            result = this.countSpaces();
        }
        return result;
    }

    /**
     * Returns amount of worlds in the text.
     *
     * @return amount of worlds in the text.
     */
    private int countWords() {
        return (this.text.split(" ")).length;
    }

    /**
     * Returns amount of spaces in the text.
     *
     * @return amount of spaces in the text.
     */
    private int countSpaces() {
        int result = 0;
        for (int i = 0; i < this.text.length(); i++) {
            if (' ' == this.text.charAt(i)) {
                result++;
            }
        }
        return result;
    }
}
