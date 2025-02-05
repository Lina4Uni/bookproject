package de.northcodes.course.jsfspring.bean;

import javax.annotation.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.northcodes.course.jsfspring.model.Book;
import de.northcodes.course.jsfspring.service.BookService;

import java.util.List;

@RequestScoped
@Component
@ManagedBean
public class PopularBooks {

	@Autowired
	private BookService bookService;

	public List<Book> getBooks() {
		return bookService.getPopularBooks();
	}
}
