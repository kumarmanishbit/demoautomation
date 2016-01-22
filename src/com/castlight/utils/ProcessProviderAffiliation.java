package com.castlight.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.castlight.beans.ProviderAffiliation;
import com.castlight.beans.ProviderAffiliation.Affiliation_Type;
import com.castlight.beans.Providers;
import com.castlight.beans.SourceExcel;
import com.castlight.dao.ProviderAffiliationDao;
import com.castlight.dao.ProviderDao;
import com.castlight.demo.ProcessExcel;

public class ProcessProviderAffiliation {

	public static void main(String[] args) {
		List<SourceExcel> sourceExcel = new ArrayList<SourceExcel>();

		ProcessExcel processExcel = new ProcessExcel();

		sourceExcel = processExcel.getSourceExcelRow();

		ProviderAffiliation providerAffiliation = null;

		ProviderAffiliationDao providerAffiliationDao = new ProviderAffiliationDao();

		long id = providerAffiliationDao.getMaxId();
		long parent_provider_id=0L;
		System.out.println(sourceExcel.size());
		String query = "REPLACE INTO `provider_affiliations` (`id`,`provider_id`,`parent_provider_id`,`affiliation_type`,`source`,`created_at`,`updated_at` ) VALUES";

		SourceExcel rowExcel = null;
		Iterator<SourceExcel> iterator = sourceExcel.iterator();
		while (iterator.hasNext()) {
			rowExcel = iterator.next();
			
			providerAffiliation = new  ProviderAffiliation(id, rowExcel.getId(), parent_provider_id, Affiliation_Type.valueOf(rowExcel.getAffilation_Type()) ,"SKA");
		
			query += "( "+ providerAffiliation.getId()+","+providerAffiliation.getProvider_id()+","+providerAffiliation.getParent_provider_id()+","+providerAffiliation.getAffiliation_type()+","+providerAffiliation.getSource()+", now() , NULL";
			id++;
		}

		query = query.substring(0, query.length() - 1);
		query += ";";
		System.out.println(query);

	}
}
