package com.castlight.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.castlight.beans.ProviderSpecialities;
import com.castlight.beans.SourceExcel;
import com.castlight.dao.ProviderSpecialitiesDao;
import com.castlight.dao.SpecialitiesDao;

public class ProcessProvidersSpecialities {

	public String process(List<SourceExcel> sourceExcel) {
		SpecialitiesDao specialitiesDao = new SpecialitiesDao();
		Set<ProviderSpecialities> setOfProviderSpecialities = null;
		long id = specialitiesDao.getMaxId();
		id++;

		SourceExcel rowExcel = null;
		Iterator<SourceExcel> iterator = sourceExcel.iterator();
		ProviderSpecialities providerSpecialities;
		ProviderSpecialitiesDao providerSpecialitiesDao = new ProviderSpecialitiesDao();
		long tmpId = providerSpecialitiesDao.getMaxId();
		tmpId++;
		String query = "REPLACE INTO `provider_specialties` (`id`, `provider_id`, `specialty_id`, `created_at`, `updated_at`) VALUES \n";
		boolean providerSpecialityPresent = false;

		while (iterator.hasNext()) {
			setOfProviderSpecialities = new HashSet<ProviderSpecialities>();
			rowExcel = iterator.next();
			List<String> specialitiesName = new ArrayList<String>(
					Arrays.asList(rowExcel.getSpecialities().split(",|\n")));
			List<Long> specialitiesIds = specialitiesDao.getSpecialitiesIds(specialitiesName);

			for (Long specialitiesId : specialitiesIds) {
				id++;
				if (!specialitiesDao.ifPresentProviderSpecialties(rowExcel.getId(), specialitiesId)) {
					providerSpecialities = new ProviderSpecialities(id, rowExcel.getId(), specialitiesId);
					setOfProviderSpecialities.add(providerSpecialities);
				}
			}
			for (ProviderSpecialities secialities : setOfProviderSpecialities) {
				query += "(" + tmpId + "," + secialities.getProvider_id() + "," + secialities.getSpecialty_id()
						+ ", now() , NULL) ,\n";

				tmpId++;
				providerSpecialityPresent = true;
			}
		}

		query = query.substring(0, query.length() - 2);
		query += ";";
		query = providerSpecialityPresent ? query : "";

		return query;
	}
}
