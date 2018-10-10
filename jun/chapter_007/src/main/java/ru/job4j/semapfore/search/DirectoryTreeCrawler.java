package ru.job4j.semapfore.search;

import java.io.File;
import java.util.List;
import java.util.Queue;

/**
 * The class for crawl directories tree and search all appropriated files.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 08.11.2017.
 */
public class DirectoryTreeCrawler implements Runnable {

    /**
     * Queue for storing files with appropriates extension.
     */
    private final Queue<File> files;

    /**
     * The root directory path.
     */
    private final String root;

    /**
     * Appropriates extension.
     */
    private final List<String> exts;

    public DirectoryTreeCrawler(String root, List<String> exts, Queue<File> files) {
        this.root = root;
        this.exts = exts;
        this.files = files;
    }

    /**
     * Start traverse directory tree.
     */
    public void traverse() {
        File[] content = new File(root).listFiles();
        if (content != null) {
            for (File item : content) {
                if (item.isDirectory()) {
                    Thread t = new Thread(new DirectoryTreeCrawler(item.getAbsolutePath(), this.exts, this.files));
                    t.start();
                } else {
                    if (checkExtension(item)) {
                        files.add(item);
                    }
                }
            }
        }
    }

    private boolean checkExtension(File file) {
        boolean result = false;
        for (String item : this.exts) {
            if (file.getPath().contains(item)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public void run() {
        this.traverse();
    }
}
