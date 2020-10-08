package com.flipkart.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.flipkart.constants.CredentialConstant;

public class EmailUtil {

	public static void sendEmail(String emailId) throws AddressException, MessagingException {		
			String fromAddress = CredentialConstant.EMAIL_ADDRESS;	
	        // Create a mail session
	        Properties properties = new Properties();
	        properties.put("mail.transport.protocol", "smtp");
	        properties.put("mail.smtp.host", "smtp.gmail.com");
	        properties.put("mail.smtp.port", "587");
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	
	        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication( CredentialConstant.EMAIL_ADDRESS,  CredentialConstant.PASSWORD);
	            }
	        });
	        
	        Message message = new MimeMessage(session);
	
	        message.setFrom(new InternetAddress(fromAddress));
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailId));
	
	        message.setSubject("Product Price Changed");
	        message.setText("Price of the product Subscibed by you has changed!!");
	        Transport.send(message);
	
	        System.out.println("Email sent successfully"); 
	}
	
}
