package com.company.service;

import com.company.model.Product;

public interface ProductService {

    void addProduct(Product product);

    void deleteProduct(String productName);

    void buyProduct(String productName, int numberOfProduct);

    void sellProduct(String productName, int numberOfProduct);

    void listProducts();

    void availableBalance();
}
