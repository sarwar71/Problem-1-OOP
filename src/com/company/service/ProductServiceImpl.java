package com.company.service;

import com.company.Application;
import com.company.constants.UserInput;
import com.company.db.Database;
import com.company.model.Product;

public class ProductServiceImpl implements ProductService {

    private ProductServiceImpl() {

    }

    private static class SingletonHelper {
        private static final ProductServiceImpl INSTANCE = new ProductServiceImpl();
    }

    public static ProductServiceImpl getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public void addProduct(Product product) {
        Database.PRODUCT_LIST.add(product);
        Application.init();
    }

    @Override
    public void deleteProduct(String productName) {
        Database.PRODUCT_LIST.forEach(product -> {
            if (product.getProductName().equals(productName)) {
                Database.PRODUCT_LIST.remove(product);
                Application.init();
            }
        });
    }

    @Override
    public void buyProduct(String productName, int numberOfProduct) {
        Database.PRODUCT_LIST.forEach(product -> {
            if (product.getProductName().equals(productName)) {

                int totalPrice = numberOfProduct * product.getBuyPrice();

                if (totalPrice <= Database.USER_AVAILABLE_BALANCE) {
                    product.setAvailableProductToSell(product.getAvailableProductToSell() + numberOfProduct);
                    Database.USER_AVAILABLE_BALANCE -= (product.getBuyPrice() * numberOfProduct);
                } else {
                    System.out.println("Your total price: " + totalPrice + ". But your available balance: "
                            + Database.USER_AVAILABLE_BALANCE + ". Buy less product.");
                }
                Application.init();
            }
        });
    }

    @Override
    public void sellProduct(String productName, int numberOfProduct) {
        Database.PRODUCT_LIST.forEach(product -> {
            if (product.getProductName().equals(productName)) {
                if (numberOfProduct <= product.getAvailableProductToSell()) {

                    int profit = (product.getSellPrice() - product.getBuyPrice()) * numberOfProduct;
                    product.setTotalProfitSellingProduct(product.getTotalProfitSellingProduct() + profit);
                    product.setAvailableProductToSell(product.getAvailableProductToSell() - numberOfProduct);

                    Database.USER_AVAILABLE_BALANCE += (product.getSellPrice() * numberOfProduct);
                } else {
                    int availableProduct = product.getAvailableProductToSell();
                    System.out.println(availableProduct + " " + productName + " are available. You can sell "
                            + availableProduct + " or less.");
                }
                Application.init();
            }
        });
    }

    @Override
    public void listProducts() {
        System.out.println(UserInput.PRODUCT_LIST);
        Database.PRODUCT_LIST.forEach(product -> {
            System.out.println("Product name: " + product.getProductName() + ", Available products: "
                    + product.getAvailableProductToSell() + ", Profit: " + product.getTotalProfitSellingProduct());
        });
        Application.init();
    }

    @Override
    public void availableBalance() {
        System.out.println("Available Balance: " + Database.USER_AVAILABLE_BALANCE);
        Application.init();
    }
}