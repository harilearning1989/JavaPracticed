package com;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringExample {

    public static void main(String[] args) {
        String[] str={"apple", "banana", "apple", "orange", "banana", "apple","mango", "banana", "orange", "banana"};
        //Count the frequency of word in java 8  Streams
        findTheFrequencyFromString(str);
    }

    private static void findTheFrequencyFromString(String[] input) {
        Map<String,Long> mapFrequency = Arrays.stream(input)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        mapFrequency.forEach((k,v) -> {
            System.out.println(k+":"+v);
        });
    }
}
