package com.alevel.nix.java.project.onlinestore.service;

import com.alevel.nix.java.project.onlinestore.entity.Basket;
import com.alevel.nix.java.project.onlinestore.entity.Product;
import com.alevel.nix.java.project.onlinestore.entity.response.BasketResponse;
import com.alevel.nix.java.project.onlinestore.exception.BasketNotFoundException;
import com.alevel.nix.java.project.onlinestore.exception.ProductNotFoundException;
import com.alevel.nix.java.project.onlinestore.repository.BasketRepository;
import com.alevel.nix.java.project.onlinestore.repository.ProductRepository;
import com.alevel.nix.java.project.onlinestore.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BasketService implements BasketOperations {

    private final BasketRepository basketRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    public BasketService(BasketRepository basketRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.basketRepository = basketRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BasketResponse getBasketByUserId(Long userId) {
        Basket userBasket = userRepository
                .findById(userId)
                .orElseThrow(() -> new BasketNotFoundException(userId))
                .getUserBasket();
        return new BasketResponse(userBasket);

    }

    @Override
    public void clearBasketByUser(Long userId) {
        Basket basket = userRepository
                .findById(userId)
                .orElseThrow(() -> new BasketNotFoundException(userId))
                .getUserBasket();
        basket.resetBasket();
        basketRepository.save(basket);

    }

    @Override
    public void addProductInBasketByUserIdAndProductId(Long userId, Long productId) {
        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        Basket basket = userRepository
                .findById(userId)
                .orElseThrow(() -> new BasketNotFoundException(userId))
                .getUserBasket();
        basket.addProductInBasket(product);

        basketRepository.save(basket);

    }

    @Override
    public void deleteProductOfBasketByUserIdAndProductId(Long userId, Long productId) {
        Basket basket = userRepository
                .findById(userId)
                .orElseThrow(() -> new BasketNotFoundException(userId))
                .getUserBasket();
        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        basket.deleteProductOfBasket(product);
        product.getBaskets().remove(basket);
        basketRepository.save(basket);
    }
}
