package com.example.employees_and_departments;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)   //fetch = FetchType.EAGER  --> can't delete
    public Set<Employee> employees;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
