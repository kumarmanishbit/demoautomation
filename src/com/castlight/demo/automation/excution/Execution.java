package com.castlight.demo.automation.excution;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.castlight.beans.SourceExcel;
import com.castlight.demo.ProcessExcel;
import com.castlight.utils.ProcessPricing;
import com.castlight.utils.ProcessProvider;
import com.castlight.utils.ProcessProviderAffiliation;
import com.castlight.utils.ProcessProviderLocation;
import com.castlight.utils.ProcessProviderMetrics;
import com.castlight.utils.ProcessProviderParticipation;
import com.castlight.utils.ProcessProviderRatings;
import com.castlight.utils.ProcessProvidersSpecialities;
import com.castlight.utils.ProcessSpecialities;
import com.castlight.utils.ScriptFileGenerator;

public class Execution {

	private final static Logger LOGGER = Logger.getLogger(Execution.class.getName());

	public static void main(String[] args) {

		String specialityType = "medical";
		long providerNetworkId = 9482;
		List<Long> procedureMappingIdList = Arrays.asList(2141l, 2142l, 2143l, 2144l, 6907l, 6908l);
		ScriptFileGenerator fileWriter = new ScriptFileGenerator();

		StringBuilder finalQuery = new StringBuilder();
		List<SourceExcel> sourceExcel = new ProcessExcel().getSourceExcelRow();
		LOGGER.info("Processing Specialities");
		finalQuery.append("\n\n\\*####################################### \n");
		finalQuery.append("###################### Specialities \n");
		finalQuery.append("####################################### \\*\n");
		finalQuery.append(new ProcessSpecialities().process(sourceExcel, specialityType) + "\n");
		
		LOGGER.info("Processing Providers");
		finalQuery.append("\n\n####################################### \n");
		finalQuery.append("###################### Providers \n");
		finalQuery.append("####################################### \n");
		finalQuery.append(new ProcessProvider().process(sourceExcel) + "\n");
		
		LOGGER.info("Processing Providers Specialities");
		finalQuery.append("\n\n####################################### \n");
		finalQuery.append("###################### Providers Providers Specialities  \n");
		finalQuery.append("####################################### \n");
		finalQuery.append(new ProcessProvidersSpecialities().process(sourceExcel) + "\n");

		LOGGER.info("Processing ProcessProviderLocation");
		finalQuery.append("\n\n####################################### \n");
		finalQuery.append("###################### Provider Location \n");
		finalQuery.append("####################################### \n");
		finalQuery.append(new ProcessProviderLocation().process(sourceExcel) + "\n");

		LOGGER.info("Processing ProviderParticipation");
		finalQuery.append("\n\n####################################### \n");
		finalQuery.append("###################### Provider Participation \n");
		finalQuery.append("####################################### \n");
		finalQuery.append(new ProcessProviderParticipation().process(sourceExcel, providerNetworkId));
		
		LOGGER.info("Processing ProviderAffiliation");
		finalQuery.append("\n\n####################################### \n");
		finalQuery.append("###################### Provider Affiliation \n");
		finalQuery.append("####################################### \n");
		finalQuery.append(new ProcessProviderAffiliation().process(sourceExcel) + "\n");
		
		LOGGER.info("Processing ProviderMetrics");
		finalQuery.append("\n\n####################################### \n");
		finalQuery.append("###################### Provider Metrics  \n");
		finalQuery.append("####################################### \n");
		finalQuery.append(new ProcessProviderMetrics().process(sourceExcel) + "\n\n\n");
		
		LOGGER.info("Processing ProviderRatings");
		finalQuery.append("\n\n####################################### \n");
		finalQuery.append("###################### Provider Metrics  \n");
		finalQuery.append("####################################### \n");
		finalQuery.append(new ProcessProviderRatings().process(sourceExcel) + "\n\n\n");
		
		LOGGER.info("Processing Pricing");
		finalQuery.append("\n\n####################################### \n");
		finalQuery.append("###################### Pricing \n");
		finalQuery.append("####################################### \n");
		finalQuery.append(new ProcessPricing().process(sourceExcel, procedureMappingIdList, providerNetworkId));

		fileWriter.writeToFile(finalQuery.toString());
	}
}
