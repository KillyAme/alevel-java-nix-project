package com.alevel.nix.java.project.onlinestore.repository;

import com.alevel.nix.java.project.onlinestore.entity.Category;
import com.alevel.nix.java.project.onlinestore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAllByCategory(Category category);
}
