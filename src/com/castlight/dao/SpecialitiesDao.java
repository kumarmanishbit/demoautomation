package com.castlight.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.castlight.beans.Specialities;

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
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return id;
	}

	public List<Long> getSpecialitiesIds(List<String> specialitiesName) {
		List<Long> listOfSpecialities = new ArrayList<>();

		Statement stmt = null;
		Connection conn = Connections.getConnection();
		StringBuilder sb = new StringBuilder();
		String sep = "";
		Map<String, Long> speicalityNameToId = new Specialities().getSpeicalityNameTOID();
		for (String s : specialitiesName) {
			if (speicalityNameToId.containsKey(s.trim())) {
				listOfSpecialities.add(speicalityNameToId.get(s.trim()));
			} else {
				sb.append(sep).append("'" + s.trim().replaceAll("'", "''") + "'");
				sep = ",";
			}
		}

		String sql = "SELECT id FROM specialties where name in (" + sb + ")";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				listOfSpecialities.add(rs.getLong(1));
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

		return listOfSpecialities;

	}

	public boolean searchSpecialities(String specialityName) {

		Statement stmt = null;
		Connection conn = Connections.getConnection();

		String sql = "SELECT id FROM specialties where name='" + specialityName.replaceAll("'", "''") + "'";
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(sql);
			rs.last();
			int size = rs.getRow();
			rs.beforeFirst();
			return size != 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	public boolean ifPresentProviderSpecialties(Long providerId, Long specialitiesId) {
		Statement stmt = null;
		Connection conn = Connections.getConnection();

		String sql = "SELECT id FROM provider_specialties where provider_id=" +providerId +" and specialty_id = "+specialitiesId;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(sql);
			rs.last();
			int size = rs.getRow();
			rs.beforeFirst();
			return size != 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

}
