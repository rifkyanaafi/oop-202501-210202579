package com.upb.agripos;

import com.upb.agripos.dao.*;
import com.upb.agripos.service.*;
import com.upb.agripos.controller.*;
import com.upb.agripos.view.ProductFormView;
import javafx.application.Application;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;

public class AppJavaFX extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Pastikan driver PostgreSQL ter-load sebelum getConnection
        Class.forName("org.postgresql.Driver");

        Connection conn = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/agripos",
            "postgres",
            "1234" // ganti dengan password PostgreSQL kamu
        );

        ProductDAO dao = new ProductDAOImpl(conn);
        ProductService service = new ProductService(dao);
        ProductController controller = new ProductController(service);

        ProductFormView view = new ProductFormView();
        view.show(stage, controller);
    }

    public static void main(String[] args) {
        launch(args);
    }
}