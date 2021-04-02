package ua.com.alevel.service.impl;

import ua.com.alevel.dao.AuthorDAO;
import ua.com.alevel.dao.impl.AuthorDAOImpl;
import ua.com.alevel.entity.Author;
import ua.com.alevel.service.AuthorService;

import java.util.Set;

public class AuthorServiceImpl implements AuthorService {

    private final AuthorDAO authorDAO = new AuthorDAOImpl();

    @Override
    public void create(Author author) {
        authorDAO.create(author);
    }

    @Override
    public Author read(int id) {
        existAuthor(id);
        return authorDAO.read(id);
    }

    @Override
    public Set<Author> read() {
        return authorDAO.read();
    }

    @Override
    public void update(Author author) {
        existAuthor(author.getId());
        authorDAO.update(author);
    }

    @Override
    public void delete(int id) {
        existAuthor(id);
        authorDAO.delete(id);
    }

    private void existAuthor(int id) {
        Author author = authorDAO.read(id);
        if (author == null) {
            throw new RuntimeException("404");
        }
    }
}
