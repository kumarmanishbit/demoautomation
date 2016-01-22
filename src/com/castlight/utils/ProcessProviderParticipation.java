package com.castlight.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.castlight.beans.ProviderParticipation;
import com.castlight.beans.SourceExcel;
import com.castlight.dao.ProviderDao;
import com.castlight.dao.ProviderLocationDao;
import com.castlight.dao.ProviderParticipationDao;
import com.castlight.demo.ProcessExcel;

public class ProcessProviderParticipation {

	public static void main(String[] args) {

		List<SourceExcel> sourceExcel = new ArrayList<SourceExcel>();

		ProcessExcel processExcel = new ProcessExcel();

		sourceExcel = processExcel.getSourceExcelRow();

		ProviderParticipation providersParticipation = null;

		ProviderParticipationDao providerParticipationDao = new ProviderParticipationDao();

		long id = providerParticipationDao.getMaxId();
		id++;
		String query = "REPLACE INTO `provider_participations` (`id`, `provider_network_id`, `accepting_new_patients`, `primary_phone`, `provider_id`, `provider_role`, `facility`, `expired_at`, `updated_at`, `provider_location_id`) VALUES";


		ProviderLocationDao providerLocationDao = new ProviderLocationDao();

		ProcessString processString = new ProcessString();
		
		long providerNetworkId = 1589, providerLocationId = 0;
		SourceExcel rowExcel = null;
		Iterator<SourceExcel> iterator = sourceExcel.iterator();
		while (iterator.hasNext()) {

			rowExcel = iterator.next();
			
			providerLocationId = providerLocationDao.findProviderLocation(rowExcel.getAddress(), rowExcel.getCity(),
					rowExcel.getZip());
			providersParticipation = new ProviderParticipation(id, providerNetworkId, 1, processString.getModifiedString(rowExcel.getPhoneNumber()),
					rowExcel.getId(), "NULL", "NULL", "NULL", providerLocationId);

			query += "(" +providersParticipation.getId() +","+providersParticipation.getProvider_network_id()+","+providersParticipation.getAccepting_new_patients()+","+providersParticipation.getPrimary_phone()+","+providersParticipation.getProvider_id()+","+providersParticipation.getProvider_role()+","+providersParticipation.getFacility()+","+providersParticipation.getExpired_at()+","+providersParticipation.getExpired_at()+","+providersParticipation.getProvider_location_id()+") ,";

			id++;
		}

		query = query.substring(0, query.length() - 1);
		query += ";";
		System.out.println(query);

	}

}
