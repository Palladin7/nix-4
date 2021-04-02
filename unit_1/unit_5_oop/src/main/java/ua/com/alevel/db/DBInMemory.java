package ua.com.alevel.db;

import org.apache.log4j.Logger;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.HashSet;
import java.util.Set;

public class DBInMemory {

    // Logger
    private final static Logger logger = Logger.getLogger(DBInMemory.class);

    private final Set<Book> books;
    private final Set<Author> authors;

    private static DBInMemory instance;

    private DBInMemory() {
        this.books = new HashSet<>();
        this.authors = new HashSet<>();
    }

    public static DBInMemory getInstance() {
        if (instance == null) {
            instance = new DBInMemory();
        }
        return instance;
    }
    // ----------------Create----------------
    public void createBook(Book book) {
        logger.info("Entering the createBook method");

        int id = books.size() + 1;
        book.setId(id);
        books.add(book);

        logger.info("Exiting the createBook method");
    }

    public void createAuthor(Author author) {
        logger.info("Entering the createAuthor method");

        int id = authors.size() + 1;
        author.setId(id);
        authors.add(author);

        logger.info("Exiting the createAuthor method");
    }
    // ----------------Read----------------
    public Set<Book> readAllBooks() {
        logger.info("Executing the readAllBooks method");

        return books;
    }

    public Set<Author> readAllAuthors() {
        logger.info("Executing the readAllAuthors method");

        return authors;
    }

    public Book readBook(int id) {
        logger.info("Executing the readBook method");

        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public Author readAuthor(int id) {
        logger.info("Executing the readAuthor method");

        for (Author author : authors) {
            if (author.getId() == id) {
                return author;
            }
        }
        return null;
    }
    // ----------------Read By----------------
    public Set<Author> readAuthorsByBook(Book book) {
        logger.info("Executing the readAuthorsByBook method");

        return book.getAuthors();
    }

    public Set<Book> readBooksByAuthor(Author author) {
        logger.info("Executing the readBooksByAuthor method");

        return author.getBooks();
    }
    // ----------------Update----------------
    public void updateBook(Book book) {
        logger.info("Entering the updateBook method");

        Book current = readBook(book.getId());

        current.setName(book.getName());
        current.setAuthors(book.getAuthors());

        logger.info("Exiting the updateBook method");
    }

    public void updateAuthor(Author author) {
        logger.info("Entering the updateBook method");

        Author current = readAuthor(author.getId());

        current.setFirstName(author.getFirstName());
        current.setLastName(author.getLastName());
        current.setBooks(author.getBooks());

        logger.info("Entering the updateAuthor method");
    }
    // ----------------Delete----------------
    public void deleteBook(int id) {
        logger.info("Entering the deleteBook method");

        books.removeIf(book -> book.getId() == id);

        logger.info("Exiting the deleteBook method");
    }

    public void deleteAuthor(int id) {
        logger.info("Entering the deleteAuthor method");

        authors.removeIf(author -> author.getId() == id);

        logger.info("Exiting the deleteAuthor method");
    }
}
