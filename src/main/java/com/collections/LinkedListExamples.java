package com.collections;

import java.util.LinkedList;
import java.util.List;

public class LinkedListExamples {

    public static void main(String[] args) {
        List<String> linkedListExample = new LinkedList<>();
        linkedListExample.add("A");
        linkedListExample.add("B");
        linkedListExample.add("D");
        linkedListExample.add("A");

        linkedListExample.forEach(System.out::println);

        String input = "streamsample";

        //first repeated character using stream
        String firstRepeatedChar = input.chars()
                .mapToObj(c -> (char) c)
                .filter(ch -> input.indexOf(ch) != input.lastIndexOf(ch))
                .findFirst()
                .map(String::valueOf)
                .orElse("No repeated character found");

        long count = input.chars()
                .mapToObj(c -> (char) c)
                .filter(ch -> input.indexOf(ch) != input.lastIndexOf(ch))
                .distinct()
                .count();

        //first repeated character using stream
        Character firstRepeated = input.chars()
                .mapToObj(c -> (char) c)
                .filter(ch -> input.indexOf(ch) != input.lastIndexOf(ch))
                .findFirst()
                .orElse(null);



    }
}
