package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.AuthorDAO;
import ua.com.alevel.db.CSVDB;
import ua.com.alevel.entity.Author;

import java.util.Set;

public class AuthorDAOImpl implements AuthorDAO {

    @Override
    public void create(Author author) {
        CSVDB.getInstance().createAuthor(author);
    }

    @Override
    public Author read(int id) {
        return CSVDB.getInstance().readAuthor(id);
    }

    @Override
    public Set<Author> read() {
        return CSVDB.getInstance().readAllAuthors();
    }

    @Override
    public void update(Author author) {
        CSVDB.getInstance().updateAuthor(author);
    }

    @Override
    public void delete(int id) {
        CSVDB.getInstance().deleteAuthor(id);
    }
}
