package com.challengerFinal.arte.dtos;

import com.challengerFinal.arte.model.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDto {
    private  Long id;
    private  String name;
    private  String description;
    private  List<Double> dimensionsList;
    private String image;

    private String category;
    private Double price;
    private Boolean status;
    private LocalDate date;
    private Integer units;

    private long artistId;

    private String artistName;

    private String artistLastName;


    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.category = product.getCategory();
        this.dimensionsList = product.getDimensionsList();
        this.image = product.getImage();
        this.price = product.getPrice();
        this.status = product.getStatus();
        this.date = product.getDate();
        this.units = product.getUnits();
        this.artistId = product.getClient().getId();
        this.artistName = product.getClient().getName();
        this.artistLastName = product.getClient().getLastName();

          }

    public String getArtistName() {
        return artistName;
    }

    public String getArtistLastName() {
        return artistLastName;
    }

    public long getArtistId() {
        return artistId;
    }

    public String getCategory() {
        return category;
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

    public List<Double> getDimensionsList() {
        return dimensionsList;
    }

    public String getImage() {
        return image;
    }

    public Double getPrice() {
        return price;
    }

    public Boolean getStatus() {
        return status;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getUnits() {
        return units;
    }
}
