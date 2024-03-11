package com.emp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class EmployeeMain {

    //praveen.chivukula@capgemini.com
    public static void main(String[] args) {
        //List<EmployeeDTO> employeeDTOList = getEmployeeData();

        /*employeeDTOList.stream()
                .sorted(Comparator.comparing(EmployeeDTO::salary))
                .toList();*/

        /*Map<String, List<EmployeeDTO>> map = employeeDTOList.stream()
                .collect(Collectors.
                        groupingBy(EmployeeDTO::dept,
                                collectingAndThen(toList(), l -> l.parallelStream()
                                        .sorted(comparing(EmployeeDTO::salary))
                                        .collect(toList()))));
        map.forEach((k, v) -> {
            System.out.println(k + "===Value::" + v);
            List<EmployeeDTO> empList = v;
            empList.forEach(f-> System.out.println(f.salary()));
            System.out.println("================");
        });*/

        List<Integer> element = Arrays.asList(5,10,10,15,20,5,30,40,20);

        element.stream()
                .distinct()
                .forEach(System.out::println);
    }

    private static List<EmployeeDTO> getEmployeeData() {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        EmployeeDTO empDto = new EmployeeDTO();
        empDto.setId(123);
        empDto.setName("Hari");
        empDto.setSalary(20000);
        empDto.setDept("IT");
        employeeDTOList.add(empDto);
        empDto = new EmployeeDTO();
        empDto.setId(125);
        empDto.setName("Gopal");
        empDto.setSalary(10000);
        empDto.setDept("IT");
        employeeDTOList.add(empDto);

        empDto = new EmployeeDTO();
        empDto.setId(122);
        empDto.setName("Suresh");
        empDto.setSalary(30000);
        empDto.setDept("Admin");
        employeeDTOList.add(empDto);

        empDto = new EmployeeDTO();
        empDto.setId(120);
        empDto.setName("Ramesh");
        empDto.setSalary(40000);
        empDto.setDept("Finance");
        employeeDTOList.add(empDto);
        return employeeDTOList;
    }
}
