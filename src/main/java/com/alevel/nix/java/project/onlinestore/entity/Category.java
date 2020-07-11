package com.alevel.nix.java.project.onlinestore.entity;

import io.micrometer.core.lang.NonNull;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> productsOfCategory = new ArrayList<>();

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public List<Product> getProductsOfCategory() {
        return productsOfCategory;
    }

    public void setProductsOfCategory(List<Product> productsOfCategory) {
        this.productsOfCategory = productsOfCategory;
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
}
