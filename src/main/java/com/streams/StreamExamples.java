package com.streams;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExamples {

    public static void main(String[] args) {
        //sameStreamProcessAgain();
        //correctWayToUse();
        //sameStreamProcessAgainSupplier();
        groupByExample();
    }

    private static void groupByExample() {
        List<EmployeeDto> employees = Arrays.asList(
                new EmployeeDto("Alice", "HR", 5000),
                new EmployeeDto("Bob", "IT", 7000),
                new EmployeeDto("Charlie", "HR", 6000),
                new EmployeeDto("David", "IT", 8000),
                new EmployeeDto("Eve", "Finance", 9000)
        );

        Optional<EmployeeDto> dto = employees.stream()
                .sorted(Comparator.comparing(EmployeeDto::getSalary).reversed())
                .skip(7-1)
                .findFirst();

        Map<String,EmployeeDto> map = employees
                .stream()
                .collect(Collectors.groupingBy(EmployeeDto::getDepartment,
                        Collectors.collectingAndThen(Collectors.toList(),
                                list -> list
                                        .stream()
                                        .sorted(Comparator.comparing(EmployeeDto::getSalary).reversed())
                                        .skip(5-1)
                                        .findFirst().get())));

        Map<String,List<EmployeeDto>> map2 = employees.stream()
                .collect(Collectors.groupingBy(EmployeeDto::getDepartment));


        Map<String, EmployeeDto> maxSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        EmployeeDto::getDepartment,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingDouble(EmployeeDto::getSalary)),
                                Optional::get
                        )
                ));

        maxSalaryByDept.forEach((dept, emp) -> {
            System.out.println(dept + " -> " + emp.getName() + " ($" + emp.getSalary() + ")");
        });
    }

    private static void correctWayToUse() {
        List<String> names = List.of("Alice", "Bob", "Charlie", "David", "Eve");
        names.stream()
                .filter(name -> name.startsWith("A"))
                .forEach(System.out::println);
        names.stream() // Creating a new stream again
                .filter(name -> name.length() > 3)
                .forEach(System.out::println);
    }

    private static void sameStreamProcessAgainSupplier() {
        List<String> names = List.of("Alice", "Bob", "Charlie", "David", "Eve");
        // Supplier that provides a new stream every time it's called
        Supplier<Stream<String>> nameStreamSupplier = names::stream;
        // First usage
        nameStreamSupplier.get()
                .filter(name -> name.startsWith("A"))
                .forEach(System.out::println);

        // Second usage (works fine)
        nameStreamSupplier.get()
                .filter(name -> name.length() > 3)
                .forEach(System.out::println);

        Set<String> s = new HashSet<>();
        List<String> names2 = new ArrayList<>(s);

        // This ensures a fresh stream is available each time you call get().
    }
    private static void sameStreamProcessAgain() {
        List<String> names = List.of("Alice", "Bob", "Charlie", "David", "Eve");
        Stream<String> nameStream = names.stream(); // Creating a stream
        // First usage
        nameStream.filter(name -> name.startsWith("A")).forEach(System.out::println);
        // Second usage (Throws IllegalStateException)
        nameStream.filter(name -> name.length() > 3).forEach(System.out::println);
        //Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed
    }
}

class EmployeeDto {
    private String name;
    private String department;
    private double salary;

    public EmployeeDto(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }
}
