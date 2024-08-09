package com.example.userinterface.view;

public class FamilyTreeConsoleView implements FamilyTreeView {
    @Override
    public String promptForString(String prompt) {
        // Реализация метода для запроса строки у пользователя
        System.out.print(prompt);
        return new java.util.Scanner(System.in).nextLine();
    }

    @Override
    public void displayMessage(String message) {
        // Реализация метода для отображения сообщения
        System.out.println(message);
    }

    @Override
    public void showMenu() {
        // Реализация метода для отображения меню
        System.out.println("Меню:");
        System.out.println("1. Добавить человека");
        System.out.println("2. Добавить супруга");
        System.out.println("3. Получить всех детей");
        System.out.println("4. Печать древа");
        System.out.println("5. Сохранить дерево");
        System.out.println("6. Загрузить дерево");
        System.out.println("7. Выход");
    }

    @Override
    public void displayTree(String treeRepresentation) {
        // Реализация метода для отображения дерева
        System.out.println("Семейное дерево:");
        System.out.println(treeRepresentation);
    }
}
