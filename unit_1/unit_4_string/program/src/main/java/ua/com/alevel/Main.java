package ua.com.alevel;

import ua.com.alevel.test.Test;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Quick functionality check
        Test test = new Test();
        test.run();

        // Full functionality check
        Scanner scanner = new Scanner(System.in);
        boolean finished = false; // When to finish program execution

        System.out.println();
        System.out.println("-----ReverseString-----");
        System.out.println();

        // Program execution
        while (!finished) {
            System.out.println("-------------------------------------------------------------");
            System.out.println("Do you wish to continue? (Y / N)");
            System.out.println("-------------------------------------------------------------");
            String choice = scanner.nextLine();

            // Getting valid input
            while (!choice.equals("Y") && !choice.equals("N")) {
                System.out.println("Invalid input, please try again");
                System.out.println("Do you wish to continue? (Y / N)");
                System.out.println("-------------------------------------------------------------");
                choice = scanner.nextLine();
            }

            // Finish program execution
            if (choice.equals("N")) {
                System.out.println("Have a nice day!");
                System.out.println("-------------------------------------------------------------");
                finished = true;
            } else {
                System.out.println("-------------------------------------------------------------");
                System.out.println("Enter string: ");
                String string = scanner.nextLine();

                System.out.println();
                System.out.println("How to reverse?");
                System.out.println("1: reverse(String str)");
                System.out.println("2: reverse(String str, String dest)");
                System.out.println("3: reverse(String str, int firstIndex, int lastIndex)");
                System.out.print("Enter number 1 - 3 : ");

                String input = scanner.nextLine();

                System.out.println();
                switch (input) {
                    case "1":
                        System.out.println("--> " + string);
                        System.out.println("<-- " + ReverseString.reverse(string));
                        break;
                    case "2":
                        System.out.println("Enter dest:");
                        String dest = scanner.nextLine();

                        System.out.println("--> " + string);
                        System.out.println("<-- " + ReverseString.reverse(string, dest));
                        break;
                    case "3":
                        try{
                            System.out.print("Enter firstIndex: ");
                            int firstIndex = scanner.nextInt();

                            System.out.print("Enter secondIndex: ");
                            int secondIndex = scanner.nextInt();

                            // After using nexInt()
                            scanner.nextLine();

                            System.out.println("--> " + string);
                            System.out.println("<-- " + ReverseString.reverse(string, firstIndex, secondIndex));
                        } catch (InputMismatchException ex) {
                            System.out.println("Invalid number inputted");
                        }

                        break;
                    default:
                        System.out.println("Invalid number inputted");
                }
            }
        }
    }
}
