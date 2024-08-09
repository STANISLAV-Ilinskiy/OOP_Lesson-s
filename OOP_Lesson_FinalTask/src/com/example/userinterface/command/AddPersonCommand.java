package com.example.userinterface.command;

import com.example.familytree.model.Person;
import com.example.familytree.service.FamilyTreeService;
import com.example.userinterface.view.FamilyTreeView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddPersonCommand implements Command {
    private final FamilyTreeService familyTreeService;
    private final FamilyTreeView view;

    public AddPersonCommand(FamilyTreeService familyTreeService, FamilyTreeView view) {
        this.familyTreeService = familyTreeService;
        this.view = view;
    }

    @Override
    public void execute() {
        String id = view.promptForString("Введите ID человека: ");
        String name = view.promptForString("Введите имя человека: ");
        Date birthDate = parseDate(view.promptForString("Введите дату рождения (yyyy-MM-dd): "));
        if (birthDate == null) {
            view.displayMessage("Некорректный формат даты.");
            return;
        }
        // Обновите создание объекта Person в зависимости от конструкторов
        Person person = new Person(id, name, null, birthDate); // Например, если `spouseId` не нужен, передайте `null`
        String parentId = view.promptForString("Введите ID родителя (если есть, оставьте пустым): ");
        familyTreeService.addPerson(parentId, person);
        view.displayMessage("Человек добавлен: " + person.getName());
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