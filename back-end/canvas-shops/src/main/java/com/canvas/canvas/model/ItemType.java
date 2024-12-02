package com.canvas.canvas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_type")
public class ItemType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Long typeId;

    @Column(name = "description", length = 100, nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "store_id", nullable = true)
    private Store store; // Assuming a Store entity exists

    @ManyToOne
    @JoinColumn(name = "category_type", referencedColumnName = "type_id", nullable = false)
    private CategoryType categoryType;

    // Constructors, getters and setters
    public ItemType() {}

    public ItemType(String description, Store store, CategoryType categoryType) {
        this.description = description;
        this.store = store;
        this.categoryType = categoryType;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }
}
