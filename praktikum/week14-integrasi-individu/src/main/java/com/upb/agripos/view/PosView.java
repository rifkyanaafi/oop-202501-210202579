package com.upb.agripos.view;

import com.upb.agripos.controller.PosController;
import com.upb.agripos.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class PosView {

    private PosController controller = new PosController();
    private TableView<Product> table = new TableView<>();
    private ObservableList<Product> data = FXCollections.observableArrayList();
    private Label lblTotal = new Label("Total: 0");

    public void show(Stage stage) {

        // Kolom TableView
        TableColumn<Product, String> colCode = new TableColumn<>("Kode");
        colCode.setCellValueFactory(c -> c.getValue().codeProperty());

        TableColumn<Product, String> colName = new TableColumn<>("Nama");
        colName.setCellValueFactory(c -> c.getValue().nameProperty());

        TableColumn<Product, Number> colPrice = new TableColumn<>("Harga");
        colPrice.setCellValueFactory(c -> c.getValue().priceProperty());

        TableColumn<Product, Number> colStock = new TableColumn<>("Stok");
        colStock.setCellValueFactory(c -> c.getValue().stockProperty());

        table.getColumns().addAll(colCode, colName, colPrice, colStock);
        loadData();

        // Form input
        TextField txtCode = new TextField();
        txtCode.setPromptText("Kode");

        TextField txtName = new TextField();
        txtName.setPromptText("Nama");

        TextField txtPrice = new TextField();
        txtPrice.setPromptText("Harga");

        TextField txtStock = new TextField();
        txtStock.setPromptText("Stok");

        Button btnAdd = new Button("Tambah Produk");
        Button btnDelete = new Button("Hapus Produk");
        Button btnCart = new Button("Tambah ke Keranjang");

        // Event Tambah Produk
        btnAdd.setOnAction(e -> {
            try {
                Product p = new Product(
                        txtCode.getText(),
                        txtName.getText(),
                        Double.parseDouble(txtPrice.getText()),
                        Integer.parseInt(txtStock.getText())
                );
                controller.addProduct(p);
                loadData();
                clearFields(txtCode, txtName, txtPrice, txtStock);
            } catch (Exception ex) {
                showAlert("Error", "Data tidak valid: " + ex.getMessage());
            }
        });

        // Event Hapus Produk
        btnDelete.setOnAction(e -> {
            Product selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                controller.deleteProduct(selected.getCode());
                loadData();
            } else {
                showAlert("Peringatan", "Pilih produk yang akan dihapus");
            }
        });

        // Event Tambah ke Keranjang
        btnCart.setOnAction(e -> {
            Product selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                TextInputDialog dialog = new TextInputDialog("1");
                dialog.setTitle("Tambah ke Keranjang");
                dialog.setHeaderText("Produk: " + selected.getName());
                dialog.setContentText("Jumlah:");
                dialog.showAndWait().ifPresent(qty -> {
                    try {
                        controller.addToCart(selected, Integer.parseInt(qty));
                        lblTotal.setText("Total: " + controller.getCartTotal());
                    } catch (Exception ex) {
                        showAlert("Error", ex.getMessage());
                    }
                });
            } else {
                showAlert("Peringatan", "Pilih produk yang akan ditambahkan");
            }
        });

        // Layout
        VBox formBox = new VBox(10, txtCode, txtName, txtPrice, txtStock);
        HBox btnBox = new HBox(10, btnAdd, btnDelete, btnCart);
        VBox leftPanel = new VBox(10, new Label("Form Produk"), formBox, btnBox);
        leftPanel.setPadding(new Insets(10));

        VBox rightPanel = new VBox(10, new Label("Daftar Produk"), table, lblTotal);
        rightPanel.setPadding(new Insets(10));

        HBox mainLayout = new HBox(20, leftPanel, rightPanel);
        mainLayout.setPadding(new Insets(10));

        Scene scene = new Scene(mainLayout, 800, 600);
        stage.setTitle("AgriPOS - Point of Sale");
        stage.setScene(scene);
        stage.show();
    }

    private void loadData() {
        data.clear();
        data.addAll(controller.getAllProducts());
        table.setItems(data);
    }

    private void clearFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
