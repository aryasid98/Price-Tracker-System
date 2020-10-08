package com.flipkart.service;


import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.flipkart.DAO.UserDao;
import com.flipkart.DAO.UserDaoImpl;
import com.flipkart.utils.EmailUtil;

public class EmailService {

	UserDao userImpl=new UserDaoImpl();	
	
	public void sendEmail(String emailId) throws AddressException, MessagingException {
		EmailUtil.sendEmail(emailId);
	}

}
