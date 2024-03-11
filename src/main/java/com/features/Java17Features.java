package com.features;

public class Java17Features {

    public static void main(String[] args) {
        switchCaseStatement();
    }
    private static void switchCaseStatement() {
        String day = "Monday";
        int dayNumber;
        switch (day) {
            case "Monday":
                dayNumber = 1;
                break;
            case "Tuesday":
                dayNumber = 2;
                break;
            default:
                dayNumber = 0;
        }
        System.out.println("Day Number::" + dayNumber);

        int dayOneNumber = switch (day) {
            case "Monday" -> 1;
            case "Tuesday" -> 2;
            default -> 0;
        };
        System.out.println("Day One Number::" + dayOneNumber);
    }
}
