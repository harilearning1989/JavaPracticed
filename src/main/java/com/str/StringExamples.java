package com.str;

import java.util.Arrays;

public class StringExamples {
    public static void main(String[] args) {
        //stringReverseDemo();
        //mergeArrays();
        //abcd
    }

    private static void mergeArrays() {
        int a[] ={ 2,4,6,7,0,0,0};
        int b[] = {1,3,5};

       /* for(int i=0;i<a.length;i++){
           for(int j=0;j<b.length;j++){
                if(a[i] >= b[j]){

                }
           }
        }*/

        /*for(int i=0;i<=b.length;i++){
            for(int j=0;j<a.length;j++){
                if(a[j]==0){
                    a[j]=b[i];
                    break;
                }
            }
        }

        for(int i=0;i<a.length;i++){
            int first = a[i];
            for(int j=i+1;j<a.length;j++){
                if(first>=a[j]){
                    a[i]=a[j];
                    a[j]=first;
                    break;
                }
            }
        }
        Arrays.stream(a).boxed().forEach(System.out::println);*/

        //output= {1,2,3,4,5,6,7}
    }

    private static void stringReverseDemo() {
        String name = "Hari";
        String finalStr= "";
        for(int i=name.length()-1;i>=0;i--){
            finalStr = finalStr + name.charAt(i);
        }
        System.out.println(finalStr);
    }
}
