package com.company.model;

public class Product {

    private String productName;
    private int buyPrice;
    private int sellPrice;
    private int availableProductToSell;
    private int totalProfitSellingProduct;

    public Product(String productName, int buyPrice, int sellPrice, int availableProductToSell) {
        this.productName = productName;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.availableProductToSell = availableProductToSell;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setAvailableProductToSell(int availableProductToSell) {
        this.availableProductToSell = availableProductToSell;
    }

    public void setTotalProfitSellingProduct(int totalProfitSellingProduct) {
        this.totalProfitSellingProduct = totalProfitSellingProduct;
    }

    public String getProductName() {
        return productName;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public int getAvailableProductToSell() {
        return availableProductToSell;
    }

    public int getTotalProfitSellingProduct() {
        return totalProfitSellingProduct;
    }
}
