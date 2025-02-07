package de.northcodes.course.jsfspring.bean;

import javax.faces.view.ViewScoped;

import de.northcodes.course.jsfspring.model.User;
import de.northcodes.course.jsfspring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.northcodes.course.jsfspring.model.Book;

import java.io.Serializable;
import javax.annotation.ManagedBean;

@Component
@ViewScoped
@ManagedBean
public class BookDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserManager userManager;

    private long bookId;

    private Book book;
    private User user;

    public long getBookId() {
        return bookId;
    }

    public String borrowBook() {
        return bookService.borrowBook(bookId, getUserId());
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public void onload() {

        book = bookService.getBook(bookId);
        user = userManager.getCurrentUser();

    }

    public Book getBook() {
        return book;
    }

    public long getUserId() {
        if (user == null) {
            return 0;
        }
        return user.getId();
    }
}
