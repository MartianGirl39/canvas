package com.canvas.canvas.models;

import jakarta.persistence.*;

@Entity
@Table(name = "account")
public class Account {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user; // Foreign key to CanvasUser

    @Id
    @Column(name = "username")
    private String username; // Primary key for account

    @Column(name = "logo_url", length = 5000)
    private String logoUrl;

    // Constructors, getters and setters

    public Account() {}

    public Account(User canvasUser, String username, String logoUrl) {
        this.user = canvasUser;
        this.username = username;
        this.logoUrl = logoUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User canvasUser) {
        this.user = canvasUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
