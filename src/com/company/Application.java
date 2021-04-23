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
        System.out.println(UserInput.LINE);

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
                default:
                    throw new Exception();
            }
        } catch (Exception e) {
            System.out.println(UserInput.TEXT_RED + "Please provide the natural number between 1 and 6"
                    + UserInput.TEXT_RESET);
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
            System.out.println(UserInput.LINE);
            System.out.print(UserInput.PRODUCT_NAME);
            String productName = sc.next();

            System.out.print(UserInput.BUY_PRICE);
            int buyPrice = sc.nextInt();

            System.out.print(UserInput.SELL_PRICE);
            int sellPrice = sc.nextInt();

            System.out.print(UserInput.INVENTORY_PRODUCT_TO_SELL);
            int availableProducts = sc.nextInt();
            System.out.println(UserInput.LINE);

            Product product = new Product(productName, buyPrice, sellPrice, availableProducts);
            ProductServiceImpl.getInstance().addProduct(product);

        } catch (Exception e) {
            System.out.println(UserInput.TEXT_RED + "Buy price, Sell price and Available products should be " +
                    "natural number." + UserInput.TEXT_RESET);
            init();
        } finally {
            sc.close();
        }
    }

    private static void deleteProduct(Scanner sc) {

        System.out.println(UserInput.DELETE_PRODUCT);
        System.out.println(UserInput.LINE);
        System.out.print(UserInput.PRODUCT_NAME);
        String productName = sc.next();
        System.out.println(UserInput.LINE);

        ProductServiceImpl.getInstance().deleteProduct(productName);
    }

    private static void buyProduct(Scanner sc) {
        System.out.println(UserInput.BUY_PRODUCT);
        try {
            System.out.println(UserInput.LINE);
            System.out.print(UserInput.PRODUCT_NAME);
            String productName = sc.next();

            System.out.print(UserInput.NUMBER_OF_PRODUCTS);
            int numberOfProduct = sc.nextInt();
            System.out.println(UserInput.LINE);

            ProductServiceImpl.getInstance().buyProduct(productName, numberOfProduct);
        } catch (Exception e) {
            System.out.println(UserInput.TEXT_RED + "Please provide the valid product name for buy product."
                    + UserInput.TEXT_RESET);
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
            System.out.println(UserInput.LINE);
            System.out.print(UserInput.PRODUCT_NAME);
            String productName = sc.next();

            System.out.print(UserInput.NUMBER_OF_PRODUCTS);
            int numberOfProduct = sc.nextInt();
            System.out.println(UserInput.LINE);

            ProductServiceImpl.getInstance().sellProduct(productName, numberOfProduct);
            init();
        } catch (Exception e) {
            System.out.println(UserInput.TEXT_RED + "Please provide the valid product name for sell product."
                    + UserInput.TEXT_RESET);
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
