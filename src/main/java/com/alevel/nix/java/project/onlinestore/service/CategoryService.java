package com.alevel.nix.java.project.onlinestore.service;

import com.alevel.nix.java.project.onlinestore.entity.Category;
import com.alevel.nix.java.project.onlinestore.entity.Product;
import com.alevel.nix.java.project.onlinestore.entity.response.CategoryResponse;
import com.alevel.nix.java.project.onlinestore.entity.response.ProductResponse;
import com.alevel.nix.java.project.onlinestore.exception.CategoryAlreadyTakenException;
import com.alevel.nix.java.project.onlinestore.exception.CategoryNotFoundException;
import com.alevel.nix.java.project.onlinestore.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryService implements CategoryOperations {

    private final CategoryRepository categoryRepository;


    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;

    }

    @Override
    public CategoryResponse createCategory(String categoryName) {
        if (categoryRepository.existsByName(categoryName)) {
            throw new CategoryAlreadyTakenException(categoryName);
        }
        Category category = new Category(categoryName);
        Category save = categoryRepository.save(category);

        return new CategoryResponse(save);
    }

    @Override
    public List<ProductResponse> getProductsByCategory(String categoryName) {
        Category category = categoryRepository
                .getByName(categoryName)
                .orElseThrow(() -> new CategoryNotFoundException(categoryName));
        List<Product> productsOfCategory = category.getProductsOfCategory();
        List<ProductResponse> productsResponse = new ArrayList<>();
        for (Product productOfCategory : productsOfCategory) {
            productsResponse.add(new ProductResponse(productOfCategory));
        }
        return productsResponse;
    }

    @Override
    public List<CategoryResponse> getCategories() {
        List<CategoryResponse> categories = new ArrayList<>();
        for (Category category : categoryRepository.findAll()) {
            categories.add(new CategoryResponse(category));
        }
        return categories;
    }

    @Override
    public void deleteCategory(String categoryName) {
        if (!categoryRepository.existsByName(categoryName)) {
            throw new CategoryNotFoundException(categoryName);
        }
        Category category = categoryRepository
                .getByName(categoryName)
                .orElseThrow(() -> new CategoryNotFoundException(categoryName));
        categoryRepository.delete(category);
    }
}
