package com.upb.agripos.view;

import com.upb.agripos.model.Product;
import com.upb.agripos.controller.ProductController;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

public class ProductTableView extends VBox {

    private TableView<Product> table;
    private ProductController controller;

    public ProductTableView(ProductController controller) {
        this.controller = controller;
        this.table = new TableView<>();
        
        // Columns
        TableColumn<Product, String> colCode = new TableColumn<>("Kode");
        colCode.setCellValueFactory(d -> d.getValue().codeProperty());

        TableColumn<Product, String> colName = new TableColumn<>("Nama");
        colName.setCellValueFactory(d -> d.getValue().nameProperty());

        TableColumn<Product, Number> colPrice = new TableColumn<>("Harga");
        colPrice.setCellValueFactory(d -> d.getValue().priceProperty());

        TableColumn<Product, Number> colStock = new TableColumn<>("Stok");
        colStock.setCellValueFactory(d -> d.getValue().stockProperty());

        table.getColumns().addAll(colCode, colName, colPrice, colStock);
        table.setStyle("-fx-font-size: 12px;");
        
        // Buttons dengan Lambda Expression
        Button btnAdd = new Button("Tambah Produk");
        Button btnDelete = new Button("Hapus Produk");
        Button btnReload = new Button("Reload Data");
        
        btnAdd.setStyle("-fx-font-size: 11px; -fx-padding: 8px 20px;");
        btnDelete.setStyle("-fx-font-size: 11px; -fx-padding: 8px 20px;");
        btnReload.setStyle("-fx-font-size: 11px; -fx-padding: 8px 20px;");
        
        // Lambda: Tambah produk
        btnAdd.setOnAction(e -> showAddProductDialog());
        
        // Lambda: Hapus produk
        btnDelete.setOnAction(e -> {
            Product p = table.getSelectionModel().getSelectedItem();
            if (p != null) {
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
                confirm.setTitle("Hapus Produk");
                confirm.setContentText("Yakin hapus produk " + p.getName() + "?");
                if (confirm.showAndWait().get() == ButtonType.OK) {
                    controller.deleteProduct(p.getCode());
                    refreshTable();
                }
            } else {
                Alert warning = new Alert(Alert.AlertType.WARNING);
                warning.setTitle("Pilih Produk");
                warning.setContentText("Pilih produk dari tabel terlebih dahulu");
                warning.showAndWait();
            }
        });
        
        // Lambda: Reload data
        btnReload.setOnAction(e -> refreshTable());
        
        HBox buttonBox = new HBox(10, btnAdd, btnDelete, btnReload);
        buttonBox.setPadding(new Insets(10));
        
        setVgrow(table, javafx.scene.layout.Priority.ALWAYS);
        getChildren().addAll(table, buttonBox);
        setPadding(new Insets(10));
        setSpacing(10);
        
        refreshTable();
    }
    
    private void refreshTable() {
        controller.loadData(table);
    }
    
    private void showAddProductDialog() {
        Dialog<Product> dialog = new Dialog<>();
        dialog.setTitle("Tambah Produk");
        dialog.setHeaderText("Masukkan Data Produk Baru");
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        
        TextField txtCode = new TextField();
        TextField txtName = new TextField();
        TextField txtPrice = new TextField();
        TextField txtStock = new TextField();
        
        txtCode.setPromptText("Kode");
        txtName.setPromptText("Nama");
        txtPrice.setPromptText("Harga");
        txtStock.setPromptText("Stok");
        
        grid.add(new Label("Kode:"), 0, 0);
        grid.add(txtCode, 1, 0);
        grid.add(new Label("Nama:"), 0, 1);
        grid.add(txtName, 1, 1);
        grid.add(new Label("Harga:"), 0, 2);
        grid.add(txtPrice, 1, 2);
        grid.add(new Label("Stok:"), 0, 3);
        grid.add(txtStock, 1, 3);
        
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        
        dialog.setResultConverter(b -> {
            if (b == ButtonType.OK) {
                try {
                    return new Product(
                        txtCode.getText(),
                        txtName.getText(),
                        Double.parseDouble(txtPrice.getText()),
                        Integer.parseInt(txtStock.getText())
                    );
                } catch (Exception ex) {
                    Alert err = new Alert(Alert.AlertType.ERROR);
                    err.setTitle("Error");
                    err.setContentText("Input tidak valid");
                    err.showAndWait();
                    return null;
                }
            }
            return null;
        });
        
        if (dialog.showAndWait().isPresent()) {
            Product p = dialog.getResult();
            if (p != null) {
                controller.addProduct(p);
                refreshTable();
            }
        }
    }
}
