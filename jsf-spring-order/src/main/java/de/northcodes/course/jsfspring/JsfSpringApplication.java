package de.northcodes.course.jsfspring;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import de.northcodes.course.jsfspring.model.*;
import de.northcodes.course.jsfspring.persistence.BookRentRepository;
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

@SpringBootApplication
public class JsfSpringApplication extends SpringBootServletInitializer {
	
	private static final Logger log = LoggerFactory.getLogger(JsfSpringApplication.class);
	
    public static void main(String[] args) {
        SpringApplication.run(JsfSpringApplication.class, args);
    }

    @Bean
    ServletRegistrationBean<FacesServlet> jsfServletRegistration (ServletContext servletContext) {
    	log.info("jsfServletRegistration started...");
    	
        servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());

        ServletRegistrationBean<FacesServlet> srb = new ServletRegistrationBean<FacesServlet>();
        srb.setServlet(new FacesServlet());
        srb.setUrlMappings(Collections.singletonList("*.xhtml"));
        srb.setLoadOnStartup(1);
        return srb;
    }
    
    
    @Bean
    public CommandLineRunner demo(BookRepository repository, UserRepository userRepository, BookRentRepository rentRepository) {
      return (args) -> {
          repository.save(new Book(
                  "Der kleine Abenteuerdrache",
                  "Ein mutiger kleiner Drache begibt sich auf eine Reise, um das Geheimnis des verschwundenen Zauberbuchs zu lüften.",
                  "Der kleine Abenteuerdrache",
                  "Miriam Schneider",
                  "978-3-1234-5678-1", // ISBN
                  "Kinderbuchverlag Fantasia", // Publisher
                  "Fantasy, Abenteuer", // Genre
                  true,
                  State.AVAILABLE
          ));

          repository.save(new Book(
                  "Lillis verrückte Zeitmaschine",
                  "Lilli findet eine alte Uhr, die sie in die Vergangenheit reisen lässt – doch wird sie wieder nach Hause finden?",
                  "Lillis verrueckte Zeitmaschine",
                  "Thomas Becker",
                  "978-3-2345-6789-2", // ISBN
                  "Zeitreise Verlag", // Publisher
                  "Abenteuer, Zeitreise", // Genre
                  false,
                  State.AVAILABLE
          ));

          repository.save(new Book(
                  "Max und die Zauberschule",
                  "Max entdeckt eine geheime Schule für junge Magier und muss eine wichtige Prüfung bestehen.",
                  "Max und die Zauberschule",
                  "Julia Meier",
                  "978-3-3456-7890-3", // ISBN
                  "Magische Bücherwelt", // Publisher
                  "Fantasy, Magie", // Genre
                  true,
                  State.AVAILABLE
          ));

          repository.save(new Book(
                  "Emmas geheime Detektivbande",
                  "Emma und ihre Freunde lösen Rätsel und finden verlorene Gegenstände – diesmal ist ein wertvolles Gemälde verschwunden!",
                  "Emmas geheime Detektivbande",
                  "Daniel Hoffmann",
                  "978-3-4567-8901-4", // ISBN
                  "Spannung & Spaß Verlag", // Publisher
                  "Krimi, Abenteuer", // Genre
                  false,
                  State.AVAILABLE
          ));

          repository.save(new Book(
                  "Das magische Baumhaus am See",
                  "Ein altes Baumhaus führt zwei Geschwister in eine geheimnisvolle Welt voller Abenteuer und Magie.",
                  "Das magische Baumhaus am See",
                  "Sabrina Keller",
                  "978-3-5678-9012-5", // ISBN
                  "Abenteuer Verlag", // Publisher
                  "Fantasy, Abenteuer", // Genre
                  false,
                  State.AVAILABLE
          ));

          repository.save(new Book(
                  "Finn und die verlorene Musik",
                  "Finn entdeckt eine magische Melodie, die ihm hilft, seine Angst zu überwinden und große Abenteuer zu erleben.",
                  "Finn und die verlorene Musik",
                  "Lena Braun",
                  "978-3-6789-0123-6", // ISBN
                  "Harmonie Verlag", // Publisher
                  "Musik, Abenteuer", // Genre
                  false,
                  State.AVAILABLE
          ));

          repository.save(new Book(
                  "Paul und der sprechende Käfer",
                  "Ein winziger Käfer verrät Paul das Geheimnis, wie er mit Tieren sprechen kann.",
                  "Paul und der sprechende Kaefer",
                  "Sebastian Krüger",
                  "978-3-7890-1234-7", // ISBN
                  "Tierfreunde Verlag", // Publisher
                  "Tiere, Freundschaft", // Genre
                  false,
                  State.AVAILABLE
          ));

          repository.save(new Book(
                  "Luna und das Geheimnis der Sternenblume",
                  "Luna findet eine magische Blume, die ihr besondere Kräfte verleiht und sie auf eine fantastische Reise schickt.",
                  "Luna und das Geheimnis der Sternenblume",
                  "Sophia Richter",
                  "978-3-8901-2345-8", // ISBN
                  "Sternenlicht Verlag", // Publisher
                  "Fantasy, Magie", // Genre
                  false,
                  State.AVAILABLE
          ));

          repository.save(new Book(
                  "Der freche Kobold Niko",
                  "Ein kleiner Kobold sorgt in der Stadt für Chaos, bis ein Kind ihn zähmt und sie Freunde werden.",
                  "Der freche Kobold Niko",
                  "Martin Schmid",
                  "978-3-9012-3456-9", // ISBN
                  "Spaß & Spiel Verlag", // Publisher
                  "Humor, Abenteuer", // Genre
                  false,
                  State.AVAILABLE
          ));
          Book s = new Book(
                  "Die geheime Insel der sprechenden Tiere",
                  "Ein Schiffbruch führt Tom und seine Schwester auf eine Insel, auf der Tiere sprechen können und ihnen helfen, nach Hause zu finden.",
                  "Die geheime Insel der sprechenden Tiere",
                  "Katrin Lehmann",
                  "978-3-0123-4567-0", // ISBN
                  "Abenteuer Verlag", // Publisher
                  "Tiere, Abenteuer", // Genre
                  true,
                  State.REQUESTED
          );
          repository.save(s);

          User testUser = new User();
          testUser.setUsername("Test");
          testUser.setPassword("1111");
          testUser.setFirstName("Test");
          testUser.setLastName("TeUser");
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

          rentRepository.save(new BookRent(testUser, s, new Timestamp(System.currentTimeMillis())));

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