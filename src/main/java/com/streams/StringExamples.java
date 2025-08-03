package com.streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringExamples {
    public static void main(String[] args) {
        String input = "qpzxfstreamsathyzpxQFs";
        //findFirstRepeatedCharacter(input);
        //findMostRepeatedCharacter(input);
        //findOccurencesEachCharacter(input);
        //findFirstNonRepeatedChar(input);
        //twoStringsAreAnagram();
        //reverseString(input);
        //findDuplicateCharacters(input);
        //removeDuplicateChars(input);
        getDuplicateChars(input);
        String inputWords = "This is a sample string that contains several words. Some of the words are repeated. "
                + "This string is used to test the Java program that finds duplicate words. In a string of more "
                + "than fifty words, common words like 'the', 'and', 'is', or 'in' may occur multiple times. "
                + "This example is intended to show how Java 8 streams can be used to find and count duplicate "
                + "words in a large string of text for demonstration purposes.";
        //findWordFrequency(inputWords);

        //findFirstRepeatedWord(inputWords);
        //findDuplicateWordsString(inputWords);
        //countWordRepeatMoreThanOne(inputWords);
        //countWordRepeatMoreThanOneSorted(inputWords);
        //findMostRepeatedWord(inputWords);

        List<String> namesList = Arrays.asList(
                "Aarav", "Vivaan", "Aditya", "Vihaan", "Arjun",
                "Sai", "Reyansh", "Ayaan", "Krishna", "Ishaan",
                "Aarav", "Sai", "Vihaan", "Rohan", "Kabir",
                "Rudra", "Arnav", "Atharv", "Kartik", "Krishna"
        );

        //findUniqueName(namesList);
        //findFrequencyOfNames(namesList);

    }

    private static void getDuplicateChars(String input) {
        String uniqueString = input.toLowerCase().chars()
                .mapToObj(c -> (char) c)
                .distinct()
                .map(String::valueOf)
                .collect(Collectors.joining());
        System.out.printf("The unique string is: %s%n", uniqueString);
    }

    private static void findFrequencyOfNames(List<String> namesList) {
        Map<String, Long> map = namesList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        //map.forEach((k,v) -> System.out.println(k + ": " + v));

        map = namesList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                //.sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        map.forEach((k, v) -> System.out.println(k + ": " + v));
    }

    private static void findUniqueName(List<String> namesList) {
        List<String> uniqueNames = namesList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .sorted()
                .toList();

        uniqueNames.forEach(f -> System.out.printf("Unique Name %s\n", f));
        System.out.println("====================================");

        List<String> duplicateNames = namesList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .sorted()
                .toList();

        duplicateNames.forEach(f -> System.out.printf("Duplicate Name %s\n", f));

    }

    private static void findWordFrequency(String inputWords) {
        String[] words = inputWords.toLowerCase().replaceAll("[^a-z ]]", "").split("\\s+");
        Map<String, Long> map = Arrays.stream(words)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        map.forEach((k, v) -> System.out.printf("%s : %d%n", k, v));
    }

    private static void removeDuplicateChars(String input) {
        /*IntStream intStream = input.toLowerCase().chars();
        intStream.forEach(s -> System.out.printf("%c", s));*/
        //input.chars().forEach(System.out::println);
        Set<Character> set = input.toLowerCase().chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toSet());
        set.forEach(s -> System.out.printf("%s ", s));
    }

    private static void findMostRepeatedWord(String inputWords) {
        String[] words = inputWords.toLowerCase().replaceAll("[^a-z ]", "").split("\\s+");
        Optional<Map.Entry<String, Long>> map = Arrays.stream(words)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());
        map.ifPresent(p -> System.out.printf("The most repeated word is %s.", p.getKey()));
    }

    private static void findMostRepeatedCharacter(String input) {
        Optional<Map.Entry<Character, Long>> map = input.toLowerCase().chars()
                .mapToObj(m -> (char) m)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());

        map.ifPresent(characterLongEntry -> System.out.printf("Most repeated character: %s\n", characterLongEntry.getKey()));
    }

    private static void findFirstRepeatedWord(String inputWords) {
        String[] words = inputWords.toLowerCase().replaceAll("[^a-z ]", "").split("\\s+");
        String firstWord = Arrays.stream(words)
                .filter(first -> {
                    long count = Arrays.stream(words).filter(second -> second.equals(first)).count();
                    return count > 1;
                })
                .findFirst()
                .orElse("No Word Found");

        System.out.printf("First Word: %s%n", firstWord);
    }

    private static void countWordRepeatMoreThanOneSorted(String inputWords) {
        String[] words = inputWords.toLowerCase().replaceAll("[^a-z ]", "").split("\\s+");
        var map = Arrays.stream(words)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(m -> m.getValue() > 1)
                //.sorted(Comparator.comparing(Map.Entry::getValue))
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new // maintain order
                ));
        if (map.isEmpty()) {

        } else {
            map.forEach((k, v) -> System.out.printf("key is %s and value is %s", k, v));
        }
    }

    private static void countWordRepeatMoreThanOne(String inputWords) {
        String[] words = inputWords.toLowerCase().replaceAll("[^a-z ]", "").split("\\s+");
        var moreThanOneRepeatedWord = Arrays.stream(words)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(map -> map.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println("\n=== Duplicate Words ===");
        if (moreThanOneRepeatedWord.isEmpty()) {
            System.out.println("No duplicate words found.");
        } else {
            moreThanOneRepeatedWord.forEach((word, count) ->
                    System.out.println(word + ": " + count));
        }

    }

    private static void findDuplicateWordsString(String inputWords) {
        String[] words = inputWords.toLowerCase().replaceAll("[^a-z ]", "").split("\\s+");
        List<String> duplicateWords = Arrays.stream(words)
                .peek(w -> System.out.printf("Before Process Duplicate word: %s\n", w))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .peek(word -> System.out.printf("After ProcessDuplicate word: %s\n", word.getKey()))
                .filter(f -> f.getValue() > 1)
                .peek(word -> System.out.printf("After ProcessDuplicate word: %s\n", word.getKey()))
                .map((Map.Entry::getKey))
                .peek(w -> System.out.printf("After ProcessDuplicate word: %s\n", w))
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.printf("Found duplicate words: %s\n", duplicateWords);
        // Print result
        if (duplicateWords.isEmpty()) {
            System.out.println("No duplicate words found.");
        } else {
            System.out.println("Duplicate words:");
            duplicateWords.forEach(System.out::println);
        }
    }

    private static void findDuplicateCharacters(String input) {
        Set<String> chars = input.chars()
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(f -> f.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        System.out.printf("Found duplicate characters: %s%n", chars);
    }

    private static void reverseString(String input) {
        String reversed = IntStream.rangeClosed(1, input.length() - 1)
                .mapToObj(i -> input.charAt(input.length() - i))
                .map(String::valueOf)
                .collect(Collectors.joining());
        System.out.printf("Reversed string: %s\n", reversed);
    }

    private static void twoStringsAreAnagram() {
        String first = "nliset";
        String second = "sieltn";
        boolean isAnagram = sortChars(first).equals(sortChars(second));
        System.out.printf("String is Anagram: %s\n", isAnagram);
    }

    private static String sortChars(String input) {
        return input.chars()
                .mapToObj(c -> String.valueOf((char) c))
                .sorted()
                .collect(Collectors.joining());

    }

    private static void findFirstNonRepeatedChar(String input) {
        String firstNonRepeatedChar = input.chars()
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(f -> f.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("No Char Found");

        System.out.printf("First non repeated char: %s%n", firstNonRepeatedChar);
    }

    private static void findOccurencesEachCharacter(String input) {
        var occurences = input.chars()
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.printf("Occurences each character: %s%n", occurences);
    }

    private static void findFirstRepeatedCharacter(String input) {
        String firstRepeatedCharacter = input.toLowerCase().chars()
                .mapToObj(c -> (char) c)
                .filter(f -> input.indexOf(f) != input.lastIndexOf(f))
                .findFirst()
                .map(String::valueOf)
                .orElse("No Character Found");

        /*Optional<Character> firstRepeatedChar = input.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> input.indexOf(c) != input.lastIndexOf(c))
                .findFirst();*/

        System.out.printf("First repeated character: %s%n", firstRepeatedCharacter);

        long totalDuplicateCount = input.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> input.indexOf(c) != input.lastIndexOf(c))
                .distinct()
                .count();
        System.out.printf("First repeated character: %d%n", totalDuplicateCount);

        var frequency = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        //frequency.forEach((k,v) -> System.out.printf("%s -> %d%n", k, v));

        var map = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        map.forEach((k, v) -> System.out.printf("%s -> %d%n", k, v));

    }
}
