package com.flipkart.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.crypto.URIReferenceException;


import com.flipkart.model.User;
import com.flipkart.service.EmailService;
import com.flipkart.service.UserService;
import com.sun.jersey.api.core.ResourceContext;


@Path("/user")
public class UserController {
	
	//private static Logger logger= Logger.getLogger(UserController.class);
	
	UserService userService=new UserService();
	EmailService emailService=new EmailService();
	@Context private ResourceContext rc;
	
	
	
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getStudents(){      
		
		List<User> usersList=userService.listUsers(); 
		return usersList;
	}

	@POST
	@Path("/addUser")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(User user){
		
		userService.addUser(user);
		String result="Track saved: " + user;		
		return Response.status(201).entity(result).build();	
		
	}
	
	
	@DELETE
	@Path("/deleteUser/{userId}")
	public Response deleteUser(@PathParam("userId") int userId) throws URIReferenceException{
	
		userService.deleteUser(userId);			
		String result="User Id: " + userId + " deleted.";
		return Response.status(200).entity(result).build();	
	}
	
	
	@PUT
	@Path("/updateUser/{userId}")
	@Consumes("application/json")
	public Response updateUser(@PathParam("userId") int userId,User user) {	
		
		userService.updateUser(userId,user);		
		String result="Record Updated.";		
		return Response.status(200).entity(result).build();	
		
	}
	
	
	@POST
	@Path("/subscribeProduct/{userId}/{productId}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response subscribeProduct(@PathParam("userId") int userId,@PathParam("productId") int productId){
		
		userService.subscribeProduct(userId,productId);
		String result="Track saved: ";		
		return Response.status(201).entity(result).build();	
		
	}
	
}
