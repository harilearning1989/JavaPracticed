package com.array;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.OptionalInt;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class ArrayExamples {

    //https://www.java67.com/2018/06/data-structure-and-algorithm-interview-questions-programmers.html

    public static void main(String[] args) {
        int[] arrayInt = {
                1789, 2035, 1899, 1456, 2013,
                1458, 2458, 1254, 1472, 2365,
                1456, 2165, 1457, 2456
        };
        //exampleMethod();
        //sortArray(arrayInt);
        OptionalInt indexOpt = findIndex(arrayInt, 18899);
        if(indexOpt.isPresent()){
            System.out.println(indexOpt.getAsInt());
        }else{
            System.out.println("Nothing Present");
        }
    }

    private static OptionalInt findIndex(int[] arrayInt, int num) {
        if (arrayInt == null)
            return OptionalInt.empty();
        int length = arrayInt.length;
        int i = 0;
        while (i < length) {
            if (arrayInt[i] == num) {
                return OptionalInt.of(i);
            } else {
                i = i + 1;
            }
        }
        return OptionalInt.empty();
    }

    private static void sortArray(int[] arrayInt) {
        Arrays.sort(arrayInt);
        System.out.println("Sorted numeric array : " + Arrays.toString(arrayInt));

        String[] my_array2 = {"Java", "Python", "PHP", "C#", "C Programming", "C++"};
        Arrays.sort(my_array2);
        System.out.println("Sorted string array : " + Arrays.toString(my_array2));
    }

    private static void exampleMethod() {
        int[] numbers = {10, 20, 30, 40, 50};
        IntStream stream = IntStream.range(1, 100);
        /*sumIntegers(numbers);
        sumIntStream(stream);
        summaryStats(numbers);*/

        /*List<Integer> primes = stream.filter(ArrayExamples::isPrime)
                .boxed()
                .toList();
        System.out.println(primes);*/

        int[] iArray = new int[]{1, 2, 3, 5};
        int missing = printMissingNumber(iArray, 5);
        System.out.printf("Missing number in array %s is %d %n",
                Arrays.toString(iArray), missing);

        System.out.printf("Missing number ::" + missing);
    }

    private static int printMissingNumber(int[] numbers, int totalCount) {
        int expectedSum = totalCount * ((totalCount + 1) / 2);
        int actualSum = 0;
        for (int i : numbers) {
            actualSum += i;
        }
        return expectedSum - actualSum;
    }

    public static boolean isPrime(int i) {
        IntPredicate isDivisible = index -> i % index == 0;
        return i > 1 && IntStream.range(2, i).noneMatch(isDivisible);
    }

    private static void summaryStats(int[] numbers) {
        IntSummaryStatistics stats = IntStream.of(numbers).summaryStatistics();
        System.out.println("Sum::" + stats.getSum() + "===Max::" + stats.getMax());
    }

    private static void sumIntStream(IntStream stream) {
        System.out.println("The stream sum is::" + stream.sum());
    }

    private static void sumIntegers(int[] numbers) {
        int sum = IntStream.of(numbers).sum();
        System.out.println("The int sum is::" + sum);
    }


}
