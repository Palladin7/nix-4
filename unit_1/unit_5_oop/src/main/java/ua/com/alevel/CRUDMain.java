package ua.com.alevel;

import ua.com.alevel.controller.AuthorController;
import ua.com.alevel.controller.BookController;

public class CRUDMain {

    public static void main(String[] args) {
        System.out.println("-----------------Book Controller-----------------");
        BookController bookController = new BookController();
        bookController.run();

        System.out.println("-----------------Author Controller-----------------");
        AuthorController authorController = new AuthorController();
        authorController.run();
    }
}
