package com.challengerFinal.arte.dtos;

import com.challengerFinal.arte.model.Client;
import com.challengerFinal.arte.model.enums.TypeUser;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientsDto implements Serializable {

    private Long id;
    private String name;
    private String lastName;
    private String nickname;
    private Integer ranking;
    private String email;
    private String telephone;
    private TypeUser typeUser;
    private Boolean active;
    private String direction;
    private String image;
    private List<String> networks;
    private Set<ProductDto> products;

    public ClientsDto(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.lastName = client.getLastName();
        this.nickname = client.getNickname();
        this.ranking = client.getRanking();
        this.email = client.getEmail();
        this.telephone = client.getTelephone();
        this.typeUser = client.getTypeUser();
        this.direction = client.getDirection();
        this.networks = client.getNetworks();
        this.image = client.getImage();
        this.active = client.getActive();
        this.products = client.getProducts().stream().map(ProductDto::new).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public Integer getRanking() {
        return ranking;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public TypeUser getTypeUser() {
        return typeUser;
    }

    public Boolean getActive() {
        return active;
    }

    public String getDirection() {
        return direction;
    }

    public String getImage() {
        return image;
    }

    public List<String> getNetworks() {
        return networks;
    }

    public Set<ProductDto> getProducts() {
        return products;
    }

}
