package ru.job4j.semapfore.search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class represent interface for start parallel searching.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 04.11.2017.
 */
public class ParallelSearch implements Runnable {

    private List<String> findedFiles;

    private final String root;

    private final String text;

    private final List<String> exts;

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        System.out.println("Start");
        long time = -System.currentTimeMillis();
        ParallelSearch searcher = new ParallelSearch("c:\\Program Files", "file", Arrays.asList("txt", "pdf"));
        Thread t = new Thread(searcher);
        t.start();
        t.join();
        time += System.currentTimeMillis();
        searcher.getList().forEach(System.out::println);
        System.out.println("End." + time);
    }

    public ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
        findedFiles = new CopyOnWriteArrayList<>();
    }

    /**
     * Execute multithreaded searching.
     *
     * @throws FileNotFoundException if file not found.
     * @throws InterruptedException if thread are interrupt.
     */
    public void search() throws FileNotFoundException, InterruptedException {
        File rootDirectory = new File(root);
        File[] files = rootDirectory.listFiles();
        if (files != null) {
            for (File item : files) {
                if (item.isDirectory()) {
                    ParallelSearch searcher = new ParallelSearch(item.getAbsolutePath(), text, exts);
                    Thread t = new Thread(searcher);
                    t.start();
                    t.join();
                    findedFiles.addAll(searcher.getList());
                } else {
                    for (String extention : exts) {
                        if (item.getPath().contains(extention)) {
                            new FileProcessor(findedFiles, text, item).process();
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Returns list finded files.
     *
     * @return list finded files.
     */
    public List<String> getList() {
        return findedFiles;
    }

    @Override
    public void run() {
        try {
            search();
        } catch (FileNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
