package com.castlight.beans;

public class ProviderLocation {

	private long id;
	private String street_address;
	private String street_address_2;
	private String city;
	private String state;

	public long getId() {
		return id;
	}

	public ProviderLocation(long id, String street_address, String street_address_2, String city, String state,
			String zip, float latitude, float longitude, String building_name) {
		super();
		this.id = id;
		this.street_address = street_address;
		this.street_address_2 = street_address_2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.latitude = latitude;
		this.longitude = longitude;
		this.building_name = building_name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet_address() {
		return street_address;
	}

	public void setStreet_address(String street_address) {
		this.street_address = street_address;
	}

	public String getStreet_address_2() {
		return street_address_2;
	}

	public void setStreet_address_2(String street_address_2) {
		this.street_address_2 = street_address_2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getBuilding_name() {
		return building_name;
	}

	public void setBuilding_name(String building_name) {
		this.building_name = building_name;
	}

	private String zip;
	private float latitude;
	private float longitude;
	private String building_name;

}
