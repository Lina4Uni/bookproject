package de.northcodes.course.jsfspring.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.view.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import de.northcodes.course.jsfspring.model.Book;
import de.northcodes.course.jsfspring.service.BookService;
import java.io.Serializable;
import java.util.List;

@Component
@RequestScoped
public class SearchBooks implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private BookService bookService;


    private List<Book> books;
    private String searchQuery;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

//    @PostConstruct
//    public void init() {
//        books = bookService.findAllBooks();
//    }

    public List<Book> getBooks() {
        if (books == null) {
            books = bookService.findAllBooks();
        }
        return books;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public void search() {
        if (searchQuery != null && !searchQuery.isEmpty()) {
            books = bookService.searchBooks(searchQuery);
        } else {
            books = bookService.findAllBooks();
        }
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}