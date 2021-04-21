package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Author extends AbstractEntity {

    private String firstName;
    private String lastName;
    // When author is called, books returned as string, so no StackOverflow will occur
    private Set<Book> books = new HashSet<>();

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Author {" + "firstName = ").append(firstName)
                .append(", lastName = ").append(lastName)
                .append(", id = ").append(getId())
                .append(", books = ");

        Book[] booksArray = new Book[books.size()];
        books.toArray(booksArray);

        for (int i = 0; i < booksArray.length; i++) {
            string.append(booksArray[i].getName());

            if (i + 1 < booksArray.length) {
                string.append(", ");
            }
        }
        string.append("}");
        return string.toString();
    }
}
