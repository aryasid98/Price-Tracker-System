package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.constants.SQLConstantsQuery;
import com.flipkart.model.Product;
import com.flipkart.model.User;
import com.flipkart.utils.DBUtil;

public class ProductDaoImpl implements ProductDao{

	private static Logger logger= Logger.getLogger(ProductDaoImpl.class);

	@Override
	public List<Product> listProducts() {
		List <Product> productList=new ArrayList<>();
		Connection connect=DBUtil.getConnection();
		try {
			
			String sql=SQLConstantsQuery.VIEW_PRODUCT_LIST;
			PreparedStatement stmt = connect.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){		
		     String name= rs.getString("productName");
			 int productId = rs.getInt("productId");
			 double price=rs.getDouble("price");
			 String type=rs.getString("type");
	         Product product=new Product();
	         product.setType(type);
	         product.setPrice(price);
	         product.setProductName(name);
	         product.setProductId(productId);	         
	         productList.add(product);
	         logger.info("hello");
	         }
			
	       return productList;
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public void addProduct(Product product) {
		Connection connect=DBUtil.getConnection();
		try {
			
			String sql = SQLConstantsQuery.ADD_PRODUCT;
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setString(1, product.getProductName());
			stmt.setDouble(2, product.getPrice());
			stmt.setString(3, product.getType());
			stmt.executeUpdate();
	       	return;
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}		
		
	}

	@Override
	public void deleteProduct(int productId) {
		Connection connect=DBUtil.getConnection();
		String query = SQLConstantsQuery.DELETE_PRODUCT;
		
		try {
			PreparedStatement stmt = connect.prepareStatement(query);
			stmt.setInt(1, productId);
			int row=stmt.executeUpdate();
		} catch (SQLException e) {			
			logger.error(e.getMessage());
		}
	}

	@Override
	public void updateProduct(int productId, Product product) {
		Connection connect=DBUtil.getConnection();
		String query = SQLConstantsQuery.UPDATE_PRODUCT;
		
		try {
			PreparedStatement stmt = connect.prepareStatement(query);
			stmt.setString(1, product.getProductName());
			stmt.setDouble(2, product.getPrice());
			stmt.setString(3, product.getType());
			stmt.setInt(4, productId);
			int row=stmt.executeUpdate();
		} catch (SQLException e) {			
			logger.error(e.getMessage());
		}
	}

	@Override
	public List<String> getSubscribedUsers(int productId) {
		List <String> usersList=new ArrayList<>();
		Connection connect=DBUtil.getConnection();
		
		try {
			String sql=SQLConstantsQuery.VIEW_SUBSCRIBED_PRODUCTS_LIST;
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setInt(1, productId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){		
				 String emailId=rs.getString("emailId");
				 usersList.add(emailId);
	         }			
	       return usersList;			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return usersList;
	}

}
