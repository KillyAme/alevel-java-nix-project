package com.alevel.nix.java.project.onlinestore.repository;

import com.alevel.nix.java.project.onlinestore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> getByName(String categoryName);

    Optional<Category> getById(Long id);

    boolean existsByName(String name);
    boolean existsById(Long id);
}
