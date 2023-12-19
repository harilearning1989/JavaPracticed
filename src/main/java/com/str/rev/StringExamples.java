package com.str.rev;

public class StringExamples {
    public static void main(String[] args) {
        String rev = "Hello";
        String revVal = "";
        for (int i = rev.length() - 1; i >= 0; i--) {//i=5;5>=0;4
            revVal = revVal + rev.charAt(i);
        }
        System.out.println("revVal::" + revVal);
        //StringBuilder reverseBuilder = new StringBuilder(rev);
        //System.out.println("revVal::"+reverseBuilder.reverse());
    }

}
