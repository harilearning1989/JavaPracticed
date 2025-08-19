package com.emp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeDepartment {

    public static void main(String[] args) {
        List<EmployeeRecord> employeeRecords = getEmployeeDepartmentList();
        assert employeeRecords != null;
        //groupByDepartment(employeeRecords);
        //groupByDepartmentCountValues(employeeRecords);
        //groupByDepartmentCountValuesSort(employeeRecords);
        //groupByDepartmentCountValuesSortDesc(employeeRecords);
        //findMaxSalaryFromEachDepartment(employeeRecords);
        //findSummaryStatsEachDepartment(employeeRecords);
        //groupByDepartmentSortEmployee(employeeRecords);
        //groupByDepartmentSortEmployeeDesc(employeeRecords);
        //groupByDepartmentTop5Employee(employeeRecords);
        groupByDepartmentTop5EmployeeDesc(employeeRecords);
    }

    private static void groupByDepartmentTop5EmployeeDesc(List<EmployeeRecord> employeeRecords) {
        Map<String, List<EmployeeRecord>> map = employeeRecords.stream()
                .collect(Collectors.groupingBy(EmployeeRecord::departmentName,
                        Collectors.collectingAndThen(Collectors.toList(),
                                list -> list.stream()
                                        .filter(f -> "TN".equalsIgnoreCase(f.state()))
                                        .sorted(Comparator.comparingDouble(EmployeeRecord::salary).reversed())
                                        .limit(5)
                                        .toList())));
        map.forEach((k,v) ->{
            System.out.println(k + "\t" + v.size());
        });
    }

    private static void groupByDepartmentTop5Employee(List<EmployeeRecord> employeeRecords) {
        Map<String, List<EmployeeRecord>> map = employeeRecords.stream()
                .collect(Collectors.groupingBy(EmployeeRecord::departmentName,
                        Collectors.collectingAndThen(Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparingDouble(EmployeeRecord::salary))
                                        .limit(5)
                                        .toList())));
    }

    private static void groupByDepartmentSortEmployeeDesc(List<EmployeeRecord> employeeRecords) {
        Map<String, List<EmployeeRecord>> map = employeeRecords.stream()
                .collect(Collectors.groupingBy(EmployeeRecord::departmentName,
                        Collectors.collectingAndThen(Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparingDouble(EmployeeRecord::salary).reversed())
                                        .toList())));
    }

    private static void groupByDepartmentSortEmployee(List<EmployeeRecord> employeeRecords) {
        Map<String, List<EmployeeRecord>> map = employeeRecords.stream()
                .collect(Collectors.groupingBy(EmployeeRecord::departmentName,
                        Collectors.collectingAndThen(Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparingDouble(EmployeeRecord::salary))
                                        .toList()
                        )
                ));
    }

    private static void findSummaryStatsEachDepartment(List<EmployeeRecord> employeeRecords) {
        Map<String, DoubleSummaryStatistics> statsMap = employeeRecords.stream()
                .collect(Collectors
                        .groupingBy(EmployeeRecord::departmentName,
                                Collectors.summarizingDouble(EmployeeRecord::salary)));
        statsMap.forEach((k, v) -> {
            System.out.println(k + "===Count::" + v.getCount()
                    + ": Sum::" + v.getSum()
                    + "===Max::" + v.getMax()
                    + "===Min::" + v.getMin()
                    + "===Average::" + v.getAverage());
        });
    }

    private static void findMaxSalaryFromEachDepartment(List<EmployeeRecord> employeeRecords) {
        employeeRecords.stream().collect(Collectors.groupingBy(EmployeeRecord::departmentId));
    }

    private static void groupByDepartmentCountValuesSortDesc(List<EmployeeRecord> employeeRecords) {
        Map<String, Long> groupByMap = employeeRecords.stream()
                .collect(Collectors.groupingBy(EmployeeRecord::departmentName, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
        groupByMap.forEach((k, v) -> {
            System.out.println("Key::" + k + "===Value Size::" + v);
        });
    }

    private static void groupByDepartmentCountValuesSort(List<EmployeeRecord> employeeRecords) {
        Map<String, Long> groupByMapCountValuesSort = employeeRecords.stream()
                .collect(Collectors.groupingBy(EmployeeRecord::departmentName, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

        groupByMapCountValuesSort.forEach((k, v) -> {
            System.out.println("Key::" + k + "===Value Size::" + v);
        });
    }

    private static void groupByDepartmentCountValues(List<EmployeeRecord> employeeRecords) {
        Map<String, Long> groupByMapCountValues = employeeRecords.stream()
                .collect(Collectors.groupingBy(EmployeeRecord::departmentName, Collectors.counting()));
        groupByMapCountValues.forEach((k, v) -> {
            System.out.println("Key::" + k + "===Value Size::" + v);
        });
    }

    private static void groupByDepartment(List<EmployeeRecord> employeeRecords) {
        Map<String, List<EmployeeRecord>> groupByMap = employeeRecords.stream()
                .collect(Collectors.groupingBy(EmployeeRecord::departmentName));
        groupByMap.forEach((k, v) -> {
            System.out.println("Key::" + k + "===Value Size::" + v.size());
        });
    }

    private static List<EmployeeRecord> getEmployeeDepartmentList() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(
                    new File("employee_department.json"),
                    new TypeReference<>() {
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
