package com.castlight.utils;

import java.util.Iterator;
import java.util.List;

import com.castlight.beans.ProviderAffiliation;
import com.castlight.beans.SourceExcel;
import com.castlight.dao.ProviderAffiliationDao;
import com.castlight.dao.ProviderDao;

public class ProcessProviderAffiliation {

	public String process(List<SourceExcel> sourceExcel) {
		long id = new ProviderAffiliationDao().getMaxId();
		long parent_provider_id = 0L;
		id++;
		ProcessString processString = new ProcessString();

		SourceExcel rowExcel = null;
		boolean ifPresent =false;
		Iterator<SourceExcel> iterator = sourceExcel.iterator();
		String query = "REPLACE INTO `provider_affiliations` (`id`,`provider_id`,`parent_provider_id`,`affiliation_type`,`source`,`created_at`,`updated_at` ) VALUES \n";
		while (iterator.hasNext()) {
			rowExcel = iterator.next();
			if(!"NULL".equalsIgnoreCase(rowExcel.getHospital_Name())) {
			parent_provider_id = new ProviderDao().getParentProviderId(rowExcel.getHospital_Name());
			ProviderAffiliation providerAffiliation = new ProviderAffiliation(id, rowExcel.getId(), parent_provider_id,
					rowExcel.getAffilation_Type(), "SKA");
			query += "( " + providerAffiliation.getId() + "," + providerAffiliation.getProvider_id() + ","
					+ providerAffiliation.getParent_provider_id() + ","
					+ processString.getModifiedString(providerAffiliation.getAffiliation_type()) + ","
					+ processString.getModifiedString(providerAffiliation.getSource()) + ", now() , NULL ) \n,";
			id++;
			ifPresent = true;
			}
		}
		query = query.substring(0, query.length() - 2);
		query += ";";
		query = ifPresent ? query : "";
		return query;
	}
}
