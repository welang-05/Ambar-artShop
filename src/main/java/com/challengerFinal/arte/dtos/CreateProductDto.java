package com.challengerFinal.arte.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class CreateProductDto implements Serializable {
    private Long id;
    private String name;
    private String description;
    private String category;
    private List<Double> dimensionsList;
    private String image;
    private Double price;
    private Boolean status;
    private LocalDate date;
    private Integer units;


    public CreateProductDto() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public List<Double> getDimensionsList() {
        return dimensionsList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDimensionsList(List<Double> dimensionsList) {
        this.dimensionsList = dimensionsList;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }
}
