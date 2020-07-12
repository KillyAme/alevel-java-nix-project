package com.alevel.nix.java.project.onlinestore.controller;

import com.alevel.nix.java.project.onlinestore.entity.request.CategoryRequest;
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
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest request) {
        CategoryResponse categoryResponse = categoryOperations.createCategory(request);
        return ResponseEntity
                .created(URI.create("/categories/" + categoryResponse.getId()))
                .body(categoryResponse);
    }

    @GetMapping("/{categoryId}/products")
    public List<ProductResponse> getProducts(@PathVariable Long categoryId) {

        return categoryOperations.getProductsByCategory(categoryId);
    }

    @GetMapping
    public List<CategoryResponse> getCategories() {
        return categoryOperations.getCategories();
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategory(@PathVariable Long id){
        return categoryOperations.getCategoryById(id);
    }
}

