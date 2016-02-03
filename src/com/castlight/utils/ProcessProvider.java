package com.castlight.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.castlight.beans.Providers;
import com.castlight.beans.SourceExcel;
import com.castlight.dao.ProviderDao;
import com.castlight.demo.ProcessExcel;

public class ProcessProvider {

	public static void main(String[] args) {
		ProcessProvider processProvider = new ProcessProvider();

		List<SourceExcel> sourceExcel = new ArrayList<SourceExcel>();

		ProcessExcel processExcel = new ProcessExcel();
		sourceExcel = processExcel.getSourceExcelRow();
		ScriptFileGenerator fileWriter = new ScriptFileGenerator();
		if (fileWriter.writeToFile(processProvider.process(sourceExcel))) {
			System.out.println("done...");
		}

	}

	public String process(List<SourceExcel> sourceExcel) {
		Providers providers = null;
		ProcessString processString = new ProcessString();
		long id = new ProviderDao().getMaxId();

		SourceExcel rowExcel = null;
		Iterator<SourceExcel> iterator = sourceExcel.iterator();
		String query = "REPLACE INTO `providers` (`id`,`provider_type`, `medical_school`, `medical_school_graduation`, `name`, `billing_unit_name`, `friendly_name`, `gender`, `created_at`, `updated_at`, `facility_name`, `first_name`, `last_name`, `initials`, `suffix`, `years_of_experience`, `review_provider_type`) VALUES \n";

		boolean isPresent = false;
		boolean makeQuery = false;
		while (iterator.hasNext()) {
			boolean isDoctor = false;
			rowExcel = iterator.next();

			if ("D".equalsIgnoreCase(rowExcel.getDoctor_Hospital().trim())) {
				isDoctor = true;
			}

			if (isDoctor) {
				isPresent = (new ProviderDao().getProviderId(rowExcel.getFirst_Name().trim(), rowExcel.getLast_Name().trim(),
						rowExcel.getInitials().trim()) != 0);

			} else {
				isPresent = new ProviderDao().getProviderId(rowExcel.getProvider_Name()) != 0;
			}
			if (!isPresent) {
				id++;
				providers = new Providers(id, rowExcel.getEducation_Associations_Publications(),
						rowExcel.getMedical_School_Graduation_Year(), rowExcel.getProvider_Name(), "NULL",
						rowExcel.getFirst_Name(), rowExcel.getGender(), rowExcel.getHospital_Name(),
						rowExcel.getFirst_Name(), rowExcel.getLast_Name(), rowExcel.getInitials(), rowExcel.getSuffix(),
						rowExcel.getYears_Of_Experience(), rowExcel.getProvider_Type(),
						rowExcel.getReview_Provider_Type());
				query += "(" + providers.getId() + "," + processString.getModifiedString(providers.getProvider_type())
						+ "," + processString.getModifiedString(providers.getMedical_school()) + ","
						+ processString.getModifiedString(providers.getMedical_school_graduation()) + ","
						+ processString.getModifiedString(providers.getName()) + ","
						+ processString.getModifiedString(providers.getBilling_unit_name()) + ","
						+ processString.getModifiedString(providers.getFriendly_name()) + ","
						+ processString.getModifiedString(providers.getGender()) + ",now() , NULL , "
						+ processString.getModifiedString(providers.getFacility_name()) + ","
						+ processString.getModifiedString(providers.getFirst_name()) + ","
						+ processString.getModifiedString(providers.getLast_name()) + ","
						+ processString.getModifiedString(providers.getInitials()) + ","
						+ processString.getModifiedString(providers.getSuffix()) + ","
						+ providers.getYears_of_experience() + ","
						+ processString.getModifiedString(providers.getReview_provider_type()) + ") ,\n";
				makeQuery = true;

			}
		}

		query = query.substring(0, query.length() - 2);
		query += ";";
		query = makeQuery ? query : "";
		return query;
	}

}
