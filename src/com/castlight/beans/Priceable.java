package com.castlight.beans;

public class Priceable {

	private Long id;
	private Long procedureMappingId;
	private Long providerParticipationId;
	private Long providerNetwork;
	private float latitude, longitude;
	private float amount, priceLower, priceUppper;

	public Priceable(Long id, Long procedureMappingId, Long providerParticipationId, Long providerNetwork,
			float latitude, float longitude, float amount, float priceLower, float priceUppper) {
		this.id = id;
		this.procedureMappingId = procedureMappingId;
		this.providerParticipationId = providerParticipationId;
		this.providerNetwork = providerNetwork;
		this.latitude = latitude;
		this.longitude = longitude;
		this.amount = amount;
		this.priceLower = priceLower;
		this.priceUppper = priceUppper;
	}

	public Long getId() {
		return id;
	}

	public Long getProcedureMappingId() {
		return procedureMappingId;
	}

	public Long getProviderParticipation() {
		return providerParticipationId;
	}

	public Long getProviderNetwork() {
		return providerNetwork;
	}

	public float getLatitude() {
		return latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public float getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return "Priceable [id=" + id + ", procedureMappingId=" + procedureMappingId + ", providerParticipationId="
				+ providerParticipationId + ", providerNetwork=" + providerNetwork + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", amount=" + amount + ", priceLower=" + priceLower + ", priceUppper="
				+ priceUppper + "]";
	}

	public float getPriceLower() {
		return priceLower;
	}

	public float getPriceUppper() {
		return priceUppper;
	}
}