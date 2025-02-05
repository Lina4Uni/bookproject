package de.northcodes.course.jsfspring.bean;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.northcodes.course.jsfspring.model.User;
import de.northcodes.course.jsfspring.service.UserService;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@ViewScoped
@Component
@ManagedBean
public class UserDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UserManager userManager;

	@Autowired
	private UserService userService;

	@Autowired
	private ServletContext servletContext;

	private User user;

	private List<SelectItem> profileImages;

	@PostConstruct
	public void init() {
		profileImages = new ArrayList<>();
		String path = servletContext.getRealPath("/resources/musicshop/images/skins");
		File folder = new File(path);
		for (File file : folder.listFiles()) {
			if (file.isFile()) {
				profileImages.add(new SelectItem(file.getName(), file.getName()));
			}
		}
	}

	public List<SelectItem> getProfileImages() {
		return profileImages;
	}

	public User getUser() {
		return user;
	}

	public void onload() {
		user = userManager.isSignedIn() ? userManager.getCurrentUser() : new User();
	}

	public String submit() {
		return userManager.save(user);
	}

	public void validateEmail(FacesContext context, UIComponent component, Object value) {
		String emailAddress = (String) value;
		if (emailAddress == null || emailAddress.isEmpty() || !emailAddress.contains("@") || !emailAddress.contains(".") || (!isUserOwnEmailAddress(emailAddress) && userService.isEmailAlreadyExisting(emailAddress))) {
			throw new ValidatorException(new FacesMessage("Please enter a valid e-mail address."));
		}
	}

	private boolean isUserOwnEmailAddress(String emailAddress) {
		return user.getEmailAddress() != null && user.getEmailAddress().equals(emailAddress);
	}

	public void validatePhoneNumber(FacesContext context, UIComponent component, Object value) {
		String phoneNumber = (String) value;
		if (phoneNumber == null || phoneNumber.isEmpty() || !Pattern.compile("[0-9]{4}-[0-9]{7}").matcher(phoneNumber).matches()) {
			throw new ValidatorException(new FacesMessage("Please enter a valid phone number of the form: 0123-1234567."));
		}
	}

	public void validateBirthDate(FacesContext context, UIComponent component, Object value) {
		Date birthDate = (Date) value;
		if (birthDate == null || !birthDate.before(new Date())) {
			throw new ValidatorException(new FacesMessage("Please enter a valid birth date."));
		}
	}
}