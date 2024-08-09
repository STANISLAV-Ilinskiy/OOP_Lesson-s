package com.example.familytree.model;

import java.util.Date;

public class Person {
    private final String id;
    private final String name;
    private final String spouseId; // Пример дополнительного поля
    private final Date birthDate;

    // Конструктор с четырьмя параметрами
    public Person(String id, String name, String spouseId, Date birthDate) {
        this.id = id;
        this.name = name;
        this.spouseId = spouseId; // Пример использования дополнительного поля
        this.birthDate = birthDate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpouseId() {
        return spouseId;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return "Person{id='" + id + "', name='" + name + "', spouseId='" + spouseId + "', birthDate=" + birthDate + "}";
    }
}

