package com.example.qs.controller;


import com.example.qs.common.ApiResponse;
import com.example.qs.dto.ProductDto;
import com.example.qs.model.Product;
import com.example.qs.model.User;
import com.example.qs.model.WishList;
import com.example.qs.service.AuthenticationService;
import com.example.qs.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/wishlist")
public class WishListController {

    @Autowired
    WishListService wishListService;

    @Autowired
    AuthenticationService authenticationService;

    //actions here:
    // save products to the wishlist

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product, @RequestParam("token") String token) {
        //actions:
        // Authenticate token
        authenticationService.authenticate(token);

        //find the user
        User user = authenticationService.getUser(token);

        //save item to wishlist
        WishList wishList = new WishList(user, product);
        wishListService.createWishList(wishList);

        ApiResponse apiResponse = new ApiResponse(true, "item added to wishlist successfully!");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }


    //retrieve the wishlist items for the user
    //first check if an item is in a users wishlist
    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token") String token) {

        authenticationService.authenticate(token);

        User user = authenticationService.getUser(token);

        List<ProductDto> productDtos = wishListService.getWishListForUser(user);
        return new ResponseEntity<>(productDtos, HttpStatus.OK);

    }


}
