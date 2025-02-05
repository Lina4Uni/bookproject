package de.northcodes.course.jsfspring.service;

import de.northcodes.course.jsfspring.persistence.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.northcodes.course.jsfspring.model.Book;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {

	private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);
	
	@Autowired
	BookRepository bookRepository;

	@Override
	public List<Book> getPopularBooks() {
		log.info("getPopularProducts called");
		return StreamSupport.stream(bookRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	@Override
	public void saveBook(Book book) {
		bookRepository.save(book);
	}

	@Override
	public Book getBook(long id) {
			return bookRepository.findById(id).get();
	}

	@Override
	public List<Book> findAllBooks() {
		return StreamSupport.stream(bookRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}

	@Override
	public List<Book> searchBooks(String searchQuery) {
		return bookRepository.findByNameContainingIgnoreCase(searchQuery);
	}
}
