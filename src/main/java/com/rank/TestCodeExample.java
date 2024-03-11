package com.rank;

import java.util.*;

public class TestCodeExample {

    /*A fruit has below properties:
            1. Name
            2. Color
            3. Price

    Create a collection and add below fruits to it.

    Name color price
    Apple Red 100
    Banana Yellow 20
    Strawberry Red 150
    Guava Green 80
    Papaya Yellow 40

            1. Print names of all the red fruits
            2. Sort fruits by ascending price and print(cheapest fruit comes first)*/

    public static void main(String[] args) {
        List<Fruit> fruitList =new ArrayList<>();
        Fruit fruit = new Fruit("Apple","Red",100);
        fruitList.add(fruit);
        fruit = new Fruit("Banana","Yellow",20);
        fruitList.add(fruit);
        fruit = new Fruit("Strawberry", "Red", 150);
        fruitList.add(fruit);
        fruit = new Fruit("Guava", "Green", 80);
        fruitList.add(fruit);
        fruit = new Fruit("Papaya", "Yello", 40);
        fruitList.add(fruit);

        printRedFruitNames(fruitList);
        System.out.println("Sorting the fruits");
        sortFruitCheapestFirst(fruitList);
    }

    private static void sortFruitCheapestFirst(List<Fruit> fruitList) {
        Optional.ofNullable(fruitList)
                .orElseGet(Collections::emptyList)
                .stream()
                .sorted(Comparator.comparing(Fruit::price))
                .forEach(f -> {
                    System.out.println(f.name()+"===Price::"+f.price());
                });
    }

    private static void printRedFruitNames(List<Fruit> fruitList) {
        Optional.ofNullable(fruitList)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(f -> f.color().equals("Red"))
                .forEach(f -> {
                    System.out.println(f.name());
                });
    }
}
