package com.dp.creational.single;

public class CloningSingleton {
    public static void main(String[] args) throws CloneNotSupportedException, Exception {
        Singleton instanceOne = Singleton.getInstance();
        Singleton instanceTwo = (Singleton) instanceOne.clone();
        System.out.println("hashCode of instance 1 - " + instanceOne.hashCode());
        System.out.println("hashCode of instance 2 - " + instanceTwo.hashCode());
    }

}
