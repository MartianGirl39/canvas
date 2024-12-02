package com.canvas.canvas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


@Entity
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_id_seq")
    @SequenceGenerator(name = "store_id_seq", sequenceName = "store_id_seq", allocationSize = 1)
    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "store_owner", nullable = false, length = 50)
    private String storeOwner;

    @Column(name = "store_name", nullable = false, length = 500)
    private String storeName;

    @Column(name = "store_logo", length = 5000)
    private String storeLogo;

    @Column(name = "description", length = 5000)
    private String description;

    @Column(name = "custom_url", length = 5000, unique = true)
    private String customUrl;

    // Foreign Key relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_owner", referencedColumnName = "username", insertable = false, updatable = false)
    private Account account;

    // Getters and Setters

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getStoreOwner() {
        return storeOwner;
    }

    public void setStoreOwner(String storeOwner) {
        this.storeOwner = storeOwner;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreLogo() {
        return storeLogo;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomUrl() {
        return customUrl;
    }

    public void setCustomUrl(String customUrl) {
        this.customUrl = customUrl;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
