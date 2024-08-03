package com.example.userinterface.view;

import com.example.familytree.model.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class FamilyTreeConsoleView implements FamilyTreeView {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void showMenu() {
        System.out.println("Меню:");
        System.out.println("1. Создать новое дерево");
        System.out.println("2. Добавить человека");
        System.out.println("3. Получить всех детей человека");
        System.out.println("4. Вывести все дерево");
        System.out.println("5. Сохранить дерево в файл");
        System.out.println("6. Загрузить дерево из файла");
        System.out.println("7. Выйти");
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void displayTree(String treeStructure) {
        System.out.println(treeStructure);
    }

    @Override
    public Person promptForPersonDetails() {
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        System.out.print("Введите пол (M/F): ");
        String gender = scanner.nextLine();
        System.out.print("Введите дату рождения (yyyy-MM-dd): ");
        Date birthDate = parseDate(scanner.nextLine());
        System.out.print("Введите ID: ");
        String id = scanner.nextLine();

        return new Person(id, name, gender, birthDate);
    }

    @Override
    public String promptForString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    @Override
    public String promptForSpouseOption() {
        System.out.print("Есть ли у человека супруг/супруга? (да/нет): ");
        return scanner.nextLine();
    }

    @Override
    public void displayError(String errorMessage) {
        System.out.println("Ошибка: " + errorMessage);
    }

    private Date parseDate(String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            displayError("Некорректный формат даты. Используйте yyyy-MM-dd.");
            return null;
        }
    }
}
