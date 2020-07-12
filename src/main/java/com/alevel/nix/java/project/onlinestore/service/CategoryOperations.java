package com.alevel.nix.java.project.onlinestore.service;

import com.alevel.nix.java.project.onlinestore.entity.request.CategoryRequest;
import com.alevel.nix.java.project.onlinestore.entity.response.CategoryResponse;
import com.alevel.nix.java.project.onlinestore.entity.response.ProductResponse;

import java.util.List;

public interface CategoryOperations {

    CategoryResponse createCategory(CategoryRequest categoryName);

    List<ProductResponse> getProductsByCategory(Long id);

    List<ProductResponse> getProductsByCategoryAndAvailability(Long id, Boolean availability);

    List<CategoryResponse> getCategories();

    CategoryResponse getCategoryById(Long id);

    void deleteCategory(Long id);


}
