package com.castlight.beans;

public class Specialities {

	private long id;

	public long getId() {
		return id;
	}

	public Specialities(long id, String name, Specialty_Type specialty_type, String source) {
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

	public Specialty_Type getSpecialty_type() {
		return specialty_type;
	}

	public void setSpecialty_type(Specialty_Type specialty_type) {
		this.specialty_type = specialty_type;
	}

	private String name;

	private Specialty_Type specialty_type;
	
	public enum Specialty_Type {
		medical, dental, bh_inpatient
	};

	private String source;

}
