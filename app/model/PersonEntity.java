package model;

public class PersonEntity {

    private String name;
    private String firstName;
    private int age;
    private String id;

    public PersonEntity(String name, String firstName, int age, String id) {
        this.name = name;
        this.firstName = firstName;
        this.age = age;
        this.id = id;
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

    public void setId(String id) {
        this.id = id;
    }
}
