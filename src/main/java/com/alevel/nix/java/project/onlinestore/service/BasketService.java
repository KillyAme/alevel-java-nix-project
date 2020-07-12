package com.alevel.nix.java.project.onlinestore.service;

import com.alevel.nix.java.project.onlinestore.entity.Basket;
import com.alevel.nix.java.project.onlinestore.entity.Product;
import com.alevel.nix.java.project.onlinestore.entity.User;
import com.alevel.nix.java.project.onlinestore.entity.response.BasketResponse;
import com.alevel.nix.java.project.onlinestore.exception.BasketNotFoundException;
import com.alevel.nix.java.project.onlinestore.exception.NotAuthorizedException;
import com.alevel.nix.java.project.onlinestore.exception.ProductAvailabilityException;
import com.alevel.nix.java.project.onlinestore.exception.ProductNotFoundException;
import com.alevel.nix.java.project.onlinestore.repository.BasketRepository;
import com.alevel.nix.java.project.onlinestore.repository.ProductRepository;
import com.alevel.nix.java.project.onlinestore.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
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
        User user = getUser(userId);

        String nameAuthorized = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!nameAuthorized.equals(user.getName())) {
            throw new NotAuthorizedException("You cannot view someone else’s basket");
        }

        Basket userBasket = user.getUserBasket();
        return new BasketResponse(userBasket);

    }

    @Override
    public void clearBasketByUser(Long userId) {
        User user = getUser(userId);

        String nameAuthorized = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!nameAuthorized.equals(user.getName())) {
            throw new NotAuthorizedException("You cannot clear someone else’s basket");
        }

        Basket basket = user.getUserBasket();
        basket.resetBasket();
        basketRepository.save(basket);

    }

    @Override
    public void addProductInBasketByUserIdAndProductId(Long userId, Long productId) {
        User user = getUser(userId);

        String nameAuthorized = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!nameAuthorized.equals(user.getName())) {
            throw new NotAuthorizedException("You cannot add product in someone else’s basket");
        }

        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        if (!product.getAvailability()){
            throw new ProductAvailabilityException(productId);
        }

        Basket basket = user.getUserBasket();
        basket.addProductInBasket(product);

        basketRepository.save(basket);

    }

    @Override
    public void deleteProductOfBasketByUserIdAndProductId(Long userId, Long productId) {
        User user = getUser(userId);

        String nameAuthorized = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!nameAuthorized.equals(user.getName())) {
            throw new NotAuthorizedException("You cannot delete product of someone else’s basket");
        }

        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        Basket basket = user.getUserBasket();
        basket.deleteProductOfBasket(product);
        //product.getBaskets().remove(basket);
        basketRepository.save(basket);
    }

    private User getUser(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new BasketNotFoundException(userId));
    }
}
