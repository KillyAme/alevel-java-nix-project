package com.alevel.nix.java.project.onlinestore.entity.response;

import com.alevel.nix.java.project.onlinestore.entity.Product;

public class ProductInBasketResponse {
    private String name;
    private Integer price;

    public ProductInBasketResponse() {
    }

    public ProductInBasketResponse(Product product) {
        this.name = product.getModel().getCompanyName()
                + " " + product.getModel().getSeries() + " "
                + product.getModel().getModelName() + " "
                + product.getModel().getProductName();
        this.price = product.getPrice();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
