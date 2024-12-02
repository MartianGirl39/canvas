package com.canvas.canvas.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class StoreDto {
	@JsonIgnore
	private String username;
    private String name;
    private String description;
    
    public String getUsername() {
    return username;
    }
    
    public void setUsername(String username) {
    	this.username = username;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Setter for description
    public void setDescription(String description) {
        this.description = description;
    }
}
