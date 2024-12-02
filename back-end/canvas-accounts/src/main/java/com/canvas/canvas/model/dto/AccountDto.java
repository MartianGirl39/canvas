package com.canvas.canvas.model.dto;

public class AccountDto {
	private String username;
	private byte[] logo;
	
	public AccountDto() {}
	public AccountDto(String username, byte[] logo) {
		this.username = username;
		this.logo = logo;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setLogo(byte[] logo) {
		this.logo = logo;
	}
	
	public String getUsername() {
		return username;
	}
	
	public byte[] getLogo() {
		return logo;
	}
}
