package com.alevel.nix.java.project.onlinestore.entity.response;

import com.alevel.nix.java.project.onlinestore.entity.Category;

public class CategoryResponse {
    private Long id;
    private String name;

    public CategoryResponse(Category category) {

        this.name = category.getName();
        this.id = category.getId();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
