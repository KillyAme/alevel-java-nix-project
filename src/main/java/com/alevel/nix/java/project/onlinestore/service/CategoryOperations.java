package com.alevel.nix.java.project.onlinestore.service;

import com.alevel.nix.java.project.onlinestore.entity.request.CategoryRequest;
import com.alevel.nix.java.project.onlinestore.entity.response.CategoryResponse;
import com.alevel.nix.java.project.onlinestore.entity.response.ProductResponse;

import java.util.List;

public interface CategoryOperations {

    CategoryResponse createCategory(String categoryName);

    List<ProductResponse> getProductsByCategory(String categoryName);

    List<CategoryResponse> getCategories();

    void deleteCategory(String categoryName);


}
