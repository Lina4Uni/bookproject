package de.northcodes.course.jsfspring.persistence;

import org.springframework.data.repository.CrudRepository;

import de.northcodes.course.jsfspring.model.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByNameContainingIgnoreCase(String title);

}