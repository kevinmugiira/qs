package com.example.qs.dto;

import org.springframework.lang.NonNull;

public class ProductDto {


    // for creating is optional
    // for updating it's compulsory
    private Integer id;
    private @NonNull String name;
    private @NonNull String imageUrl;
    private @NonNull String description;
    private @NonNull double price;
    private @NonNull Integer categoryId;

    public ProductDto() {
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(@NonNull String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @NonNull
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(@NonNull Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
