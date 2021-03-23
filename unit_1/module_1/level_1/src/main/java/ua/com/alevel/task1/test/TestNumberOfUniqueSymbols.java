package ua.com.alevel.task1.test;

import ua.com.alevel.task1.NumberOfUniqueSymbols;

import java.util.Arrays;

public class TestNumberOfUniqueSymbols {

    public static void run() {
        Integer[] numbers1 = {1, 4, 5, 1, 1, 3};
        Integer[] numbers2 = {1, 1, 1, 1, 2, 2};
        System.out.println("-----------------------Unique Symbols----------------------");
        System.out.println("Array: " + Arrays.toString(numbers1));
        System.out.println("Number of unique symbols: " +
                NumberOfUniqueSymbols.uniqueSymbols(numbers1));

        System.out.println();

        System.out.println("Array: " + Arrays.toString(numbers2));
        System.out.println("Number of unique symbols: " +
                NumberOfUniqueSymbols.uniqueSymbols(numbers2));
        System.out.println("-----------------------------------------------------------");
    }
}
