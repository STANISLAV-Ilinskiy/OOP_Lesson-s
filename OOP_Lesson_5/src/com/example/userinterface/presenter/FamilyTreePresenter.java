package com.example.userinterface.presenter;

import com.example.familytree.model.Person;
import com.example.familytree.model.FamilyTree;
import com.example.familytree.model.Node;
import com.example.familytree.util.FileHandler;
import com.example.userinterface.view.FamilyTreeView;

import java.io.IOException;

public class FamilyTreePresenter {
    private FamilyTree<Person> familyTree;
    private final FileHandler fileHandler;
    private final FamilyTreeView view;

    public FamilyTreePresenter(FamilyTreeView view) {
        this.view = view;
        this.fileHandler = new FileHandler();
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
    }

    private void addPerson() {
        if (familyTree == null) {
            view.displayError("Пожалуйста, сначала создайте или загрузите дерево.");
            return;
        }

        String parentId = view.promptForString("Введите ID родителя: ");
        Node<Person> parent = familyTree.findNodeById(parentId, familyTree.getRoot());
        if (parent == null) {
            view.displayError("Родитель с таким ID не найден.");
            return;
        }

        Person newChild = view.promptForPersonDetails();
        familyTree.addNode(parent, newChild);

        addSpouseToNode(familyTree.findNodeById(newChild.getId(), familyTree.getRoot()));
    }

    private void getAllChildren() {
        if (familyTree == null) {
            view.displayError("Пожалуйста, сначала создайте или загрузите дерево.");
            return;
        }

        String parentId = view.promptForString("Введите ID родителя: ");
        Node<Person> parent = familyTree.findNodeById(parentId, familyTree.getRoot());

        if (parent != null) {
            Node<Person> child = parent.getLeftChild();
            while (child != null) {
                view.displayMessage("Ребенок: " + child.getData());
                child = child.getRightChild();
            }
        } else {
            view.displayError("Родитель с таким ID не найден.");
        }
    }

    private void printTree() {
        if (familyTree == null) {
            view.displayError("Пожалуйста, сначала создайте или загрузите дерево.");
            return;
        }

        StringBuilder treeStructure = new StringBuilder();
        buildTreeString(familyTree.getRoot(), 0, treeStructure);
        view.displayTree(treeStructure.toString());
    }

    private void buildTreeString(Node<Person> node, int level, StringBuilder treeStructure) {
        if (node == null) return;

        treeStructure.append("  ".repeat(level)).append(node.getData()).append("\n");
        buildTreeString(node.getLeftChild(), level + 1, treeStructure);
        buildTreeString(node.getRightChild(), level, treeStructure);
    }

    private void saveTree() {
        if (familyTree == null) {
            view.displayError("Пожалуйста, сначала создайте или загрузите дерево.");
            return;
        }

        String fileName = view.promptForString("Введите имя файла для сохранения: ");

        try {
            fileHandler.saveToFile(familyTree, fileName);
            view.displayMessage("Дерево успешно сохранено в файл.");
        } catch (IOException e) {
            view.displayError("Ошибка при сохранении дерева: " + e.getMessage());
        }
    }

    private void loadTree() {
        String fileName = view.promptForString("Введите имя файла для загрузки: ");

        try {
            familyTree = (FamilyTree<Person>) fileHandler.loadFromFile(fileName);
            view.displayMessage("Дерево успешно загружено из файла.");
        } catch (IOException | ClassNotFoundException e) {
            view.displayError("Ошибка при загрузке дерева: " + e.getMessage());
        }
    }

    private void addSpouseToNode(Node<Person> node) {
        if (node == null) return;

        String hasSpouse = view.promptForSpouseOption();
        if (hasSpouse.equalsIgnoreCase("да")) {
            Person spouse = view.promptForPersonDetails();
            familyTree.addSpouse(node, spouse);
        }
    }
}
