package com.alevel.nix.java.project.onlinestore.service;

import com.alevel.nix.java.project.onlinestore.entity.Category;
import com.alevel.nix.java.project.onlinestore.entity.Product;
import com.alevel.nix.java.project.onlinestore.entity.request.CategoryRequest;
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
    public CategoryResponse createCategory(CategoryRequest request) {
        if (categoryRepository.existsByName(request.getName())) {
            throw new CategoryAlreadyTakenException(request.getName());
        }
        Category category = new Category(request.getName());
        Category save = categoryRepository.save(category);

        return new CategoryResponse(save);
    }

    @Override
    public List<ProductResponse> getProductsByCategory(Long categoryId) {
        Category category = categoryRepository
                .getById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        List<Product> productsOfCategory = category.getProductsOfCategory();
        List<ProductResponse> productsResponse = new ArrayList<>();
        for (Product productOfCategory : productsOfCategory) {
            productsResponse.add(new ProductResponse(productOfCategory));
        }
        return productsResponse;
    }

    @Override
    public List<ProductResponse> getProductsByCategoryAndAvailability(Long categoryId, Boolean availability) {
        Category category = categoryRepository
                .getById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        List<Product> productsOfCategory = category.getProductsOfCategory();
        List<ProductResponse> productsResponse = new ArrayList<>();
        for (Product productOfCategory : productsOfCategory) {
            if (productOfCategory.getAvailability().equals(availability)) {
                productsResponse.add(new ProductResponse(productOfCategory));
            }
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
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException(id);
        }
        Category category = categoryRepository
                .getById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        categoryRepository.delete(category);
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepository.getById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));

        return new CategoryResponse(category);
    }
}
