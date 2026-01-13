# Laporan Praktikum Minggu 12

Topik: GUI Dasar JavaFX (Event-Driven Programming) Terintegrasi MVC, Service, dan DAO

## Identitas
- Nama : Muhammad Rifqi An Naafi
- NIM : 210202579
- Kelas : 3IKKA

---

## Tujuan
Mahasiswa mampu menjelaskan konsep event-driven programming, membangun antarmuka grafis sederhana menggunakan JavaFX, membuat form input data produk, menampilkan daftar produk pada GUI, serta mengintegrasikan GUI dengan modul backend (DAO & Service) sesuai arsitektur MVC dan prinsip SOLID.

---

## Dasar Teori
1. Event-driven programming adalah paradigma pemrograman yang mengeksekusi proses berdasarkan event dari pengguna.
2. JavaFX digunakan untuk membangun antarmuka grafis berbasis Java.
3. MVC memisahkan tanggung jawab Model, View, dan Controller.
4. DAO menangani akses data ke database menggunakan JDBC.
5. Service menjadi penghubung antara Controller dan DAO sesuai prinsip Dependency Inversion.

---

## Langkah Praktikum
1. Menginstal JavaFX SDK dan mengatur VM Options di VS Code.
2. Membuat struktur project week12-gui-dasar dengan pola MVC.
3. Menggunakan kembali Product, ProductDAO, dan ProductService dari Week 11.
4. Membuat form JavaFX dengan TextField (kode, nama, harga, stok) dan Button (Tambah Produk).
5. Menambahkan event handler pada tombol Tambah.
6. Menghubungkan alur View → Controller → Service → DAO → Database PostgreSQL.
7. Menjalankan aplikasi dan memverifikasi data tersimpan di pgAdmin.
8. Commit :
week12-gui-dasar: implementasi GUI JavaFX terintegrasi DAO

---

## Kode Program
```java
btnAdd.setOnAction(event -> {
    Product p = new Product(
        txtCode.getText(),
        txtName.getText(),
        Double.parseDouble(txtPrice.getText()),
        Integer.parseInt(txtStock.getText())
    );
    productService.insert(p);
    listView.getItems().add(p.getCode() + " - " + p.getName());
});
```
---

## Hasil Eksekusi
GUI JavaFX berhasil menampilkan form input dan daftar produk.
Data yang dimasukkan melalui tombol Tambah Produk muncul di ListView dan tersimpan ke database PostgreSQL.

---

## Analisis
Program berjalan dengan konsep event-driven, di mana klik tombol memicu pemanggilan Controller, kemudian Service, lalu DAO untuk menyimpan data ke database.
Dibandingkan praktikum sebelumnya yang berbasis console, pada minggu ini interaksi dilakukan melalui GUI.
Kendala utama adalah konfigurasi JavaFX dan koneksi JDBC, yang diatasi dengan pengaturan library JavaFX dan driver PostgreSQL.

--- 

## Kesimpulan
Dengan penerapan JavaFX, MVC, Service, dan DAO, aplikasi menjadi lebih terstruktur, interaktif, dan sesuai prinsip SOLID. Pemisahan layer memastikan GUI tidak langsung berinteraksi dengan database, sehingga sistem lebih mudah dikembangkan dan dipelihara.

---

## Tabel Traceability Bab 6
| Artefak Bab 6 | Referensi           | Handler GUI   | Controller / Service                              | DAO                 | Dampak UI / DB                              |
| ------------- | ------------------- | ------------- | ------------------------------------------------- | ------------------- | ------------------------------------------- |
| Use Case      | UC-01 Tambah Produk | Tombol Tambah | ProductController.add() → ProductService.insert() | ProductDAO.insert() | Data tampil di ListView dan tersimpan di DB |
| Activity      | AD-01 Tambah Produk | Tombol Tambah | Validasi → Simpan → Tampil                        | insert()            | Alur sesuai Activity Diagram                |
| Sequence      | SD-01 Tambah Produk | Tombol Tambah | View → Controller → Service                       | DAO → DB            | Urutan pemanggilan sesuai Sequence Diagram  |
