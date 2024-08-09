package com.example.familytree.service;

import com.example.familytree.model.FamilyTree;
import com.example.familytree.model.Person;

import java.util.List; // Импорт для List
import java.util.ArrayList; // Импорт для ArrayList
import java.util.Comparator; // Импорт для Comparator

public class FamilyTreeService {
    private FamilyTree<Person> familyTree;
    private final FileHandlerService fileHandlerService;

    public FamilyTreeService(FileHandlerService fileHandlerService) {
        this.familyTree = new FamilyTree<>();
        this.fileHandlerService = fileHandlerService;
    }

    public FamilyTree<Person> getTree() {
        return familyTree;
    }

    public void setTree(FamilyTree<Person> tree) {
        this.familyTree = tree;
    }

    // Метод для загрузки древа
    public void loadTree() {
        FamilyTree<Person> loadedTree = fileHandlerService.loadTree();
        if (loadedTree != null) {
            this.familyTree = loadedTree;
        }
    }

    // Метод для получения списка всех людей
    public List<Person> getAllPeople() {
        return new ArrayList<>(familyTree.getAllPeople());
    }

    // Метод для сортировки по имени
    public List<Person> getPeopleSortedByName() {
        List<Person> people = getAllPeople();
        people.sort(Comparator.comparing(Person::getName));
        return people;
    }

    // Метод для сортировки по дате рождения
    public List<Person> getPeopleSortedByBirthDate() {
        List<Person> people = getAllPeople();
        people.sort(Comparator.comparing(Person::getDateOfBirth));
        return people;
    }
}