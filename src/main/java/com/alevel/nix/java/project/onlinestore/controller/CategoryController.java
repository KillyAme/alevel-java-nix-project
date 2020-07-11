package com.alevel.nix.java.project.onlinestore.controller;

import com.alevel.nix.java.project.onlinestore.entity.response.CategoryResponse;
import com.alevel.nix.java.project.onlinestore.entity.response.ProductResponse;
import com.alevel.nix.java.project.onlinestore.service.CategoryOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryOperations categoryOperations;

    public CategoryController(CategoryOperations categoryOperations) {
        this.categoryOperations = categoryOperations;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody String categoryName) {
        CategoryResponse categoryResponse = categoryOperations.createCategory(categoryName);
        return ResponseEntity
                .created(URI.create("/categories/" + categoryResponse.getName()))
                .body(categoryResponse);
    }

    @GetMapping("/{categoryName}/products")
    public List<ProductResponse> getProducts(@PathVariable String categoryName) {
        return categoryOperations.getProductsByCategory(categoryName);
    }

    @GetMapping
    public List<CategoryResponse> getCategories() {
        return categoryOperations.getCategories();
    }
}
