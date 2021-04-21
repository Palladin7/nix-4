package ua.com.alevel.controller;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.impl.AuthorServiceImpl;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class AuthorController {

    private final AuthorService authorService = new AuthorServiceImpl();

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
        System.out.print("Enter author first name: ");
        String firstName = input.nextLine();
        System.out.print("Enter author last name: ");
        String lastName = input.nextLine();

        System.out.print("How many books has this author? (1 - 9) : ");
        // Check input to be a valid number
        String possibleNumbers = "123456789";
        String booksCount;
        while (!possibleNumbers.contains(booksCount = input.nextLine())) {
            System.out.print("Invalid number, try again: ");
        }
        int n = Integer.parseInt(booksCount);

        String name;
        Set<Book> books = new HashSet<>();

        // Get all authors of this book
        getBooks(input, n, books);

        // Create Author object
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setBooks(books);

        authorService.create(author);
    }

    private void read() {
        System.out.println("Authors: " + authorService.read());
        System.out.println();
    }

    private void update(Scanner input) {
        System.out.print("Enter id: ");
        int id = input.nextInt();
        input.nextLine();
        Author author = authorService.read(id);
        System.out.print("Enter new first name: ");
        String firstName = input.nextLine();
        System.out.print("Enter new last name: ");
        String lastName = input.nextLine();

        System.out.print("How many books does this author have?: ");
        int n = input.nextInt();
        input.nextLine();
        String name;
        Set<Book> books = new HashSet<>();

        // Get all authors of this book
        getBooks(input, n, books);

        // Update author
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setBooks(books);

        authorService.update(author);
    }

    private void delete(Scanner input) {
        System.out.print("Enter id of author to be deleted: ");
        int id = input.nextInt();
        input.nextLine();

        authorService.delete(id);
    }

    // Method to get all books of author
    private void getBooks(Scanner input, int n, Set<Book> books) {
        String name;
        for (int i = 0; i < n; i++) {
            System.out.print("Enter book name: ");
            name = input.nextLine();

            Book book = new Book();
            book.setName(name);
            books.add(book);
        }
        System.out.println();
    }
}
