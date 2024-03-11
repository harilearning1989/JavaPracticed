package com.list;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ListExamples {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(10, 23, 12, 34, 23, 5, 67, 54, 44, 33, 66, 77);

        int maxEven = Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(f -> f % 2 == 0)
                .max(Comparator.naturalOrder())
                .get();

        System.out.println("Max Even Number::" + maxEven);

        Stream<Integer> integerStream = Stream.of(12,34,54,23,23,22);

       /* int max = integerStream.max(Comparator.naturalOrder()).get();
        int min = integerStream.min(Comparator.naturalOrder()).get();
        System.out.println("Max::"+max+"===Min::"+min);*/

        Predicate<Integer> rangeNum = i -> i>2;

        boolean b = integerStream.allMatch(rangeNum);
        System.out.println(b);

    }
}
