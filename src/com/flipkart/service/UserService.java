package com.flipkart.service;

import java.util.List;

import com.flipkart.DAO.SubscriptionDao;
import com.flipkart.DAO.SubscriptionDaoImpl;
import com.flipkart.DAO.UserDao;
import com.flipkart.DAO.UserDaoImpl;
import com.flipkart.model.User;

public class UserService {
	UserDao userImpl=new UserDaoImpl();
	SubscriptionDao subscriptionImpl=new SubscriptionDaoImpl();
	
	public List<User> listUsers() {
		return userImpl.listUsers(); 	
		
	}


	public void addUser(User user) {
		
		userImpl.addUser(user);
		
	}


	public void deleteUser(int userId) {
		userImpl.deleteUser(userId);
		
	}


	public void updateUser(int userId, User user) {
		userImpl.updateUser(userId,user);
	}


	public void subscribeProduct(int userId, int productId) {
		subscriptionImpl.subscribeProduct(userId,productId);
	}

}
