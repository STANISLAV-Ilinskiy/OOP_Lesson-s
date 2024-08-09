package com.example.userinterface.command;

import com.example.familytree.model.Person;
import com.example.familytree.service.FamilyTreeService;
import com.example.userinterface.view.FamilyTreeView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddSpouseCommand implements Command {
    private final FamilyTreeService familyTreeService;
    private final FamilyTreeView view;

    public AddSpouseCommand(FamilyTreeService familyTreeService, FamilyTreeView view) {
        this.familyTreeService = familyTreeService;
        this.view = view;
    }

    @Override
    public void execute() {
        String personId = view.promptForString("Введите ID человека: ");
        String spouseId = view.promptForString("Введите ID супруга: ");
        String name = view.promptForString("Введите имя супруга: ");
        Date birthDate = parseDate(view.promptForString("Введите дату рождения супруга (yyyy-MM-dd): "));
        if (birthDate == null) {
            view.displayMessage("Некорректный формат даты.");
            return;
        }
        Person spouse = new Person(spouseId, name, personId, birthDate); // Передайте personId как spouseId
        familyTreeService.addSpouseToNode(personId, spouse);
        view.displayMessage("Супруг(а) добавлен(а): " + spouse.getName());
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
