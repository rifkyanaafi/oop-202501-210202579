package com.upb.agripos.dao;

import com.upb.agripos.model.Product;
import java.sql.*;
import java.util.*;

public class ProductDAO {

    private Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM products");
            while (rs.next()) {
                list.add(new Product(
                        rs.getString("code"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public void save(Product p) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO products(code, name, price, stock) VALUES(?, ?, ?, ?) ON CONFLICT(code) DO UPDATE SET name=?, price=?, stock=?"
            );
            ps.setString(1, p.getCode());
            ps.setString(2, p.getName());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getStock());
            ps.setString(5, p.getName());
            ps.setDouble(6, p.getPrice());
            ps.setInt(7, p.getStock());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void delete(String code) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM products WHERE code=?");
            ps.setString(1, code);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
