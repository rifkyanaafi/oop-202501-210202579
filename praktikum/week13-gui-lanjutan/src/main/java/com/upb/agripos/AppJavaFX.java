package com.upb.agripos;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.controller.ProductController;
import com.upb.agripos.view.ProductTableView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;

public class AppJavaFX extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Class.forName("org.postgresql.Driver");
        
        Connection conn = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/agripos",
            "postgres",
            "1234"
        );
        
        ProductDAO dao = new ProductDAO(conn);
        ProductService service = new ProductService(dao);
        ProductController controller = new ProductController(service);

        ProductTableView view = new ProductTableView(controller);

        Scene scene = new Scene(view, 800, 500);
        stage.setTitle("Agri-POS - Week 13 TableView & Lambda");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
