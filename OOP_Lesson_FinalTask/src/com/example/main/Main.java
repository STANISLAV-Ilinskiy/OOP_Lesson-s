package com.example.main;

import com.example.familytree.model.Person;
import com.example.familytree.model.FamilyTree;
import com.example.familytree.service.FamilyTreeService;
import com.example.familytree.service.FileHandlerService;
import com.example.userinterface.presenter.FamilyTreePresenter;
import com.example.userinterface.view.FamilyTreeConsoleView;

public class Main {
    public static void main(String[] args) {
        // Создание объекта FileHandlerService
        FileHandlerService fileHandlerService = new FileHandlerService();

        // Создание объекта FamilyTreeService с передачей fileHandlerService
        FamilyTreeService familyTreeService = new FamilyTreeService(fileHandlerService);

        // Создание объекта FamilyTreeConsoleView
        FamilyTreeConsoleView view = new FamilyTreeConsoleView();

        // Создание объекта FamilyTreePresenter с передачей необходимых зависимостей
        FamilyTreePresenter presenter = new FamilyTreePresenter(familyTreeService, fileHandlerService, view);

        // Запуск приложения
        presenter.start();
    }
}
