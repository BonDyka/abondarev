package ru.job4j.generics;

/**
 * The abstract class, that is based for all stores. Implements a large part
 * methods from Store interface.
 *
 * @param <T> type of stored elements.
 * @author abondarev.
 * @since 31.08.2017
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {

    /**
     * The inner data structure object.
     */
    private SimpleArray<T> sArr;

    /**
     * The constructor takes as parameter size of inner data structure.
     *
     * @param size it's size of inner data structure.
     */
    public AbstractStore(int size) {
        this.sArr = new SimpleArray<>(size);
    }

    /**
     * Adds element to the store.
     *
     * @param element for adding.
     */
    @Override
    public void add(T element) {
        this.sArr.add(element);
    }

    /**
     * Updated element with specified as parameter <tt>id</tt>.
     *
     * @param id of element that need update.
     * @param newElement updated element.
     */
    @Override
    public void update(String id, T newElement) {
        int index = findElementIndex(id);
        if (index != -1) {
            this.sArr.update(index, newElement);
        }
    }

    /**
     * Deletes element from store.
     *
     * @param id of element that need delete.
     */
    @Override
    public void delete(String id) {
        int index = this.findElementIndex(id);
        if (index != -1) {
            this.sArr.delete(index);
        }
    }


    /**
     * Return element with <tt>id</tt> specified as parameter if it exist
     * or <tt>null</tt> otherwise.
     *
     * @param id searched element.
     * @return element if it exist or null otherwise.
     */
    @Override
    public T get(String id) {
        T result = null;
        int index = this.findElementIndex(id);
        if (index != -1) {
            result = this.sArr.get(index);
        }
        return result;
    }

    /**
     * Search an element with id specified as parameter. Returns position of
     * element if it found or -1.
     *
     * @param id it's id of element for search.
     * @return position of element if it found or -1.
     */
    private int findElementIndex(String id) {
        int result = -1;
        for (int i = 0; i < this.sArr.size(); i++) {
            if (sArr.get(i).getId().equals(id)) {
                result = i;
                break;
            }
        }
        return result;
    }
}
