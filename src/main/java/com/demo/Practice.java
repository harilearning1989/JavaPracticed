package com.demo;

public class Practice {

    public static void main(String[] args) {
        String s1 = "a";
        String s2 = "b";
        String s3 = "c";
        String s4 = "d";
        String s5 = s1 + s2 + s3 + s4;//value will be different

        System.out.println(s5);

        CustomStringBuffer stringBuffer = new CustomStringBuffer();
        stringBuffer.append(s1);
        stringBuffer.append(s2);
        stringBuffer.append(s3);
        stringBuffer.append(s4);

        System.out.println(stringBuffer);

        insert();
        //Exception thrown
        update();

    }

    public static void insert(){

    }
    public static void update(){

    }

}
