package ua.com.alevel;

import ua.com.alevel.stringdate.StringDate;

import java.util.Arrays;
import java.util.List;

public class MainStringDate {

    public static void main(String[] args) {
        System.out.println("--------------------Task 1---------------------");

        String[] correctDates = {"2020/04/05", "05/04/9999", "04-05-1975"};
        String[] invalidDates = {"2020/04-05", "5/4/2020", "4-05-20"};

        // Convert correctDates into custom sting
        List<String> newDates = StringDate.getStringDates(Arrays.asList(correctDates));
        System.out.println("Before: " + Arrays.toString(correctDates));
        System.out.println("After: " + newDates);

        // Try to convert invalidDates into custom string
        newDates = StringDate.getStringDates(Arrays.asList(invalidDates));
        System.out.println("\nBefore: " + Arrays.toString(invalidDates));
        System.out.println("After: " + newDates);
    }
}
