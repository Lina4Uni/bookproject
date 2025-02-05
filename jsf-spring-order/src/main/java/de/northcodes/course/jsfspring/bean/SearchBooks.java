package de.northcodes.course.jsfspring.bean;

import javax.faces.view.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import de.northcodes.course.jsfspring.model.Book;
import de.northcodes.course.jsfspring.service.BookService;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class SearchBooks implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private BookService bookService;

    private List<Book> books;
    private String searchQuery;

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
}