package com.upb.agripos.dao;

import com.upb.agripos.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    private Connection connection;

    public ProductDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Product p) throws Exception {
        String sql = "INSERT INTO products(code, name, price, stock) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, p.getCode());
        ps.setString(2, p.getName());
        ps.setDouble(3, p.getPrice());
        ps.setInt(4, p.getStock());
        ps.executeUpdate();
    }

    @Override
    public List<Product> findAll() throws Exception {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            list.add(new Product(
                rs.getString("code"),
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getInt("stock")
            ));
        }
        return list;
    }
}
