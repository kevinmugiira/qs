package com.example.qs.repository;

import com.example.qs.model.WishList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WishListRepository extends JpaRepository<WishList, Integer> {


}
