package com.castlight.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import com.castlight.beans.ProviderLocation;

public class ProviderLocationDao {

	public long getMaxId() {
		long id = 1L;
		Statement stmt = null;
		
		Connection conn = Connections.getConnection();
		String sql = "SELECT max(id) FROM provider_locations";
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

	public int findProviderLocation(String address, String city, long zip) {

		address = address.replace("\n", "");
		address = address.replace(",", "");
		address = address.replace(" ", "");

		Statement stmt = null;
		Connection conn = Connections.getConnection();

		String sql = "select id from provider_locations where zip='" + zip
				+ "' and replace(concat(COALESCE(street_address,''),COALESCE(street_address_2,''),COALESCE(city,''),COALESCE(state,''),COALESCE(zip,'')),' ','') like '%"
				+ address + "%';";

		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next())
				return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;

	}

	public ProviderLocation getProviderLocationById(long providerLocationById) {
		ProviderLocation providerLocation = null;
		Statement stmt = null;
		Connection conn = Connections.getConnection();

		String sql = "select * from provider_locations where id= " + providerLocationById;

		System.out.println("providerLocationById = "+providerLocationById);
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				providerLocation = new ProviderLocation(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getLong(6), rs.getFloat(7), rs.getFloat(7), rs.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return providerLocation;
	}

}
