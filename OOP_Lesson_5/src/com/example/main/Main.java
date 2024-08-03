package com.example.main;

import com.example.userinterface.presenter.FamilyTreePresenter;
import com.example.userinterface.view.FamilyTreeConsoleView;

public class Main {
    public static void main(String[] args) {
        FamilyTreeConsoleView view = new FamilyTreeConsoleView();
        FamilyTreePresenter presenter = new FamilyTreePresenter(view);
        presenter.onStart();
    }
}
