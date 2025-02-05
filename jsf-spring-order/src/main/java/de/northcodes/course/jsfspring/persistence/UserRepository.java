package de.northcodes.course.jsfspring.persistence;

import org.springframework.data.repository.CrudRepository;

import de.northcodes.course.jsfspring.model.User;


public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String userName);
	User findByEmailAddress(String emailAddress);
}