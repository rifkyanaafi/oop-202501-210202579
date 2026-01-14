package com.upb.agripos.dao;

import com.upb.agripos.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcProductDAO implements ProductDAO {

    @Override
    public void insert(Product p) {
        try {
            // Try insert to 'products' table first
            String sql = "INSERT INTO products(code, name, price, stock) VALUES(?,?,?,?)";
            try {
                PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
                ps.setString(1, p.getCode());
                ps.setString(2, p.getName());
                ps.setDouble(3, p.getPrice());
                ps.setInt(4, p.getStock());
                ps.executeUpdate();
                ps.close();
                System.out.println("Produk berhasil ditambahkan: " + p.getCode() + " (table: products)");
            } catch (SQLException e1) {
                // If products table doesn't exist, try 'product' table
                System.out.println("Trying 'product' table instead...");
                String sql2 = "INSERT INTO product(code, name, price, stock) VALUES(?,?,?,?)";
                PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql2);
                ps.setString(1, p.getCode());
                ps.setString(2, p.getName());
                ps.setDouble(3, p.getPrice());
                ps.setInt(4, p.getStock());
                ps.executeUpdate();
                ps.close();
                System.out.println("Produk berhasil ditambahkan: " + p.getCode() + " (table: product)");
            }
        } catch (Exception e) {
            System.err.println("Error insert: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String code) {
        try {
            // Try delete from 'products' table first
            String sql = "DELETE FROM products WHERE code=?";
            try {
                PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
                ps.setString(1, code);
                ps.executeUpdate();
                ps.close();
                System.out.println("Produk berhasil dihapus: " + code + " (table: products)");
            } catch (SQLException e1) {
                // If products table doesn't exist, try 'product' table
                System.out.println("Trying 'product' table instead...");
                String sql2 = "DELETE FROM product WHERE code=?";
                PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql2);
                ps.setString(1, code);
                ps.executeUpdate();
                ps.close();
                System.out.println("Produk berhasil dihapus: " + code + " (table: product)");
            }
        } catch (Exception e) {
            System.err.println("Error delete: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        try {
            // Try to select from 'products' table first
            String sql = "SELECT * FROM products";
            try {
                Statement st = DBUtil.getConnection().createStatement();
                ResultSet rs = st.executeQuery(sql);
                
                System.out.println("Connected to 'products' table");
                while (rs.next()) {
                    try {
                        Product p = new Product(
                            rs.getString("code"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getInt("stock")
                        );
                        list.add(p);
                    } catch (SQLException colError) {
                        System.err.println("Column error in 'products' table: " + colError.getMessage());
                        // Try alternative column names (Indonesian)
                        try {
                            Product p = new Product(
                                rs.getString("kode"),
                                rs.getString("nama"),
                                rs.getDouble("harga"),
                                rs.getInt("stok")
                            );
                            list.add(p);
                        } catch (Exception altColError) {
                            System.err.println("Alternative column names also failed: " + altColError.getMessage());
                        }
                    }
                }
                st.close();
                System.out.println("✓ Berhasil mengambil " + list.size() + " produk dari table 'products'");
                return list;
            } catch (SQLException tableError) {
                System.out.println("Table 'products' not found, trying 'product'...");
                
                // Try 'product' table
                String sql2 = "SELECT * FROM product";
                Statement st = DBUtil.getConnection().createStatement();
                ResultSet rs = st.executeQuery(sql2);

                System.out.println("Connected to 'product' table");
                while (rs.next()) {
                    try {
                        Product p = new Product(
                            rs.getString("code"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getInt("stock")
                        );
                        list.add(p);
                    } catch (SQLException colError) {
                        System.err.println("Column error in 'product' table: " + colError.getMessage());
                        System.out.println("Available columns: ");
                        ResultSetMetaData metadata = rs.getMetaData();
                        for (int i = 1; i <= metadata.getColumnCount(); i++) {
                            System.out.println("  - " + metadata.getColumnName(i) + " (" + metadata.getColumnTypeName(i) + ")");
                        }
                    }
                }
                st.close();
                System.out.println("✓ Berhasil mengambil " + list.size() + " produk dari table 'product'");
            }
        } catch (Exception e) {
            System.err.println("Error findAll: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
}
