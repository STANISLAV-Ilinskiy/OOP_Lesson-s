package com.example.userinterface.presenter;

import com.example.familytree.model.Person;
import com.example.familytree.tree.FamilyTree;
import com.example.familytree.tree.Node;
import com.example.familytree.util.FileHandler;
import com.example.userinterface.view.FamilyTreeView;
import java.util.List;
import java.util.ArrayList;

import java.io.IOException;

public class FamilyTreePresenter {
    private FamilyTree<Person> familyTree;
    private final FileHandler<Person> fileHandler;
    private final FamilyTreeView view;

    public FamilyTreePresenter(FamilyTreeView view, FileHandler<Person> fileHandler) {
        this.view = view;
        this.fileHandler = fileHandler;
    }

    public void onStart() {
        boolean running = true;

        while (running) {
            view.showMenu();
            String choice = view.promptForString("Выберите опцию: ");

            switch (choice) {
                case "1" -> createTree();
                case "2" -> addPerson();
                case "3" -> getAllChildren();
                case "4" -> printTree();
                case "5" -> saveTree();
                case "6" -> loadTree();
                case "7" -> running = false;
                default -> view.displayError("Некорректный выбор. Попробуйте снова.");
            }
        }
    }

    private void createTree() {
        Person root = view.promptForPersonDetails();
        familyTree = new FamilyTree<>(root);
        addSpouseToNode(familyTree.getRoot());
        view.displayMessage("Создано новое дерево с корневым узлом: " + root.getName());
    }

    private void addPerson() {
        if (familyTree == null) {
            view.displayError("Дерево не создано. Сначала создайте дерево.");
            return;
        }

        String parentId = view.promptForString("Введите ID родителя: ");
        Node<Person> parentNode = familyTree.findNodeById(parentId, familyTree.getRoot());

        if (parentNode == null) {
            view.displayError("Родитель с таким ID не найден.");
            return;
        }

        Person child = view.promptForPersonDetails();
        familyTree.addNode(parentNode, child);
        addSpouseToNode(familyTree.findNodeById(child.getId(), familyTree.getRoot()));

        view.displayMessage("Человек добавлен: " + child.getName());
    }

    private void getAllChildren() {
        if (familyTree == null) {
            view.displayError("Дерево не создано. Сначала создайте дерево.");
            return;
        }

        String parentId = view.promptForString("Введите ID родителя: ");
        Node<Person> parentNode = familyTree.findNodeById(parentId, familyTree.getRoot());

        if (parentNode == null) {
            view.displayError("Родитель с таким ID не найден.");
            return;
        }

        view.displayMessage("Дети родителя " + parentNode.getData().getName() + ":");
        familyTree.getChildren(parentNode).forEach(child -> view.displayMessage("- " + child));
    }

    private void printTree() {
        if (familyTree == null) {
            view.displayError("Дерево не создано. Сначала создайте дерево.");
            return;
        }

        StringBuilder treeStructure = new StringBuilder();
        buildTreeStructure(familyTree.getRoot(), treeStructure, "", true);
        view.displayTree(treeStructure.toString());
    }

    private void buildTreeStructure(Node<Person> node, StringBuilder treeStructure, String prefix, boolean isTail) {
        if (node != null) {
            treeStructure.append(prefix).append(isTail ? "└── " : "├── ")
                    .append(node.getData().getId()).append(" - ").append(node.getData().getName()).append("\n");

            if (node.getSpouse() != null) {
                treeStructure.append(prefix).append(isTail ? "    " : "│   ")
                        .append("└── Спутник жизни: ").append(node.getSpouse().toString()).append("\n");
            }

            List<Node<Person>> children = new ArrayList<>();
            Node<Person> childNode = node.getLeftChild();
            while (childNode != null) {
                children.add(childNode);
                childNode = childNode.getRightChild();
            }

            for (int i = 0; i < children.size(); i++) {
                buildTreeStructure(children.get(i), treeStructure,
                        prefix + (isTail ? "    " : "│   "), i == children.size() - 1);
            }
        }
    }

    private void saveTree() {
        if (familyTree == null) {
            view.displayError("Дерево не создано. Сначала создайте дерево.");
            return;
        }

        String fileName = view.promptForString("Введите имя файла для сохранения: ");
        try {
            fileHandler.saveToFile(familyTree, fileName);
            view.displayMessage("Дерево успешно сохранено в файл: " + fileName);
        } catch (IOException e) {
            view.displayError("Ошибка при сохранении файла: " + e.getMessage());
        }
    }

    private void loadTree() {
        String fileName = view.promptForString("Введите имя файла для загрузки: ");
        try {
            familyTree = fileHandler.loadFromFile(fileName);
            view.displayMessage("Дерево успешно загружено из файла: " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            view.displayError("Ошибка при загрузке файла: " + e.getMessage());
        }
    }

    private void addSpouseToNode(Node<Person> node) {
        String hasSpouse = view.promptForSpouseOption();
        if ("да".equalsIgnoreCase(hasSpouse)) {
            Person spouse = view.promptForPersonDetails();
            familyTree.addSpouse(node, spouse);
            view.displayMessage("Добавлен супруг/супруга: " + spouse.getName());
        }
    }
}
