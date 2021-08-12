package com.example.employees_and_departments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
public class CommandLineRunnerBean implements CommandLineRunner {
    @Autowired
    DepartmentRepository departmentRepository;

    public void run(String...args){
        // Employees collection
        Set<Employee> employees = new HashSet<>();

        //create a Department
        Department department = new Department();
        department.setName("IT");
        department.setEmployees(employees);

        //create a employee
        Employee employee1 = new Employee();
        employee1.setName("Anna Smith");
        employee1.setJobtitle("Software Developer");
        employee1.setDepartment(department);
        employee1.setPhoto("https://media.istockphoto.com/vectors/user-avatar-profile-icon-black-vector-illustration-vector-id1209654046?k=6&m=1209654046&s=612x612&w=0&h=sNiHvwJm5SPrpTCjz-7eqSDqew5-f2hASM2FrGLtMJ4=");

        //add employee1 to an empty list of employees
        employees.add(employee1);

        //create employee2
        Employee employee2 = new Employee();
        employee2.setName("Bill James");
        employee2.setJobtitle("Web Designer");
        employee2.setDepartment(department);
        employee2.setPhoto("https://media.istockphoto.com/vectors/user-avatar-profile-icon-black-vector-illustration-vector-id1209654046?k=6&m=1209654046&s=612x612&w=0&h=sNiHvwJm5SPrpTCjz-7eqSDqew5-f2hASM2FrGLtMJ4=");


        //add employee2 to the list of employees
        employees.add(employee2);

        //save the department to the database
        departmentRepository.save(department);
    }




}
