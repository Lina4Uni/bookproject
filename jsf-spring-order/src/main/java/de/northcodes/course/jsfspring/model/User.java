package de.northcodes.course.jsfspring.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import java.util.regex.Pattern;
import javax.faces.event.ValueChangeEvent;

@Entity
@Table(name = AbstractEntity.SHOP_PREFIX + "user")
public class User extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "name", nullable = false, unique = true)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "profileImage", nullable = false)
	private String profileImage;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "street", nullable = false)
	private String street;

	@Column(name = "house_number", nullable = false)
	private String houseNumber;

	@Column(name = "postal_code", nullable = false)
	private String postalCode;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "country", nullable = false)
	private String country;

	@Column(name = "email_address", nullable = false, unique = true)
	private String emailAddress;

	@Column(name = "phone_number", nullable = true)
	private String phoneNumber;

	@Column(name = "birth_date", nullable = false)
	private Date birthDate;

	@Column(name = "subscribed_to_newsletter", nullable = false)
	private Boolean subscribedToNewsletter;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	private UserRole role = UserRole.KUNDEN;

	private boolean lengthValid;
	private boolean uppercaseValid;
	private boolean lowercaseValid;
	private boolean specialCharValid;

	public User() {}

	public boolean isLengthValid() {
		return lengthValid;
	}

	public boolean isUppercaseValid() {
		return uppercaseValid;
	}

	public boolean isLowercaseValid() {
		return lowercaseValid;
	}

	public boolean isSpecialCharValid() {
		return specialCharValid;
	}

	public void validatePassword(ValueChangeEvent event) {
		String newPassword = (String) event.getNewValue();

		lengthValid = newPassword.length() >= 8;
		uppercaseValid = Pattern.compile("[A-Z]").matcher(newPassword).find();
		lowercaseValid = Pattern.compile("[a-z]").matcher(newPassword).find();
		specialCharValid = Pattern.compile("[!@#$%^&*(),.?\":{}|<>=]").matcher(newPassword).find();
	}

	// CSS-Klasse für das Passwortfeld basierend auf den Prüfungen
	public String getPasswordCssClass() {
		StringBuilder cssClass = new StringBuilder("password-field");

		if (!lengthValid) {
			cssClass.append(" invalid-length");
		}
		if (!uppercaseValid) {
			cssClass.append(" invalid-uppercase");
		}
		if (!lowercaseValid) {
			cssClass.append(" invalid-lowercase");
		}
		if (!specialCharValid) {
			cssClass.append(" invalid-special");
		}

		return cssClass.toString();
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Boolean getSubscribedToNewsletter() {
		return subscribedToNewsletter;
	}

	public void setSubscribedToNewsletter(Boolean subscribedToNewsletter) {
		this.subscribedToNewsletter = subscribedToNewsletter;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
