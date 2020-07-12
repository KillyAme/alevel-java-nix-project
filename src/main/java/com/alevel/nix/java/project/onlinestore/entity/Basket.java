package com.alevel.nix.java.project.onlinestore.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "baskets")
public class Basket {

    // todo use mapsId
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "product_basket",
            joinColumns = @JoinColumn(name = "basket_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private final List<Product> productListInBasket = new ArrayList<>();

    private Long amount = 0L;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User basketUser;


    public List<Product> getProductListInBasket() {
        return productListInBasket;
    }

    public Long getAmount() {
        return amount;
    }

    public Basket() {
    }

    public void addProductInBasket(Product product) {
        productListInBasket.add(product);
        amount = amount + product.getPrice().longValue();
    }

    public void deleteProductOfBasket(Product product) {
        if(productListInBasket.contains(product)) {
            Long price = product.getPrice().longValue();
            productListInBasket.remove(product);
            amount = amount - price;
        }
    }

    public void resetBasket() {
        amount = 0L;
        productListInBasket.clear();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getBasketUser() {
        return basketUser;
    }

    public void setBasketUser(User basketUser) {
        this.basketUser = basketUser;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }


}
