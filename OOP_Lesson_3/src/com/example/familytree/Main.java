package com.example.familytree;

import com.example.familytree.model.Person;
import com.example.familytree.tree.FamilyTree;
import com.example.familytree.io.FamilyTreeFileHandler;
import com.example.familytree.io.FamilyTreeFileHandlerImpl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FamilyTree familyTree = null;
        FamilyTreeFileHandler fileHandler = new FamilyTreeFileHandlerImpl();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        boolean running = true;
        while (running) {
            // Меню на русском языке
            System.out.println("\nМеню:");
            System.out.println("1. Создать новое дерево");
            System.out.println("2. Добавить человека");
            System.out.println("3. Получить всех детей человека");
            System.out.println("4. Вывести все дерево");
            System.out.println("5. Сохранить дерево в файл");
            System.out.println("6. Загрузить дерево из файла");
            System.out.println("7. Выйти");

            System.out.print("Выберите опцию: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    // Создание нового дерева
                    System.out.print("Введите имя коренного человека: ");
                    String rootName = scanner.nextLine();
                    System.out.print("Введите пол коренного человека (M/F): ");
                    String rootGender = scanner.nextLine();
                    System.out.print("Введите дату рождения коренного человека (yyyy-MM-dd): ");
                    Date rootBirthDate = parseDate(scanner.nextLine());
                    if (rootBirthDate == null) break;

                    System.out.print("Введите ID коренного человека: ");
                    String rootId = scanner.nextLine();

                    Person root = new Person(rootId, rootName, rootGender, rootBirthDate);
                    familyTree = new FamilyTree(root);

                    // Добавление супруга коренному человеку
                    System.out.print("Есть ли у коренного человека супруг/супруга? (да/нет): ");
                    String hasSpouse = scanner.nextLine();
                    if (hasSpouse.equalsIgnoreCase("да")) {
                        System.out.print("Введите имя супруга/супруги: ");
                        String spouseName = scanner.nextLine();
                        System.out.print("Введите пол супруга/супруги (M/F): ");
                        String spouseGender = scanner.nextLine();
                        System.out.print("Введите дату рождения супруга/супруги (yyyy-MM-dd): ");
                        Date spouseBirthDate = parseDate(scanner.nextLine());
                        if (spouseBirthDate == null) break;

                        System.out.print("Введите ID супруга/супруги: ");
                        String spouseId = scanner.nextLine();

                        Person spouse = new Person(spouseId, spouseName, spouseGender, spouseBirthDate);
                        familyTree.addSpouse(root, spouse);
                    }
                    break;
                case 2:
                    // Добавление человека
                    if (familyTree == null) {
                        System.out.println("Пожалуйста, сначала создайте или загрузите дерево.");
                        break;
                    }

                    System.out.print("Введите ID родителя: ");
                    String parentId = scanner.nextLine();
                    Person parent = familyTree.findPersonById(parentId);
                    if (parent == null) {
                        System.out.println("Родитель с таким ID не найден.");
                        break;
                    }

                    System.out.print("Введите имя ребенка: ");
                    String childName = scanner.nextLine();
                    System.out.print("Введите пол ребенка (M/F): ");
                    String childGender = scanner.nextLine();
                    System.out.print("Введите дату рождения ребенка (yyyy-MM-dd): ");
                    Date childBirthDate = parseDate(scanner.nextLine());
                    if (childBirthDate == null) break;

                    System.out.print("Введите ID ребенка: ");
                    String childId = scanner.nextLine();

                    Person newChild = new Person(childId, childName, childGender, childBirthDate); // Изменение имени переменной
                    familyTree.addPerson(parent, newChild);

                    // Добавление супруга ребенку
                    System.out.print("Есть ли у ребенка супруг/супруга? (да/нет): ");
                    String hasChildSpouse = scanner.nextLine();
                    if (hasChildSpouse.equalsIgnoreCase("да")) {
                        System.out.print("Введите имя супруга/супруги: ");
                        String childSpouseName = scanner.nextLine();
                        System.out.print("Введите пол супруга/супруги (M/F): ");
                        String childSpouseGender = scanner.nextLine();
                        System.out.print("Введите дату рождения супруга/супруги (yyyy-MM-dd): ");
                        Date childSpouseBirthDate = parseDate(scanner.nextLine());
                        if (childSpouseBirthDate == null) break;

                        System.out.print("Введите ID супруга/супруги: ");
                        String childSpouseId = scanner.nextLine();

                        Person childSpouse = new Person(childSpouseId, childSpouseName, childSpouseGender, childSpouseBirthDate);
                        familyTree.addSpouse(newChild, childSpouse); // Использование новой переменной
                    }
                    break;
                case 3:
                    // Получение всех детей человека
                    if (familyTree == null) {
                        System.out.println("Пожалуйста, сначала создайте или загрузите дерево.");
                        break;
                    }

                    System.out.print("Введите ID человека: ");
                    String personId = scanner.nextLine();
                    List<Person> children = familyTree.getAllChildren(personId);
                    if (children.isEmpty()) {
                        System.out.println("У человека нет детей.");
                    } else {
                        System.out.println("Дети человека:");
                        for (Person child : children) {
                            System.out.println("Имя: " + child.getName() + ", ID: " + child.getId());
                        }
                    }
                    break;
                case 4:
                    // Вывод всего древа
                    if (familyTree == null) {
                        System.out.println("Пожалуйста, сначала создайте или загрузите дерево.");
                        break;
                    }
                    familyTree.printTree();
                    break;
                case 5:
                    // Сохранение дерева в файл
                    if (familyTree == null) {
                        System.out.println("Пожалуйста, сначала создайте или загрузите дерево.");
                        break;
                    }
                    System.out.print("Введите имя файла для сохранения: ");
                    String saveFileName = scanner.nextLine();
                    try {
                        fileHandler.saveToFile(familyTree, saveFileName);
                        System.out.println("Дерево успешно сохранено в файл.");
                    } catch (IOException e) {
                        System.out.println("Ошибка при сохранении дерева в файл: " + e.getMessage());
                    }
                    break;
                case 6:
                    // Загрузка дерева из файла
                    System.out.print("Введите имя файла для загрузки: ");
                    String loadFileName = scanner.nextLine();
                    try {
                        familyTree = fileHandler.loadFromFile(loadFileName);
                        System.out.println("Дерево успешно загружено из файла.");
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Ошибка при загрузке дерева из файла: " + e.getMessage());
                    }
                    break;
                case 7:
                    // Выход из программы
                    running = false;
                    break;
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
            }
        }
        scanner.close();
    }

    private static Date parseDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Некорректный формат даты. Используйте yyyy-MM-dd.");
            return null;
        }
    }
}
