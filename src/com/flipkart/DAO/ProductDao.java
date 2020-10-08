package com.flipkart.DAO;

import java.util.List;

import com.flipkart.model.Product;

public interface ProductDao {

	List<Product> listProducts();

	void addProduct(Product product);

	void deleteProduct(int productId);

	void updateProduct(int productId, Product product);

	List<String> getSubscribedUsers(int productId);

	

}
