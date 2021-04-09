package ua.com.alevel.controller;

import ua.com.alevel.date.DateClass;
import ua.com.alevel.exception.DateClassException;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class DateClassController {

    public static void run() {
        boolean created = false;
        while (!created)
        try {
            String input;
            Scanner scanner = new Scanner(System.in);
            System.out.println("-------------------DateClass---------------------");
            System.out.println("Possible date input formats:");
            System.out.println(
                    "1) dd/mm/yy - 01/12/21\n" +
                            "2) m/d/yyyy - 3/4/2021\n" +
                            "3) mmm-d-yy - March 4 21\n" +
                            "4) dd-mmm-yyyy 00:00 - 09 April 789 45:23\n");
            System.out.print("Enter desired date in one of the formats above: ");
            input = scanner.nextLine();
            DateClass date1 = new DateClass(input);

            DateClass date2 = new DateClass("// 22:15:34");
            DateClass date3 = new DateClass("09 December 1 6:23");
            DateClass date4 = new DateClass("//");
            DateClass date5 = new DateClass("23/04/1809");

            Set<DateClass> dates = new TreeSet<>();

            dates.add(date1);
            dates.add(date2);
            dates.add(date3);
            dates.add(date4);
            dates.add(date5);

            // Ascending dates, format 4
            System.out.println();
            System.out.println("Ascending dates:");
            for (DateClass d : dates ) {
                d.print();
            }

            // Changing date format
            System.out.print("Date format 4 : ");
            date1.print();

            date1.setDateFormat();
            System.out.print("New date format: ");
            date1.print();

            // Adding days
            System.out.print("Enter number of days to add: ");
            date1.addDays(scanner.nextLong());
            scanner.nextLine();
            date1.print();

            // Subtracting days
            System.out.print("Enter number of days to subtract: ");
            date1.subtractDays(scanner.nextLong());
            scanner.nextLine();
            date1.print();

            // What date is bigger
            System.out.println("\nIs date1 larger than date2?: ");
            date1.print();
            date2.print();
            System.out.println(date1.larger(date2));

            // Difference
            date1.difference(date2);

            // Current date
            System.out.print("Current date: ");
            DateClass dateClass = new DateClass();
            dateClass.print();

            created = true;
        } catch (DateClassException e) {
            System.out.println(e.getMessage());
            System.out.println("Try again");
        } catch (InputMismatchException ex) {
            System.out.println("Wrong input");
        }
    }
}
