package com.castlight.beans;

import java.util.HashMap;
import java.util.Map;

public class ProviderLocationMapper {

	private  Map<Long, ProviderLocation> providerLocationToId = new HashMap<>();

	private ProviderLocationMapper(){
		
	}
	private static ProviderLocationMapper instance = null;
	
	public static ProviderLocationMapper getInstance(){
		if(instance ==null)
		{
			instance = new ProviderLocationMapper();
		}
		return instance;
	}
	
	public Map<Long, ProviderLocation> getLocationNameTOID() {
		return providerLocationToId;
	}

	public void setLocationNameTOID(Map<Long, ProviderLocation> providerLocationToId) {
		this.providerLocationToId = providerLocationToId;
	}
}
