package com.company.Lambda;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private String name;
    private List<Employee> employees;

    public Department(String names) {
        this.name = name;
        this.employees = new ArrayList<>();
    }

    public void addEmployees(Employee e) {
         employees.add(e);
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
