package com.test;

import java.util.Arrays;
import java.util.List;

public class Parent {

    void print(String text) {
        System.out.println("String: " + text);
    }

    void print(int number) {
        System.out.println("Integer: " + number);
    }
}

class Child extends Parent {
    void print(int number) {
        System.out.println("I am a child" + number);
    }

    void print(float number) {
        System.out.println("Float: " + number);
    }
}

 class Main {
    public static void main(String[] args) {
        Parent p = new Parent();
        p.print(5);
        p = new Child();
        //p.print(2.3f);

        List<Character> list = Arrays.asList('C','A');
    }
}
