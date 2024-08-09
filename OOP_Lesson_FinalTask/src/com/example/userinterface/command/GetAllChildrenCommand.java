package com.example.userinterface.command;

import com.example.familytree.model.Person;
import com.example.familytree.service.FamilyTreeService;
import com.example.userinterface.view.FamilyTreeView;

import java.util.List;

public class GetAllChildrenCommand implements Command {
    private final FamilyTreeService familyTreeService;
    private final FamilyTreeView view;

    public GetAllChildrenCommand(FamilyTreeService familyTreeService, FamilyTreeView view) {
        this.familyTreeService = familyTreeService;
        this.view = view;
    }

    @Override
    public void execute() {
        String parentId = view.promptForString("Введите ID родителя: ");
        List<Person> children = familyTreeService.getAllChildren(parentId);
        if (children.isEmpty()) {
            view.displayMessage("Нет детей для родителя с ID " + parentId);
        } else {
            StringBuilder message = new StringBuilder("Дети для родителя с ID " + parentId + ":\n");
            for (Person child : children) {
                message.append("  ").append(child.getName()).append(" (ID: ").append(child.getId()).append(")\n");
            }
            view.displayMessage(message.toString());
        }
    }
}