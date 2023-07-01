package com.dp.creational.single;

import java.lang.reflect.Constructor;

public class ReflectionSingleton {
    public static void main(String[] args)  {

        Singleton objOne = Singleton.getInstance();
        Singleton objTwo = null;
        try {
            Constructor constructor = Singleton.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            objTwo = (Singleton) constructor.newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex);
        }

        System.out.println("Hashcode of Object 1 - "+objOne.hashCode());
        System.out.println("Hashcode of Object 2 - "+objTwo.hashCode());

    }
}
