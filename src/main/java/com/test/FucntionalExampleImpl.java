package com.test;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class FucntionalExampleImpl implements FunctionalExample{

    @Override
    public void show() {

        //Calendar.getInstance()

    }

    public static void main(String[] args) {
        /*List<String> list = Arrays.asList("s");
        long count = list.stream().filter(f -> f.startsWith("sa")).count();
        System.out.println(count);


        String s="aabc";
        int n=5;

        String str="abc";
        int no=3;

        StringBuilder builder = new StringBuilder();
        for(int i=0;i<=n;i++){
            builder.append(str);
        }
        String output = builder.toString();
        int countChar= 0;
        for(int i=0;i<str.length();i++){
            if(output.charAt(i) == 'a'){
                countChar++;
            }
        }
        System.out.println(countChar*n);*/

        String str = "ab";
        int num =5;
        String output = "";

        while(output.length()+ str.length() < num){
            output +=  str;
        }
        System.out.println(output);


    }
}
