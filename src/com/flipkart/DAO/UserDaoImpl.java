package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.flipkart.constants.CredentialConstant;
import com.flipkart.constants.SQLConstantsQuery;
import com.flipkart.model.User;
import com.flipkart.utils.DBUtil;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class UserDaoImpl implements UserDao {

	private static Logger logger= Logger.getLogger(UserDaoImpl.class);
	
	
	@Override
	public List<User> listUsers() {
		List <User> usersList=new ArrayList<>();
		Connection connect=DBUtil.getConnection();
		try {			
			String sql=SQLConstantsQuery.VIEW_USER_LIST;
			PreparedStatement stmt = connect.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){		
		     String name= rs.getString("name");
			 int userId = rs.getInt("userId");
			 String contactNo=rs.getString("contactNo");
			 String emailId=rs.getString("emailId");
	         User user=new User();
	         user.setEmailId(emailId);
	         user.setContactNo(contactNo);
	         user.setName(name);
	         user.setUserId(userId);	         
	         usersList.add(user);
	         logger.info("hello");
			}
			
	       return usersList;
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	
	@Override
	public void sendEmail(String emailId) throws AddressException, MessagingException {		
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
	
	        message.setSubject("Email From Yourself");
	        message.setText("You have just sent a email to yourself!");
	        Transport.send(message);
	
	        System.out.println("Email sent successfully"); 
	}

	
	@Override
	public void addUser(User user) {		
		Connection connect=DBUtil.getConnection();
		try {			
			String sql = SQLConstantsQuery.ADD_USER;
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmailId());
			stmt.setString(3, user.getContactNo());
			stmt.executeUpdate();
	       	return;			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}		
		
	}

	
	@Override
	public void deleteUser(int userId) {		
		Connection connect=DBUtil.getConnection();
		String query = SQLConstantsQuery.DELETE_USER;		
		try {
			PreparedStatement stmt = connect.prepareStatement(query);
			stmt.setInt(1, userId);
			int row=stmt.executeUpdate();
		} catch (SQLException e) {			
			logger.error(e.getMessage());
		}
		
	}

	@Override
	public void updateUser(int userId, User user) {
		Connection connect=DBUtil.getConnection();
		String query = SQLConstantsQuery.UPDATE_USER;		
		try {
			PreparedStatement stmt = connect.prepareStatement(query);
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmailId());
			stmt.setString(3, user.getContactNo());
			stmt.setInt(4, userId);
			int row=stmt.executeUpdate();
		} catch (SQLException e) {			
			logger.error(e.getMessage());
		}
	}




}
