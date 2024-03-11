package com.rank;

import java.util.*;

import static java.util.stream.Collectors.toMap;

public class ProblemStatement {

    /*Find most frequently occurring word(s) along with its frequency in given statement.
    If there is a tie in frequency, prefer the word that occurs first.
    Example input:
            "Ram is employee of ABC company, RAM is from Pune, RAM! is good in java.“
    Expected output: Word – Ram, Frequency - 3*/

    public static void main(String[] args) {
        /*String frequencyStr = "Ram is employee of ABC company, RAM is from Pune, RAM! is good in java.";
        frequencyStr = frequencyStr.replaceAll("[!,.]","");
        frequencyStr = frequencyStr.toLowerCase();
        String[] words = frequencyStr.split(" ");
        *//*Linked Hash Map Implementation Starting*//*
        Map<String,Integer> linkedHashMap = new LinkedHashMap<>();
        for(int i=0;i<words.length;i++){
            String word = words[i];
            linkedHashMap.put(word,Collections.frequency(Arrays.asList(words),word));
        }
        int max = linkedHashMap.values()
                .stream()
                .max(Comparator.naturalOrder())
                .get();
        Map.Entry<String,Integer> mapFinal =linkedHashMap.entrySet()
                .stream()
                .filter(f -> f.getValue() == max)
                .findFirst()
                .get();
        System.out.println("Key::"+mapFinal.getKey()+"===Value::"+mapFinal.getValue());*/


        String text = "Ram is employee of ABC company, ram is from Blore, RAM! is good in algorithms.";
        text = text.toLowerCase();
        List<String> wordsList = Arrays.asList(text.split("[^a-zA-Z]+"));
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        for (int i = 0; i < wordsList.size(); i++) {
            String word = wordsList.get(i);
            linkedHashMap.put(word, Collections.frequency(wordsList, word));
        }
        int maxInt = linkedHashMap.values()
                .stream()
                .max(Comparator.naturalOrder())
                .get();
        System.out.println(maxInt);

        Map.Entry<String,Integer> entry = linkedHashMap.entrySet()
                .stream()
                .filter(f -> f.getValue() == maxInt)
                .findFirst()
                .get();
        System.out.println(entry.getKey()+"==="+entry.getValue());
    }
}