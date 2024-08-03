package com.example.userinterface;

import com.example.familytree.model.Person;
import com.example.familytree.tree.FamilyTree;
import com.example.familytree.tree.Node;
import com.example.familytree.util.FileHandler;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UserInterface {
    private FamilyTree<Person> familyTree;
    private final FileHandler fileHandler;
    private final Scanner scanner;

    public UserInterface() {
        this.fileHandler = new FileHandler();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("Меню:");
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
                case 1 -> createTree();
                case 2 -> addPerson();
                case 3 -> getAllChildren();
                case 4 -> printTree();
                case 5 -> saveTree();
                case 6 -> loadTree();
                case 7 -> running = false;
                default -> System.out.println("Некорректный выбор. Попробуйте снова.");
            }
        }

        scanner.close();
    }

    private void createTree() {
        System.out.print("Введите имя коренного человека: ");
        String rootName = scanner.nextLine();
        System.out.print("Введите пол коренного человека (M/F): ");
        String rootGender = scanner.nextLine();
        System.out.print("Введите дату рождения коренного человека (yyyy-MM-dd): ");
        Date rootBirthDate = parseDate(scanner.nextLine());
        if (rootBirthDate == null) return;

        System.out.print("Введите ID коренного человека: ");
        String rootId = scanner.nextLine();

        Person root = new Person(rootId, rootName, rootGender, rootBirthDate);
        familyTree = new FamilyTree<>(root);

        addSpouseToNode(familyTree.getRoot());
    }

    private void addPerson() {
        if (familyTree == null) {
            System.out.println("Пожалуйста, сначала создайте или загрузите дерево.");
            return;
        }

        System.out.print("Введите ID родителя: ");
        String parentId = scanner.nextLine();
        Node<Person> parent = familyTree.findNodeById(parentId, familyTree.getRoot());
        if (parent == null) {
            System.out.println("Родитель с таким ID не найден.");
            return;
        }

        System.out.print("Введите имя ребенка: ");
        String childName = scanner.nextLine();
        System.out.print("Введите пол ребенка (M/F): ");
        String childGender = scanner.nextLine();
        System.out.print("Введите дату рождения ребенка (yyyy-MM-dd): ");
        Date childBirthDate = parseDate(scanner.nextLine());
        if (childBirthDate == null) return;

        System.out.print("Введите ID ребенка: ");
        String childId = scanner.nextLine();

        Person newChild = new Person(childId, childName, childGender, childBirthDate);
        familyTree.addNode(parent, newChild);

        addSpouseToNode(familyTree.findNodeById(childId, familyTree.getRoot()));
    }

    private void getAllChildren() {
        if (familyTree == null) {
            System.out.println("Пожалуйста, сначала создайте или загрузите дерево.");
            return;
        }

        System.out.print("Введите ID родителя: ");
        String parentId = scanner.nextLine();
        Node<Person> parent = familyTree.findNodeById(parentId, familyTree.getRoot());

        if (parent != null) {
            Node<Person> child = parent.getLeftChild();
            while (child != null) {
                System.out.println("Ребенок: " + child.getData());
                child = child.getRightChild();
            }
        } else {
            System.out.println("Родитель с таким ID не найден.");
        }
    }

    private void printTree() {
        if (familyTree == null) {
            System.out.println("Пожалуйста, сначала создайте или загрузите дерево.");
            return;
        }

        familyTree.printTree();
    }

    private void saveTree() {
        if (familyTree == null) {
            System.out.println("Пожалуйста, сначала создайте или загрузите дерево.");
            return;
        }

        System.out.print("Введите имя файла для сохранения: ");
        String fileName = scanner.nextLine();

        try {
            fileHandler.saveToFile(familyTree, fileName);
            System.out.println("Дерево успешно сохранено в файл.");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении дерева: " + e.getMessage());
        }
    }

    private void loadTree() {
        System.out.print("Введите имя файла для загрузки: ");
        String fileName = scanner.nextLine();

        try {
            familyTree = (FamilyTree<Person>) fileHandler.loadFromFile(fileName);
            System.out.println("Дерево успешно загружено из файла.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при загрузке дерева: " + e.getMessage());
        }
    }

    private Date parseDate(String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Некорректный формат даты. Используйте yyyy-MM-dd.");
            return null;
        }
    }

    private void addSpouseToNode(Node<Person> node) {
        if (node == null) return;

        System.out.print("Есть ли у человека супруг/супруга? (да/нет): ");
        String hasSpouse = scanner.nextLine();
        if (hasSpouse.equalsIgnoreCase("да")) {
            System.out.print("Введите имя супруга/супруги: ");
            String spouseName = scanner.nextLine();
            System.out.print("Введите пол супруга/супруги (M/F): ");
            String spouseGender = scanner.nextLine();
            System.out.print("Введите дату рождения супруга/супруги (yyyy-MM-dd): ");
            Date spouseBirthDate = parseDate(scanner.nextLine());
            if (spouseBirthDate == null) return;

            System.out.print("Введите ID супруга/супруги: ");
            String spouseId = scanner.nextLine();

            Person spouse = new Person(spouseId, spouseName, spouseGender, spouseBirthDate);
            familyTree.addSpouse(node, spouse);
        }
    }
}
