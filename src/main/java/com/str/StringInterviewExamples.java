package com.str;

public class StringInterviewExamples {

    public static void main(String[] args) {
        String input = "java is good language";
        manipulateString(input);
        //String output="JavA IS GooD LanguagE";
    }

    private static void manipulateString(String input) {
        String[] words = input.split(" ");
        StringBuilder output = new StringBuilder();

        for (String word : words) {
            if (word.length() == 1) {
                output.append(Character.toUpperCase(word.charAt(0)));
            } else {
                output.append(Character.toUpperCase(word.charAt(0)));
                if (word.length() > 2) {
                    output.append(word, 1, word.length() - 1);
                }
                output.append(Character.toUpperCase(word.charAt(word.length() - 1)));
            }
            output.append(" ");
        }
        System.out.println(output.toString().trim());
    }
}
