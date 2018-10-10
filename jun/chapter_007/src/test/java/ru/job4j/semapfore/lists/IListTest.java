package ru.job4j.semapfore.lists;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests threadsafe implementations of IList.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 03.11.2017.
 */
public class IListTest {

    @Test
    public void testConcurrentArrayList() throws InterruptedException {
        IList<Integer> list = new ConcurrentSimpleArrayList<>();
        for (int i = 0; i < 5; i++) {
            Thread thread = new MyThread(list);
            thread.start();
            thread.join();
        }
        int expected = 25;
        Iterator<Integer> it = list.iterator();
        int result = 0;
        while (it.hasNext()) {
            it.next();
            result++;
        }
        assertThat(result, is(expected));
    }

    @Test
    public void testConcurrentLinkedList() throws InterruptedException {
        IList<Integer> list = new ConcurrentSimpleLinkedList<>();
        for (int i = 0; i < 5; i++) {
            Thread thread = new MyThread(list);
            thread.start();
            thread.join();
        }
        int expected = 25;
        Iterator<Integer> it = list.iterator();
        int result = 0;
        while (it.hasNext()) {
            it.next();
            result++;
        }
        assertThat(result, is(expected));
    }


    static class MyThread extends Thread {
        IList<Integer> list;
        public MyThread(IList<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                list.add(i);
            }
        }
    }

}