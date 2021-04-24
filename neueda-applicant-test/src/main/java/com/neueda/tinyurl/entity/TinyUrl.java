package com.neueda.tinyurl.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.ToString;

/**
 * This is entity class for TinyUrl.
 */
@Entity
@Table(name = "url_process")
@ToString
public class TinyUrl {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long urlPrivacyid;
	
	@Column(nullable = false)
	private String longUrl;
	
	@Column(nullable = false)
	private Timestamp expiryTimestamp;
	
	private Timestamp createdTimestamp;

	public long getUrlPrivacyid() {
		return urlPrivacyid;
	}

	public void setUrlPrivacyid(long urlPrivacyid) {
		this.urlPrivacyid = urlPrivacyid;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public Timestamp getExpiryTimestamp() {
		return expiryTimestamp;
	}

	public void setExpiryTimestamp(Timestamp expiryTimestamp) {
		this.expiryTimestamp = expiryTimestamp;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	
}
