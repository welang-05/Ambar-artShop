package com.challengerFinal.arte.model;

import com.challengerFinal.arte.model.enums.TypeUser;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Client {
   @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(strategy = "native",name = "native")
    private Long id;
    private String name;
    private String lastName;
    private String nickname;
    private String email;
    private String telephone;
    private String password;
    private TypeUser typeUser;
    private Boolean active;
    private String direction;
    private Integer ranking;
    private String image;
    @ElementCollection
    @Column(name = "networks")
    private List<String> networks;
    @OneToMany(
            mappedBy = "client",
            fetch = FetchType.EAGER)
    private Set<Product> products = new HashSet<>();
    @OneToMany(
            mappedBy = "client",
            fetch = FetchType.EAGER)
    private Set<ShoppingCart> client = new HashSet<>();

    public Client() {
    }
    //Constructor de usuario basico para todos los usuarios
    public Client(String name, String lastName, String email, String password, TypeUser typeUser, Integer ranking, String image) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.typeUser = typeUser;
        this.ranking = ranking;
        this.image = image;
        this.active = true;
    }

    //Contructor con todas las propiedades
    public Client(String name, String lastName, String nickname, String email, String telephone, String password, TypeUser typeUser, String direction, Integer ranking, String image, List<String> networks) {
        this.name = name;
        this.lastName = lastName;
        this.nickname = nickname;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
        this.typeUser = typeUser;
        this.direction = direction;
        this.ranking = ranking;
        this.image = image;
        this.networks = networks;
        this.active = true;

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TypeUser getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(TypeUser typeUser) {
        this.typeUser = typeUser;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getNetworks() {
        return networks;
    }

    public void setNetworks(List<String> networks) {
        this.networks = networks;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void addClients(Product product) {
     product.setClient(this);
        products.add(product);
    }
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", password='" + password + '\'' +
                ", typeUser=" + typeUser +
                ", active=" + active +
                ", direction='" + direction + '\'' +
                ", ranking=" + ranking +
                ", image='" + image + '\'' +
                ", networks=" + networks +
                '}';
    }
}
