package com.canvas.canvas.model.dto;

import java.math.BigDecimal;

public class PriceSettingsDto {

    private String sizeDescription;
    private String colorName;
    private String itemTypeDescription;
    private String categoryTypeDescription;
    private BigDecimal defaultPrice;

    public String getSizeDescription() {
        return sizeDescription;
    }

    public void setSizeDescription(String sizeDescription) {
        this.sizeDescription = sizeDescription;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getItemTypeDescription() {
        return itemTypeDescription;
    }

    public void setItemTypeDescription(String itemTypeDescription) {
        this.itemTypeDescription = itemTypeDescription;
    }

    public String getCategoryTypeDescription() {
        return categoryTypeDescription;
    }

    public void setCategoryTypeDescription(String categoryTypeDescription) {
        this.categoryTypeDescription = categoryTypeDescription;
    }

    public BigDecimal getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(BigDecimal defaultPrice) {
        this.defaultPrice = defaultPrice;
    }
}
