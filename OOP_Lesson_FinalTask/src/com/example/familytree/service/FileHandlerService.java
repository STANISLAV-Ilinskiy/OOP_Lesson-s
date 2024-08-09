package com.example.familytree.service;

import com.example.familytree.model.FamilyTree;
import com.example.familytree.model.Person;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileHandlerService {

    // Метод для сохранения древа
    public boolean saveTree(FamilyTree<Person> tree) {
        try (FileOutputStream fileOut = new FileOutputStream("familyTree.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(tree);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Метод для загрузки древа
    public FamilyTree<Person> loadTree() {
        try (FileInputStream fileIn = new FileInputStream("familyTree.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return (FamilyTree<Person>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
