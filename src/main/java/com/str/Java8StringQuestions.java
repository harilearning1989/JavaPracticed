package com.str;

// Java 8 String Interview Questions with Answers

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8StringQuestions {
    public static void main(String[] args) {

        // 1. Count character occurrences
        String input1 = "banana";
        Map<Character, Long> freq = input1.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Character frequency: " + freq);

        // 2. Remove duplicate characters from a string
        String input2 = "programming";
        String noDup = input2.chars()
                .mapToObj(c -> (char) c)
                .distinct()
                .map(String::valueOf)
                .collect(Collectors.joining());
        System.out.println("Without duplicates: " + noDup);

        // 3. Reverse a string
        String input3 = "hello";
        String reversed = new StringBuilder(input3).reverse().toString();
        System.out.println("Reversed: " + reversed);

        // 4. First non-repeated character using indexOf and lastIndexOf
        String input4 = "swiss";
        Optional<Character> firstNonRepeat = input4.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> input4.indexOf(c) == input4.lastIndexOf(c))
                .findFirst();
        System.out.println("First non-repeating character: " + firstNonRepeat.orElse('-'));

        // 5. Check anagram
        String s1 = "listen", s2 = "silent";
        boolean isAnagram = s1.length() == s2.length() &&
                s1.chars().sorted().boxed().collect(Collectors.toList())
                        .equals(s2.chars().sorted().boxed().collect(Collectors.toList()));
        System.out.println("Are anagrams: " + isAnagram);

        // 6. Uppercase list of strings
        List<String> names = Arrays.asList("alice", "bob", "charlie");
        List<String> upperNames = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Uppercase names: " + upperNames);

        // 7. Join strings with delimiter
        String joined = String.join("-", names);
        System.out.println("Joined: " + joined);

        // 8. Word frequency
        String sentence = "hello world hello java";
        Map<String, Long> wordFreq = Arrays.stream(sentence.split(" "))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Word frequency: " + wordFreq);

        // 9. Separate digits and letters
        String mixed = "abc123xyz456";
        String digits = mixed.chars().filter(Character::isDigit).mapToObj(c -> (char) c).map(String::valueOf).collect(Collectors.joining());
        String letters = mixed.chars().filter(Character::isLetter).mapToObj(c -> (char) c).map(String::valueOf).collect(Collectors.joining());
        System.out.println("Digits: " + digits + ", Letters: " + letters);

        // 10. Filter names starting with 'A'
        List<String> filtered = Arrays.asList("Anil", "Ravi", "Amit", "John").stream()
                .filter(name -> name.startsWith("A"))
                .collect(Collectors.toList());
        System.out.println("Names starting with A: " + filtered);

        // 11. Most frequent word
        Optional<Map.Entry<String, Long>> mostFreq = wordFreq.entrySet().stream()
                .max(Map.Entry.comparingByValue());
        System.out.println("Most frequent word: " + mostFreq.map(Map.Entry::getKey).orElse("None"));

        // 12. Sort strings by length then alphabetically
        List<String> animals = Arrays.asList("elephant", "cat", "dog", "tiger", "ant");
        List<String> sortedAnimals = animals.stream()
                .sorted(Comparator.comparingInt(String::length).thenComparing(String::compareTo))
                .collect(Collectors.toList());
        System.out.println("Sorted by length: " + sortedAnimals);

        // 13. Remove vowels
        String noVowels = "education".replaceAll("[aeiouAEIOU]", "");
        System.out.println("Without vowels: " + noVowels);

        // 14. CSV to list
        String csv = "apple,banana,mango";
        List<String> fruits = Arrays.asList(csv.split(","));
        System.out.println("Fruits list: " + fruits);

        // 15. Word count with streams
        Map<String, Long> wordCount = Arrays.stream("the cat and the dog and the cat".split(" "))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Word counts: " + wordCount);

        // 16. map vs flatMap demo
        List<String> lines = Arrays.asList("java8", "is awesome");
        List<String> flatMapped = lines.stream()
                .flatMap(line -> Arrays.stream(line.split("")))
                .collect(Collectors.toList());
        System.out.println("FlatMapped chars: " + flatMapped);

        // 17. toUpperCase safely with null
        String maybeNull = null;
        String safeUpper = Optional.ofNullable(maybeNull)
                .map(String::toUpperCase)
                .orElse("DEFAULT");
        System.out.println("Safe upper: " + safeUpper);
    }
}

