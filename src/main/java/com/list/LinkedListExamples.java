package com.list;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LinkedListExamples {

    public static void main(String[] args) {
        linkedListExamples();
    }

    private static void linkedListExamples() {
        LinkedList<String> animals = new LinkedList<>();
        animals.add("Dog");
        animals.add("Cat");
        animals.add("Cow");
        System.out.println("LinkedList: " + animals);

        animals.add(1, "Horse");
        System.out.println("Updated LinkedList: " + animals);

        String str = animals.remove(1);
        System.out.println("Removed Element: " + str);

        Iterator iterator = animals.descendingIterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
