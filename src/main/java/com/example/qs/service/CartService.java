package com.example.qs.service;


import com.example.qs.dto.cart.AddToCartDto;
import com.example.qs.model.Cart;
import com.example.qs.model.Product;
import com.example.qs.model.User;
import com.example.qs.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public CartService() {
    }

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void addToCart(AddToCartDto addToCartDto, Product product, User user) {
        Cart cart = new Cart(product, user, addToCartDto.getQuantity());
        cartRepository.save(cart);
    }
}
