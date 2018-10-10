package ru.job4j.jmm;

/**
 * Created for start show JMM problems.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 23.10.2017.
 */
public class Main {

    /**
     * Main.
     *
     * @param args array of arguments.
     * @throws InterruptedException exception.
     */
    public static void main(String[] args) throws InterruptedException {
        showVisibilityProblem();
        showRaceCondition();
    }

    /**
     * Shows case of visibility problem.
     *
     * @throws InterruptedException if thread interrupted.
     */
    static void showVisibilityProblem() throws InterruptedException {
        System.out.println("Visibility problem in JMM");
        VisibilityObj obj = new VisibilityObj();
        Thread first = new Thread(obj);
        Thread second = new Thread(obj);
        first.start();
        second.start();
        first.join();
        second.join();
        System.out.printf("Expected: %d\r\n", VisibilityObj.BOUND_OF_EXECUTION);
        System.out.printf("Actually: %d\r\n", obj.get());
    }

    /**
     * Shows case of race condition.
     *
     * @throws InterruptedException if thread interrupted.
     */
    static void showRaceCondition() throws InterruptedException {
        System.out.println("Race condition problem in JMM");
        RaceConditionObj obj = new RaceConditionObj();
        Thread first = new Thread(obj);
        Thread second = new Thread(obj);
        first.start();
        second.start();
        first.join();
        second.join();
        System.out.printf("Expected: %d\r\n", VisibilityObj.BOUND_OF_EXECUTION);
        System.out.printf("Actually: %d\r\n", obj.get());
    }
}
