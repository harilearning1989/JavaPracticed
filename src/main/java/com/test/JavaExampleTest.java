package com.test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JavaExampleTest {

    public static void main(String[] args) {
        int a[]= {1,2,4,4,2,5};

        //List<Integer> list = Arrays.asList(1,2,4,4,2,5);

        Arrays.stream(a).boxed()
                .collect(Collectors.groupingBy(i -> i,Collectors.counting()))
                .entrySet()
                .stream()
                .filter(f -> f.getValue() > 1)
                .forEach(k -> {
                    System.out.println(k.getKey()+"==="+k.getValue());
                });
    }
}
