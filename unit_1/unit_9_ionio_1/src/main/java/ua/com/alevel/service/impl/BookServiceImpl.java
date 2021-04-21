package ua.com.alevel.service.impl;

import ua.com.alevel.dao.BookDAO;
import ua.com.alevel.dao.impl.BookDAOImpl;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.BookService;

import java.util.Set;

public class BookServiceImpl implements BookService {

    private final BookDAO bookDAO = new BookDAOImpl();

    @Override
    public void create(Book book) {
        bookDAO.create(book);
    }

    @Override
    public Book read(int id) {
        existBook(id);
        return bookDAO.read(id);
    }

    @Override
    public Set<Book> read() {
        return bookDAO.read();
    }

    @Override
    public void update(Book book) {
        existBook(book.getId());
        bookDAO.update(book);
    }

    @Override
    public void delete(int id) {
        existBook(id);
        bookDAO.delete(id);
    }

    private void existBook(int id) {
        Book book = bookDAO.read(id);
        if (book == null) {
            throw new RuntimeException("404");
        }
    }
}
