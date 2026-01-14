package com.upb.agripos.model;

import javafx.beans.property.*;

public class Product {
    private StringProperty code;
    private StringProperty name;
    private DoubleProperty price;
    private IntegerProperty stock;

    public Product() {
        this("", "", 0.0, 0);
    }

    public Product(String code, String name, double price, int stock) {
        this.code = new SimpleStringProperty(code);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.stock = new SimpleIntegerProperty(stock);
    }

    // Getters and Setters for basic types
    public String getCode() { return code.get(); }
    public void setCode(String code) { this.code.set(code); }

    public String getName() { return name.get(); }
    public void setName(String name) { this.name.set(name); }

    public double getPrice() { return price.get(); }
    public void setPrice(double price) { this.price.set(price); }

    public int getStock() { return stock.get(); }
    public void setStock(int stock) { this.stock.set(stock); }

    // JavaFX Property methods untuk TableView
    public StringProperty codeProperty() {
        return code;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public IntegerProperty stockProperty() {
        return stock;
    }
}
