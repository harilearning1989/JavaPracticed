package com.streams;

import com.emp.Employee;
import com.emp.Languages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamEmployee {

    static Map<String, String> proficiencyMap = Map.of(
            "U", "Understand",
            "S", "Speak",
            "R", "Read",
            "W", "Write"
    );

    public static void main(String[] args) {
        List<Employee> employeeList = generateEmployees();
        List<Employee> filteredEmployees = filteredEmployees(employeeList);
        //List<Employee> filteredEmployees = filteredEmployees(employeeList);
         filteredEmployees.forEach(f -> {
            System.out.println("ID: " + f.getId() + ", Name: " + f.getName());
            f.getLanguages().forEach(lang -> {
                System.out.println("Language: " + lang.getLanguage() + ", Proficiency: " + lang.getProficiency());
            });
        });
    }

    private static List<Employee> filteredEmployees(List<Employee> employeeList) {
        return employeeList.stream()
                .peek(employee -> employee.getLanguages().forEach(lang -> {
                    String mapped = Arrays.stream(lang.getProficiency().split(","))
                            .map(proficiencyMap::get)
                            .collect(Collectors.joining(", "));
                    lang.setProficiency(mapped);
                }))
                .collect(Collectors.toList());
    }

    private static List<Employee> generateEmployees() {
        return List.of(
                new Employee(1,"John Doe",
                        List.of(new Languages("English", "U,S,R,W"),
                                new Languages("French", "U,S,R,W"),
                                new Languages("German", "U,S"))),
                new Employee(2,"Jane Smith",
                        List.of(new Languages("Spanish", "U,S,R,W"),
                                new Languages("Italian", "U,S,R"),
                                new Languages("Chinese", "U,S,R,W"))),
                new Employee(3,"Alice Johnson",
                        List.of(new Languages("French", "U,S,R,W"),
                                new Languages("Japanese", "R,W"),
                                new Languages("Russian", "U,S,R,W"))),
                new Employee(4,"Bob Brown",
                        List.of(new Languages("German", "U,S"),
                                new Languages("Portuguese", "U,S,R"))),
                new Employee(5,"Charlie White",
                        List.of(new Languages("Italian", "U,S,R"),
                                new Languages("Chinese", "U,S,R,W")))
        );
    }

    /*
    private static List<Employee> filteredEmployees(List<Employee> employeeList) {
        return employeeList.stream()
                .peek(m -> {
                    String prof = Arrays.stream(m.getProficiency().split(","))
                            .map(proficiencyMap::get)
                            .collect(Collectors.joining(", "));
                    m.setProficiency(prof);
                })
                .collect(Collectors.toList());
    }

    private static List<Employee> generateEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        Employee emp = new Employee();
        emp.setId(1);
        emp.setName("John Doe");
        emp.setLanguage("English");
        emp.setProficiency("U,S,R,W");

        employeeList.add(emp);

        emp = new Employee();
        emp.setId(2);
        emp.setName("Jane Smith");
        emp.setLanguage("Spanish");
        emp.setProficiency("U,S,R,W");
        employeeList.add(emp);
        emp = new Employee();
        emp.setId(3);
        emp.setName("Alice Johnson");
        emp.setLanguage("French");
        emp.setProficiency("U,S,R,W");
        employeeList.add(emp);

        emp = new Employee();
        emp.setId(4);
        emp.setName("Bob Brown");
        emp.setLanguage("German");
        emp.setProficiency("U,S");

        employeeList.add(emp);
        emp = new Employee();
        emp.setId(5);
        emp.setName("Charlie White");
        emp.setLanguage("Italian");
        emp.setProficiency("U,S,R");
        employeeList.add(emp);
        emp = new Employee();
        emp.setId(6);
        emp.setName("David Green");
        emp.setLanguage("Chinese");
        emp.setProficiency("U,S,R,W");
        employeeList.add(emp);
        emp = new Employee();
        emp.setId(7);
        emp.setName("Eve Black");
        emp.setLanguage("Japanese");
        emp.setProficiency("R,W");
        employeeList.add(emp);
        emp = new Employee();
        emp.setId(8);
        emp.setName("Frank Blue");
        emp.setLanguage("Russian");
        emp.setProficiency("U,S,R,W");
        employeeList.add(emp);
        emp = new Employee();
        emp.setId(9);
        emp.setName("Grace Yellow");
        emp.setLanguage("Portuguese");
        emp.setProficiency("U,S,R");
        employeeList.add(emp);

        return employeeList;
    }*/
}
