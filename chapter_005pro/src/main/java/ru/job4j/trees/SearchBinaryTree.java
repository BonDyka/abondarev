package ru.job4j.trees;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Implements search binary tree structure.
 *
 * @param <E> type of stored elements.
 * @author abondarev.
 * @since 01.10.2017.
 */
public class SearchBinaryTree<E extends Comparable<E>> {

    /**
     * Links on first element of the tree.
     */
    private Node<E> root;

    /**
     * Holds amount of elements of the tree.
     */
    private int size;

    /**
     * Adds element to the tree. Search end node one of branches of the tree
     * and add element to the node. Element lesser than or equals root node
     * will add to left node found root otherwise to right node.
     *
     * @param elem it's element for adding.
     */
    public void add(E elem) {
        Node<E> n = new Node<>(elem);
        if (this.root == null) {
            this.root = n;
        } else {
            this.traverse(this.root, n);
        }
        size++;
    }

    /**
     * Returns amount elements of the tree.
     *
     * @return amount elements of the tree.
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns iterator for the tree elements.
     *
     * @return iterator for the tree elements.
     */
    public Iterator iterator() {
        return this.treeToList(this.root).iterator();
    }

    /**
     * Represent node for the tree.
     *
     * @param <E> type of stored element.
     */
    class Node<E extends Comparable<E>> implements Comparable<Node<E>> {

        /**
         * Value of the node.
         */
        private E value;

        /**
         * Left child of the node.
         */
        private Node<E> left;

        /**
         * Right child of the node.
         */
        private Node<E> right;

        /**
         * Te constructor. Takes value for constructing the node.
         *
         * @param value for the node.
         */
        Node(E value) {
            this.value = value;
        }

        /**
         * <@inheritedDoc>.
         *
         * @param o instance for comparision.
         * @return integer lesser than, equals or greaser than zero
         *         if this node lesser than, equals or greases than
         *         node specifies as parameter appropriate.
         */
        @Override
        public int compareTo(Node<E> o) {
            return this.value.compareTo(o.value);
        }
    }

    /**
     * Searches appropriated node for adding to it our node started from root node
     * specified as parameter.
     *
     * @param root start node.
     * @param elem it's node for adding.
     */
    private void traverse(Node<E> root, Node<E> elem) {
        if (root.compareTo(elem) <= 0) {
            if ((root.left == null)) {
                root.left = elem;
            } else {
                this.traverse(root.left, elem);
            }
        } else {
            if ((root.right == null)) {
                root.right = elem;
            } else {
                this.traverse(root.right, elem);
            }
        }
    }

    /**
     * Converts a tree from pointed root to tree.
     *
     * @param root of tree
     * @return list of tree element.
     */
    private List<E> treeToList(Node<E> root) {
        List<E> result = new ArrayList<>();
        if (root != null) {
            result.add(root.value);
            this.treeToList(root.left);
            this.treeToList(root.right);
        }
        return result;
    }
}
