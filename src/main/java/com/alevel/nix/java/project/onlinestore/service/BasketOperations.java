package com.alevel.nix.java.project.onlinestore.service;

import com.alevel.nix.java.project.onlinestore.entity.response.BasketResponse;

public interface BasketOperations {

    BasketResponse getBasketByUserId(Long id);

    void clearBasketByUser(Long userId);

    void addProductInBasketByUserIdAndProductId(Long userId, Long productId);

    void deleteProductOfBasketByUserIdAndProductId(Long userId, Long productId);
}
