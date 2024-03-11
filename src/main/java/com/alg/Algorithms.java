package com.alg;

import java.util.Arrays;

public class Algorithms {

    public static void main(String[] args) {
        boolean status = anagramTest("hari","hraia");
        System.out.println("anagram status::"+status);
    }

    private static boolean anagramTest(String first,String second) {
        if (first.length() != second.length()) {
            return false;
        } else {
            char[] ArrayS1 = first.toLowerCase().toCharArray();
            char[] ArrayS2 = second.toLowerCase().toCharArray();
            Arrays.sort(ArrayS1);
            Arrays.sort(ArrayS2);
            return Arrays.equals(ArrayS1, ArrayS2);
        }
    }


}
