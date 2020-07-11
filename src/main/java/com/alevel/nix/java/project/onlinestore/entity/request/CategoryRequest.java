package com.alevel.nix.java.project.onlinestore.entity.request;

public class CategoryRequest {
    private Long id;
    private String name;



    public CategoryRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
