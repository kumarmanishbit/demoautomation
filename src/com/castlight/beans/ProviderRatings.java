package com.castlight.beans;


public class ProviderRatings {

	private long id;
	private long provider_id;
	private String external_data_source;
	private String author;
	private String title;
	private String comment;
	private String drcomment;
	public ProviderRatings(long id, long provider_id, String external_data_source, String author, String title,
			String comment, String drcomment, String url, double string, double d, double e,
			double f, double g, double h, double i, double j, double k) {
		super();
		this.id = id;
		this.provider_id = provider_id;
		this.external_data_source = external_data_source;
		this.author = author;
		this.title = title;
		this.comment = comment;
		this.drcomment = drcomment;
		this.url = url;
		this.overall_rating = string;
		this.q1_rating = d;
		this.q2_rating = e;
		this.q3_rating = f;
		this.q4_rating = g;
		this.q5_rating = h;
		this.q6_rating = i;
		this.q7_rating = j;
		this.q8_rating = k;
	}

	private String url;
	private double overall_rating;
	private double q1_rating;
	private double q2_rating;

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

	public String getExternal_data_source() {
		return external_data_source;
	}

	public void setExternal_data_source(String external_data_source) {
		this.external_data_source = external_data_source;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDrcomment() {
		return drcomment;
	}

	public void setDrcomment(String drcomment) {
		this.drcomment = drcomment;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public double getOverall_rating() {
		return overall_rating;
	}

	public void setOverall_rating(float overall_rating) {
		this.overall_rating = overall_rating;
	}

	public double getQ1_rating() {
		return q1_rating;
	}

	public void setQ1_rating(float q1_rating) {
		this.q1_rating = q1_rating;
	}

	public double getQ2_rating() {
		return q2_rating;
	}

	public void setQ2_rating(float q2_rating) {
		this.q2_rating = q2_rating;
	}

	public double getQ3_rating() {
		return q3_rating;
	}

	public void setQ3_rating(float q3_rating) {
		this.q3_rating = q3_rating;
	}

	public double getQ4_rating() {
		return q4_rating;
	}

	public void setQ4_rating(float q4_rating) {
		this.q4_rating = q4_rating;
	}

	public double getQ5_rating() {
		return q5_rating;
	}

	public void setQ5_rating(float q5_rating) {
		this.q5_rating = q5_rating;
	}

	public double getQ6_rating() {
		return q6_rating;
	}

	public void setQ6_rating(float q6_rating) {
		this.q6_rating = q6_rating;
	}

	public double getQ7_rating() {
		return q7_rating;
	}

	public void setQ7_rating(float q7_rating) {
		this.q7_rating = q7_rating;
	}

	public double getQ8_rating() {
		return q8_rating;
	}

	public void setQ8_rating(float q8_rating) {
		this.q8_rating = q8_rating;
	}

	private double q3_rating;
	private double q4_rating;
	private double q5_rating;
	private double q6_rating;
	private double q7_rating;
	private double q8_rating;

}
