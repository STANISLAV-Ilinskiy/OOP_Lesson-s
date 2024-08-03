package com.example.familytree.tree;

public class Node<T> {
    private T data;
    private Node<T> leftChild;
    private Node<T> rightChild;
    private Node<T> spouse;

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }

    public Node<T> getSpouse() {
        return spouse;
    }

    public void setSpouse(Node<T> spouse) {
        this.spouse = spouse;
    }
}
