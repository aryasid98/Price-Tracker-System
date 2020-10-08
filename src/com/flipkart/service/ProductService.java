package com.flipkart.service;

import java.util.List;

import com.flipkart.DAO.ProductDao;
import com.flipkart.DAO.ProductDaoImpl;
import com.flipkart.model.Product;

public class ProductService {
	ProductDao productImpl=new ProductDaoImpl();
	
	public List<Product> listProducts() {
		return productImpl.listProducts();
	}

	public void addProduct(Product product) {
		productImpl.addProduct(product);
		
	}

	public void deleteProduct(int productId) {
		productImpl.deleteProduct(productId);
		
	}

	public void updateProduct(int productId, Product product) {
		productImpl.updateProduct(productId,product);
		
	}

	public List<String> getSubscribedUsers(int productId) {
		return productImpl.getSubscribedUsers(productId);
	}

	

}
