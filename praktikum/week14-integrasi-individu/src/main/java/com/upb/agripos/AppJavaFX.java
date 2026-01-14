package com.upb.agripos;

import com.upb.agripos.view.PosView;
import com.upb.agripos.controller.PosController;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.service.CartService;
import com.upb.agripos.dao.JdbcProductDAO;
import javafx.application.Application;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;

public class AppJavaFX extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Hello, I am Muhammad Rifqi An Naafi - 210202579");
        
        // Initialize SQLite Database
        initializeDatabase();
        
        // Create controller
        PosController controller = new PosController();

        // Create view
        PosView view = new PosView();
        view.show(stage);
    }

    private void initializeDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:agripos.db");
            
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("CREATE TABLE IF NOT EXISTS product (" +
                        "code TEXT PRIMARY KEY, " +
                        "name TEXT NOT NULL, " +
                        "price REAL NOT NULL, " +
                        "stock INTEGER NOT NULL" +
                        ")");
                System.out.println("Database initialized successfully");
            }
            
            conn.close();
        } catch (Exception e) {
            System.err.println("Error initializing database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
