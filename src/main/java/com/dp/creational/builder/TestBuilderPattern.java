package com.dp.creational.builder;

import java.util.stream.IntStream;

public class TestBuilderPattern {

    public static void main(String[] args) {
        //Using builder to get the object in a single line of code and
        //without any inconsistent state or arguments management issues
        Computer comp = new Computer.ComputerBuilder(
                "500 GB", "2 GB").setBluetoothEnabled(true)
                .setGraphicsCardEnabled(true).build();

        System.out.println(comp);

        /*IntStream.iterate(0, i -> i + 100)
                .limit(1000 / 100)
                .forEach(i -> { *//* the test *//* });*/

        IntStream.range(1,10).forEach(f -> System.out.println(f));
    }
}
