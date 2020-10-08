package com.flipkart.DAO;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.flipkart.model.User;

public interface UserDao {

	List<User> listUsers();

	void sendEmail(String emailId) throws AddressException, MessagingException;

	void addUser(User user);

	void deleteUser(int userId);

	void updateUser(int userId, User user);

}
