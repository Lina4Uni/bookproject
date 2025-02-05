package de.northcodes.course.jsfspring.bean;

import de.northcodes.course.jsfspring.model.Book;
import de.northcodes.course.jsfspring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ManagedBean(name = "bookManagement")
@ViewScoped
@Component
public class BookManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private BookService bookService;

    private String name;
    private String description;
    private BigDecimal price;
    private String imageName;
    private String author;
    private String isbn;
    private String publisher;
    private String genre;

    @PostConstruct
    public void init() {
    }

    public void addBook() {
        Book newBook = new Book(name, description, price, imageName, author, isbn, publisher, genre);
        bookService.saveBook(newBook);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Buch hinzugefügt", "Das Buch wurde erfolgreich hinzugefügt."));
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}