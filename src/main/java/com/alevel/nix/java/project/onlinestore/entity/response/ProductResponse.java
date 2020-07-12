package com.alevel.nix.java.project.onlinestore.entity.response;

import com.alevel.nix.java.project.onlinestore.entity.Product;

public class ProductResponse {
    private Long id;
    private String description;
    private String categoryName;
    private Integer price;
    private boolean availability;
    private String company;
    private String series;
    private String model;
    private String productName;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.description = product.getDescription();
        this.categoryName = product.getCategory().getName();
        this.price = product.getPrice();
        this.availability = product.getAvailability();
        this.company = product.getModel().getCompanyName();
        this.series = product.getModel().getSeries();
        this.model = product.getModel().getModelName();
        this.productName = product.getModel().getProductName();
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
