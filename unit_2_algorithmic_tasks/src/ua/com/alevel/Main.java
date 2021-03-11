package ua.com.alevel;

import ua.com.alevel.tasks.FindAndSumNumbersInString;
import ua.com.alevel.tasks.FindSortAndCountSymbolsInString;
import ua.com.alevel.tasks.WhenDoesTheLessonEnds;

public class Main {

    public static void main(String[] args) {
        // Find and sum numbers
        String str1 = "1w4adj;ka1.2k8tt!72";
        System.out.println(FindAndSumNumbersInString.findAndSumNumbers(str1));

        System.out.println();

        // Count letters
        String str2 = "1w4adj;ka1.2k8tt!72ыииИ";
        FindSortAndCountSymbolsInString.findSortAndCountSymbols(str2);

        System.out.println();

        // When does the lesson ends
        int lesson = 7;
        WhenDoesTheLessonEnds.whenDoesTheLessonEnds(lesson);
    }
}
