package com.castlight.demo.automation.excution;

import java.util.List;

import com.castlight.beans.SourceExcel;
import com.castlight.demo.ProcessExcel;
import com.castlight.utils.ProcessPricing;
import com.castlight.utils.ProcessProvider;
import com.castlight.utils.ProcessProviderAffiliation;
import com.castlight.utils.ProcessProviderLocation;
import com.castlight.utils.ProcessProviderMetrics;
import com.castlight.utils.ProcessProviderParticipation;
import com.castlight.utils.ProcessProvidersSpecialities;
import com.castlight.utils.ProcessSpecialities;
import com.castlight.utils.ScriptFileGenerator;

public class Execution {

	public static void main(String[] args) {

		String specialityType = "medical";
		long providerNetworkId = 9481;
		long procedureMappingId = 6907;

		StringBuilder finalQuery = new StringBuilder();
		List<SourceExcel> sourceExcel = new ProcessExcel().getSourceExcelRow();
		finalQuery.append("\n\n####################################### \n");
		finalQuery.append("###################### Specialities \n");
		finalQuery.append("####################################### \n");
		finalQuery.append(new ProcessSpecialities().process(sourceExcel, specialityType) + "\n");
		finalQuery.append("\n\n####################################### \n");
		finalQuery.append("###################### Provider Location \n");
		finalQuery.append("####################################### \n");
		finalQuery.append(new ProcessProviderLocation().process(sourceExcel) + "\n");
		finalQuery.append("\n\n####################################### \n");
		finalQuery.append("###################### Provider Participation \n");
		finalQuery.append("####################################### \n");
		finalQuery.append(new ProcessProviderParticipation().process(sourceExcel, providerNetworkId));
		finalQuery.append("\n\n####################################### \n");
		finalQuery.append("###################### Providers \n");
		finalQuery.append("####################################### \n");
		finalQuery.append(new ProcessProvider().process(sourceExcel) + "\n");
		finalQuery.append("\n\n####################################### \n");
		finalQuery.append("###################### Providers Specialities  \n");
		finalQuery.append("####################################### \n");
		finalQuery.append(new ProcessProvidersSpecialities().process(sourceExcel) + "\n");
		finalQuery.append("\n\n####################################### \n");
		finalQuery.append("###################### Provider Affiliation \n");
		finalQuery.append("####################################### \n");
		finalQuery.append(new ProcessProviderAffiliation().process(sourceExcel) + "\n");
		finalQuery.append("\n\n####################################### \n");
		finalQuery.append("###################### Provider Metrics  \n");
		finalQuery.append("####################################### \n");
		finalQuery.append(new ProcessProviderMetrics().process(sourceExcel) + "\n\n\n");

		finalQuery.append("\n\n####################################### \n");
		finalQuery.append("###################### Pricing \n");
		finalQuery.append("####################################### \n");
		finalQuery.append(new ProcessPricing().process(sourceExcel, procedureMappingId, providerNetworkId));

		ScriptFileGenerator fileWriter = new ScriptFileGenerator();
		fileWriter.writeToFile(finalQuery.toString());
	}
}
