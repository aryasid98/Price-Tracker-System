package com.flipkart.rest;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.crypto.URIReferenceException;

import com.flipkart.model.Product;
import com.flipkart.service.ProductService;
import com.flipkart.service.UserService;



@Path("/product")
public class ProductController {

	ProductService productService=new ProductService();
	UserService userService=new UserService();
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getStudents(){
		
		List<Product> usersList=productService.listProducts(); 
		return usersList;
	}

	
	@POST
	@Path("/addProduct")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProduct(Product product){
		
		productService.addProduct(product);
		String result="Track saved: " + product;		
		return Response.status(201).entity(result).build();	
		
	}
	
	
	@DELETE
	@Path("/deleteProduct/{productId}")
	public Response deleteUser(@PathParam("productId") int productId) throws URIReferenceException{
	
		productService.deleteProduct(productId);			
		String result="User Id: " + productId + " deleted.";
		return Response.status(200).entity(result).build();	
		
	}
	
	@PUT
	@Path("/updateProduct/{productId}")
	@Consumes("application/json")
	public Response updateProduct(@PathParam("productId") int productId,Product product) throws IOException {	
		
		productService.updateProduct(productId,product);	
		sendEmail(productId);
		
		String result="Record Updated.";
		return Response.status(200).entity(result).build();	
	}


	private void sendEmail(int productId) throws IOException {
		
		List<String> emailList=productService.getSubscribedUsers(productId);
		System.out.println(emailList);
		
		for(String email: emailList) {			
			System.out.println("Entered!");
			String loc="http://localhost:8080/PriceTrackerSystem/rest/email/notify";			
			URL url = new URL(loc);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			String jsonInputString = "\"" + email  + "\" ";
			
			try(OutputStream os = con.getOutputStream()) {
			    byte[] input = jsonInputString.getBytes("utf-8");
			    os.write(input, 0, input.length);           
			}
			try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
				StringBuilder response = new StringBuilder();
				String responseLine = null;
				while ((responseLine = br.readLine()) != null) {
				     response.append(responseLine.trim());
				}
				System.out.println(response.toString());
			}
		}
	}
	
}
