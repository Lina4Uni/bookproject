package de.northcodes.course.jsfspring.service;

import de.northcodes.course.jsfspring.bean.SearchBooks;
import de.northcodes.course.jsfspring.model.Book;
import de.northcodes.course.jsfspring.model.BookRent;
import de.northcodes.course.jsfspring.model.State;
import de.northcodes.course.jsfspring.model.User;
import de.northcodes.course.jsfspring.persistence.BookRentRepository;
import de.northcodes.course.jsfspring.persistence.BookRepository;
import de.northcodes.course.jsfspring.persistence.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {

	private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private ApplicationContext applicationContext;


	@Autowired
	private BookRentRepository bookRentRepository;

	@Autowired
	private UserRepository userRepository;

//	@Autowired
//	public void setSearchBooks(SearchBooks searchBooks) {
//		this.searchBooks = searchBooks;
//	}

	@Override
	public List<Book> getPopularBooks() {
		return StreamSupport.stream(bookRepository.findAll().spliterator(), false)
				.filter(Book::isRecommended)
				.collect(Collectors.toList());
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

	@Override
	public List<BookRent> getAllBookRents() {
		return StreamSupport.stream(bookRentRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}

	@Override
	public void deleteBookRent(BookRent bookRent) {
		bookRentRepository.delete(bookRent);

		SearchBooks searchBooks = applicationContext.getBean(SearchBooks.class);
		searchBooks.setBooks(null);
	}

	@Override
	public void updateBook(Book book) {
		bookRepository.save(book);

		SearchBooks searchBooks = applicationContext.getBean(SearchBooks.class);
		searchBooks.setBooks(null);
	}

	@Override
	public void saveBookRent(Book book, User user, Timestamp rentDate) {
		BookRent bookRent = new BookRent(user, book, rentDate);
		bookRentRepository.save(bookRent);

		SearchBooks searchBooks = applicationContext.getBean(SearchBooks.class);
		searchBooks.setBooks(null);
	}

	@Override
	public List<BookRent> findByUser(User currentUser) {
		return bookRentRepository.findByUser(currentUser);
	}

	@Override
	public String borrowBook(long bookId, long userId) {
		Book book = bookRepository.findById(bookId).get();
		book.setStatus(State.REQUESTED);
		bookRepository.save(book);

		User user = userRepository.findById(userId).get();
		BookRent bookRent = new BookRent(user, book, new Timestamp(System.currentTimeMillis()));
		bookRentRepository.save(bookRent);

		SearchBooks searchBooks = applicationContext.getBean(SearchBooks.class);
		searchBooks.setBooks(null);
		return "search?faces-redirect=true";
	}
}