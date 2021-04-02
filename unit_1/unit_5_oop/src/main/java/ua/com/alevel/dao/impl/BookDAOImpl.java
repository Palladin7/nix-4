package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.BookDAO;
import ua.com.alevel.db.DBInMemory;
import ua.com.alevel.entity.Book;

import java.util.Set;

public class BookDAOImpl implements BookDAO {

    @Override
    public void create(Book book) {
        DBInMemory.getInstance().createBook(book);
    }

    @Override
    public Book read(int id) {
        return DBInMemory.getInstance().readBook(id);
    }

    @Override
    public Set<Book> read() {
        return DBInMemory.getInstance().readAllBooks();
    }

    @Override
    public void update(Book book) {
        DBInMemory.getInstance().updateBook(book);
    }

    @Override
    public void delete(int id) {
        DBInMemory.getInstance().deleteBook(id);
    }
}
