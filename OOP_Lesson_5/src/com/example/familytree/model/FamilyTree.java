package com.example.familytree.model;

import java.util.*;

public class FamilyTree<T> implements Iterable<T> {
    private Node<T> root;

    public FamilyTree(T rootData) {
        this.root = new Node<>(rootData);
    }

    public Node<T> getRoot() {
        return root;
    }

    public void addNode(Node<T> parent, T childData) {
        Node<T> child = new Node<>(childData);
        if (parent.getLeftChild() == null) {
            parent.setLeftChild(child);
        } else {
            Node<T> sibling = parent.getLeftChild();
            while (sibling.getRightChild() != null) {
                sibling = sibling.getRightChild();
            }
            sibling.setRightChild(child);
        }
    }

    public void addSpouse(Node<T> node, T spouseData) {
        node.setSpouse(new Node<>(spouseData));
    }

    public Node<T> findNodeById(String id, Node<T> current) {
        if (current == null) {
            return null;
        }
        if (current.getData().toString().equals(id)) {
            return current;
        }
        Node<T> foundNode = findNodeById(id, current.getLeftChild());
        if (foundNode == null) {
            foundNode = findNodeById(id, current.getRightChild());
        }
        return foundNode;
    }

    @Override
    public Iterator<T> iterator() {
        return new TreeIterator<>(root);
    }

    private static class TreeIterator<T> implements Iterator<T> {
        private Queue<Node<T>> queue = new LinkedList<>();

        public TreeIterator(Node<T> root) {
            if (root != null) {
                queue.add(root);
            }
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public T next() {
            Node<T> node = queue.poll();
            if (node.getLeftChild() != null) queue.add(node.getLeftChild());
            if (node.getRightChild() != null) queue.add(node.getRightChild());
            return node.getData();
        }
    }

    public void printTree() {
        printSubTree(root, 0);
    }

    private void printSubTree(Node<T> node, int level) {
        if (node == null) return;

        System.out.println("  ".repeat(level) + node.getData());
        printSubTree(node.getLeftChild(), level + 1);
        printSubTree(node.getRightChild(), level);
    }
}
