package com.example.userinterface.presenter;

import com.example.familytree.service.FamilyTreeService;
import com.example.familytree.service.FileHandlerService;
import com.example.userinterface.command.*;
import com.example.userinterface.view.FamilyTreeView;

import java.util.HashMap;
import java.util.Map;

public class FamilyTreePresenter {
    private final FamilyTreeService familyTreeService;
    private final FileHandlerService fileHandlerService;
    private final FamilyTreeView view;
    private final Map<String, Command> commandMap;

    public FamilyTreePresenter(FamilyTreeService familyTreeService, FileHandlerService fileHandlerService, FamilyTreeView view) {
        this.familyTreeService = familyTreeService;
        this.fileHandlerService = fileHandlerService;
        this.view = view;
        this.commandMap = new HashMap<>();

        // Регистрация команд
        commandMap.put("1", new AddPersonCommand(familyTreeService, view));
        commandMap.put("2", new AddSpouseCommand(familyTreeService, view));
        commandMap.put("3", new GetAllChildrenCommand(familyTreeService, view));
        commandMap.put("4", new PrintTreeCommand(familyTreeService, view));
        commandMap.put("5", new SaveTreeCommand(familyTreeService, fileHandlerService, view));
        commandMap.put("6", new LoadTreeCommand(familyTreeService, fileHandlerService, view));
        commandMap.put("7", new ExitCommand(view));
    }

    public void start() {
        boolean running = true;
        while (running) {
            view.showMenu();
            String commandKey = view.promptForString("Введите команду: ");
            Command command = commandMap.get(commandKey);
            if (command != null) {
                command.execute();
                if (command instanceof ExitCommand) {
                    running = false;
                }
            } else {
                view.displayMessage("Неверная команда. Пожалуйста, попробуйте снова.");
            }
        }
    }
}
