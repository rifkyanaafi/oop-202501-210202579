package com.upb.agripos.model;

import javafx.beans.property.*;

public class Product {
    private StringProperty code = new SimpleStringProperty();
    private StringProperty name = new SimpleStringProperty();
    private DoubleProperty price = new SimpleDoubleProperty();
    private IntegerProperty stock = new SimpleIntegerProperty();

    public Product(String code, String name, double price, int stock) {
        this.code.set(code);
        this.name.set(name);
        this.price.set(price);
        this.stock.set(stock);
    }

    public String getCode() { return code.get(); }
    public StringProperty codeProperty() { return code; }

    public String getName() { return name.get(); }
    public StringProperty nameProperty() { return name; }

    public double getPrice() { return price.get(); }
    public DoubleProperty priceProperty() { return price; }

    public int getStock() { return stock.get(); }
    public IntegerProperty stockProperty() { return stock; }
}
