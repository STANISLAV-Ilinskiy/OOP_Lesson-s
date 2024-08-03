package com.example.familytree.tree;

import com.example.familytree.model.Person;

import java.util.*;

public class FamilyTree implements Iterable<Person> {
    private Person root;

    public FamilyTree(Person root) {
        this.root = root;
    }

    public Person getRoot() {
        return root;
    }

    public void addPerson(Person parent, Person child) {
        if (parent.getLeftChild() == null) {
            parent.setLeftChild(child);
        } else {
            Person sibling = parent.getLeftChild();
            while (sibling.getRightChild() != null) {
                sibling = sibling.getRightChild();
            }
            sibling.setRightChild(child);
        }
    }

    public void addSpouse(Person person, Person spouse) {
        person.setSpouse(spouse);
        spouse.setSpouse(person);
    }

    public Person findPersonById(String id) {
        return findPersonById(root, id);
    }

    private Person findPersonById(Person current, String id) {
        if (current == null) {
            return null;
        }
        if (current.getId().equals(id)) {
            return current;
        }
        Person foundPerson = findPersonById(current.getLeftChild(), id);
        if (foundPerson == null) {
            foundPerson = findPersonById(current.getRightChild(), id);
        }
        return foundPerson;
    }

    public List<Person> getAllChildren(String id) {
        Person person = findPersonById(id);
        if (person != null) {
            List<Person> children = new ArrayList<>();
            Person child = person.getLeftChild();
            while (child != null) {
                children.add(child);
                child = child.getRightChild();
            }
            return children;
        }
        return Collections.emptyList();
    }

    public void printTree() {
        printTree(root, 0, "");
    }

    private void printTree(Person person, int level, String prefix) {
        if (person == null) {
            return;
        }
        System.out.println(prefix + person.getName() + " (" + person.getId() + ")");
        if (person.getSpouse() != null) {
            System.out.println(prefix + "  Супруг: " + person.getSpouse().getName() + " (" + person.getSpouse().getId() + ")");
        }
        if (person.getLeftChild() != null) {
            printTree(person.getLeftChild(), level + 1, prefix + person.getId() + "-----| Ребенок: ");
            Person sibling = person.getLeftChild().getRightChild();
            while (sibling != null) {
                printTree(sibling, level + 1, prefix + person.getId() + "-----| Ребенок: ");
                sibling = sibling.getRightChild();
            }
        }
    }

    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<>();
        for (Person p : this) {
            persons.add(p);
        }
        return persons;
    }

    public List<Person> sortByName() {
        List<Person> persons = getAllPersons();
        Collections.sort(persons);
        return persons;
    }

    public List<Person> sortByBirthDate() {
        List<Person> persons = getAllPersons();
        persons.sort(Person.compareByBirthDate());
        return persons;
    }

    @Override
    public Iterator<Person> iterator() {
        return new TreeIterator(root);
    }

    private class TreeIterator implements Iterator<Person> {
        private Queue<Person> queue = new LinkedList<>();

        public TreeIterator(Person root) {
            if (root != null) {
                queue.add(root);
                while (!queue.isEmpty()) {
                    Person current = queue.poll();
                    if (current.getLeftChild() != null) queue.add(current.getLeftChild());
                    if (current.getRightChild() != null) queue.add(current.getRightChild());
                }
            }
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public Person next() {
            return queue.poll();
        }
    }
}
