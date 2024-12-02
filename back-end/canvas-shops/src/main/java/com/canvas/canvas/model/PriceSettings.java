package com.canvas.canvas.model;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PriceSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="settings_id")
    private long settingsId;
    
    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "store_id")
    private Store store; // Assuming Store is another entity

    @ManyToOne
    @JoinColumn(name = "size_id", referencedColumnName = "size_id")
    private Size size;

    @ManyToOne
    @JoinColumn(name = "color_id", referencedColumnName = "color_id")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "item_type", referencedColumnName = "type_id")
    private ItemType itemType; // Assuming ItemType is another entity

    @ManyToOne
    @JoinColumn(name = "category_type", referencedColumnName = "type_id")
    private CategoryType categoryType; // Assuming CategoryType is another entity

    @Column(nullable = false)
    private BigDecimal defaultPrice;

    // Getters and Setters
    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public BigDecimal getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(BigDecimal defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceSettings that = (PriceSettings) o;
        return Objects.equals(store, that.store) && Objects.equals(size, that.size)
                && Objects.equals(color, that.color) && Objects.equals(itemType, that.itemType)
                && Objects.equals(categoryType, that.categoryType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(store, size, color, itemType, categoryType);
    }
}

