package com.castlight.beans;

public class ProviderParticipation {

	private long id;
	private long provider_network_id;
	private long accepting_new_patients;
	private String primary_phone;
	private long provider_id;

	public ProviderParticipation(long id, long provider_network_id, long accepting_new_patients, String primary_phone,
			long provider_id, String provider_role, String facility, String expired_at, long provider_location_id) {
		super();
		this.id = id;
		this.provider_network_id = provider_network_id;
		this.accepting_new_patients = accepting_new_patients;
		this.primary_phone = primary_phone;
		this.provider_id = provider_id;
		this.provider_role = provider_role;
		this.facility = facility;
		this.expired_at = expired_at;
		this.provider_location_id = provider_location_id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProvider_network_id() {
		return provider_network_id;
	}

	public void setProvider_network_id(long provider_network_id) {
		this.provider_network_id = provider_network_id;
	}

	public long getAccepting_new_patients() {
		return accepting_new_patients;
	}

	public void setAccepting_new_patients(long accepting_new_patients) {
		this.accepting_new_patients = accepting_new_patients;
	}

	public String getPrimary_phone() {
		return primary_phone;
	}

	public void setPrimary_phone(String primary_phone) {
		this.primary_phone = primary_phone;
	}

	public long getProvider_id() {
		return provider_id;
	}

	public void setProvider_id(long provider_id) {
		this.provider_id = provider_id;
	}

	public String getProvider_role() {
		return provider_role;
	}

	public void setProvider_role(String provider_role) {
		this.provider_role = provider_role;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public String getExpired_at() {
		return expired_at;
	}

	public void setExpired_at(String expired_at) {
		this.expired_at = expired_at;
	}

	public long getProvider_location_id() {
		return provider_location_id;
	}

	public void setProvider_location_id(long provider_location_id) {
		this.provider_location_id = provider_location_id;
	}

	private String provider_role;
	private String facility;
	private String expired_at;
	private long provider_location_id;

}
