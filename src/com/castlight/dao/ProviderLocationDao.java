package com.castlight.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

	public int findProviderLocation(String address, String city, String zip) {

		System.out.println(zip);

		address = address.replace("\n", "");
		address = address.replace(",", "");
		address = address.replace(" ", "");
		System.out.println(address);

		Statement stmt = null;
		Connection conn = Connections.getConnection();

		float tmpval = Float.parseFloat(zip);

		int tmpzip = (int) tmpval;

		String sql = "select id from provider_locations where zip='" + tmpzip
				+ "' and replace(concat(COALESCE(street_address,''),COALESCE(street_address_2,''),COALESCE(city,''),COALESCE(state,''),COALESCE(zip,'')),' ','') like '%"
				+ address + "%';";

		System.out.println(sql);

		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next())
				return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;

	}

}
