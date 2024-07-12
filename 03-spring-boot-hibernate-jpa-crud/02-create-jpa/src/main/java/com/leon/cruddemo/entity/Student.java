package com.leon.cruddemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    /*
    This is a Student Data Acess Object (DAO)
    responsible for the access to the database
    Common methods:
        save(...)
        findById(...)
        findAll(...)
        findByLastName(...)
        update(...)
        delete(...)
        deleteAll(...)
     */

    /*
    DAO needs a JPA Entity Manager
    JPA Entity Manager is the main component for saving/retrieving entities

    Student DAO <--> Entity Manager <--> Supporting Components <--> DB

    One of the Supporting Component is a Data Source (defines database connection info)
     */

    /*
    Spring boot automatically creates the JPA Entity Manager and Data Source
        based on application.properties (JDBC URL, user id, password, etc...)

    Then we can autowire/inject the JPA Entity Manager into our Student DAO
     */

    /*
    Student DAO <--> Entity Manager <--> Data Source <--> DB
     */

    /*
    Steps:
        1 - Define DAO interface
        2 - Define DAO implementation
            Inject the entity manager
        3 - Update main app
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    public Student() {
    }

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
