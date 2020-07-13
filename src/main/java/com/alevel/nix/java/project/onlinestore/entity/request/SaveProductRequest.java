package com.alevel.nix.java.project.onlinestore.entity.request;

public class SaveProductRequest {

    private String company;
    private String series;
    private String model;
    private String productName;
    private Integer price;
    private String description;
    private String categoryName;

    public SaveProductRequest(String company, String series, String model, String productName, Integer price, String description, String categoryName) {
        this.company = company;
        this.series = series;
        this.model = model;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.categoryName = categoryName;
    }

    public SaveProductRequest() {
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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
}
