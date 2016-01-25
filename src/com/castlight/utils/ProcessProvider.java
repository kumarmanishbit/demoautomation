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
      processProvider.process(sourceExcel);

   }

   public String process(List<SourceExcel> sourceExcel) {
      Providers providers = null;
      ProcessString processString = new ProcessString();
      long id = new ProviderDao().getMaxId();

      SourceExcel rowExcel = null;
      Iterator<SourceExcel> iterator = sourceExcel.iterator();
      String query = "REPLACE INTO `providers` (`id`,`provider_type`, `medical_school`, `medical_school_graduation`, `name`, `billing_unit_name`, `friendly_name`, `gender`, `created_at`, `updated_at`, `facility_name`, `first_name`, `last_name`, `initials`, `suffix`, `years_of_experience`, `review_provider_type`) VALUES \n";
      while (iterator.hasNext()) {

         // providers = new Providers();
         rowExcel = iterator.next();
      // System.out.print(rowExcel.getAddress());
         providers = new Providers(id, rowExcel.getEducation_Associations_Publications(),
               rowExcel.getMedical_School_Graduation_Year(), rowExcel.getProvider_Name(), "NULL",
               rowExcel.getFirst_Name(), rowExcel.getGender(), rowExcel.getHospital_Name(),
               rowExcel.getFirst_Name(), rowExcel.getLast_Name(), rowExcel.getInitials(), rowExcel.getSuffix(),
               rowExcel.getYears_Of_Experience(), rowExcel.getProvider_Type(), rowExcel.getReview_Provider_Type());
         query += "(" + providers.getId() + "," + processString.getModifiedString(providers.getProvider_type()) + ","
               + processString.getModifiedString(providers.getMedical_school())
               + "," + processString.getModifiedString(providers.getMedical_school_graduation()) + "," + 
               processString.getModifiedString(providers.getName()) + ","
               + processString.getModifiedString(providers.getBilling_unit_name()) + "," + processString.getModifiedString(providers.getFriendly_name()) + ","
               + processString.getModifiedString(providers.getGender()) + ",now() , NULL , " + processString.getModifiedString(providers.getFacility_name()) + ","
               + processString.getModifiedString(providers.getFirst_name()) + "," + processString.getModifiedString(providers.getLast_name()) + "," + processString.getModifiedString(providers.getInitials()) + ","
               + processString.getModifiedString(providers.getSuffix()) + "," + providers.getYears_of_experience() + ","
               + processString.getModifiedString(providers.getReview_provider_type()) + ") ,";
         id++;
      }

      query = query.substring(0, query.length() - 1);
      query += ";";
      System.out.println(query);
      query = query.length()>0 ? query : null;
      return query;
   }


}
