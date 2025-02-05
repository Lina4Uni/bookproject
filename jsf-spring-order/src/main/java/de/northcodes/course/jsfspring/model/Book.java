package de.northcodes.course.jsfspring.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = AbstractEntity.SHOP_PREFIX + "book")
public final class Book extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "image_name", nullable = false)
    private String imageName;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @Column(name = "publisher", nullable = false)
    private String publisher;

    @Column(name = "genre")
    private String genre;

    private Book() {}

    public Book(String name, String description, BigDecimal price, String imageName, String author,
                String isbn, String publisher, String genre) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageName = imageName;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getShortDescription() {
        int i = description.indexOf('.');
        if (i >= 0 && i < 100) {
            return description.substring(0, i + 1);
        } else {
            return description.substring(0, Math.min(description.length(), 100)) + "...";
        }
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getImageName() {
        return imageName;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Product ID: " + this.getId() + ", name: " + this.getName() + ", author: " + this.getAuthor() +
                ", description: " + this.getDescription() + ", ISBN: " + this.getIsbn() + ", publisher: " +
                this.getPublisher() + ", genre: " + this.getGenre() + ", price: " + this.getPrice();
    }
}
