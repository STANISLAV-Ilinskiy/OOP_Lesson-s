package com.example.familytree.io;

import com.example.familytree.tree.FamilyTree;

import java.io.IOException;

public interface FamilyTreeFileHandler {
    void saveToFile(FamilyTree familyTree, String filename) throws IOException;
    FamilyTree loadFromFile(String filename) throws IOException, ClassNotFoundException;
}
