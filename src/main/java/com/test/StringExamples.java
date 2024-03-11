package com.test;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringExamples {

    public static void main(String[] args) {
       /* String first = "HRAI";
        String second = "hari";
        boolean anagram = anagramExamples(first, second);
        System.out.println("Anagram ::"+anagram);*/

        /*int num = 10;
        fibonacci(num);*/

        //printAlphabetsAtoZ();
        //printAlphabetsAtoZLowerCase();

       /* int num = 10;
        factorialNum(num);*/

        /*String str = "abvabdbaabfgabwdbaababab";
        int count = findNumberOfOccurance(str);
        System.out.println("Count::"+count);*/

        /*String text = "Ram is employee of ABC company, ram is from Blore, RAM! is good in algorithms.";
        stringFrequency(text);*/

        boolean b = isPalindrome("NAQBBQAN");
        System.out.println("String palindrome or not::" + b);
    }

    public static boolean isPalindrome(String original) {
        int i = 0;
        int j = original.length()-1;
        while(i < j){
            if(original.charAt(i) != original.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    private static void stringFrequency(String text) {
        //List<String> wordsList = Arrays.asList(text.split("[^a-zA-Z0-9]+"));
        Pattern pattern = Pattern.compile("[^a-zA-Z]+");
        List<String> wordsList = Arrays.asList(pattern.split(text));
        Map<String, Long> wordFrequency = wordsList.stream()
                .map(word -> word.toLowerCase())
                .collect(Collectors
                        .groupingBy(Function.identity(), LinkedHashMap::new,
                                Collectors.counting()));
        long maxCount = Collections.max(wordFrequency.values());

        wordFrequency.forEach((k, v) -> {
            if (maxCount == v) {
                System.out.println(k + "===" + v);
            }
        });

        /*Map<String, Long> maxFrequencyList = wordFrequency.entrySet().stream().filter(e -> e.getValue() == maxCount)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));*/

        //System.out.println(maxFrequencyList);
    }

    private static int findNumberOfOccurance(String str) {
        Pattern pattern = Pattern.compile("ab");
        Matcher matcher = pattern.matcher(str);
        int count = 0;
        while (matcher.find()) {
            count++;
            System.out.println(matcher.start());//Start index of string
        }
        return count;
    }

    private static void factorialNum(int num) {
        int factorial = 1;
        for (int i = 1; i <= num; i++) {
            factorial *= i;
        }
        System.out.println("Factorial Number::" + factorial);
    }

    private static void printAlphabetsAtoZLowerCase() {
        char c;
        for (c = 'a'; c <= 'z'; ++c)
            System.out.print(c + " ");
    }

    private static void printAlphabetsAtoZ() {
        char c;
        for (c = 'A'; c <= 'Z'; ++c)
            System.out.print(c + " ");
    }

    private static void fibonacci(int n) {
        int firstTerm = 0, secondTerm = 1;
        System.out.println("Fibonacci Series till " + n + " terms:");

        for (int i = 1; i <= n; ++i) {
            System.out.print(firstTerm + ", ");

            int nextTerm = firstTerm + secondTerm;
            firstTerm = secondTerm;
            secondTerm = nextTerm;
        }
    }

    private static boolean anagramExamples(String first, String second) {
        if (first.length() != second.length()) {
            return false;
        }
        char[] a1 = first.toLowerCase().toCharArray();
        char[] a2 = second.toLowerCase().toCharArray();
        Arrays.sort(a1);
        Arrays.sort(a2);
        return Arrays.equals(a1, a2);
    }
}
