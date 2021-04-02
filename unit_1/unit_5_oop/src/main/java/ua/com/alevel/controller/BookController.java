package ua.com.alevel.controller;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.BookService;
import ua.com.alevel.service.impl.BookServiceImpl;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BookController {

    private final BookService bookService = new BookServiceImpl();

    public void run() {
        Scanner input = new Scanner(System.in);
        String options = "12345";
        String option;

        while (true) {
            System.out.println(
                    "1 - create\n" +
                    "2 - read\n" +
                    "3 - update\n" +
                    "4 - delete\n" +
                    "5 - exit\n\n");
            System.out.print("Choose option: ");
            // Getting correct input
            while (!options.contains(option = input.nextLine())) {
                System.out.print("Invalid option, try again: ");
            }

            switch (option) {
                case "1" -> create(input);
                case "2" -> read();
                case "3" -> update(input);
                case "4" -> delete(input);
                case "5" -> {
                    System.out.println("Execution finished");
                    return;
                }
            }
        }
    }

    private void create(Scanner input) {
        System.out.print("Enter book name: ");
        String name = input.nextLine();

        System.out.print("How many authors in this book? (1 - 9) : ");
        // Check input to be a valid number
        String possibleNumbers = "123456789";
        String authorsCount;
        while (!possibleNumbers.contains(authorsCount = input.nextLine())) {
            System.out.print("Invalid number, try again: ");
        }
        int n = Integer.parseInt(authorsCount);

        String firstName;
        String lastName;
        Set<Author> authors = new HashSet<>();

        // Get all authors of this book
        getAuthors(input, n, authors);

        // Create Book object
        Book book = new Book();
        book.setName(name);
        book.setAuthors(authors);

        bookService.create(book);
    }

    private void read() {
        System.out.println("Books: " + bookService.read());
        System.out.println();
    }

    private void update(Scanner input) {
        System.out.print("Enter id: ");
        int id = input.nextInt();
        input.nextLine();
        Book book = bookService.read(id);
        System.out.print("Enter new name: ");
        String name = input.nextLine();

        System.out.print("How many authors in new book?: ");
        int n = input.nextInt();
        input.nextLine();
        String firstName;
        String lastName;
        Set<Author> authors = new HashSet<>();

        // Get all authors of this book
        getAuthors(input, n, authors);

        // Update book
        book.setName(name);
        book.setAuthors(authors);

        bookService.update(book);
    }

    private void delete(Scanner input) {
        System.out.print("Enter id of book to be deleted: ");
        int id = input.nextInt();
        input.nextLine();

        bookService.delete(id);
    }

    // Method to get all authors in book
    private void getAuthors(Scanner input, int n, Set<Author> authors) {
        String firstName;
        String lastName;
        for (int i = 0; i < n; i++) {
            System.out.print("Enter author firstName: ");
            firstName = input.nextLine();
            System.out.print("Enter author lastName: ");
            lastName = input.nextLine();

            Author author = new Author();
            author.setFirstName(firstName);
            author.setLastName(lastName);
            authors.add(author);
        }
        System.out.println();
    }
}
