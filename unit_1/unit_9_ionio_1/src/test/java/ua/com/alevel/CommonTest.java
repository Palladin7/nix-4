package ua.com.alevel;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.BookService;
import ua.com.alevel.service.impl.AuthorServiceImpl;
import ua.com.alevel.service.impl.BookServiceImpl;

import java.util.HashSet;
import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommonTest {

    private static final BookService bookService = new BookServiceImpl();
    private static final AuthorService authorService = new AuthorServiceImpl();

    @BeforeAll
    public static void init() {
        for (int i = 1; i <= 10; i++) {
            //---------------Book-------------
            String name = "Book" + i;
            Set<Author> authors = new HashSet<>();

            // Author 1
            Author author1 = new Author();
            author1.setFirstName("FirstName" + i);
            author1.setLastName("LastName" + i);
            authors.add(author1);

            // Author 2
            Author author2 = new Author();
            author2.setFirstName("FirstName" + (i + 1));
            author2.setLastName("LastName" + (i + 1));
            authors.add(author2);

            // Book itself
            Book book = new Book();
            book.setName(name);
            book.setAuthors(authors);

            bookService.create(book);

            //---------------Author-------------
            String firstName = "FirstName" + i;
            String lastName = "LastName" + i;

            Set<Book> books = new HashSet<>();

            // Book 1
            Book book1 = new Book();
            book1.setName("BookName" + i);
            books.add(book1);

            // Book 2
            Book book2 = new Book();
            book2.setName("BookName" + (i + 1));
            books.add(book2);

            // Author itself
            Author author = new Author();
            author.setFirstName(firstName);
            author.setLastName(lastName);
            author.setBooks(books);

            authorService.create(author);
        }
        Assertions.assertNotNull(bookService.read());
        Assertions.assertNotNull(authorService.read());
    }

    @Test
    @Order(1)
    public void createBook() {
        // To check if we successfully created new Book
        Set<Book> books = bookService.read();
        int previous = books.size();

        String name = "Book";
        Set<Author> authors = new HashSet<>();

        // Author 1
        Author author1 = new Author();
        author1.setFirstName("FirstName_1");
        author1.setLastName("LastName_1");
        authors.add(author1);

        // Author 2
        Author author2 = new Author();
        author2.setFirstName("FirstName_2");
        author2.setLastName("LastName_2");
        authors.add(author2);

        // Book itself
        Book book = new Book();
        book.setName(name);
        book.setAuthors(authors);

        bookService.create(book);

        books = bookService.read();
        int next = books.size();
        Assertions.assertEquals(previous + 1, next);
    }

    @Test
    @Order(2)
    public void readBook() {
        Assertions.assertEquals("Book1", bookService.read(1).getName());
    }

    @Test
    @Order(3)
    public void readAllBooks() {
        Assertions.assertNotNull(bookService.read());
    }

    @Test
    @Order(4)
    public void updateBook() {
        Book book = bookService.read(5);

        String name = "UpdatedBook";
        Set<Author> authors = new HashSet<>();

        // Author 1
        Author author1 = new Author();
        author1.setFirstName("UpdatedFirstName_1");
        author1.setLastName("UpdatedLastName_1");
        authors.add(author1);

        // Author 2
        Author author2 = new Author();
        author2.setFirstName("UpdatedFirstName_2");
        author2.setLastName("UpdatedLastName_2");
        authors.add(author2);

        // Book itself
        book.setName(name);
        book.setAuthors(authors);

        bookService.update(book);

        Assertions.assertEquals("UpdatedBook", book.getName());
    }

    @Test
    @Order(5)
    public void deleteBook() {
        int beforeDeletion = bookService.read().size();
        bookService.delete(1);
        int afterDeletion = bookService.read().size();
        Assertions.assertEquals(beforeDeletion - 1, afterDeletion);
    }

    @Test
    @Order(6)
    public void createAuthor() {
        // To check if we successfully created new Author
        Set<Author> authors = authorService.read();
        int previous = authors.size();

        String firstName = "FirstName";
        String lastName = "LastName";

        Set<Book> books = new HashSet<>();

        // Book 1
        Book book1 = new Book();
        book1.setName("BookName_1");
        books.add(book1);

        // Book 2
        Book book2 = new Book();
        book2.setName("BookName_2");
        books.add(book2);

        // Author itself
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setBooks(books);

        authorService.create(author);

        authors = authorService.read();
        int next = authors.size();
        Assertions.assertEquals(previous + 1, next);
    }

    @Test
    @Order(7)
    public void readAuthor() {
        Assertions.assertEquals("FirstName1", authorService.read(1).getFirstName());
    }

    @Test
    @Order(8)
    public void readAllAuthors() {
        Assertions.assertNotNull(authorService.read());
    }

    @Test
    @Order(9)
    public void updateAuthor() {
        Author author = authorService.read(5);

        String firstName = "UpdatedFirstName";
        String lastName = "UpdatedLastName";
        Set<Book> books = new HashSet<>();

        // Book 1
        Book book1 = new Book();
        book1.setName("UpdatedBook1");
        books.add(book1);

        // Book 2
        Book book2 = new Book();
        book2.setName("UpdatedBook2");
        books.add(book2);

        // Author itself
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setBooks(books);

        authorService.update(author);

        Assertions.assertEquals("UpdatedFirstName", author.getFirstName());
    }

    @Test
    @Order(10)
    public void deleteAuthor() {
        int beforeDeletion = authorService.read().size();
        authorService.delete(1);
        int afterDeletion = authorService.read().size();
        Assertions.assertEquals(beforeDeletion - 1, afterDeletion);
    }
}
