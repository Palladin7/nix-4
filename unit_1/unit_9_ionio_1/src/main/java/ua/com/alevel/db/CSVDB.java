package ua.com.alevel.db;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.apache.log4j.Logger;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CSVDB {

    private final static Logger logger = Logger.getLogger(CSVDB.class);

    private final String authorsFileName = "authors.csv";
    private final String booksFileName = "books.csv";

    private int idAuthors;
    private int idBooks;

    private static CSVDB instance;

    private CSVDB() {
        String[] bookHeader = {"ID", "Name", "Authors", "Visible"};
        String[] authorHeader = {"ID", "First name", "Last name", "Books", "Visible"};

        try {
            File bookFile = new File(booksFileName);
            File authorFile = new File(authorsFileName);

            // Will not create new file if it already exists
            bookFile.createNewFile();
            authorFile.createNewFile();

            CSVReader bookReader = new CSVReader(new FileReader(bookFile));
            CSVReader authorReader = new CSVReader(new FileReader(authorFile));

            idBooks = bookReader.readAll().size();
            idAuthors = authorReader.readAll().size();

            bookReader.close();
            authorReader.close();

            if (idBooks == 0) {
                CSVWriter bookWriter = new CSVWriter(new FileWriter(bookFile));
                bookWriter.writeNext(bookHeader);
                idBooks++;
                bookWriter.close();
            }
            if (idAuthors == 0) {
                CSVWriter authorWriter = new CSVWriter(new FileWriter(authorFile));
                authorWriter.writeNext(authorHeader);
                idAuthors++;
                authorWriter.close();
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    public static CSVDB getInstance() {
        if (instance == null) {
            instance = new CSVDB();
        }
        return instance;
    }

    // ----------------Create----------------
    public void createBook(Book book) {
        logger.info("Entering the createBook method");

        book.setId(idBooks++);

        String[] bookString = new String[4];
        bookString[0] = String.valueOf(book.getId());
        bookInitializing(book, bookString);
        bookString[3] = String.valueOf(true);

        try (CSVWriter writer = new CSVWriter(new FileWriter(booksFileName, true))) {
            writer.writeNext(bookString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("Exiting the createBook method");
    }

    public void createAuthor(Author author) {
        logger.info("Entering the createAuthor method");

        author.setId(idAuthors++);

        String[] authorString = new String[5];
        authorString[0] = String.valueOf(author.getId());
        authorInitializing(author, authorString);
        authorString[4] = String.valueOf(true);

        try (CSVWriter writer = new CSVWriter(new FileWriter(authorsFileName, true))) {
            writer.writeNext(authorString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("Exiting the createAuthor method");
    }

    // ----------------Read----------------
    public Set<Book> readAllBooks() {
        logger.info("Executing the readAllBooks method");

        Set<Book> books = new HashSet<>();

        try (CSVReader reader = new CSVReader(new FileReader(booksFileName))) {
            reader.readNext();

            List<String[]> booksString;
            booksString = reader.readAll();

            for (String[] strings : booksString) {
                if (strings[3].equals("true")) {
                    int id = Integer.parseInt(strings[0]);
                    books.add(bookCreation(id, strings));
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return books;
    }

    public Set<Author> readAllAuthors() {
        logger.info("Executing the readAllAuthors method");

        Set<Author> authors = new HashSet<>();

        try (CSVReader reader = new CSVReader(new FileReader(authorsFileName))) {
            reader.readNext();

            List<String[]> authorsString;
            authorsString = reader.readAll();

            for (String[] strings : authorsString) {
                if (strings[4].equals("true")) {
                    int id = Integer.parseInt(strings[0]);
                    authors.add(authorCreation(id, strings));
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return authors;
    }

    public Book readBook(int id) {
        logger.info("Executing the readBook method");

        try (CSVReader reader = new CSVReader(new FileReader(booksFileName))) {
            reader.readNext();

            List<String[]> booksString;
            booksString = reader.readAll();

            for (String[] strings : booksString) {
                if (Integer.parseInt(strings[0]) == id && strings[3].equals("true")) {
                    return bookCreation(id, strings);
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Author readAuthor(int id) {
        logger.info("Executing the readAuthor method");

        try (CSVReader reader = new CSVReader(new FileReader(authorsFileName))) {
            reader.readNext();

            List<String[]> authorsString;
            authorsString = reader.readAll();

            for (String[] strings : authorsString) {
                if (Integer.parseInt(strings[0]) == id && strings[4].equals("true")) {
                    return authorCreation(id, strings);
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Helper method to remove duplicate code
    private Book bookCreation(int id, String[] strings) {
        String name = strings[1];
        Set<Author> authors = new HashSet<>();

        String[] authorsString = strings[2].split(", ");
        for (String s : authorsString) {
            String[] authorStr = s.split(" ");

            Author author = new Author();
            author.setFirstName(authorStr[0]);
            author.setLastName(authorStr[1]);

            authors.add(author);
        }

        Book book = new Book();

        book.setId(id);
        book.setName(name);
        book.setAuthors(authors);

        return book;
    }

    // Helper method to remove duplicate code
    private Author authorCreation(int id, String[] strings) {
        String firstName = strings[1];
        String lastName = strings[2];
        Set<Book> books = new HashSet<>();

        String[] booksString = strings[3].split(", ");
        for (String s : booksString) {
            Book book = new Book();
            book.setName(s);

            books.add(book);
        }

        Author author = new Author();

        author.setId(id);
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setBooks(books);

        return author;
    }

    // ----------------Read By----------------
    public Set<Author> readAuthorsByBook(Book book) {
        logger.info("Executing the readAuthorsByBook method");

        Set<Author> authors = new HashSet<>();

        try (CSVReader reader = new CSVReader(new FileReader(authorsFileName))) {
            reader.readNext();

            List<String[]> authorsString;
            authorsString = reader.readAll();

            for (String[] strings : authorsString) {
                if (strings[3].contains(book.getName()) && strings[4].equals("true")) {
                    int id = Integer.parseInt(strings[0]);
                    authors.add(authorCreation(id, strings));
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return authors;
    }

    public Set<Book> readBooksByAuthor(Author author) {
        logger.info("Executing the readBooksByAuthor method");

        Set<Book> books = new HashSet<>();

        try (CSVReader reader = new CSVReader(new FileReader(booksFileName))) {
            reader.readNext();

            List<String[]> booksString;
            booksString = reader.readAll();

            for (String[] strings : booksString) {
                if (strings[2].contains(author.getFirstName() + " " + author.getLastName())
                        && strings[4].equals("true")) {
                    int id = Integer.parseInt(strings[0]);
                    books.add(bookCreation(id, strings));
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return books;
    }

    // ----------------Update----------------
    public void updateBook(Book book) {
        logger.info("Entering the updateBook method");

        try {
            CSVReader reader = new CSVReader(new FileReader(booksFileName));
            List<String[]> books = reader.readAll();
            reader.close();

            if (books.size() < book.getId() - 1 || books.get(book.getId())[3].equals("false")) {
                return;
            }

            String[] updatedBook = books.get(book.getId());
            bookInitializing(book, updatedBook);

            books.set(book.getId(), updatedBook);

            CSVWriter writer = new CSVWriter(new FileWriter(booksFileName));
            writer.writeAll(books);
            writer.close();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        logger.info("Exiting the updateBook method");
    }

    // Helper method to remove duplicate code
    private void bookInitializing(Book book, String[] updatedBook) {
        updatedBook[1] = book.getName();

        Author[] authors = new Author[book.getAuthors().size()];
        book.getAuthors().toArray(authors);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < authors.length; i++) {
            stringBuilder.append(authors[i].getFirstName());
            stringBuilder.append(" ");
            stringBuilder.append(authors[i].getLastName());

            if (i + 1 < authors.length) {
                stringBuilder.append(", ");
            }
        }

        updatedBook[2] = stringBuilder.toString();
    }

    public void updateAuthor(Author author) {
        logger.info("Entering the updateBook method");

        try {
            CSVReader reader = new CSVReader(new FileReader(authorsFileName));
            List<String[]> authors = reader.readAll();
            reader.close();

            if (authors.size() < author.getId() - 1|| authors.get(author.getId())[4].equals("false")) {
                return;
            }

            String[] updatedAuthor = authors.get(author.getId());
            authorInitializing(author, updatedAuthor);

            authors.set(author.getId(), updatedAuthor);

            CSVWriter writer = new CSVWriter(new FileWriter(authorsFileName));
            writer.writeAll(authors);
            writer.close();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        logger.info("Exiting the updateAuthor method");
    }

    private void authorInitializing(Author author, String[] updatedAuthor) {
        updatedAuthor[1] = author.getFirstName();
        updatedAuthor[2] = author.getLastName();

        Book[] books = new Book[author.getBooks().size()];
        author.getBooks().toArray(books);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < books.length; i++) {
            stringBuilder.append(books[i].getName());

            if (i + 1 < books.length) {
                stringBuilder.append(", ");
            }
        }

        updatedAuthor[3] = stringBuilder.toString();
    }

    // ----------------Delete----------------
    public void deleteBook(int id) {
        logger.info("Entering the deleteBook method");

        try {
            CSVReader reader = new CSVReader(new FileReader(booksFileName));
            List<String[]> books = reader.readAll();
            reader.close();

            if (books.size() < id - 1 || books.get(id)[3].equals("false")) {
                return;
            }

            books.get(id)[3] = "false";

            CSVWriter writer = new CSVWriter(new FileWriter(booksFileName));
            writer.writeAll(books);
            writer.close();

            logger.info("Exiting the deleteBook method");
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    public void deleteAuthor(int id) {
        logger.info("Entering the deleteAuthor method");

        try {
            CSVReader reader = new CSVReader(new FileReader(authorsFileName));
            List<String[]> authors = reader.readAll();
            reader.close();

            if (authors.size() < id - 1 || authors.get(id)[4].equals("false")) {
                return;
            }

            authors.get(id)[4] = "false";

            CSVWriter writer = new CSVWriter(new FileWriter(authorsFileName));
            writer.writeAll(authors);
            writer.close();

            logger.info("Exiting the deleteBook method");
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        logger.info("Exiting the deleteAuthor method");
    }
}
