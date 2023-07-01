package com.assignment;

public class PostAndPrefix {
    public static void main(String[] args) {
       //incrementNumber();
       //numericPattern();
        illiterateMen();
    }

    private static void incrementNumber() {
        int number = 5;

        System.out.println("Actual number:: " + number);

        int postFix = number++;
        System.out.println("Postfix:: " + postFix);
        System.out.println("After postfix increment: " + number);

        number = 5;

        int prefix = ++number;
        System.out.println("Prefix increment:: " + prefix);
        System.out.println("After prefix increment: " + number);
    }

    public static void numericPattern(){
        int n = 5;
        for(int i=n;i>=1;i--){
            for(int j=1;j<=i;j++){
                //System.out.print(j+ " ");
                System.out.print(j);
            }
            System.out.println();
        }
    }
    public static void illiterateMen(){
        int totalPop = 80000;
        double menPercentage = 52;
        double literateMenPercentage = 35;
        double menPopulation = totalPop * (menPercentage / 100);
        double literateMen = menPopulation * (literateMenPercentage / 100);
        double illiterateMen = menPopulation - literateMen;
        System.out.println("Number of illiterate men: " + illiterateMen);
    }
}
