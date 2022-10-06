package com.challengerFinal.arte.dtos;

import com.challengerFinal.arte.model.enums.TypeUser;

import java.io.Serializable;
import java.util.List;

public class ToUpdateClientsDto implements Serializable {
    private Long id;
    private String name;
    private String lastName;
    private String nickname;
    private String email;
    private String telephone;
    private String password;
    private TypeUser typeUser;
    private String direction;
    private String image;
    private List<String> networks;

    public ToUpdateClientsDto() {

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

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getPassword() {
        return password;
    }

    public TypeUser getTypeUser() {
        return typeUser;
    }

    public List<String> getNetworks() {
        return networks;
    }

    public String getDirection() {
        return direction;
    }

    public String getImage() {
        return image;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTypeUser(TypeUser typeUser) {
        this.typeUser = typeUser;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setNetworks(List<String> networks) {
        this.networks = networks;
    }
}
