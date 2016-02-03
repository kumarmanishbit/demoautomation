package com.castlight.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProviderAffiliationDao {
	
	public long getMaxId() {
		long id = 1L;
		Statement stmt = null;
		Connection conn = Connections.getConnection();
		String sql = "SELECT max(id) FROM provider_affiliations";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				id = rs.getLong(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return id;
	}
	
	
}
