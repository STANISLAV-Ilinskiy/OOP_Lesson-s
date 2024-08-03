package com.example.familytree.tree;

import java.util.ArrayList;
import java.util.List;

public class FamilyTree<T> {
    private final Node<T> root;

    public FamilyTree(T rootData) {
        this.root = new Node<>(rootData);
    }

    public Node<T> getRoot() {
        return root;
    }

    public void addNode(Node<T> parent, T childData) {
        Node<T> childNode = new Node<>(childData);
        if (parent.getLeftChild() == null) {
            parent.setLeftChild(childNode);
        } else {
            Node<T> current = parent.getLeftChild();
            while (current.getRightChild() != null) {
                current = current.getRightChild();
            }
            current.setRightChild(childNode);
        }
    }

    public void addSpouse(Node<T> node, T spouseData) {
        node.setSpouse(spouseData);
    }

    public Node<T> findNodeById(String id, Node<T> currentNode) {
        if (currentNode == null) {
            return null;
        }

        if (currentNode.getData().toString().contains(id)) {
            return currentNode;
        }

        Node<T> foundNode = findNodeById(id, currentNode.getLeftChild());
        if (foundNode != null) {
            return foundNode;
        }

        return findNodeById(id, currentNode.getRightChild());
    }

    public List<T> getChildren(Node<T> parent) {
        List<T> children = new ArrayList<>();
        Node<T> child = parent.getLeftChild();
        while (child != null) {
            children.add(child.getData());
            child = child.getRightChild();
        }
        return children;
    }
}