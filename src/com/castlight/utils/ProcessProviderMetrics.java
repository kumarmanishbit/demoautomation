package com.castlight.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.castlight.beans.ProviderMetrics;
import com.castlight.beans.SourceExcel;
import com.castlight.dao.ProviderMetricsDao;

public class ProcessProviderMetrics {

	public String process(List<SourceExcel> sourceExcel) {
		long id = new ProviderMetricsDao().getMaxId();
		id++;

		ProviderMetrics providerMetrics = null;

		SourceExcel rowExcel = null;
		Iterator<SourceExcel> iterator = sourceExcel.iterator();

		ProcessString processString = new ProcessString();

		String[] metricName = { "SATISFACTION_RECOMMEND_HOSPITAL", "SATISFACTION_COMMUNICATION_DOCTORS",
				"SATISFACTION_COMMUNICATION_NURSES", "SATISFACTION_DISCHARGE_INSTRUCTIONS",
				"SATISFACTION_PAIN_CONTROLLED", "SATISFACTION_MEDICINES_EXPLAINED", "SATISFACTION_ROOM_CLEAN",
				"SATISFACTION_ROOM_QUIET", "SATISFACTION_HELP_ASAP" };
		String query = "REPLACE INTO `provider_metrics` (`id`, `provider_id`, `metric_name`, `decimal_value`, `decimal_value_avg`, `string_value`, `sample_size`, `data_provided_by`, `data_date`, `created_at`, `updated_at`) VALUES";

		String deleteQuery = "Delete from provider_metrics where id in (";

		while (iterator.hasNext()) {

			List<ProviderMetrics> listProviderMetrics = new ArrayList<>();

			rowExcel = iterator.next();

			double[] decimal_value = { Double.parseDouble(rowExcel.getOverallIWouldRecommendThisProvider()),
					Double.parseDouble(rowExcel.getDoctorsCommunicateWell()),
					Double.parseDouble(rowExcel.getNursesCommunicateWell()),
					Double.parseDouble(rowExcel.getClearDischargeInstructions()),
					Double.parseDouble(rowExcel.getPainWellControlled()),
					Double.parseDouble(rowExcel.getMedicationsExplainedBeforeGiving()),
					Double.parseDouble(rowExcel.getRoomBathroomKeptClean()),
					Double.parseDouble(rowExcel.getRoomKeptQuietAtNight()),
					Double.parseDouble(rowExcel.getReceiveHelpQuickly()) };

			for (int i = 0; i < metricName.length; i++) {
				providerMetrics = new ProviderMetrics(id, rowExcel.getId(), metricName[i], decimal_value[i], 3.0,
						rowExcel.getNursesCommunicateWell(), "300+", "CMS", "January 2016");
				listProviderMetrics.add(providerMetrics);
				id++;
			}
			for (ProviderMetrics providerMetric : listProviderMetrics) {
				query += "(" + providerMetric.getId() + "," + providerMetric.getProvider_id() + ","
						+ processString.getModifiedString(providerMetric.getMetric_name()) + ","
						+ providerMetric.getDecimal_value() + "," + providerMetric.getDecimal_value_avg() + ","
						+ processString.getModifiedString(providerMetric.getString_value()) + ","
						+ processString.getModifiedString(providerMetric.getSample_size()) + ","
						+ processString.getModifiedString(providerMetric.getData_provided_by()) + ","
						+ processString.getModifiedString(providerMetric.getData_date()) + ", now() , NULL),\n";
				deleteQuery += providerMetric.getId() + ",";
			}

		}

		deleteQuery = deleteQuery.substring(0,deleteQuery.length()-1);
		deleteQuery += ")";
		query = query.substring(0, query.length() - 2);
		query += ";";
		//query += "\n\n"+deleteQuery;
		return query;
	}

}
