package ru.job4j.trees;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class implements SimpleTree.
 *
 * @param <E> type of stored elements.
 * @author abondarev.
 * @since 25.09.2017.
 */
public class Tree<E extends  Comparable<E>> implements SimpleTree<E> {

    /**
     * Amount of stored elements.
     */
    private int size;

    /**
     * The root of the tree.
     */
    private Node<E> root;

    /**
     * The default constructor.
     */
    public Tree() {

    }

    /**
     * Returns amount of stored elements.
     *
     * @return amount of stored elements.
     */
    public int size() {
        return this.size;
    }

    /**
     * Searches pointed as parameter <tt>parent</tt> node. If node found
     * compare its children with pointed as parameter <tt>child</tt>. If
     * if equal child doesn't exist add pointed as parameter <tt>child</tt>
     * to children list and return <tt>true</tt>. Returns <tt>false</tt>
     * otherwise.
     *
     * @param parent parent.
     * @param child child.
     * @return <tt>true</tt> if child was add.
     */
    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        if (this.root == null) {
            this.root = new Node<>(parent);
            this.size = 1;
        }
        Node<E> par = this.findNode(this.root, new Node<>(parent));
        if (par != null) {
            Node<E> ch = new Node<>(child);
            if (this.findNode(this.root, ch) == null) {
                par.children.add(ch);
                size++;

            }
        }
        return false;
    }

    /**
     * Checks the tree is binary or not.
     *
     * @return <tt>true</tt> if tree is binary.
     */
    public boolean isBinary() {
        return !this.traverse(this.root);
    }

    /**
     * Returns an iterator instance for this tree.
     *
     * @return an iterator instance for this tree.
     */
    @Override
    public Iterator<E> iterator() {
        List<E> values = new ArrayList<>(this.size);
        this.treeToList(this.root, values);
        return values.iterator();
    }

    /**
     * Class represents nodes of the tree.
     *
     * @param <E> type of stored values.
     */
    private class Node<E extends Comparable<E>> implements Comparable<Node<E>> {

        /**
         * List of children of the node.
         */
        private List<Node<E>> children;

        /**
         * Value of the node.
         */
        private E value;

        /**
         * The constructor.
         *
         * @param value of the node.
         */
        Node(E value) {
            this.value = value;
            this.children = new ArrayList<>(2);
        }

        /**
         * Compares this.node with node specified as parameter.
         *
         * @param node for comparision.
         * @return Negative, zero or positive value if node, specified as
         *         parameter, are bigger, equal or lesser than this node.
         */
        @Override
        public int compareTo(Node<E> node) {
            return this.value.compareTo(node.value);
        }
    }

    /**
     * Searches <tt>Node<E></tt> in tree with <tt>root</tt> that equals
     * <tt>searched</tt> node specified as parameter.
     *
     * @param root it's the tree root.
     * @param searched it's node for comparision.
     * @return searched node if it exist or null otherwise.
     */
    private Node<E> findNode(Node<E> root, Node<E> searched) {
        Node<E> result = null;
        if (root.compareTo(searched) == 0) {
            result = root;
        } else {
            for (Node<E> node : root.children) {
                result = findNode(node, searched);
                if (result != null) {
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Collects all values from a tree with the <tt>root</tt> into <tt>list</tt>
     * specified as parameter.
     *
     * @param root it's root of a tree.
     * @param list it's list for storing values.
     */
    private void treeToList(Node<E> root, List<E> list) {
        if (root != null) {
            list.add(root.value);
            for (Node<E> node: root.children) {
                this.treeToList(node, list);
            }
        }
    }

    /**
     * Checks contains at least one of node of the tree more then two child.
     *
     * @param root it's root of the tree.
     * @return true if there is node with more than two children.
     */
    private boolean traverse(Node<E> root) {
        boolean result = root.children.size() > 2;
        if (!result) {
            for (Node<E> node : root.children) {
                result = this.traverse(node);
                if (result) {
                    break;
                }
            }
        }
        return result;
    }
}
