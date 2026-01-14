package com.upb.agripos.service;

import com.upb.agripos.model.Cart;
import com.upb.agripos.model.CartItem;
import com.upb.agripos.model.Product;

public class CartService {

    private Cart cart = new Cart();

    public void addToCart(Product product, int qty) {
        if (qty <= 0) {
            throw new IllegalArgumentException("Jumlah harus > 0");
        }
        cart.addItem(product, qty);
    }

    // Method untuk test
    public void addItem(Product product, int qty) {
        addToCart(product, qty);
    }

    public Cart getCart() {
        return cart;
    }

    public double getTotal() {
        return cart.getTotal();
    }
}
