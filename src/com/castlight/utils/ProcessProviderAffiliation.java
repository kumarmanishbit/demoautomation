package com.castlight.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.castlight.beans.ProviderAffiliation;
import com.castlight.beans.SourceExcel;
import com.castlight.dao.ProviderAffiliationDao;
import com.castlight.demo.ProcessExcel;

public class ProcessProviderAffiliation {

	public static void main(String[] args) {
		List<SourceExcel> sourceExcel = new ArrayList<SourceExcel>();
		ProcessExcel processExcel = new ProcessExcel();
		sourceExcel = processExcel.getSourceExcelRow();

		ScriptFileGenerator fileWriter = new ScriptFileGenerator();
		if (fileWriter.writeToFile((new ProcessProviderAffiliation()).process(sourceExcel))) {
			System.out.println("done...");
		}
	}

	public String process(List<SourceExcel> sourceExcel) {
		long id = new ProviderAffiliationDao().getMaxId();
		long parent_provider_id = 0L;
		id++;
		ProcessString processString = new ProcessString();

		SourceExcel rowExcel = null;
		Iterator<SourceExcel> iterator = sourceExcel.iterator();
		String query = "REPLACE INTO `provider_affiliations` (`id`,`provider_id`,`parent_provider_id`,`affiliation_type`,`source`,`created_at`,`updated_at` ) VALUES \n";
		while (iterator.hasNext()) {
			rowExcel = iterator.next();
			ProviderAffiliation providerAffiliation = new ProviderAffiliation(id, rowExcel.getId(), parent_provider_id,
					rowExcel.getAffilation_Type(), "SKA");
			query += "( " + providerAffiliation.getId() + "," + providerAffiliation.getProvider_id() + ","
					+ providerAffiliation.getParent_provider_id() + ","
					+ processString.getModifiedString(providerAffiliation.getAffiliation_type()) + ","
					+ processString.getModifiedString(providerAffiliation.getSource()) + ", now() , NULL ) \n,";
			id++;
		}
		query = query.substring(0, query.length() - 1);
		query += ";";
		return query;
	}
}
