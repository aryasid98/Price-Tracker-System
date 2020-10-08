package com.flipkart.constants;

public class SQLConstantsQuery {

	public static final String VIEW_USER_LIST = "select * from user";
	public static final String ADD_USER = "insert into user(name,emailId,contactNo) values(?,?,?)";
	public static final String DELETE_USER = "delete from user where userId=?";
	public static final String UPDATE_USER = "update user set name=?,emailId=?,contactNo=? where userId=?";
	public static final String VIEW_PRODUCT_LIST = "select * from product";
	public static final String ADD_PRODUCT = "insert into product(productName,price,type) values(?,?,?)";
	public static final String DELETE_PRODUCT = "delete from product where productId=?";
	public static final String UPDATE_PRODUCT = "update product set productName=?,price=?,type=? where productId=?";
	public static final String VIEW_SUBSCRIBED_PRODUCTS_LIST = "select emailId from user,subscription where user.userId=subscription.userId and productId=?";
	public static final String SUBSCRIBE_PRODUCT = "insert into subscription values(?,?)";

}
