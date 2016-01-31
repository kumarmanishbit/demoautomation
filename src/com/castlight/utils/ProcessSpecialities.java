package com.castlight.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.castlight.beans.SourceExcel;
import com.castlight.beans.Specialities;
import com.castlight.dao.SpecialitiesDao;
import com.castlight.demo.ProcessExcel;

public class ProcessSpecialities {
	
	private Map<String,Long> speicalityNameToId = new HashMap<>();

	public Map<String, Long> getSpeicalityNameTOID() {
		return speicalityNameToId;
	}

	public void setSpeicalityNameTOID(HashMap<String, Long> speicalityNameTOID) {
		this.speicalityNameToId = speicalityNameTOID;
	}

	public static void main(String[] args) {
		ProcessSpecialities processSpecialities = new ProcessSpecialities();
		processSpecialities.processSpeciality("medical");
	}

	public String processSpeciality(String specialityType) {
		List<SourceExcel> sourceExcel = new ArrayList<SourceExcel>();
		ProcessExcel processExcel = new ProcessExcel();
		sourceExcel = processExcel.getSourceExcelRow();
		String query = process(sourceExcel, specialityType);
		return query;
	}

	public String process(List<SourceExcel> sourceExcel, String specialityType) {
		SpecialitiesDao specialitiesDao = new SpecialitiesDao();
		long specialitiesMaxID = specialitiesDao.getMaxId();
		specialitiesMaxID++;

		SourceExcel rowExcel = null;
		Iterator<SourceExcel> iterator = sourceExcel.iterator();

		Specialities specialities = null;
		String query = "REPLACE INTO `specialties` (`id`, `name`, `specialty_type`, `source`, `created_at`, `updated_at`) VALUES \n";

		Set<String> nameOfSpecialities = new TreeSet();

		boolean specialityPresent = true;
		while (iterator.hasNext()) {

			rowExcel = iterator.next();

			List<String> specialitiesName = new ArrayList<String>(
					Arrays.asList(rowExcel.getSpecialities().split(",|\n")));

			for (String string : specialitiesName) {
				if (!specialitiesDao.searchSpecialities(string.trim())) {
					nameOfSpecialities.add(string.trim());
					specialityPresent = false;
				}
			}
		}
		
		for(String string : nameOfSpecialities){
			specialities = new Specialities(specialitiesMaxID, string.trim(), specialityType, "NULL");

			query += " (" + specialities.getId() + ",'" + specialities.getName() + "','"
					+ specialities.getSpecialty_type().toString() + "'," + specialities.getSource()
					+ ", now() , NULL) \n,";
			speicalityNameToId.put(specialities.getName(), specialities.getId());
			specialitiesMaxID++;
		}
		
		query = query.substring(0, query.length() - 1);
		query += ";";
		
		query = !specialityPresent ? query : "";
		
		return query;
	}

}
