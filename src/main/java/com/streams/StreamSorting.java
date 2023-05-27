package com.streams;

import java.util.*;
import java.util.stream.Collectors;

public class StreamSorting {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(2, 10, 12, 13, 14, 16, 28);

        List<Integer> listTemp = Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .filter(f -> {
                    if (f > 10 && f % 2 == 0) {
                        return true;
                    }
                    return false;
                })
                .map(m -> m * m)
                .collect(Collectors.toList());
        System.out.println(listTemp);
    }
}
