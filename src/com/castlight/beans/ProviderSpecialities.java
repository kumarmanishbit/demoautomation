package com.castlight.beans;

public class ProviderSpecialities {

	private long id;
	public long getId() {
		return id;
	}
	public ProviderSpecialities(long id, long provider_id, long specialty_id) {
		super();
		this.id = id;
		this.provider_id = provider_id;
		this.specialty_id = specialty_id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getProvider_id() {
		return provider_id;
	}
	public void setProvider_id(long provider_id) {
		this.provider_id = provider_id;
	}
	public long getSpecialty_id() {
		return specialty_id;
	}
	public void setSpecialty_id(long specialty_id) {
		this.specialty_id = specialty_id;
	}
	private long provider_id;
	private long specialty_id;
	
}
