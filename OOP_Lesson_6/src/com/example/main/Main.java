package com.example.main;

import com.example.userinterface.presenter.FamilyTreePresenter;
import com.example.userinterface.view.FamilyTreeConsoleView;
import com.example.familytree.util.FileHandler;
import com.example.familytree.model.Person;

public class Main {
    public static void main(String[] args) {
        FamilyTreeConsoleView view = new FamilyTreeConsoleView();
        FileHandler<Person> fileHandler = new FileHandler<>();
        FamilyTreePresenter presenter = new FamilyTreePresenter(view, fileHandler);
        presenter.onStart();
    }
}
