package model;

import java.util.UUID;

public class PersonModel {

    private String name;
    private String firstName;
    private int age;
    private String id;

    public PersonModel() {
        this.name = "";
        this.firstName = "";
        this.age = 0;
    }

    public PersonModel(String name, String firstName, int age) {
        this.name = name;
        this.firstName = firstName;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }

    public String getId() {
        return id;
    }

}
