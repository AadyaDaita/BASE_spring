package com.nighthawk.spring_portfolio.mvc.searchapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nighthawk.spring_portfolio.mvc.searchapi.products.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE " +
    "p.name LIKE CONCAT('%',:query, '%')" +
    "Or p.description LIKE CONCAT('%', :query, '%')")

    List<Product> searchProducts(String query);

}