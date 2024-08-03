package com.example.familytree.model;

import java.util.Date;

public class Person {
    private final String id;
    private final String name;
    private final String gender;
    private final Date birthDate;

    public Person(String id, String name, String gender, Date birthDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
    }

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

    @Override
    public String toString() {
        return "Person{id='" + id + "', name='" + name + "', gender='" + gender + "', birthDate=" + birthDate + "}";
    }
}
