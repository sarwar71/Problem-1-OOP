package com.company.service;

import com.company.Application;
import com.company.constants.UserInput;
import com.company.db.Database;
import com.company.model.Product;

import java.util.concurrent.atomic.AtomicBoolean;

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

        AtomicBoolean productExist = new AtomicBoolean(false);
        try {
            Database.PRODUCT_LIST.forEach(product -> {
                if (product.getProductName().equals(productName)) {
                    Database.PRODUCT_LIST.remove(product);
                    productExist.set(true);
                }
            });
        } catch (Exception e) {
            Application.init();
        }
        if (!productExist.get()) {
            System.out.println(UserInput.TEXT_RED + "Product name doesn't matched for delete. Please provide the " +
                    "valid product name." + UserInput.TEXT_RESET);

        }
        Application.init();
    }

    @Override
    public void buyProduct(String productName, int numberOfProduct) {

        AtomicBoolean productExist = new AtomicBoolean(false);

        Database.PRODUCT_LIST.forEach(product -> {
            if (product.getProductName().equals(productName)) {

                int totalPrice = numberOfProduct * product.getBuyPrice();

                if (totalPrice <= Database.USER_AVAILABLE_BALANCE) {
                    product.setAvailableProductToSell(product.getAvailableProductToSell() + numberOfProduct);
                    Database.USER_AVAILABLE_BALANCE -= (product.getBuyPrice() * numberOfProduct);
                } else {
                    System.out.println(UserInput.TEXT_RED + "Your total price: " + totalPrice + ". But your available balance: "
                            + Database.USER_AVAILABLE_BALANCE + ". Buy less product." + UserInput.TEXT_RESET);
                }
                productExist.set(true);
            }
        });
        if (!productExist.get()) {
            System.out.println(UserInput.TEXT_RED + "Product name doesn't matched for buy. Please provide the " +
                    "valid product name." + UserInput.TEXT_RESET);
        }
        Application.init();
    }

    @Override
    public void sellProduct(String productName, int numberOfProduct) {

        AtomicBoolean productExist = new AtomicBoolean(false);

        Database.PRODUCT_LIST.forEach(product -> {
            if (product.getProductName().equals(productName)) {
                if (numberOfProduct <= product.getAvailableProductToSell()) {

                    int profit = (product.getSellPrice() - product.getBuyPrice()) * numberOfProduct;
                    product.setTotalProfitSellingProduct(product.getTotalProfitSellingProduct() + profit);
                    product.setAvailableProductToSell(product.getAvailableProductToSell() - numberOfProduct);

                    Database.USER_AVAILABLE_BALANCE += (product.getSellPrice() * numberOfProduct);
                } else {
                    int availableProduct = product.getAvailableProductToSell();
                    System.out.println(UserInput.TEXT_RED + availableProduct + " " + productName + " are available. " +
                            "You can sell " + availableProduct + " or less." + UserInput.TEXT_RESET);
                }
                productExist.set(true);
            }
        });
        if (!productExist.get()) {
            System.out.println(UserInput.TEXT_RED + "Product name doesn't matched for sell. Please provide the " +
                    "valid product name." + UserInput.TEXT_RESET);
        }
    }

    @Override
    public void listProducts() {

        System.out.println(UserInput.LINE);
        System.out.printf(UserInput.TEXT_BLUE + "%10s %22s %15s", "Product Name", "Available Products", "Profit"
                + UserInput.TEXT_RESET);
        System.out.println();
        System.out.println(UserInput.LINE);

        Database.PRODUCT_LIST.forEach(product -> {
            System.out.format(UserInput.TEXT_BLUE + "%10s %15s %22s", product.getProductName(),
                    product.getAvailableProductToSell(), product.getTotalProfitSellingProduct() + UserInput.TEXT_RESET);
            System.out.println();
        });
        System.out.println(UserInput.LINE);
    }

    @Override
    public void availableBalance() {
        System.out.println(UserInput.LINE);
        System.out.println(UserInput.TEXT_BLUE + "Available Balance: " + Database.USER_AVAILABLE_BALANCE
                + UserInput.TEXT_RESET);
        System.out.println(UserInput.LINE);
    }
}
