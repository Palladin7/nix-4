package ua.com.alevel.util;

import com.opencsv.CSVWriter;
import org.apache.log4j.Logger;
import ua.com.alevel.entity.Account;
import ua.com.alevel.entity.User;
import ua.com.alevel.entity.enumeration.TransactionCategory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExportAccountStatement {

    private static final Logger logger = Logger.getLogger(NewTransaction.class);
    private static final String url = "jdbc:mysql://localhost:3306/nix-4";

    public static void exportStatement(Long idUser, String name, String password) {
        logger.info("Start exporting account statement for user " + idUser);
        try (Connection connection = DriverManager.getConnection(url, name, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from users where id = ?");

            preparedStatement.setLong(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();

            User user = new User();

            resultSet.next();

            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));

            preparedStatement = connection.prepareStatement(
                    "select * from accounts where user_id = ?");
            preparedStatement.setLong(1, user.getId());
            resultSet = preparedStatement.executeQuery();

            List<Account> accounts = new ArrayList<>();

            while (resultSet.next()) {
                Account account = new Account();

                account.setId(resultSet.getLong("id"));
                account.setIdentifier(resultSet.getString("identifier"));
                account.setUser(user);

                accounts.add(account);
            }

            user.setAccounts(accounts);

            getValidInput(connection, user);
            System.out.println("Statement export is finished");
            logger.info("Statement export is finished");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getValidInput(Connection connection, User user) throws SQLException {
        user.getAccounts().forEach(e -> System.out.println(e.getId() + " " + e.getIdentifier()));
        System.out.print("Choose account to export statement (enter id): ");
        Scanner scanner = new Scanner(System.in);
        String input = null;
        boolean correct = false;

        while (!correct) {
            input = scanner.nextLine();
            for (Account account : user.getAccounts()) {
                if (account.getId() == Long.parseLong(input)) {
                    correct = true;
                    break;
                }
            }
        }

        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from transactions " +
                        "where account_id = ? " +
                        "order by date");
        preparedStatement.setLong(1, Long.parseLong(input));
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        Timestamp firstTransaction = resultSet.getTimestamp("date");
        Timestamp lastTransaction = firstTransaction;

        while (resultSet.next()) {
            if (resultSet.isLast()) {
                lastTransaction = resultSet.getTimestamp("date");
            }
        }

        System.out.println("You need to specify first and last dates of period to make statement");
        System.out.println("First transaction: " + firstTransaction);
        System.out.println("Last transaction: " + lastTransaction);
        System.out.print("\nEnter date from: ");

        input = scanner.nextLine();

        while (firstTransaction.after(Timestamp.valueOf(input))) {
            System.out.print("Invalid date, try again: ");
            input = scanner.nextLine();
        }

        Timestamp from = Timestamp.valueOf(input);

        System.out.print("Enter date to: ");
        input = scanner.nextLine();

        while (lastTransaction.after(Timestamp.valueOf(input))) {
            System.out.print("Invalid date, try again: ");
            input = scanner.nextLine();
        }

        Timestamp to = Timestamp.valueOf(input);

        preparedStatement = connection.prepareStatement(
                "select * from transactions " +
                        "where date between ? and ? " +
                        "order by date ");
        preparedStatement.setTimestamp(1, Timestamp.valueOf(from.toString()));
        preparedStatement.setTimestamp(2, Timestamp.valueOf(to.toString()));

        resultSet = preparedStatement.executeQuery();

        preparedStatement = connection.prepareStatement(
                "select sum(amount) incomes from transactions " +
                        "where category = 0");
        ResultSet incomeResultSet = preparedStatement.executeQuery();
        incomeResultSet.next();
        int incomes = incomeResultSet.getInt("incomes");

        preparedStatement = connection.prepareStatement(
                "select sum(amount) expenses from transactions " +
                        "where category = 1");
        ResultSet expenseResultSet = preparedStatement.executeQuery();
        expenseResultSet.next();
        int expenses = expenseResultSet.getInt("expenses");

        File file = new File("export.csv");
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(file))) {

            String[] header = {"date", "category", "amount", "income sum", "saldo"};
            csvWriter.writeNext(header);

            while (resultSet.next()) {
                StringBuilder stringBuilder = new StringBuilder("");

                stringBuilder.append(resultSet.getTimestamp("date"));
                stringBuilder.append(",");

                if (resultSet.getInt("category") == 0) {
                    stringBuilder.append(TransactionCategory.INCOME);
                } else {
                    stringBuilder.append(TransactionCategory.EXPENSE);
                }

                stringBuilder.append(",");
                stringBuilder.append(resultSet.getDouble("amount"));

                if (resultSet.isFirst()) {
                    stringBuilder.append(",");
                    stringBuilder.append(incomes);
                    stringBuilder.append(",");
                    stringBuilder.append(incomes - expenses);
                }
                csvWriter.writeNext(stringBuilder.toString().split(","));
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
