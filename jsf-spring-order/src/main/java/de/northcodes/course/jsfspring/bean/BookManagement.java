package de.northcodes.course.jsfspring.bean;

import de.northcodes.course.jsfspring.model.Book;
import de.northcodes.course.jsfspring.model.State;
import de.northcodes.course.jsfspring.service.BookService;
import de.northcodes.course.jsfspring.service.UserService;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import java.io.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "bookManagement")
@ViewScoped
@Component
public class BookManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserManager userManager;

    private String name;
    private String description;
    private UploadedFile uploadedFile;
    String imageName;
    private String author;
    private String isbn;
    private String publisher;
    private String genre;
    private Boolean recommended;
    private State state;

    @PostConstruct
    public void init() {
    }

    public List<State> getStatusOptions() {
        return Arrays.asList(State.values());
    }

    public String addBook() {
        if (uploadedFile != null && uploadedFile.getSize() > 0) {
            this.imageName = uploadedFile.getFileName();

            // Definiere das Verzeichnis, in dem die Datei gespeichert wird
            String directoryPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/") + "images/covers/";
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs(); // Verzeichnis erstellen, falls es nicht existiert
            }

            // Speichere die hochgeladene Datei im angegebenen Verzeichnis
            try (InputStream input = uploadedFile.getInputStream();
                 FileOutputStream output = new FileOutputStream(new File(directory, imageName))) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler beim Hochladen", "Die Datei konnte nicht gespeichert werden."));
                return null;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler beim Hochladen", "Keine Datei hochgeladen."));
            return null;
        }

        // Speichere das Buch mit dem Dateinamen (nur der Name, nicht der vollständige Pfad)
        Book newBook = new Book(name, description, imageName, author, isbn, publisher, genre, recommended, state);
        bookService.saveBook(newBook);
        if (state != State.AVAILABLE) {
            bookService.saveBookRent(newBook, userManager.getCurrentUser(), new Timestamp(new Date().getTime()));
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Buch hinzugefügt", "Das Buch wurde erfolgreich hinzugefügt."));
        return "index?faces-redirect=true";
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

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }


    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public Boolean getRecommended() {
        return recommended;
    }

    public void setRecommended(Boolean recommended) {
        this.recommended = recommended;
    }
}