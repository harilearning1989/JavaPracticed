package com.rank;

import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ThreadExamples {

    public static void main(String[] args) {
       /*Runnable odd = () -> {
            System.out.println("Odd");
        };
        Runnable even = () -> {
            System.out.println("Even");
        };
        List<Runnable> runnableList = Arrays.asList(odd,even);
        ExecutorService es= Executors.newFixedThreadPool(2);*/

        Stream<Integer> intValue= Stream.of(1,7,8,9);
        IntSummaryStatistics statistics= intValue.collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println(statistics.getSum());
    }
}
