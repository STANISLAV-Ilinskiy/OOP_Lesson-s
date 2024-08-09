package com.example.userinterface.command;

import com.example.familytree.service.FamilyTreeService;
import com.example.familytree.service.FileHandlerService;
import com.example.userinterface.view.FamilyTreeView;

public class LoadTreeCommand implements Command {
    private final FamilyTreeService familyTreeService;
    private final FileHandlerService fileHandlerService;
    private final FamilyTreeView view;

    public LoadTreeCommand(FamilyTreeService familyTreeService, FileHandlerService fileHandlerService, FamilyTreeView view) {
        this.familyTreeService = familyTreeService;
        this.fileHandlerService = fileHandlerService;
        this.view = view;
    }

    @Override
    public void execute() {
        boolean success = familyTreeService.loadTree(fileHandlerService.loadTree());
        if (success) {
            view.displayMessage("Древо успешно загружено.");
        } else {
            view.displayMessage("Ошибка при загрузке древа.");
        }
    }
}
