package com.example.familytree.model;

import java.util.Date;

public class Person {
    private String id;
    private String name;
    private String gender;
    private Date birthDate;

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
        return id + " - " + name;
    }
}
