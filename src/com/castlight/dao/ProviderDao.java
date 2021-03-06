package com.castlight.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.castlight.beans.SourceExcel;
import com.castlight.demo.ProcessExcel;
import com.castlight.utils.ProcessProviderMetrics;

public class ProviderDao {
	
	public static void main(String[] args) {
	
		ProviderDao providerDao = new ProviderDao();
		System.out.println(providerDao.getMaxId());
	}

	public long getMaxId() {
		long id = 1L;
		Statement stmt = null;

		Connection conn = Connections.getConnection();
		String sql = "SELECT max(id) FROM providers";
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return id;
	}

	public boolean searchProviders(String providerNameName) {

		Statement stmt = null;
		Connection conn = Connections.getConnection();

		String sql = "SELECT id FROM providers where name='" + providerNameName + "'";
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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

	public Integer getParentProviderId(String completeName) {

		Statement stmt = null;
		Connection conn = Connections.getConnection();

		String sql = "SELECT id FROM providers where name='" + completeName + "'";
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next())
				return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Integer getProviderId(String hospitalName) {

		Statement stmt = null;
		Connection conn = Connections.getConnection();

		String sql = "SELECT id FROM providers where name='" + hospitalName + "'";

		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				return rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Integer getProviderId(String firstName, String lastName, String initials) {

		String str1 = "NULL".equalsIgnoreCase(firstName) ? "first_name is null" : "first_name ='" + firstName + "'";
		String str2 = "NULL".equalsIgnoreCase(lastName) ? "last_name is null" : "last_name ='" + lastName + "'";
		String str3 = "NULL".equalsIgnoreCase(initials) ? "initials is null" : "initials ='" + initials + "'";
		String query = "select max(id) from providers where " + str1 + " and " + str2 + " and " + str3;

		Statement stmt = null;
		Connection conn = Connections.getConnection();
		Integer providerid = 0;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				providerid = rs.getInt(1);
				return providerid;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return providerid;
	}

}