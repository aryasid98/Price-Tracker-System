package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipkart.constants.SQLConstantsQuery;
import com.flipkart.utils.DBUtil;

public class SubscriptionDaoImpl implements SubscriptionDao {
	
	private static Logger logger= Logger.getLogger(SubscriptionDaoImpl.class);

	@Override
	public void subscribeProduct(int userId, int productId) {
		
		Connection connect=DBUtil.getConnection();
		try {
			
			String sql = SQLConstantsQuery.SUBSCRIBE_PRODUCT;
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setInt(1, userId);
			stmt.setInt(2, productId);
			stmt.executeUpdate();
	       	return;
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}		
		
	}

}
