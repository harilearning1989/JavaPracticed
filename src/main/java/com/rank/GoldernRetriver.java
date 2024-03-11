package com.rank;

public class GoldernRetriver extends Dog{

    public void makenoise(){
        super.makenoise();
        System.out.println("Make Golder retriver noise");
    }

    public static void main(String[] args) {
        Dog dog = new GoldernRetriver();
        dog.makenoise();
    }
}
