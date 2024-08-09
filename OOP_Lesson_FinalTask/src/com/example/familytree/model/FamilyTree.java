package com.example.familytree.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FamilyTree<T extends Person> implements Serializable {
    private Map<String, T> people;
    private Map<String, List<String>> relationships; // Хранит ID родителя и список ID детей
    private Map<String, T> spouses; // Хранит супружеские отношения

    public FamilyTree() {
        this.people = new HashMap<>();
        this.relationships = new HashMap<>();
        this.spouses = new HashMap<>();
    }

    public FamilyTree(T rootPerson) {
        this();
        people.put(rootPerson.getId(), rootPerson);
    }

    public void addPerson(String parentId, T child) {
        people.put(child.getId(), child);
        relationships.computeIfAbsent(parentId, k -> new ArrayList<>()).add(child.getId());
    }

    public List<T> getChildren(String parentId) {
        List<String> childrenIds = relationships.getOrDefault(parentId, new ArrayList<>());
        List<T> children = new ArrayList<>();
        for (String childId : childrenIds) {
            children.add(people.get(childId));
        }
        return children;
    }

    public void addSpouse(String personId, T spouse) {
        spouses.put(personId, spouse);
    }

    public List<T> getAllPeople() {
        return new ArrayList<>(people.values());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, T> entry : people.entrySet()) {
            T person = entry.getValue();
            sb.append(person.getName()).append(" (").append(person.getId()).append(")\n");
            if (spouses.containsKey(person.getId())) {
                T spouse = spouses.get(person.getId());
                sb.append("  Супруг/супруга: ").append(spouse.getName()).append("\n");
            }
            List<T> children = getChildren(person.getId());
            if (!children.isEmpty()) {
                sb.append("  Дети:\n");
                for (T child : children) {
                    sb.append("    ").append(child.getName()).append(" (").append(child.getId()).append(")\n");
                }
            }
        }
        return sb.toString();
    }
}
