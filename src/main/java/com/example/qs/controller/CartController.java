package com.example.qs.controller;


import com.example.qs.common.ApiResponse;
import com.example.qs.dto.cart.AddToCartDto;
import com.example.qs.dto.cart.CartDto;
import com.example.qs.exceptions.AuthenticationFailException;
import com.example.qs.exceptions.ProductNotExistException;
import com.example.qs.model.Cart;
import com.example.qs.model.Product;
import com.example.qs.model.User;
import com.example.qs.service.AuthenticationService;
import com.example.qs.service.CartService;
import com.example.qs.service.ProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
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
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto,
                                                 @RequestParam("token") String token) throws AuthenticationFailException, ProductNotExistException {
        authenticationService.authenticate(token); //authenticate the token
        User user = authenticationService.getUser(token); //get the user
        Product product = productService.getProductById(addToCartDto.getProductId()); //get the product and append it to the cart
        System.out.println("product to add" +product.getName());
        cartService.addToCart(addToCartDto, user); //use the cartservice to push the product and the authenticated user to the cart

        return new ResponseEntity<>(new ApiResponse(true, "Item added successfully!"), HttpStatus.CREATED);
    }

    //get all cart items for user
    @GetMapping("/list")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token) {
        //authenticate the token //you can use an authorization annotation for this
        authenticationService.authenticate(token);

        //find the user
        User user = authenticationService.getUser(token);

        //get cart items
        CartDto cartDto = cartService.listCartItems(user);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    //delete an item from the cart
    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") Integer itemId,
                                                      @RequestParam("token") String token) {
        //token authentication
        authenticationService.authenticate(token);

        //find the user
        User user = authenticationService.getUser(token);

        cartService.deleteCartItem(itemId, user);
        return new ResponseEntity<>(new ApiResponse(true, "Item deleted Successfully!"), HttpStatus.OK);
    }
}
