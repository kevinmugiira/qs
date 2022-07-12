package com.example.qs.dto.cart;

import org.springframework.lang.NonNull;

public class AddToCartDto {

    private Integer Id;
    private @NonNull Integer productId;
    private @NonNull Integer quantity;

    public AddToCartDto() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    @NonNull
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(@NonNull Integer productId) {
        this.productId = productId;
    }

    @NonNull
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(@NonNull Integer quantity) {
        this.quantity = quantity;
    }
}
