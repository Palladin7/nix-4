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

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Book {" + "name = ").append(name)
                .append(", id = ").append(getId())
                .append(", authors = ");

        Author[] authorsArray = new Author[authors.size()];
        authors.toArray(authorsArray);

        for (int i = 0; i < authorsArray.length; i++) {
            string.append(authorsArray[i].getFirstName());
            string.append(" ");
            string.append(authorsArray[i].getLastName());

            if (i + 1 < authorsArray.length) {
                string.append(", ");
            }
        }
        string.append("}");
        return string.toString();
    }
}
