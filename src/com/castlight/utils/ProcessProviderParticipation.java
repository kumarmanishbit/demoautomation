package com.castlight.utils;

import java.util.Iterator;
import java.util.List;

import com.castlight.beans.ProviderLocationMapper;
import com.castlight.beans.ProviderParticipation;
import com.castlight.beans.SourceExcel;
import com.castlight.dao.ProviderLocationDao;
import com.castlight.dao.ProviderParticipationDao;

public class ProcessProviderParticipation {

   public String process(List<SourceExcel> sourceExcel, long providerNetworkId) {
      ProviderLocationDao providerLocationDao = new ProviderLocationDao();
      ProviderParticipationDao providerParticipationDao = new ProviderParticipationDao();
      long id = providerParticipationDao.getMaxId();
      id++;

      ProviderParticipation providersParticipation;
      ProcessString processString = new ProcessString();
      long providerLocationId = 0;
      SourceExcel rowExcel = null;
      Iterator<SourceExcel> iterator = sourceExcel.iterator();
      String query = "REPLACE INTO `provider_participations` (`id`, `provider_network_id`, `accepting_new_patients`, `primary_phone`, `provider_id`, `provider_role`, `facility`, `expired_at`, `updated_at`, `provider_location_id`) VALUES \n";
      while (iterator.hasNext()) {
    	  rowExcel = iterator.next(); 
         
         providerLocationId = providerLocationDao.findProviderLocation(rowExcel.getAddress(), rowExcel.getCity(),
                  rowExcel.getZip());
         if(providerLocationId == 0)
         {
        	 providerLocationId = ProviderLocationMapper.getInstance().getLocationNameTOID().get(rowExcel.getId()).getId();
         }
         
         providersParticipation = new ProviderParticipation(id, providerNetworkId, 1, processString.getModifiedString(rowExcel.getPhoneNumber()),
                  rowExcel.getId(), "NULL", "NULL", "NULL", providerLocationId);
         query += "(" +providersParticipation.getId() +","+providersParticipation.getProvider_network_id()+","+providersParticipation.getAccepting_new_patients()+","+providersParticipation.getPrimary_phone()+","+providersParticipation.getProvider_id()+","+providersParticipation.getProvider_role()+","+providersParticipation.getFacility()+","+providersParticipation.getExpired_at()+","+providersParticipation.getExpired_at()+","+providersParticipation.getProvider_location_id()+") \n,";
         id++;
         rowExcel.setProviderParticipaionId(providersParticipation.getId());
      }
      query = query.substring(0, query.length() - 2);
      query += ";";
      return query;
   }
}