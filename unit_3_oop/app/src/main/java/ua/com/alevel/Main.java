package ua.com.alevel;

import ua.com.alevel.calculator.CalcService;
import ua.com.alevel.calculator.factory.CalcFactory;
import ua.com.alevel.console.ConsoleService;
import ua.com.alevel.console.factory.ConsoleFactory;
import ua.com.alevel.test.Test;

import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
        // Quick functionality check
        Test test = new Test();
        test.run();

        // Full functionality check
        final CalcService calcService = CalcFactory.getInstance().getCalcService();
        final ConsoleService consoleService = ConsoleFactory.getInstance().getConsoleService();
        boolean finished = false; // When to finish program execution
        boolean invalidNumber = false; // Check if valid numbers were inputted

        String num1;
        String num2;
        String operand;

        consoleService.println("-----Calculator-----");
        consoleService.println();

        // Program execution
        while (!finished) {
            consoleService.println("-------------------------------------------------------------");
            consoleService.println("Do you wish to continue? (Y / N)");
            consoleService.println("-------------------------------------------------------------");
            String choise = consoleService.input();

            // Getting valid input
            while (!choise.equals("Y") && !choise.equals("N")) {
                consoleService.println("Invalid input, please try again");
                consoleService.println("Do you wish to continue? (Y / N)");
                consoleService.println("-------------------------------------------------------------");
                choise = consoleService.input();
            }

            // Finish program execution
            if (choise.equals("N")) {
                consoleService.println("Have a nice day!");
                consoleService.println("-------------------------------------------------------------");
                finished = true;
            } else {
                consoleService.println("-------------------------------------------------------------");
                consoleService.print("Enter first number: ");
                num1 = consoleService.input();

                consoleService.println("\nPossible operands: <+>, <->, <*>");
                consoleService.print("Enter operand: ");
                operand = consoleService.input();

                consoleService.print("\nEnter second number: ");
                num2 = consoleService.input();

                // Check if num1 is valid number
                for (int i = 0; i < num1.length(); i++) {
                    if (!Character.isDigit(num1.charAt(i))){
                        invalidNumber = true;
                        break;
                    }
                }

                // Check if num2 is valid number
                for (int i = 0; i < num2.length(); i++) {
                    if (!Character.isDigit(num2.charAt(i))) {
                        invalidNumber = true;
                        break;
                    }
                }

                // If num1 or num2 invalid
                if (invalidNumber) {
                    consoleService.println("\nInvalid number inputted");
                    consoleService.println("-------------------------------------------------------------");
                    invalidNumber = false; // Reset
                    continue;
                }

                consoleService.println();
                switch (operand) {
                    case "+":
                        consoleService.println(num1 + " + " + num2 + " = " + calcService.sum(num1, num2));
                        break;
                    case "-":
                        consoleService.println(num1 + " - " + num2 + " = " + calcService.subtract(num1, num2));
                        break;
                    case "*":
                        consoleService.println(num1 + " * " + num2 + " = " + calcService.multiply(num1, num2));
                        break;
                    default:
                        consoleService.println("Invalid operand inputted");
                }
                consoleService.println("-------------------------------------------------------------");
            }
        }
    }
}
