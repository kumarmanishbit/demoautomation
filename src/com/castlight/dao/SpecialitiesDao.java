package com.castlight.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SpecialitiesDao {

	public long getMaxId() {
		long id = 1L;
		Statement stmt = null;
		Connection conn = Connections.getConnection();
		String sql = "SELECT max(id) FROM specialties";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				id = rs.getLong(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}

	public List<Long> getSpecialitiesIds(List<String> specialitiesName) {
		List<Long> listOfSpecialities = new ArrayList<>();

		Statement stmt = null;
		Connection conn = Connections.getConnection();
		// String tmp = String.join(", ", specialitiesName);
		StringBuilder sb = new StringBuilder();
		String sep = "";
		for (String s : specialitiesName) {
			sb.append(sep).append("'" + s.trim() + "'");
			sep = ",";
		}

		// System.out.println(sb);
		String sql = "SELECT id FROM specialties where name in (" + sb + ")";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				listOfSpecialities.add(rs.getLong(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listOfSpecialities;

	}

	public boolean searchSpecialities(String specialityName) {

		Statement stmt = null;
		Connection conn = Connections.getConnection();

		String sql = "SELECT id FROM specialties where name='" + specialityName + "'";
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			rs.last();
			int size = rs.getRow();
			rs.beforeFirst();
			return size != 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}
