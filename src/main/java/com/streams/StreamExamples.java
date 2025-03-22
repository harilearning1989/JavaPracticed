package com.streams;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class StreamExamples {

    public static void main(String[] args) {
        //sameStreamProcessAgain();
        //correctWayToUse();
        sameStreamProcessAgainSupplier();
    }

    private static void correctWayToUse() {
        List<String> names = List.of("Alice", "Bob", "Charlie", "David", "Eve");
        names.stream()
                .filter(name -> name.startsWith("A"))
                .forEach(System.out::println);
        names.stream() // Creating a new stream again
                .filter(name -> name.length() > 3)
                .forEach(System.out::println);
    }

    private static void sameStreamProcessAgainSupplier() {
        List<String> names = List.of("Alice", "Bob", "Charlie", "David", "Eve");
        // Supplier that provides a new stream every time it's called
        Supplier<Stream<String>> nameStreamSupplier = names::stream;
        // First usage
        nameStreamSupplier.get()
                .filter(name -> name.startsWith("A"))
                .forEach(System.out::println);

        // Second usage (works fine)
        nameStreamSupplier.get()
                .filter(name -> name.length() > 3)
                .forEach(System.out::println);

        // This ensures a fresh stream is available each time you call get().
    }
    private static void sameStreamProcessAgain() {
        List<String> names = List.of("Alice", "Bob", "Charlie", "David", "Eve");
        Stream<String> nameStream = names.stream(); // Creating a stream
        // First usage
        nameStream.filter(name -> name.startsWith("A")).forEach(System.out::println);
        // Second usage (Throws IllegalStateException)
        nameStream.filter(name -> name.length() > 3).forEach(System.out::println);
        //Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed
    }
}
