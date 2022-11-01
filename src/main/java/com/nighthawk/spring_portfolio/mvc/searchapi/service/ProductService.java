package com.nighthawk.spring_portfolio.mvc.searchapi.service;

import java.util.List;

import com.nighthawk.spring_portfolio.mvc.searchapi.products.Product;

public interface ProductService {
    List<Product> searchProducts(String query);

    Product createProduct(Product product);
}