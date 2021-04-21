package ua.com.alevel;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.impl.AuthorServiceImpl;

import java.util.HashSet;
import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthorTest {

    private static final AuthorService authorService = new AuthorServiceImpl();

    @BeforeAll
    public static void init() {
        for (int i = 1; i <= 10; i++) {
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
        Assertions.assertNotNull(authorService.read());
    }

    @Test
    @Order(1)
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
    @Order(2)
    public void readAuthor() {
        Assertions.assertEquals("FirstName1", authorService.read(1).getFirstName());
    }

    @Test
    @Order(3)
    public void readAllAuthors() {
        Assertions.assertNotNull(authorService.read());
    }

    @Test
    @Order(4)
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
    @Order(5)
    public void deleteAuthor() {
        int beforeDeletion = authorService.read().size();
        authorService.delete(1);
        int afterDeletion = authorService.read().size();
        Assertions.assertEquals(beforeDeletion - 1, afterDeletion);
    }
}
