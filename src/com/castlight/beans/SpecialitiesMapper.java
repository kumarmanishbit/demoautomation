package com.castlight.beans;

import java.util.HashMap;
import java.util.Map;

public class SpecialitiesMapper {


	private  Map<String, Long> speicalityNameToId = new HashMap<>();

	private SpecialitiesMapper(){
		
	}
	private static SpecialitiesMapper instance = null;
	
	public static SpecialitiesMapper getInstance(){
		if(instance ==null)
		{
			instance = new SpecialitiesMapper();
		}
		return instance;
	}
	
	public Map<String, Long> getSpeicalityNameTOID() {
		return speicalityNameToId;
	}

	public void setSpeicalityNameTOID(Map<String, Long> speicalityNameTOID) {
		this.speicalityNameToId = speicalityNameTOID;
	}

	

	
}
