package ua.com.alevel;

import ua.com.alevel.util.ExportAccountStatement;
import ua.com.alevel.util.NewTransaction;

import java.util.Scanner;


public class MainFinanceManagement {

    public static void main(String[] args) {
        System.out.println("Welcome to Finance Management app");

        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("\t1) Add new transaction");
            System.out.println("\t2) Export account statement");
            System.out.println("\t3) Finish work");

            System.out.print("\nEnter 1, 2 or 3: ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String possibleInput = "123";

            while (!possibleInput.contains(input)) {
                System.out.print("Wrong input, try again: ");
                input = scanner.nextLine();
            }

            if (input.equals("3")) {
                break;
            }

            System.out.print("Enter user id: ");
            long id = Long.parseLong(scanner.nextLine());
            System.out.print("Enter db name: ");
            String name = scanner.nextLine();
            System.out.print("Enter db password: ");
            String password = scanner.nextLine();

            switch (input) {
                case "1" -> NewTransaction.addNewTransaction(id, name, password);
                case "2" -> ExportAccountStatement.exportStatement(id, name, password);
            }
        }

        System.out.println("\nWork is finished");
        System.out.println("Have a nice day");
    }
}
