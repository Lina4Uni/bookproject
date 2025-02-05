package de.northcodes.course.jsfspring.service;

import java.util.List;

import de.northcodes.course.jsfspring.model.Book;

public interface BookService {

    List<Book> getPopularBooks();

    void saveBook(Book book);

    Book getBook(long id);

    List<Book> findAllBooks();

    List<Book> searchBooks(String searchQuery);
}
