package com.example.userinterface.command;

import com.example.userinterface.view.FamilyTreeView;

public class ExitCommand implements Command {
    private final FamilyTreeView view;

    public ExitCommand(FamilyTreeView view) {
        this.view = view;
    }

    @Override
    public void execute() {
        view.displayMessage("Выход из программы.");
    }
}

