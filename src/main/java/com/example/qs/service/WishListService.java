package com.example.qs.service;


import com.example.qs.dto.ProductDto;
import com.example.qs.model.Product;
import com.example.qs.model.User;
import com.example.qs.model.WishList;
import com.example.qs.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListService {

    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    ProductService productService;

    public List<ProductDto> getWishListForUser(User user) {
        final List<WishList> wishLists = wishListRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<ProductDto> productDtos = new ArrayList<>();
        for (WishList wishList: wishLists) {
            productDtos.add(productService.getProductDto(wishList.getProduct()));
        }

        return productDtos;
    }

    public void createWishList(WishList wishList) {
        wishListRepository.save(wishList);
    }

//    public List<ProductDto> getWishListProducts() {
//        List<Product> allProducts = WishListRepository.findAll();
//
//        List<ProductDto> productDtos = new ArrayList<>();
//        for (Product product: allProducts) {
//            productDtos.add(getWishtListProductDto(product));
//        }
//        return productDtos;
//    }
}
