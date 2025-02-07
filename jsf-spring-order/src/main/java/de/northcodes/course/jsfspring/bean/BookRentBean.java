package de.northcodes.course.jsfspring.bean;

import de.northcodes.course.jsfspring.model.Book;
import de.northcodes.course.jsfspring.model.BookRent;
import de.northcodes.course.jsfspring.model.State;
import de.northcodes.course.jsfspring.model.User;
import de.northcodes.course.jsfspring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class BookRentBean implements Serializable {

    private static final long serialVersionUID = 1L;


    @Autowired
    private UserManager userManager;

    @Autowired
    private BookService bookService;
    private List<BookRent> bookRents;

    @PostConstruct
    public void init() {
        bookRents = bookService.getAllBookRents();
    }

    public List<de.northcodes.course.jsfspring.model.BookRent> getBookRents() {

        return bookService.getAllBookRents();
    }

    public void submitRent(BookRent bookRent) throws IOException {
        Book book = bookRent.getBook();
        book.setStatus(State.UNAVAILABLE);
        bookService.updateBook(book);

        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().getExternalContext().redirect("booksystem.xhtml");

        System.out.println("Submit rent");
    }

    public void reserveAction(BookRent bookRent) throws IOException {
        Book book = bookRent.getBook();
        book.setStatus(State.RESERVED);
        bookService.updateBook(book);

        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().getExternalContext().redirect("booksystem.xhtml");

        System.out.println("Reserve action");
    }

    public void deleteAction(BookRent bookRent) throws IOException {
        Book book = bookRent.getBook();
        book.setStatus(State.AVAILABLE);
        bookService.updateBook(book);

        bookService.deleteBookRent(bookRent);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().getExternalContext().redirect("booksystem.xhtml");

        System.out.println("Deleted BookRent entry and updated book status to available");
    }

    public List<BookRent> getCurrentUserBookRents() {
        User currentUser = userManager.getCurrentUser();
        return bookService.findByUser(currentUser);
    }
}