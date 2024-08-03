package com.example.familytree.tree;

public class Node<T> {
    private final T data;
    private Node<T> leftChild;
    private Node<T> rightChild;
    private T spouse;

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

    public T getSpouse() {
        return spouse;
    }

    public void setSpouse(T spouse) {
        this.spouse = spouse;
    }
}