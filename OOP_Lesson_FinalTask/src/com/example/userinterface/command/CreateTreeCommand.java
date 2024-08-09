package com.example.userinterface.command;

import com.example.familytree.model.Person;
import com.example.familytree.service.FamilyTreeService;
import com.example.userinterface.view.FamilyTreeView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateTreeCommand implements Command {
    private final FamilyTreeService familyTreeService;
    private final FamilyTreeView view;

    public CreateTreeCommand(FamilyTreeService familyTreeService, FamilyTreeView view) {
        this.familyTreeService = familyTreeService;
        this.view = view;
    }

    @Override
    public void execute() {
        String rootId = view.promptForString("Введите ID корневого человека: ");
        String rootName = view.promptForString("Введите имя корневого человека: ");
        Date rootBirthDate = parseDate(view.promptForString("Введите дату рождения корневого человека (yyyy-MM-dd): "));
        if (rootBirthDate == null) {
            view.displayMessage("Некорректный формат даты.");
            return;
        }
        Person rootPerson = new Person(rootId, rootName, null, rootBirthDate);
        familyTreeService.createTree(rootPerson);
        view.displayMessage("Корневое лицо добавлено: " + rootPerson.getName());
    }

    private Date parseDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
