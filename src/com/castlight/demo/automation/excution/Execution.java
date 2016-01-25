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

public class Execution {


   public static void main(String[] args) {

      String specialityType ="medical";
      long providerNetworkId=0l;
      long procedureMappingId=0l;

      StringBuilder finalQuery = new StringBuilder();
      List<SourceExcel> sourceExcel = new ProcessExcel().getSourceExcelRow();

      finalQuery.append(new ProcessSpecialities().process(sourceExcel,specialityType)+"\n");
      finalQuery.append(new ProcessProviderLocation().process(sourceExcel)+"\n");
      finalQuery.append(new ProcessProviderParticipation().process(sourceExcel,providerNetworkId));
      finalQuery.append(new ProcessProvider().process(sourceExcel) +"\n");
      finalQuery.append(new ProcessProvidersSpecialities().process(sourceExcel)+"\n");
      finalQuery.append(new ProcessProviderAffiliation().process(sourceExcel)+"\n");
      finalQuery.append(new ProcessProviderMetrics().process(sourceExcel)+"\n\n\n");

      finalQuery.append(new ProcessPricing().process(sourceExcel, procedureMappingId,providerNetworkId));

      System.out.println("***********\n"+finalQuery);
   }
}
