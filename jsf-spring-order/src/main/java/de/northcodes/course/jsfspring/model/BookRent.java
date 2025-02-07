package de.northcodes.course.jsfspring.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;

@Entity
@Table(name = AbstractEntity.SHOP_PREFIX + "book_rent")
public final class BookRent extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "rent_date", nullable = false)
    private Timestamp rentDate;

    private BookRent() {}

    public BookRent(User user, Book book, Timestamp rentDate) {
        this.user = user;
        this.book = book;
        this.rentDate = rentDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Timestamp getRentDate() {
        return rentDate;
    }

    public void setRentDate(Timestamp rentDate) {
        this.rentDate = rentDate;
    }

    @Override
    public String toString() {
        return "BookRent ID: " + this.getId() + ", User ID: " + this.getUser().getId() + ", Book ID: " + this.getBook().getId() + ", Rent Date: " + this.getRentDate();
    }
}