package com.canvas.canvas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="account")
public class Account {
	@Id
	String username;
	@Column(name="logo_url")
	String logo;
	
	public String gerUsername() {
		return username;
	}
	
	public String getLogo() {
		return logo;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setLogo(String logo) {
		this.logo = logo;
	}
}
