package de.northcodes.course.jsfspring;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;

import de.northcodes.course.jsfspring.model.User;
import de.northcodes.course.jsfspring.model.UserRole;
import de.northcodes.course.jsfspring.persistence.BookRepository;
import de.northcodes.course.jsfspring.persistence.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import de.northcodes.course.jsfspring.model.Book;

@SpringBootApplication
public class JsfSpringApplication extends SpringBootServletInitializer {
	
	private static final Logger log = LoggerFactory.getLogger(JsfSpringApplication.class);
	
    public static void main(String[] args) {
        SpringApplication.run(JsfSpringApplication.class, args);
    }

    @Bean
    ServletRegistrationBean<FacesServlet> jsfServletRegistration (ServletContext servletContext) {
    	log.info("jsfServletRegistration started...");
    	
        //spring boot only works if this is set
        servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());

        //FacesServlet registration
        ServletRegistrationBean<FacesServlet> srb = new ServletRegistrationBean<FacesServlet>();
        srb.setServlet(new FacesServlet());
        srb.setUrlMappings(Collections.singletonList("*.xhtml"));
        srb.setLoadOnStartup(1);
        return srb;
    }
    
    
    //Only need for development initialization purposes
    @Bean
    public CommandLineRunner demo(BookRepository repository, UserRepository userRepository) {
      return (args) -> {
        // save a few Books

          repository.save(new Book(
                  "The Catcher in the Rye",
                  "A novel by J.D. Salinger, partially published in serial form in 1945-1946 and as a novel in 1951.",
                  new BigDecimal("10.99"),
                  "test",
                  "J.D. Salinger",
                  "978-0-316-76948-0", // ISBN
                  "Little, Brown and Company", // Publisher
                  "Fiction" // Genre
          ));

          repository.save(new Book(
                  "To Kill a Mockingbird",
                  "A novel by Harper Lee published in 1960. Instantly successful, widely read in high schools and middle schools in the United States.",
                  new BigDecimal("7.99"),
                  "test",
                  "Harper Lee",
                  "978-0-06-112008-4", // ISBN
                  "J.B. Lippincott & Co.", // Publisher
                  "Fiction" // Genre
          ));

          repository.save(new Book(
                  "1984",
                  "A dystopian social science fiction novel and cautionary tale, written by the English writer George Orwell.",
                  new BigDecimal("8.99"),
                  "test",
                  "George Orwell",
                  "978-0-452-28423-4", // ISBN
                  "Secker & Warburg", // Publisher
                  "Dystopian, Science Fiction" // Genre
          ));

          repository.save(new Book(
                  "Pride and Prejudice",
                  "A romantic novel of manners written by Jane Austen in 1813.",
                  new BigDecimal("6.99"),
                  "test",
                  "Jane Austen",
                  "978-0-19-953556-9", // ISBN
                  "T. Egerton, Whitehall", // Publisher
                  "Romance, Fiction" // Genre
          ));

          repository.save(new Book(
                  "The Great Gatsby",
                  "A 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City.",
                  new BigDecimal("9.99"),
                  "test",
                  "F. Scott Fitzgerald",
                  "978-0-7432-7356-5", // ISBN
                  "Charles Scribner's Sons", // Publisher
                  "Fiction, Classic" // Genre
          ));

          User testUser = new User();
          testUser.setUsername("Test");
          testUser.setPassword("1111");
          testUser.setFirstName("Test");
          testUser.setLastName("User");
          testUser.setStreet("Test Street");
          testUser.setHouseNumber("123");
          testUser.setPostalCode("12345");
          testUser.setCity("Test City");
          testUser.setCountry("Test Country");
          testUser.setEmailAddress("testuser@example.com");
          testUser.setPhoneNumber("1234567890");
          testUser.setBirthDate(new Date(90, 0, 1)); // 1. Januar 1990
          testUser.setSubscribedToNewsletter(true);
          testUser.setRole(UserRole.KUNDEN);
          testUser.setProfileImage("Katze.png");
          userRepository.save(testUser);

          User testAdmin = new User();
          testAdmin.setUsername("Admin");
          testAdmin.setPassword("1111");
          testAdmin.setFirstName("Admin");
          testAdmin.setLastName("User");
          testAdmin.setStreet("Admin Street");
          testAdmin.setHouseNumber("1");
          testAdmin.setPostalCode("54321");
          testAdmin.setCity("Admin City");
          testAdmin.setCountry("Admin Country");
          testAdmin.setEmailAddress("adminuser@example.com");
          testAdmin.setPhoneNumber("0987654321");
          testAdmin.setBirthDate(new Date(85, 0, 1)); // 1. Januar 1985
          testAdmin.setSubscribedToNewsletter(false);
          testAdmin.setRole(UserRole.ADMIN);
          testAdmin.setProfileImage("Hase.png");
          userRepository.save(testAdmin);

          // fetch all products
        log.info("Books found with findAll():");
        log.info("-------------------------------");
        for (Book book : repository.findAll()) {
          log.info(book.toString());
        }
        log.info("");

        // fetch an individual Book by ID
          Book book = repository.findById(1L).get();
        log.info("Book found with findById(1L):");
        log.info("--------------------------------");
        log.info(book.toString());
        log.info("");

      };
    }


    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
        return factory -> factory.setPort(8081);
    }

}