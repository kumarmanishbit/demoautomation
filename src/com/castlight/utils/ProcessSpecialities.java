package com.castlight.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.castlight.beans.SourceExcel;
import com.castlight.beans.Specialities;
import com.castlight.beans.Specialities.Specialty_Type;
import com.castlight.dao.SpecialitiesDao;
import com.castlight.demo.ProcessExcel;

public class ProcessSpecialities {

   public static void main(String[] args) {
      ProcessSpecialities processSpecialities = new ProcessSpecialities();
      processSpecialities.processSpeciality("medical");
   }

   public String processSpeciality(String specialityType) {
      List<SourceExcel> sourceExcel = new ArrayList<SourceExcel>();
      ProcessExcel processExcel = new ProcessExcel();
      sourceExcel = processExcel.getSourceExcelRow();
      String query = process(sourceExcel,specialityType);
      return query;
   }

   public String process(List<SourceExcel> sourceExcel,String specialityType) {
      SpecialitiesDao specialitiesDao = new SpecialitiesDao();
      long specialitiesMaxID = specialitiesDao.getMaxId();
      specialitiesMaxID++;

      SourceExcel rowExcel = null;
      Iterator<SourceExcel> iterator = sourceExcel.iterator();

      Specialities specialities = null;
      String query = "REPLACE INTO `specialties` (`id`, `name`, `specialty_type`, `source`, `created_at`, `updated_at`) VALUES";
      boolean specialityPresent = false;
      while (iterator.hasNext()) {

         rowExcel = iterator.next();

         List<String> specialitiesName = new ArrayList<String>(Arrays.asList(rowExcel.getSpecialities().split(",")));

         for (String string : specialitiesName) {
            if (!specialitiesDao.searchSpecialities(string.trim())) {

               specialities = new Specialities(specialitiesMaxID, string.trim(), specialityType, "NULL");

               query += " (" + specialities.getId() + ",'" + specialities.getName() + "','"
                        + specialities.getSpecialty_type().toString() + "'," + specialities.getSource()
                        + ", now() , NULL) ,";
               specialityPresent = true;
               specialitiesMaxID++;
            }
         }
      }

      query = query.substring(0, query.length() - 1);
      query += ";";

      query = specialityPresent ? query : "";
      System.out.println(query);
      return query;
   }

}
