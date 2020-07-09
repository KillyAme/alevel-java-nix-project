package com.alevel.nix.java.project.onlinestore.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false)
    private Integer price;

    @Embedded
    private Manufacturer model;

    @Column(nullable = false)
    private Boolean availability;

    @ManyToMany
    @JoinTable(name = "product_basket",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "basket_id")
    )
    private List<Basket> listBasketWhereIsProduct;

    @ManyToMany
    @JoinTable(name = "product_order",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<Order> listOrderWhereIsProduct;


    public List<Basket> getListBasketWhereIsProduct() {
        return listBasketWhereIsProduct;
    }

    public void setListBasketWhereIsProduct(List<Basket> listBasketWhereIsProduct) {
        this.listBasketWhereIsProduct = listBasketWhereIsProduct;
    }

    public List<Order> getListOrderWhereIsProduct() {
        return listOrderWhereIsProduct;
    }

    public void setListOrderWhereIsProduct(List<Order> listOrderWhereIsProduct) {
        this.listOrderWhereIsProduct = listOrderWhereIsProduct;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Manufacturer getModel() {
        return model;
    }

    public void setModel(Manufacturer model) {
        this.model = model;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
}
