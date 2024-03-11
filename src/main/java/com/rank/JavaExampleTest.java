package com.rank;

import java.util.Arrays;
import java.util.stream.Collectors;

public class JavaExampleTest {

    public static void main(String[] args) {
        int a[]= {1,2,4,4,2,5};

        //List<Integer> list = Arrays.asList(1,2,4,4,2,5);

        Arrays.stream(a).boxed()
                .collect(Collectors.groupingBy(i -> i,Collectors.counting()))
                .entrySet()
                .stream()
                .filter(f -> f.getValue() > 1)
                .forEach(k -> {
                    System.out.println(k.getKey()+"==="+k.getValue());
                });

        /* HashTable Implementation Starting*/
        /*Hashtable<String,Integer> hashtable = new Hashtable<String, Integer>();
        for(int i=0;i<words.length;i++){
            String word = words[i];
            hashtable.put(word,Collections.frequency(Arrays.asList(words),word));
        }
        System.out.println(hashtable);
        Map.Entry<String,Integer> mapFinal = hashtable.entrySet().stream().filter(f -> f.getValue() ==3).findFirst().get();
        System.out.println("Key::"+mapFinal.getKey()+"===Value::"+mapFinal.getValue());*/

        /* HashTable Implementation Ending*/


        /*Linked Hash Map Implementation Ending*/

        /*int maxValue = map.values().stream().max(Comparator.naturalOrder()).get();
        Map.Entry<String,Integer> mapFinal =map.entrySet()
                .stream()
                .filter(f -> f.getValue() == maxValue)
                .findFirst()
                .get();
        System.out.println("Key::"+mapFinal.getKey()+"===Value::"+mapFinal.getValue());*/


        /*String frequencyStr = "Ram is employee of ABC company, RAM is from Pune, RAM! is good in java.";
        frequencyStr = frequencyStr.toLowerCase();
        frequencyStr = frequencyStr.replaceAll("[!,.]","");
        String[] words = frequencyStr.split(" ");
        Map<String,Integer> map = new HashMap<>();
        for(int i=0;i<words.length;i++){
            if(map.get(words[i]) != null){
                map.put(words[i],map.get(words[i])+1);
            }else{
                map.put(words[i],1);
            }
        }
        System.out.println(map);
        int maxValue = map.values().stream().max(Comparator.naturalOrder()).get();
        Map.Entry<String,Integer> mapFinal =map.entrySet()
                .stream()
                .filter(f -> f.getValue() == maxValue)
                .findFirst()
                .get();
        System.out.println("Key::"+mapFinal.getKey()+"===Value::"+mapFinal.getValue());*/

        /*String frequencyStr = "Ram is employee of ABC company, RAM is from Pune, RAM! is good in java.";
        frequencyStr = frequencyStr.replaceAll("[!,.]","");
        frequencyStr = frequencyStr.toLowerCase();
        String[] words = frequencyStr.split(" ");
        Map<String,Integer> map = new HashMap<>();
        Set<String> wordsSet = new HashSet<>(Arrays.asList(words));
        List<CountWords> countWordsList = new ArrayList<>();
        for(String word: wordsSet){
            //map.put(name,Collections.frequency(Arrays.asList(words),name));
            CountWords countRec = new CountWords(word,Collections.frequency(Arrays.asList(words),word));
            countWordsList.add(countRec);
        }

        CountWords countRec = countWordsList.stream().filter(f -> f.count() == 3).findFirst().get();
        System.out.println(countRec.name());*/
    }
}
