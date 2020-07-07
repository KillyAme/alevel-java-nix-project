package com.alevel.nix.java.project.onlinestore.entity;

import javax.persistence.Embeddable;
import java.util.List;

@Embeddable
public class Basket {


    private List<Product> listProductsInBasket;

    private Long amount;




    public List<Product> getListProductsInBasket() {
        return listProductsInBasket;
    }

    public void setListProductsInBasket(List<Product> listProductsInBasket) {
        this.listProductsInBasket = listProductsInBasket;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
