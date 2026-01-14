package com.upb.agripos.service;

import com.upb.agripos.dao.JdbcProductDAO;
import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.model.Product;

import java.util.List;

public class ProductService {

    private ProductDAO productDAO = new JdbcProductDAO();

    public void addProduct(Product product) {
        if (product.getCode().isEmpty() || product.getName().isEmpty()) {
            throw new IllegalArgumentException("Kode dan Nama tidak boleh kosong");
        }
        if (product.getPrice() <= 0 || product.getStock() < 0) {
            throw new IllegalArgumentException("Harga / Stok tidak valid");
        }
        productDAO.insert(product);
    }

    public void deleteProduct(String code) {
        productDAO.delete(code);
    }

    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }
}
