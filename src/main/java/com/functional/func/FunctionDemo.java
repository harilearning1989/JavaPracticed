package com.functional.func;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class FunctionDemo {

    //https://www.youtube.com/watch?v=VInk1_9vvCY&t=8s
    //https://mkyong.com/java8/java-8-predicate-examples/

   /* Function BiFunction
    DoubleFunction DoubleToIntFunction   DoubleToLongFunction  ToDoubleFunction  ToDoubleBiFunction
    IntFunction    IntToDoubleFunction   IntToLongFunction     ToIntFunction     ToIntBiFunction
    LongFunction   LongToDoubleFunction  LongToIntFunction     ToLongFunction    ToLongBiFunction
    */

    //apply  andThen  compose  identity

    public static void main(String[] args) {
        //LongStream.iterate(1, it -> it + 10).limit(8).forEach(System.out::println);
        //IntStream.iterate(1, it -> math(it, ...)).limit(8).forEach(System.out::println);
        LongStream.range(0,8).map(it -> 10*it + 1).forEach(System.out::println);

        Predicate<Integer> noGreaterThan5 = x -> x > 5;
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> collect = list.stream()
                .filter(noGreaterThan5)
                .toList();

        System.out.println(collect);
    }
}
