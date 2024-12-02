package com.canvas.canvas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="account")
public class Account {
	@Id
	private String username;
	@Column(name="user_id")
	private long user;
	@Column(name="logo_url")
	private String logo;
	
	public Account(){}
	public Account(long user, String username, String logo) {
		this.user = user;
		this.username = username;
		this.logo = logo;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getLogo() {
		return logo;
	}
	
	public void setLogo(String logo) {
		this.username = logo;
	}
	public long getUser() {
		return this.user;
	}
}
