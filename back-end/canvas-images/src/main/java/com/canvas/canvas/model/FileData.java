package com.canvas.canvas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="file_data")
public class FileData {
	
	@Id
	@Column(name="file_uri")
	private String uri;
	@Column(name="file_name")
	private String name;
	@Column(name="file_type")
	private String type;
	
	public FileData(){}
	
	public FileData(String uri, String name, String type) {
		this.uri = uri;
		this.name = name;
		this.type = type;
	}
	
	public String getUri() {
		return this.uri;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
}
