package com.example.familytree.util;

import com.example.familytree.model.FamilyTree;

import java.io.*;

public class FileHandler {

    public void saveToFile(FamilyTree<?> tree, String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(tree);
        }
    }

    public FamilyTree<?> loadFromFile(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (FamilyTree<?>) ois.readObject();
        }
    }
}
