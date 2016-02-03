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
	@Override
	public String toString() {
		return "ProviderSpecialities [provider_id=" + provider_id + ", specialty_id=" + specialty_id + "]";
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (provider_id ^ (provider_id >>> 32));
		result = prime * result + (int) (specialty_id ^ (specialty_id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProviderSpecialities other = (ProviderSpecialities) obj;
		if (provider_id != other.provider_id)
			return false;
		if (specialty_id != other.specialty_id)
			return false;
		return true;
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
