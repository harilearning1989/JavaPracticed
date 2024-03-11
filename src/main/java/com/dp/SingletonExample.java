package com.dp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SingletonExample {

   /* private static SingletonExample instance = null;

    private SingletonExample(){}

    public static SingletonExample getInstance(){
        if(instance == null){
            synchronized (SingletonExample.class){
                if(instance == null){
                    instance = new SingletonExample();
                }
            }
            return instance;
        }
        return instance;
    }*/

    public static void main(String[] args) {
        /*1 2 3 4 5
        1. get all odd
        2. add with 3
        3. add all val*/
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        long sum  = Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(f -> f%2 != 0)
                .map(m -> m+3)
                .collect(Collectors.summingInt(Integer::intValue));

        System.out.println(sum);
    }
}
