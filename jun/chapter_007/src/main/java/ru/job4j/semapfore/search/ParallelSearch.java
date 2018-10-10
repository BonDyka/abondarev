package ru.job4j.semapfore.search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class represent interface for start parallel searching.  исправить
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 04.11.2017.
 */
public class ParallelSearch {

    private List<String> foundFiles;

    private final String root;

    private final String text;

    private final List<String> exts;

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        System.out.println("Start");
        long time = -System.currentTimeMillis();
        ParallelSearch searcher = new ParallelSearch("c:\\Program Files", "file", Arrays.asList("txt", "pdf"));
        searcher.search();
        time += System.currentTimeMillis();
        searcher.getList().forEach(System.out::println);
        System.out.println(String.format("End. Time of execution: %d. Founded %d.", time, searcher.getList().size()));
    }

    public ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
        foundFiles = new CopyOnWriteArrayList<>();
    }

    /**
     * Execute multithreaded searching.
     *
     * @throws InterruptedException if thread are interrupt.
     */
    public void search() throws InterruptedException {
        Queue<File> files = new ConcurrentLinkedQueue<>();
        Thread crawler = new Thread(new DirectoryTreeCrawler(this.root, this.exts, files));
        crawler.start();
        crawler.join();
        while (!files.isEmpty()) {
            Thread processor = new Thread(new FileProcessor(this.foundFiles, this.text, files));
            processor.start();
            processor.join();
        }

    }

    /**
     * Returns list finded files.
     *
     * @return list finded files.
     */
    public List<String> getList() {
        return foundFiles;
    }
}
