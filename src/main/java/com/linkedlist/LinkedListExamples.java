package com.linkedlist;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LinkedListExamples {

    public static void main(String[] args) {
        String[] names = {"A", "B", "X", "A", "Q", "D", "R"};
        List<String> linkedList = new LinkedList<>(Arrays.asList(names));
        Map<String, Long> map = linkedList.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));
        /*Set<String> keys = map.keySet();
        for (String str : keys) {
            System.out.println("Key::" + str + "===Value::" + map.get(str));
        }*/

        Map<String, Long> frequency = linkedList.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new, Collectors.counting()));
        frequency.forEach((k, v) -> System.out.println("Key::" + k + "===Value::" + v));
    }
}
