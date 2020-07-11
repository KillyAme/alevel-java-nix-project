package com.alevel.nix.java.project.onlinestore.repository;

import com.alevel.nix.java.project.onlinestore.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Long> {


    Optional<Basket> findBasketByBasketUserId(Long id);
}
