package de.northcodes.course.jsfspring.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import de.northcodes.course.jsfspring.model.Book;
import de.northcodes.course.jsfspring.model.BookRent;
import de.northcodes.course.jsfspring.model.User;

public interface BookService {

    List<Book> getPopularBooks();

    void saveBook(Book book);

    Book getBook(long id);

    List<Book> findAllBooks();

    List<Book> searchBooks(String searchQuery);

    String borrowBook(long bookId, long userId);

    List<BookRent> getAllBookRents();

    void deleteBookRent(BookRent bookRent);

    void updateBook(Book book);

    void saveBookRent(Book book, User user, Timestamp timestamp);

    List<BookRent> findByUser(User currentUser);
}
