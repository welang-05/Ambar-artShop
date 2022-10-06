package com.challengerFinal.arte.dtos;

public class ItemDTO {
    private String id, title, currency_id, picture_url, description, category_id;
    private Integer quantity;
    private Double unit_price;

    public ItemDTO() {
    }

    public ItemDTO(String id, String title, String currency_id, String picture_url, String description, String category_id, Integer quantity, Double unit_price) {
        this.id = id;
        this.title = title;
        this.currency_id = currency_id;
        this.picture_url = picture_url;
        this.description = description;
        this.category_id = category_id;
        this.quantity = quantity;
        this.unit_price = unit_price;
    }
    public ItemDTO(String id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Double unit_price) {
        this.unit_price = unit_price;
    }
}
