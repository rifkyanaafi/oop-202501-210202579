package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.ProductService;
import java.util.List;

public class ProductController {
    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    public void addProduct(Product p) throws Exception {
        service.insert(p);
    }

    public List<Product> getAllProducts() throws Exception {
        return service.getAll();
    }
}
