package com.canvas.canvas.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="canvas_users")
public class User  {
	
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
	private long id;
	
	@Column(name="email")
	private String email;
	
	@Column(name="hash_password")
	private String password;
	
	public long getId() {
		return this.id;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setEmail(String username) {
		this.email = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
}
