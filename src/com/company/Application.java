package com.company;

import com.company.constants.UserInput;
import com.company.db.Database;
import com.company.model.Product;
import com.company.service.ProductServiceImpl;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        // initially added five products and sell three products
        addProduct();
        sellProduct();

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
            System.out.println("Please provide the natural number between 1 and 6");
            init();
        } finally {
            sc.close();
        }
    }

    private static void addProduct() {

        Database.PRODUCT_LIST.add(new Product("Mouse", 300, 320, 10));
        Database.PRODUCT_LIST.add(new Product("Monitor", 10000, 10500, 5));
        Database.PRODUCT_LIST.add(new Product("Keyboard", 400, 450, 10));
        Database.PRODUCT_LIST.add(new Product("HDD", 3000, 3200, 10));
        Database.PRODUCT_LIST.add(new Product("RAM", 3500, 3800, 5));
    }

    private static void addProduct(Scanner sc) {
        System.out.println(UserInput.ADD_PRODUCT);

        try {
            String productName = sc.next();
            int buyPrice = sc.nextInt();
            int sellPrice = sc.nextInt();
            int availableProducts = sc.nextInt();

            Product product = new Product(productName, buyPrice, sellPrice, availableProducts);

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
        System.out.println(UserInput.BUY_PRODUCT);
        try {
            String productName = sc.next();
            int numberOfProduct = sc.nextInt();

            ProductServiceImpl.getInstance().buyProduct(productName, numberOfProduct);
        } catch (Exception e) {
            System.out.println("Please provide the valid product name for buy product.");
            init();
        } finally {
            sc.close();
        }
    }

    public static void sellProduct() {
        ProductServiceImpl.getInstance().sellProduct("Mouse", 2);
        ProductServiceImpl.getInstance().sellProduct("Keyboard", 3);
        ProductServiceImpl.getInstance().sellProduct("HDD", 4);
    }

    public static void sellProduct(Scanner sc) {
        System.out.println(UserInput.SELL_PRODUCT);
        try {
            String productName = sc.next();
            int numberOfProduct = sc.nextInt();

            ProductServiceImpl.getInstance().sellProduct(productName, numberOfProduct);
            init();
        } catch (Exception e) {
            System.out.println("Please provide the valid product name for sell product.");
            init();
        } finally {
            sc.close();
        }
    }

    private static void listProducts() {
        ProductServiceImpl.getInstance().listProducts();
        init();
    }

    private static void availableBalance() {
        ProductServiceImpl.getInstance().availableBalance();
        init();
    }
}
