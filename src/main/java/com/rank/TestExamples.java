package com.rank;

import java.util.*;

public class TestExamples {

        /*Find most frequently occurring word(s) along with its frequency in given statement
•	Example input: "“
            •	Expected output: Words – John, is & frequency - 3*/

    public static void main(String[] args) {
        String findFrequency = "John is an employee of ABC company, JOHN is from India, JOHN! is good in java.";
        findFrequency = findFrequency.replaceAll("[,!]", "");
        findFrequency = findFrequency.toLowerCase();
        String[] words = findFrequency.split(" ");
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            map.put(word, Collections.frequency(Arrays.asList(words), word));
        }

        List<Integer> list = new ArrayList<>(map.values());
        Collections.sort(list,Collections.reverseOrder());
        int max = list.get(0);

        String concateString = "";
        for(Map.Entry<String,Integer> mapTemp : map.entrySet()){
            if(mapTemp.getValue()==max){
                //System.out.println("Key::"+mapTemp.getKey()+"===Value"+v);
                concateString += mapTemp.getKey()+",";
            }
        }
        concateString = concateString;
        System.out.println("Words –"+concateString+" & frequency - "+max);

        int maxInt = map.values().stream().max(Comparator.naturalOrder()).get();
        System.out.println("max Integer::"+maxInt);

    }

}
