package com.example.qs.repository;

import com.example.qs.model.User;
import com.example.qs.model.WishList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WishListRepository extends JpaRepository<WishList, Integer> {

     List<WishList> findAllByUserOrderByCreatedDateDesc(User user);
}
