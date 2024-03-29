package net.url.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hit")
public class Hit {
	public Hit() {}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="timestamp")
	private Timestamp timestamp;
	
	@Column(name="user_ip")
	private String userIp;

	@Column(name="shortened_url")
	private String shortenedUrl;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getShortenedUrl() {
		return shortenedUrl;
	}

	public void setShortenedUrl(String shortenedUrl) {
		this.shortenedUrl = shortenedUrl;
	}

	public Hit(Timestamp timestamp, String userIp, String shortenedUrl) {
		super();
		this.timestamp = timestamp;
		this.userIp = userIp;
		this.shortenedUrl = shortenedUrl;
	}
}
