package ru.job4j.list;
/**
 * The class is checker for cycled linked list.
 *
 * @author abondarev.
 * @since 04.09.2017
 */
public class CheckCycle {

    /**
     * Checks has list, created from links to each other nodes, cycles.
     *
     * @param start it's start node for checking.
     * @param <T> type of element hold in node.
     * @return <tt>true</tt> if list has cycle.
     */
    public <T> boolean hasCycle(Node<T> start) {
        boolean result = false;
        Node<T> first = start;
        Node<T> second = start.getNext();
        while (first != null || second != null) {
            if (first.equals(second)) {
                result = true;
                break;
            }
            first = first.getNext();
            if (second.getNext() == null) {
                break;
            }
            second = second.getNext().getNext();
        }
        return result;
    }
}
