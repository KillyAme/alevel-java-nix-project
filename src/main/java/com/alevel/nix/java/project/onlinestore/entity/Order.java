package com.alevel.nix.java.project.onlinestore.entity;

import com.alevel.nix.java.project.onlinestore.entity.enums.OrderStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private List<Product> productList;

    @Column(name = "order_price")
    private Long orderPrice;

    private OrderStatus orderStatus;

    private Long amount;

    private User user;




    public Long getId() {
        return id;
    }

    public void setId(Long userId) {
        this.id = userId;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Long getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Long orderPrice) {
        this.orderPrice = orderPrice;
    }
}
