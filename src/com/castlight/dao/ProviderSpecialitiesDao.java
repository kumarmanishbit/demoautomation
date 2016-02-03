package com.castlight.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProviderSpecialitiesDao {

	public long getMaxId() {
		long id = 1L;
		Statement stmt = null;
		Connection conn = Connections.getConnection();
		String sql = "SELECT max(id) FROM provider_specialties";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				id = rs.getLong(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return id;
	}
}
