package com.example.qs.service;


import com.example.qs.model.WishList;
import com.example.qs.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishListService {

    @Autowired
    WishListRepository wishListRepository;

    public void createWishList(WishList wishList) {
        wishListRepository.save(wishList);
    }
}
