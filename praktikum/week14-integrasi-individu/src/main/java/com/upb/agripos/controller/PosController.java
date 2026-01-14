package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.CartService;
import com.upb.agripos.service.ProductService;

import java.util.List;

public class PosController {

    private ProductService productService = new ProductService();
    private CartService cartService = new CartService();

    public void addProduct(Product p) {
        productService.addProduct(p);
    }

    public void deleteProduct(String code) {
        productService.deleteProduct(code);
    }

    public List<Product> loadProducts() {
        return productService.getAllProducts();
    }

    public void addToCart(Product product, int qty) {
        cartService.addToCart(product, qty);
    }

    public double getTotal() {
        return cartService.getTotal();
    }

    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    public double getCartTotal() {
        return cartService.getTotal();
    }
}
