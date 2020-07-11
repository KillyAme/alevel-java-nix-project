package com.alevel.nix.java.project.onlinestore.entity.response;

import com.alevel.nix.java.project.onlinestore.entity.Basket;
import com.alevel.nix.java.project.onlinestore.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class BasketResponse {
    private List<ProductInBasketResponse> listProducts = new ArrayList<>();
    private Long amount;

    public BasketResponse() {
    }

    public BasketResponse(Basket basket) {

        for (Product product : basket.getProductListInBasket()) {
            listProducts.add(new ProductInBasketResponse(product));
        }

        this.amount = basket.getAmount();
    }

    public List<ProductInBasketResponse> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<ProductInBasketResponse> listProducts) {
        this.listProducts = listProducts;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
