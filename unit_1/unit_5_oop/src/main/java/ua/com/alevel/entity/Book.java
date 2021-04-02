package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Book extends AbstractEntity {

    private String name;
    // When book is called, authors returned as string, so no StackOverflow will occur
    private Set<Author> authors = new HashSet<>();

    public Book() {
        super();
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Book {" + "name = ").append(name)
                .append(", id = ").append(getId())
                .append(", created = ").append(getCreated())
                .append(", authors = ");
        for (Author author : authors) {
            string.append(author.getFirstName()).append(" ").append(author.getLastName()).append(", ");
        }
        string.append("}");
        return string.toString();
    }
}
