package com.example.familytree.util;

import com.example.familytree.tree.FamilyTree;

import java.io.*;

public class FileHandler<T> {
    public void saveToFile(FamilyTree<T> tree, String fileName) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(tree);
        }
    }

    @SuppressWarnings("unchecked")
    public FamilyTree<T> loadFromFile(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return (FamilyTree<T>) in.readObject();
        }
    }
}
