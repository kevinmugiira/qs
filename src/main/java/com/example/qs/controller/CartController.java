package com.example.qs.controller;


import com.example.qs.common.ApiResponse;
import com.example.qs.dto.cart.AddToCartDto;
import com.example.qs.exceptions.AuthenticationFailException;
import com.example.qs.exceptions.ProductNotExistException;
import com.example.qs.model.Cart;
import com.example.qs.model.Product;
import com.example.qs.model.User;
import com.example.qs.service.AuthenticationService;
import com.example.qs.service.CartService;
import com.example.qs.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {


    @Autowired
    private CartService cartService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ProductService productService;

    //post cart api
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto, @RequestParam("token") String token) throws AuthenticationFailException, ProductNotExistException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        Product product = productService.getProductById(addToCartDto.getProductId());
        System.out.println("product to add" +product.getName());
        cartService.addToCart(addToCartDto, product, user);

        return new ResponseEntity<>(new ApiResponse(true, "Item added successfully!"), HttpStatus.CREATED);
    }

    //get all cart items for user

    //delete an item from the cart
}
