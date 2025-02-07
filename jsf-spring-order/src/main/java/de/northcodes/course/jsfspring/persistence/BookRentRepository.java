package de.northcodes.course.jsfspring.persistence;

import de.northcodes.course.jsfspring.model.BookRent;
import de.northcodes.course.jsfspring.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRentRepository extends CrudRepository<BookRent, Long> {
    List<BookRent> findByUser(User currentUser);
}