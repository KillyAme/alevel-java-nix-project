package com.alevel.nix.java.project.onlinestore.entity;

import com.alevel.nix.java.project.onlinestore.entity.enums.Role;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(name = "phone_number")
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;


    @OneToOne(mappedBy = "basketUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private Basket userBasket;

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Basket getUserBasket() {
        return userBasket;
    }

    public void setUserBasket(Basket userBasket) {
        this.userBasket = userBasket;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
