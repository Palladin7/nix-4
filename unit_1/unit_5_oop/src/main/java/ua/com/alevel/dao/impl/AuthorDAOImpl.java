package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.AuthorDAO;
import ua.com.alevel.db.DBInMemory;
import ua.com.alevel.entity.Author;

import java.util.Set;

public class AuthorDAOImpl implements AuthorDAO {

    @Override
    public void create(Author author) {
        DBInMemory.getInstance().createAuthor(author);
    }

    @Override
    public Author read(int id) {
        return DBInMemory.getInstance().readAuthor(id);
    }

    @Override
    public Set<Author> read() {
        return DBInMemory.getInstance().readAllAuthors();
    }

    @Override
    public void update(Author author) {
        DBInMemory.getInstance().updateAuthor(author);
    }

    @Override
    public void delete(int id) {
        DBInMemory.getInstance().deleteAuthor(id);
    }
}
