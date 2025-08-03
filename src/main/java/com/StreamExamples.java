package com;

import java.util.*;
import java.util.stream.Collectors;

public class StreamExamples {

    public static void main(String[] args) {
        List<Employee> employeeList = getEmployeeList();
        //double nthHighestSalary = getNthHighestSalary(employeeList, 2);
        departmentWiseSalary(employeeList);
    }

    private static List<Employee> departmentWiseSalary(List<Employee> employeeList) {
        Map<String,List<Employee>> stringListMap = Optional.ofNullable(employeeList)
                .orElseGet(Collections::emptyList)
                .stream()
            .collect(Collectors.groupingBy(Employee::getDepartment)
                    );

        /*stringListMap.forEach((k,v) -> {
            System.out.println("Key::"+k+"  Values::"+v);
        });*/
        return null;
    }

    private static double getNthHighestSalary(List<Employee> employeeList, int i) {
        int nth = 2; // Get the top 2 highest salaries
        List<Employee> sortedList = Optional.ofNullable(employeeList)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .toList();
        sortedList.forEach(System.out::println);
        Employee empSalary = sortedList.size() >= nth ? sortedList.get(nth-1) : null;
        System.out.println("Second Highest Salary Employee: " + empSalary.getSalary());
        return empSalary != null ? empSalary.getSalary() : 0.0;
    }

    private static List<Employee> getEmployeeList() {
        List<Employee> employeeList = new ArrayList<>();
        Employee employee = new Employee("John", 30,12000,"Admin");
        employeeList.add(employee);
        employee = new Employee("Smith", 30,21000,"HR");
        employeeList.add(employee);
        employee = new Employee("Smith", 30,11000,"HR");
        employeeList.add(employee);
        employee = new Employee("Josh", 30,12000,"IT");
        employeeList.add(employee);
        employee = new Employee("Alex", 30,60000,"Finance");
        employeeList.add(employee);
        employee = new Employee("Alex", 30,50000,"Finance");
        employeeList.add(employee);
        employee = new Employee("Alex", 30,10000,"Finance");
        employeeList.add(employee);
        employee = new Employee("Jen", 30,9000,"Admin");
        employeeList.add(employee);
        return employeeList;
    }
}

class Employee{
    private String name;
    private int age;
    private double salary;
    private String department;

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Employee(String name, int age, double salary,String department) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                '}';
    }
}
