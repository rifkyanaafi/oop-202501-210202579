package com.upb.agripos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.model.Product;

public class MainDAOTest {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, I am Muhammad Rifqi An Naafi-210202579 (Week11)");

        // Load PostgreSQL driver explicitly
        Class.forName("org.postgresql.Driver");

        Connection conn = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/agripos",
            "postgres",
            "1234"
        );

        ProductDAO dao = new ProductDAOImpl(conn);

        // CREATE
        dao.insert(new Product("P01", "Pupuk Organik", 25000, 10));
        System.out.println("Insert berhasil.");

        // UPDATE
        dao.update(new Product("P01", "Pupuk Organik Premium", 30000, 8));
        System.out.println("Update berhasil.");

        // READ BY CODE
        Product p = dao.findByCode("P01");
        System.out.println("Data ditemukan: " + p.getName());

        // READ ALL
        List<Product> list = dao.findAll();
        System.out.println("Semua Produk:");
        for (Product prod : list) {
            System.out.println(prod.getCode() + " - " + prod.getName());
        }

        // DELETE
        dao.delete("P01");
        System.out.println("Delete berhasil.");

        conn.close();
    }
}
