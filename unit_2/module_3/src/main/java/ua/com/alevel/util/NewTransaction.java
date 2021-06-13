package ua.com.alevel.util;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import ua.com.alevel.controller.ExpenseController;
import ua.com.alevel.controller.IncomeController;
import ua.com.alevel.controller.TransactionController;
import ua.com.alevel.entity.*;
import ua.com.alevel.entity.enumeration.TransactionCategory;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class NewTransaction {

    private static final TransactionController transactionController = new TransactionController();
    private final static Logger logger = Logger.getLogger(NewTransaction.class);

    public static void addNewTransaction(Long idUser, String name, String password) {
        logger.info("Start transaction creation for user " + idUser);
        try (Session session = HibernateUtil.getSessionFactory(name, password).openSession()) {
            getValidInput(session, idUser);
            System.out.println("\nTransaction was made successfully");
        }
    }

    private static void getValidInput(Session session, Long idUser) {
        logger.info("Looking for user in db");
        User user = session.load(User.class, idUser);
        if (user == null) {
            System.out.println("No such user found");
            logger.info("user not found");
            return;
        }
        user.getAccounts().forEach(e -> System.out.println(e.getId() + " " + e.getIdentifier()));
        System.out.print("Choose account to make transaction (enter id): ");

        Scanner scanner = new Scanner(System.in);
        String input = null;
        boolean correct = false;

        while (!correct) {
            input = scanner.nextLine();
            for (Account account : user.getAccounts()) {
                if (account.getIdentifier().equals(session.load(Account.class, Long.parseLong(input)).getIdentifier())) {
                    correct = true;
                    break;
                }
            }
        }

        Account account = session.load(Account.class, Long.parseLong(input));

        logger.info("Account chosen");

        Transaction transaction = new Transaction();

        System.out.print("What type of transaction would you like to make?\n" +
                "\t1) Income\n" +
                "\t2) Expense\n" +
                "Enter 1 or 2: ");

        input = scanner.nextLine();

        while (!input.equals("1") && !input.equals("2")) {
            System.out.print("Invalid input, try again: ");
            input = scanner.nextLine();
        }

        switch (input) {
            case "1" -> transaction.setCategory(TransactionCategory.INCOME);
            case "2" -> transaction.setCategory(TransactionCategory.EXPENSE);
        }

        System.out.println("\nPossible categories of transaction: ");
        String possibleCounts = "123456789";

        if (input.equals("1")) {
            logger.info("Income transaction");
            IncomeController incomeController = new IncomeController();
            Set<Income> incomeCategories = new HashSet<>();
            List<Income> incomes = incomeController.getAllIncomes();
            incomes.forEach(e -> System.out.println(e.getType()));

            System.out.print("\nHow many categories will income have?: ");
            input = scanner.nextLine();

            while (!possibleCounts.contains(input)) {
                System.out.print("Invalid input, try again: ");
                input = scanner.nextLine();
            }

            int size = Integer.parseInt(input);

            for (int i = 0; i < size; i++) {
                System.out.print("Enter category № " + (i + 1) + ": ");
                input = scanner.nextLine();

                boolean isValid = false;

                while (!isValid) {
                    try {
                        session.load(Income.class, input);
                        isValid = true;
                    } catch (org.hibernate.ObjectNotFoundException e) {
                        System.out.println("Invalid input, try again: ");
                        input = scanner.nextLine();
                    }
                }
                incomeCategories.add(incomeController.getIncomeByType(input));
            }
            transaction.setIncomes(incomeCategories);
        } else {
            logger.info("Expense transaction");
            ExpenseController expenseController = new ExpenseController();
            Set<Expense> expenseCategories = new HashSet<>();
            List<Expense> expenses = expenseController.getAllExpenses();
            expenses.forEach(e -> System.out.println(e.getType()));

            System.out.print("\nHow many categories will expense have?: ");
            input = scanner.nextLine();

            while (!possibleCounts.contains(input)) {
                System.out.print("Invalid input, try again: ");
                input = scanner.nextLine();
            }

            int size = Integer.parseInt(input);

            for (int i = 0; i < size; i++) {
                System.out.print("Enter category № " + (i + 1) + ": ");
                input = scanner.nextLine();

                boolean isValid = false;

                while (!isValid) {
                    try {
                        session.load(Expense.class, input);
                        isValid = true;
                    } catch (org.hibernate.ObjectNotFoundException e) {
                        System.out.println("Invalid input, try again: ");
                        input = scanner.nextLine();
                    }
                }

                expenseCategories.add(expenseController.getExpenseByType(input));
            }
            transaction.setExpenses(expenseCategories);
        }

        System.out.print("Enter amount of transaction: ");
        input = scanner.nextLine();

        while (Double.parseDouble(input) <= 0) {
            System.out.print("Invalid input, try again: ");
            input = scanner.nextLine();
        }

        transaction.setAccount(account);
        transaction.setAmount(Double.parseDouble(input));
        transaction.setDate(Instant.now());

        transactionController.addTransaction(transaction);
        logger.info("Transaction is made for user " + idUser);
    }
}
