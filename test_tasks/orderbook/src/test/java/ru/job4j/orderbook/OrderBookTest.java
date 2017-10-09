package ru.job4j.orderbook;

import org.junit.Test;

/**
 * Test orderbook task.
 *
 * @author abondarev.
 * @since 09.10.2017.
 */
public class OrderBookTest {
    /**
     * Test speed of program execution.
     */
    @Test
    public void whenParseOrdersTimeShouldBeLessSixSecond() {
        Processor processor = new Processor();
        long time = -System.currentTimeMillis();
        processor.init("d:\\orders.xml");
        time += System.currentTimeMillis();
        processor.showBooks();

        System.out.println(time);
    }
}