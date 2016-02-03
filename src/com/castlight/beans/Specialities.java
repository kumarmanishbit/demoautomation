package com.castlight.beans;

import java.util.HashMap;
import java.util.Map;

public class Specialities {

	private long id;

	public long getId() {
		return id;
	}

	public Specialities(){
		
	}
	
	private static Map<String, Long> speicalityNameToId = new HashMap<>();

	public Map<String, Long> getSpeicalityNameTOID() {
		return speicalityNameToId;
	}

	public void setSpeicalityNameTOID(Map<String, Long> speicalityNameTOID) {
		Specialities.speicalityNameToId = speicalityNameTOID;
	}

	public Specialities(long id, String name, String specialty_type, String source) {
		super();
		this.id = id;
		this.name = name;
		this.specialty_type = specialty_type;
		this.source = source;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSpecialty_type() {
		return specialty_type;
	}

	public void setSpecialty_type(String specialty_type) {
		this.specialty_type = specialty_type;
	}

	private String name;

	private String specialty_type;

	public enum Specialty_Type {
		medical, dental, bh_inpatient
	};

	private String source;

}
