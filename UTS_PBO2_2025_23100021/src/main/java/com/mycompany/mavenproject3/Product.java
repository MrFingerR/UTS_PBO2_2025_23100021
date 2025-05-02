package com.mycompany.mavenproject3;

public class Product {
    private int id;
    private String code;
    private String name;
    private String category;
    private double price;
    private int stock;

    // Static counter untuk menghasilkan ID unik (sederhana)
    private static int nextId = 3;

    public Product(String code, String name, String category, double price, int stock) {
        this.id = nextId++;
        this.code = code;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    public int getId() { return id; }
    public String getCode() { return code; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return "Product{" +
               "id=" + id +
               ", code='" + code + '\'' +
               ", name='" + name + '\'' +
               ", category='" + category + '\'' +
               ", price=" + price +
               ", stock=" + stock +
               '}';
    }
}