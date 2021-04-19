package com.company;

import com.company.constants.UserInput;
import com.company.model.Product;
import com.company.service.ProductServiceImpl;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        init();
    }

    public static void init() {

        Scanner sc = new Scanner(System.in);
        System.out.println(UserInput.INPUT);

        try {
            int userInput = sc.nextInt();

            switch (userInput) {
                case 1:
                    addProduct(sc);
                    break;
                case 2:
                    deleteProduct(sc);
                    break;
                case 3:
                    buyProduct(sc);
                    break;
                case 4:
                    sellProduct(sc);
                    break;
                case 5:
                    listProducts();
                    break;
                case 6:
                    availableBalance();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Please provide the valid input.");
            init();
        } finally {
            sc.close();
        }
    }

    private static void addProduct(Scanner sc) {
        System.out.println(UserInput.ADD_PRODUCT);

        try {
            String productName = sc.next();
            int buyPrice = sc.nextInt();
            int sellPrice = sc.nextInt();
            int availableProducts = sc.nextInt();

            Product product = new Product();
            product.setProductName(productName);
            product.setBuyPrice(buyPrice);
            product.setSellPrice(sellPrice);
            product.setAvailableProductToSell(availableProducts);

            ProductServiceImpl.getInstance().addProduct(product);
        } catch (Exception e) {
            System.out.println("Buy price, Sell price and Available products should be natural number.");
            init();
        } finally {
            sc.close();
        }
    }

    private static void deleteProduct(Scanner sc) {
        System.out.println(UserInput.DELETE_PRODUCT);
        String productName = sc.next();

        ProductServiceImpl.getInstance().deleteProduct(productName);
        sc.close();
    }

    private static void buyProduct(Scanner sc) {
        System.out.println(UserInput.BUY_OR_SELL_PRODUCT);
        try {
            String productName = sc.next();
            int numberOfProduct = sc.nextInt();

            ProductServiceImpl.getInstance().buyProduct(productName, numberOfProduct);
        } catch (Exception e) {
            System.out.println("Please provide the valid input.");
        } finally {
            sc.close();
        }
    }

    public static void sellProduct(Scanner sc) {
        System.out.println(UserInput.BUY_OR_SELL_PRODUCT);
        try {
            String productName = sc.next();
            int numberOfProduct = sc.nextInt();

            ProductServiceImpl.getInstance().sellProduct(productName, numberOfProduct);
        } catch (Exception e) {
            System.out.println("Please provide the valid input.");
        } finally {
            sc.close();
        }
    }

    private static void listProducts() {
        ProductServiceImpl.getInstance().listProducts();
    }

    private static void availableBalance() {
        ProductServiceImpl.getInstance().availableBalance();
    }
}
