package com.castlight.beans;

public class ProviderAffiliation {

	private long id;
	private long provider_id;
	private long parent_provider_id;
	private String affiliation_type;

	public ProviderAffiliation(long id, long provider_id, long parent_provider_id, String affiliation_type,
			String source) {
		this.id = id;
		this.provider_id = provider_id;
		this.parent_provider_id = parent_provider_id;
		this.affiliation_type = affiliation_type;
		this.source = source;
	}

	public long getId() {
		return id;
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

	public long getParent_provider_id() {
		return parent_provider_id;
	}

	public void setParent_provider_id(long parent_provider_id) {
		this.parent_provider_id = parent_provider_id;
	}

	public String getAffiliation_type() {
		return affiliation_type;
	}

	public void setAffiliation_type(String affiliation_type) {
		this.affiliation_type = affiliation_type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	private String source;

  	

}
