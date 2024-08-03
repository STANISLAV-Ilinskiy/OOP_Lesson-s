package com.example.familytree.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Comparator;

public class Person implements Serializable, Comparable<Person> {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String gender;
    private Date birthDate;
    private Person leftChild;
    private Person rightChild;
    private Person spouse;

    public Person(String id, String name, String gender, Date birthDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Person getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Person leftChild) {
        this.leftChild = leftChild;
    }

    public Person getRightChild() {
        return rightChild;
    }

    public void setRightChild(Person rightChild) {
        this.rightChild = rightChild;
    }

    public Person getSpouse() {
        return spouse;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    public static Comparator<Person> compareByBirthDate() {
        return Comparator.comparing(Person::getBirthDate);
    }

    @Override
    public int compareTo(Person other) {
        return this.name.compareTo(other.name);
    }
}
