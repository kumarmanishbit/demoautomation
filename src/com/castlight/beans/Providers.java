package com.castlight.beans;

public class Providers {

	private long id;
	private String medical_school;
	private String medical_school_graduation;
	private String name;
	private String billing_unit_name;
	private String friendly_name;
	private String gender;

	public Providers(long id, String medical_school, String medical_school_graduation, String name,
			String billing_unit_name, String friendly_name, String gender, String facility_name, String first_name,
			String last_name, String initials, String suffix, String years_of_experience, String provider_type,
			String review_provider_type) {
		super();
		this.id = id;
		this.medical_school = medical_school;
		this.medical_school_graduation = medical_school_graduation;
		this.name = name;
		this.billing_unit_name = billing_unit_name;
		this.friendly_name = friendly_name;
		this.gender = gender;
		this.facility_name = facility_name;
		this.first_name = first_name;
		this.last_name = last_name;
		this.initials = initials;
		this.suffix = suffix;
		this.years_of_experience = years_of_experience;
		this.provider_type = provider_type;
		this.review_provider_type = review_provider_type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMedical_school() {
		return medical_school;
	}

	public void setMedical_school(String medical_school) {
		this.medical_school = medical_school;
	}

	public String getMedical_school_graduation() {
		return medical_school_graduation;
	}

	public void setMedical_school_graduation(String medical_school_graduation) {
		this.medical_school_graduation = medical_school_graduation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBilling_unit_name() {
		return billing_unit_name;
	}

	public void setBilling_unit_name(String billing_unit_name) {
		this.billing_unit_name = billing_unit_name;
	}

	public String getFriendly_name() {
		return friendly_name;
	}

	public void setFriendly_name(String friendly_name) {
		this.friendly_name = friendly_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFacility_name() {
		return facility_name;
	}

	public void setFacility_name(String facility_name) {
		this.facility_name = facility_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getYears_of_experience() {
		return years_of_experience;
	}

	public void setYears_of_experience(String years_of_experience) {
		this.years_of_experience = years_of_experience;
	}

	public String getProvider_type() {
		return provider_type;
	}

	public void setProvider_type(String provider_type) {
		this.provider_type = provider_type;
	}

	public String getReview_provider_type() {
		return review_provider_type;
	}

	public void setReview_provider_type(String review_provider_type) {
		this.review_provider_type = review_provider_type;
	}

	private String facility_name;
	private String first_name;
	private String last_name;
	private String initials;
	private String suffix;
	private String years_of_experience;
	private String provider_type;
	private String review_provider_type;

}
