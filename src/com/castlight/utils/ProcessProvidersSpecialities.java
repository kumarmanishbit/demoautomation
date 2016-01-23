package com.castlight.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.castlight.beans.ProviderParticipation;
import com.castlight.beans.ProviderSpecialities;
import com.castlight.beans.SourceExcel;
import com.castlight.beans.Specialities;
import com.castlight.dao.ProviderSpecialitiesDao;
import com.castlight.dao.SpecialitiesDao;
import com.castlight.demo.ProcessExcel;

public class ProcessProvidersSpecialities {

   public static void main(String[] args) {
      List<SourceExcel> sourceExcel = new ArrayList<SourceExcel>();
      
      ProcessProvidersSpecialities providersSpecialities = new ProcessProvidersSpecialities();

      ProcessExcel processExcel = new ProcessExcel();
      sourceExcel = processExcel.getSourceExcelRow();
      
      providersSpecialities.process(sourceExcel);

   }

   public String process(List<SourceExcel> sourceExcel) {
      SpecialitiesDao specialitiesDao = new SpecialitiesDao();
      List<ProviderSpecialities> listOfProviderSpecialities = new ArrayList<>();
      long id = specialitiesDao.getMaxId();
      id++;

      SourceExcel rowExcel = null;
      Iterator<SourceExcel> iterator = sourceExcel.iterator();
      ProviderSpecialities providerSpecialities;
      ProviderSpecialitiesDao providerSpecialitiesDao = new ProviderSpecialitiesDao();
      long tmpId = providerSpecialitiesDao.getMaxId();
      tmpId++;
      String query = "REPLACE INTO `provider_specialties` (`id`, `provider_id`, `specialty_id`, `created_at`, `updated_at`) VALUES ";
      while (iterator.hasNext()) {
         rowExcel = iterator.next();
         List<String> specialitiesName = new ArrayList<String>(Arrays.asList(rowExcel.getSpecialities().split(",")));
         List<Long> specialitiesIds = specialitiesDao.getSpecialitiesIds(specialitiesName);

         for (Long specialitiesId : specialitiesIds) {
            id++;
            providerSpecialities = new ProviderSpecialities(id, rowExcel.getId(), specialitiesId);
            listOfProviderSpecialities.add(providerSpecialities);

         }

         for (ProviderSpecialities secialities : listOfProviderSpecialities) {
            query += "(" + tmpId + "," + secialities.getProvider_id() + "," + secialities.getSpecialty_id()
            + ", now() , NULL),";
            tmpId++;
         }

      }

      query = query.substring(0, query.length() - 1);
      query += ";";
      query = query.length()>=0 ? query : null;
      return query;
   }
}
