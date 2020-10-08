package com.flipkart.rest;

import javax.mail.MessagingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.flipkart.service.EmailService;
import com.flipkart.service.UserService;

@Path("/email")
public class EmailController {
	
	private static Logger logger= Logger.getLogger(EmailController.class);
	
	UserService userService=new UserService();
	EmailService emailService=new EmailService();
	
	
	@POST
	@Path("/notify")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public String sendEmail(String emailId){
		
		try {
			emailService.sendEmail(emailId);
		} catch (MessagingException e) {
			logger.error(e);
		}
		return "Done"; 
	}
	
}
