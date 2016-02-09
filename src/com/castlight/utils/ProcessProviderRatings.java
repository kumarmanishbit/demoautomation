package com.castlight.utils;

import java.util.Iterator;
import java.util.List;

import com.castlight.beans.ProviderRatings;
import com.castlight.beans.SourceExcel;
import com.castlight.dao.ProviderRatingsDao;

public class ProcessProviderRatings {

	public String process(List<SourceExcel> sourceExcel) {
		ProviderRatingsDao providerRatingsDao = new ProviderRatingsDao();
		long id = providerRatingsDao.getMaxId();
		id++;

		ProviderRatings providerRatings =null;
		ProcessString processString = new ProcessString();
		SourceExcel rowExcel = null;
		Iterator<SourceExcel> iterator = sourceExcel.iterator();
		String query = "REPLACE INTO `provider_ratings` (`provider_id`,`external_data_source`,`date`,`author`,`title`,`comment`,`drcomment`,`url`,`overall_rating`,`q1_rating`,`q2_rating`,`q3_rating`,`q4_rating`,`q5_rating`,`q6_rating`,`q7_rating`,`q8_rating`) VALUES \n";
		while (iterator.hasNext()) {
			rowExcel = iterator.next();

			providerRatings = new ProviderRatings(id, rowExcel.getId(), "Insider Pages", "NULL", "NULL", "NULL", "NULL", "http://www.insiderpages.com/doctors/Kenneth-J-Fraga-DDS-Pleasant-Hill?doctorname=Kenneth-J-Fraga-DDS-Pleasant-Hill", Double.parseDouble(rowExcel.getPatient_Rating()), 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0); 
			
			query += "("+providerRatings.getProvider_id()+","+processString.getModifiedString(providerRatings.getExternal_data_source())+",NULL ,"+processString.getModifiedString(providerRatings.getAuthor()) +","+processString.getModifiedString(providerRatings.getTitle())+","+processString.getModifiedString(providerRatings.getComment())+","+processString.getModifiedString(providerRatings.getDrcomment())+","+processString.getModifiedString(providerRatings.getUrl())+","+providerRatings.getOverall_rating()+","+providerRatings.getQ1_rating()+","+providerRatings.getQ2_rating()+","+providerRatings.getQ3_rating()+","+providerRatings.getQ4_rating()+","+providerRatings.getQ5_rating()+","+providerRatings.getQ6_rating()+","+providerRatings.getQ7_rating()+","+providerRatings.getQ8_rating()+")\n,";
			id++;
		}
		query = query.substring(0, query.length() - 2);
		query += ";";
		return query;
	}

}
