package de.northcodes.course.jsfspring.bean;

import javax.faces.view.ViewScoped;

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

    private long bookId;

    private Book book;

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public void onload() {
        book = bookService.getBook(bookId);
    }

    public Book getBook() {
        return book;
    }
}
