package com.castlight.beans;

public class ProviderMetrics {

	private long id;
	private long provider_id;
	private String metric_name;
	private double decimal_value;
	private double decimal_value_avg;
	private String string_value;



	
	public ProviderMetrics(long id, long provider_id, String metric_name, double decimal_value, double decimal_value_avg,
			String string_value, String sample_size, String data_provided_by, String data_date) {
		super();
		this.id = id;
		this.provider_id = provider_id;
		this.metric_name = metric_name;
		this.decimal_value = decimal_value;
		this.decimal_value_avg = decimal_value_avg;
		this.string_value = string_value;
		this.sample_size = sample_size;
		this.data_provided_by = data_provided_by;
		this.data_date = data_date;
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

	public String getMetric_name() {
		return metric_name;
	}

	public void setMetric_name(String metric_name) {
		this.metric_name = metric_name;
	}

	public void setDecimal_value(float decimal_value) {
		this.decimal_value = decimal_value;
	}


	public double getDecimal_value() {
		return decimal_value;
	}

	public void setDecimal_value(double decimal_value) {
		this.decimal_value = decimal_value;
	}

	public double getDecimal_value_avg() {
		return decimal_value_avg;
	}

	public void setDecimal_value_avg(double decimal_value_avg) {
		this.decimal_value_avg = decimal_value_avg;
	}

	public void setDecimal_value_avg(float decimal_value_avg) {
		this.decimal_value_avg = decimal_value_avg;
	}

	public String getString_value() {
		return string_value;
	}

	public void setString_value(String string_value) {
		this.string_value = string_value;
	}

	public String getSample_size() {
		return sample_size;
	}

	public void setSample_size(String sample_size) {
		this.sample_size = sample_size;
	}

	public String getData_provided_by() {
		return data_provided_by;
	}

	public void setData_provided_by(String data_provided_by) {
		this.data_provided_by = data_provided_by;
	}

	public String getData_date() {
		return data_date;
	}

	public void setData_date(String data_date) {
		this.data_date = data_date;
	}

	private String sample_size;
	private String data_provided_by;
	private String data_date;

}
