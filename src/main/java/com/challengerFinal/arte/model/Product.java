package com.challengerFinal.arte.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(strategy = "native",name = "native")
    private Long id;
    private String name;
    private String description;
    private String category;
    private Double price;
    private Boolean status;
    private LocalDate date;
    private Integer units;
    @ElementCollection
    @Column(name = "Dimensions")
    private List<Double> dimensionsList;

    private String image;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client")
    private Client client;

    @OneToMany(mappedBy = "product",
            fetch = FetchType.EAGER)
    Set<OrderRequest> orderRequest = new HashSet<>();

    public Product() {
    }
    //Creado para pruebas
    public Product(String name, String description, List<Double> dimensionsList, String category, Client client) {
        this.name = name;
        this.description = description;
        this.dimensionsList = dimensionsList;
        this.category = category;
        this.client = client;
    }

    public Product(String name, String description, String category, Double price, Boolean status, LocalDate date, Integer units, List<Double> dimensionsList, String image, Client client) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.status = status;
        this.date = date;
        this.units = units;
        this.dimensionsList = dimensionsList;
        this.image = image;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public List<Double> getDimensionsList() {
        return dimensionsList;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<OrderRequest> getOrderRequest() {
        return orderRequest;
    }

    public void setOrderRequest(Set<OrderRequest> orderRequest) {
        this.orderRequest = orderRequest;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", date=" + date +
                ", units=" + units +
                ", dimensionsList=" + dimensionsList +
                ", image=" + image +
                ", client=" + client +
                '}';
    }
}
