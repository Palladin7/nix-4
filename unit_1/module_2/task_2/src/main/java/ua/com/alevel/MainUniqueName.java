package ua.com.alevel;

import ua.com.alevel.uniquename.UniqueName;

import java.util.Arrays;

public class MainUniqueName {

    public static void main(String[] args) {
        System.out.println("--------------------Task 2---------------------");

        String[] names1 = {"Bill", "Alex", "Jessika", "Bill", "Arnold", "Alex"};
        String uniqueName = UniqueName.firstUniqueName(Arrays.asList(names1));
        System.out.println("Names list: " + Arrays.toString(names1));
        System.out.println("First unique name: " + uniqueName);

        String[] names2 = {"Bill", "Alex", "Jessika", "Arnold", "Alex"};
        uniqueName = UniqueName.firstUniqueName(Arrays.asList(names2));
        System.out.println("\nNames list: " + Arrays.toString(names2));
        System.out.println("First unique name: " + uniqueName);
    }
}
