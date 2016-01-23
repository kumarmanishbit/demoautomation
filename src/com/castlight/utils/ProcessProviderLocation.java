package com.castlight.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.castlight.beans.ProviderLocation;
import com.castlight.beans.SourceExcel;
import com.castlight.dao.ProviderLocationDao;
import com.castlight.demo.ProcessExcel;

public class ProcessProviderLocation {

	public static void main(String[] args) {
	   ProcessProviderLocation providerLocation = new ProcessProviderLocation();
		List<SourceExcel> sourceExcel = new ArrayList<SourceExcel>();

		ProcessExcel processExcel = new ProcessExcel();

		sourceExcel = processExcel.getSourceExcelRow();

		providerLocation.process(sourceExcel);
	}

   public String process(List<SourceExcel> sourceExcel) {
      SourceExcel rowExcel = null;
      Iterator<SourceExcel> iterator = sourceExcel.iterator();
      
      ProviderLocation providerLocation = null;
		ProviderLocationDao providerLocationDao = new ProviderLocationDao();
		long id = providerLocationDao.getMaxId();
		id++;

		String query = "REPLACE INTO `provider_locations` (`id`, `street_address`, `street_address_2`, `city`, `state`, `zip`, `latitude`, `longitude`, `building_name`, `created_at`, `updated_at`) VALUES ";
		ProcessString processString = new ProcessString();

		long providerLocationId=0L;
		float latitude = 0.0f, longitude = 0.0f;
		while (iterator.hasNext()) {
			rowExcel = iterator.next();
			providerLocation = new ProviderLocation(id, rowExcel.getAddress(), rowExcel.getAddress(),
					rowExcel.getCity(), rowExcel.getState(), rowExcel.getZip(), latitude, longitude, "NULL");
			providerLocationId = providerLocationDao.findProviderLocation(rowExcel.getAddress(), rowExcel.getCity(),
					rowExcel.getZip());

//			System.out.println("providerLocationId = "+providerLocationId);

			if(providerLocationId==0)
			query += "(" + providerLocation.getId() + "," + processString.getModifiedString(providerLocation.getStreet_address()) + ","
					+ processString.getModifiedString(providerLocation.getStreet_address_2()) + "," + processString.getModifiedString(providerLocation.getCity()) + ","
					+ processString.getModifiedString(providerLocation.getState()) + "," + providerLocation.getZip() + ","
					+ providerLocation.getLatitude() + "," + providerLocation.getLongitude() + ","
					+ processString.getModifiedString(providerLocation.getBuilding_name()) + ", now() , NULL ),";
			id++;
		}

		query = query.substring(0, query.length() - 1);
		query += ";";
		System.out.println("***** \nPrivider Location Query:");
		System.out.println(query);
		query = query.length()>=0 ? query : null;
		return query;
   }

}
