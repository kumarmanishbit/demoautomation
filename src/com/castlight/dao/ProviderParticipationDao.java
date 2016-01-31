package com.castlight.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProviderParticipationDao {

	public long getMaxId() {
		long id = 1L;
		Statement stmt = null;
		Connection conn = Connections.getConnection();
		String sql = "SELECT max(id) FROM provider_participations";
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

	public long findProviderParticipation(long providerLocationId, long providerNetworkId, long providerId) {
	
		System.out.println("providerId = "+providerId);
		
		Statement stmt = null;
		Connection conn = Connections.getConnection();

		String sql = "select id from provider_participations where provider_network_id="+providerNetworkId+" and  provider_id= "+providerId+" and provider_location_id ="+providerLocationId;

		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next())
				return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		long tmpId= getMaxId();
		return tmpId++;

	}
	
	
}
