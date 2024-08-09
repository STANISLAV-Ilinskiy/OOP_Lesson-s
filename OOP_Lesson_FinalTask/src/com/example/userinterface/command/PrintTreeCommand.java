package com.example.userinterface.command;

import com.example.familytree.service.FamilyTreeService;
import com.example.userinterface.view.FamilyTreeView;

public class PrintTreeCommand implements Command {
    private final FamilyTreeService familyTreeService;
    private final FamilyTreeView view;

    public PrintTreeCommand(FamilyTreeService familyTreeService, FamilyTreeView view) {
        this.familyTreeService = familyTreeService;
        this.view = view;
    }

    @Override
    public void execute() {
        String tree = familyTreeService.printTree();
        view.displayTree(tree);
    }
}
