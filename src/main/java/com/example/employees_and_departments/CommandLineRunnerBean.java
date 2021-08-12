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
        Set<Employee> employees2 = new HashSet<>();

        //create department1
        Department department1 = new Department();
        department1.setName("IT");
        department1.setEmployees(employees);

        //create department2
        Department department2 = new Department();
        department2.setName("HR");
        department2.setEmployees(employees2);

        //create a employee
        Employee employee1 = new Employee();
        employee1.setName("Anna Smith");
        employee1.setJobtitle("Software Developer");
        employee1.setDepartment(department1);
        employee1.setPhoto("https://media.istockphoto.com/vectors/user-avatar-profile-icon-black-vector-illustration-vector-id1209654046?k=6&m=1209654046&s=612x612&w=0&h=sNiHvwJm5SPrpTCjz-7eqSDqew5-f2hASM2FrGLtMJ4=");
        //add employee1 to an empty list of employees
        employees.add(employee1);

        //create employee2
        Employee employee2 = new Employee();
        employee2.setName("Bill James");
        employee2.setJobtitle("Web Designer");
        employee2.setDepartment(department1);
        employee2.setPhoto("https://media.istockphoto.com/vectors/user-avatar-profile-icon-black-vector-illustration-vector-id1209654046?k=6&m=1209654046&s=612x612&w=0&h=sNiHvwJm5SPrpTCjz-7eqSDqew5-f2hASM2FrGLtMJ4=");
        //add employee2 to the list of employees
        employees.add(employee2);

        //create employee3
        Employee employee3 = new Employee();
        employee3.setName("Sandra Robert");
        employee3.setJobtitle("Recruiter");
        employee3.setDepartment(department2);
        employee3.setPhoto("https://media.istockphoto.com/vectors/user-avatar-profile-icon-black-vector-illustration-vector-id1209654046?k=6&m=1209654046&s=612x612&w=0&h=sNiHvwJm5SPrpTCjz-7eqSDqew5-f2hASM2FrGLtMJ4=");
        //add employee3 to the list of employees
        employees2.add(employee3);

        //save the department to the database
        departmentRepository.save(department1);
        departmentRepository.save(department2);
    }




}
