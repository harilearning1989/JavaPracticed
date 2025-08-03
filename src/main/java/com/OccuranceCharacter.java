package com;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class OccuranceCharacter {

    public static void main(String[] args) {
       /* List<String> dataList = List.of( "apple", "banana", "orange", "grape", "melon");
        *//*Map<String,Integer> frequecny = dataList.stream()
                .collect(Collections.frequency(dataList,"apple"));*//*

        for(String word : dataList){
            //Collections.frequency(dataList,word);
            char[] charArray = word.toCharArray();
            System.out.println("Characters in word::"+charArray);
            //System.out.println(word + " : " + Collections.frequency(charArray, "a"));
        }*/

        Optional<Integer> opt = Optional.empty();
        System.out.println(opt.get());

        /*Optional<Integer> opt = Optional.of(15).filter(n -> n % 2 == 0);
        System.out.println(opt.isPresent());*/
    }
}
