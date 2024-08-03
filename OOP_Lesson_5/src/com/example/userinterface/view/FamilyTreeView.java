package com.example.userinterface.view;

import com.example.familytree.model.Person;

public interface FamilyTreeView {
    void showMenu();
    void displayMessage(String message);
    void displayTree(String treeStructure);
    Person promptForPersonDetails();
    String promptForString(String message);
    String promptForSpouseOption();
    void displayError(String errorMessage);
}
