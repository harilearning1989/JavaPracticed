package com.streams;

import java.util.Arrays;

public class StreamMethods {
    private static final String[] excludedEndpoints = new String[] {"/auth/signing1, */bar/**"};
    public static void main(String[] args) {
        boolean status  = Arrays.stream(excludedEndpoints)
                .allMatch(s -> s.contains("/auth/signing"));
        System.out.println(status);
    }
}
