package com.alevel.nix.java.project.onlinestore.repository;

import com.alevel.nix.java.project.onlinestore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUserId(Long id);
}
