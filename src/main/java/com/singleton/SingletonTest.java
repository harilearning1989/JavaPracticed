package com.singleton;

import java.lang.reflect.Constructor;

public class SingletonTest {

    public static void main(String[] args) {
        //synchronizedExample();
        reflectionExample();
        //https://dzone.com/articles/prevent-breaking-a-singleton-class-pattern
        //https://www.digitalocean.com/community/tutorials/java-singleton-design-pattern-best-practices-examples
    }

    private static void reflectionExample() {
        SingletonDemo instanceOne = SingletonDemo.getInstance();
        SingletonDemo instanceTwo = null;
        try {
            Constructor[] constructors = SingletonDemo.class.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                // This code will destroy the singleton pattern
                constructor.setAccessible(true);
                instanceTwo = (SingletonDemo) constructor.newInstance();
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(instanceOne.hashCode());
        System.out.println(instanceTwo.hashCode());
    }

    private static void synchronizedExample() {
        SingletonDemo object1 = SingletonDemo.getInstance();
        SingletonDemo object2 = SingletonDemo.getInstance();
        System.out.println("Hashcode of Object 1 - " + object1.hashCode());
        System.out.println("Hashcode of Object 2 - " + object2.hashCode());
    }
}
