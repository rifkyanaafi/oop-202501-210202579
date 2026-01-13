package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.ProductService;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;

public class ProductController {
    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    public void loadData(TableView<Product> table) {
        table.setItems(FXCollections.observableArrayList(service.findAll()));
    }

    public void addProduct(Product p) {
        service.save(p);
    }

    public void deleteProduct(String code) {
        service.delete(code);
    }
}
