package com.upb.agripos.view;

import com.upb.agripos.controller.ProductController;
import com.upb.agripos.model.Product;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProductFormView {

    public void show(Stage stage, ProductController controller) {
        TextField txtCode = new TextField();
        TextField txtName = new TextField();
        TextField txtPrice = new TextField();
        TextField txtStock = new TextField();

        txtCode.setPromptText("Kode Produk");
        txtName.setPromptText("Nama Produk");
        txtPrice.setPromptText("Harga");
        txtStock.setPromptText("Stok");

        Button btnAdd = new Button("Tambah Produk");
        ListView<String> listView = new ListView<>();

        btnAdd.setOnAction(event -> {
            try {
                // Validasi input tidak kosong
                String code = txtCode.getText().trim();
                String name = txtName.getText().trim();
                String priceStr = txtPrice.getText().trim();
                String stockStr = txtStock.getText().trim();

                if (code.isEmpty() || name.isEmpty() || priceStr.isEmpty() || stockStr.isEmpty()) {
                    showAlert(Alert.AlertType.WARNING, "Peringatan", "Semua field harus diisi!");
                    return;
                }

                // Validasi format harga dan stok
                double price;
                int stock;
                try {
                    price = Double.parseDouble(priceStr);
                    stock = Integer.parseInt(stockStr);
                } catch (NumberFormatException e) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Harga harus angka desimal dan Stok harus angka bulat!");
                    return;
                }

                // Validasi nilai positif
                if (price <= 0) {
                    showAlert(Alert.AlertType.WARNING, "Peringatan", "Harga harus lebih dari 0!");
                    return;
                }
                if (stock <= 0) {
                    showAlert(Alert.AlertType.WARNING, "Peringatan", "Stok harus lebih dari 0!");
                    return;
                }

                // Buat dan tambahkan product
                Product p = new Product(code, name, price, stock);
                controller.addProduct(p);
                
                listView.getItems().add(String.format("%s - %s (Rp%.2f) Stok: %d", 
                    code, name, price, stock));
                
                // Clear fields
                txtCode.clear();
                txtName.clear();
                txtPrice.clear();
                txtStock.clear();
                
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Produk berhasil ditambahkan!");
                
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Error", 
                    "Gagal menambahkan produk: " + e.getMessage());
            }
        });

        VBox root = new VBox(10, txtCode, txtName, txtPrice, txtStock, btnAdd, listView);
        root.setPadding(new Insets(15));
        Scene scene = new Scene(root, 500, 600);

        stage.setTitle("Form Produk Agri-POS");
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
