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
     * @throws InterruptedException if threads are interrupted.
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Start the program.");
        String text = "Let's dive in and see a variable in the wild.";
        Thread first = new Thread(new WordCounter(text));
        Thread second = new Thread(new WhitespaceCounter(text));
        long startTime = System.currentTimeMillis();
        long waitingTime = 1;
        first.start();
        System.out.println("Start first thread.");
        second.start();
        System.out.println("Start second thread.");
        first.join(100);
        second.join(100);
        if (System.currentTimeMillis() - startTime > waitingTime) {
            System.out.println("Interrupt threads.");
            if (!first.isInterrupted()) {
                first.interrupt();
                first.join();
            }
            if (!second.isInterrupted()) {
                second.interrupt();
                second.join();
            }
        }
        System.out.println("End of the program.");
    }
}
