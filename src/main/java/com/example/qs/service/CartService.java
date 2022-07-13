package com.example.qs.service;


import com.example.qs.dto.cart.AddToCartDto;
import com.example.qs.model.Cart;
import com.example.qs.model.Product;
import com.example.qs.model.User;
import com.example.qs.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    public CartService() {
    }

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

//    public static List<Cart> listCart() {
//        return cartRepository.findAll();
//    }

    public void addToCart(AddToCartDto addToCartDto, User user) {

        //validate if the product id is valid
        Product product = productService.findById(addToCartDto.getProductId());
        Cart cart = new Cart(product, user, addToCartDto.getQuantity());

        //these can be used instead of adding them as parameters to the above cart object
//        cart.setProduct(product);
//        cart.setUser(user);
//        cart.setQuantity(addToCartDto.getQuantity());
        cart.setCreatedDate(new Date());
        cartRepository.save(cart);
    }
}
