package ua.com.alevel.task1;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class NumberOfUniqueSymbols {
    // Returns number of unique symbols in given array
    public static <E> int uniqueSymbols(E[] numbers) {

        // Add unique numbers to Set
        Set<E> uniqueSymbols = new HashSet<>(Arrays.asList(numbers));

        return uniqueSymbols.size();
    }
}
