package com.emp;


public class EmployeeDTO {

    private int id;
    private String name;
    private int salary;
    private String dept;

    public int id() {
        return id;
    }

    public EmployeeDTO setId(int id) {
        this.id = id;
        return this;
    }

    public String name() {
        return name;
    }

    public EmployeeDTO setName(String name) {
        this.name = name;
        return this;
    }

    public int salary() {
        return salary;
    }

    public EmployeeDTO setSalary(int salary) {
        this.salary = salary;
        return this;
    }

    public String dept() {
        return dept;
    }

    public EmployeeDTO setDept(String dept) {
        this.dept = dept;
        return this;
    }
}
