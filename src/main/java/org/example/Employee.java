package org.example;

public class Employee {
    private long id;
    private String firstName;
    private String lastName;
    private String country;
    private int age;

    public Employee() { }

    public Employee(long id, String firstName, String lastName, String country, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.age = age;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}