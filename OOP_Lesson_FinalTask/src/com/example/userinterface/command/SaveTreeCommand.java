package com.example.userinterface.command;

import com.example.familytree.model.Person;  // Добавьте этот импорт
import com.example.familytree.model.FamilyTree;
import com.example.familytree.service.FamilyTreeService;
import com.example.familytree.service.FileHandlerService;
import com.example.userinterface.view.FamilyTreeView;

public class SaveTreeCommand implements Command {
    private final FamilyTreeService familyTreeService;
    private final FileHandlerService fileHandlerService;
    private final FamilyTreeView view;

    public SaveTreeCommand(FamilyTreeService familyTreeService, FileHandlerService fileHandlerService, FamilyTreeView view) {
        this.familyTreeService = familyTreeService;
        this.fileHandlerService = fileHandlerService;
        this.view = view;
    }

    @Override
    public void execute() {
        FamilyTree<Person> tree = familyTreeService.getTree();
        boolean success = fileHandlerService.saveTree(tree);
        if (success) {
            view.displayMessage("Древо успешно сохранено.");
        } else {
            view.displayMessage("Ошибка при сохранении древа.");
        }
    }
}
