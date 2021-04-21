package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.BookDAO;
import ua.com.alevel.db.CSVDB;
import ua.com.alevel.entity.Book;

import java.util.Set;

public class BookDAOImpl implements BookDAO {

    @Override
    public void create(Book book) {
        CSVDB.getInstance().createBook(book);
    }

    @Override
    public Book read(int id) {
        return CSVDB.getInstance().readBook(id);
    }

    @Override
    public Set<Book> read() {
        return CSVDB.getInstance().readAllBooks();
    }

    @Override
    public void update(Book book) {
        CSVDB.getInstance().updateBook(book);
    }

    @Override
    public void delete(int id) {
        CSVDB.getInstance().deleteBook(id);
    }
}
