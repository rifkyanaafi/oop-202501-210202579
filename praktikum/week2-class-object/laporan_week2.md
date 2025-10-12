# Laporan Praktikum Minggu 2  
**Topik:** Class dan Object  

---

## Identitas
- **Nama:** Muhammad Rifqi An Naafi  
- **NIM:** 210202579 
- **Kelas:** TI-3A

---

## Tujuan
- Mahasiswa memahami konsep dasar *class* dan *object* dalam Java.  
- Mahasiswa dapat menerapkan prinsip **enkapsulasi** dengan atribut `private` dan method *getter/setter*.  
- Mahasiswa mampu membuat objek dan menampilkan data produk melalui class yang terpisah.  

---

## Dasar Teori
1. **Class** adalah blueprint atau cetak biru dari objek, berisi atribut dan method.  
2. **Object** adalah instansiasi dari class yang memiliki data konkret.  
3. **Enkapsulasi** menyembunyikan data agar hanya dapat diakses melalui *getter* dan *setter*.  
4. **Method statis** memungkinkan pemanggilan fungsi tanpa membuat objek.  
5. Pemisahan kode menggunakan **package** membantu pengorganisasian program agar modular dan mudah dikembangkan.  

---

## Langkah Praktikum
1. Membuat class `Produk` pada package `com.upb.agripos.model` dengan atribut `kode`, `nama`, `harga`, dan `stok`.  
2. Menambahkan *getter* dan *setter* untuk setiap atribut serta method `tambahStok()` dan `kurangiStok()`.  
3. Membuat class `CreditBy` di package `com.upb.agripos.util` untuk menampilkan identitas mahasiswa.  
4. Membuat class `MainProduk` untuk menginstansiasi minimal tiga produk dan menampilkan data di konsol.  
5. Menjalankan program dan mengambil screenshot hasil eksekusi.  
6. Melakukan commit dengan pesan : 
git commit -m "week2-class-object"


---

## Kode Program

###  Produk.java
package com.upb.agripos.model;

public class Produk {
 private String kode;
 private String nama;
 private double harga;
 private int stok;

 public Produk(String kode, String nama, double harga, int stok) {
     this.kode = kode;
     this.nama = nama;
     this.harga = harga;
     this.stok = stok;
 }

 public String getKode() { return kode; }
 public void setKode(String kode) { this.kode = kode; }

 public String getNama() { return nama; }
 public void setNama(String nama) { this.nama = nama; }

 public double getHarga() { return harga; }
 public void setHarga(double harga) { this.harga = harga; }

 public int getStok() { return stok; }
 public void setStok(int stok) { this.stok = stok; }

 public void tambahStok(int jumlah) {
     this.stok += jumlah;
 }

 public void kurangiStok(int jumlah) {
     if (this.stok >= jumlah) {
         this.stok -= jumlah;
     } else {
         System.out.println("Stok tidak mencukupi!");
     }
 }
}

### CreditBy.java
package com.upb.agripos.util;

public class CreditBy {
    public static void print(String nim, String nama) {
        System.out.println("\ncredit by: " + nim + " - " + nama);
    }
}


### MainProduk.java
package com.upb.agripos;

import com.upb.agripos.model.Produk;
import com.upb.agripos.util.CreditBy;

public class MainProduk {
    public static void main(String[] args) {
        Produk p1 = new Produk("BNH-001", "Benih Padi IR64", 25000, 100);
        Produk p2 = new Produk("PPK-101", "Pupuk Urea 50kg", 350000, 40);
        Produk p3 = new Produk("ALT-501", "Cangkul Baja", 90000, 15);

        System.out.println("Kode: " + p1.getKode() + ", Nama: " + p1.getNama() + ", Harga: " + p1.getHarga() + ", Stok: " + p1.getStok());
        System.out.println("Kode: " + p2.getKode() + ", Nama: " + p2.getNama() + ", Harga: " + p2.getHarga() + ", Stok: " + p2.getStok());
        System.out.println("Kode: " + p3.getKode() + ", Nama: " + p3.getNama() + ", Harga: " + p3.getHarga() + ", Stok: " + p3.getStok());

	// Latihan mandiri update dan kurangi stok
	p1.tambahStok(20);
	p3.kurangiStok(5);

	System.out.println("\nSetelah update stok:");
	System.out.println("Stok " + p1.getNama() + ": " + 	p1.getStok());
	System.out.println("Stok " + p3.getNama() + ": " + p3.getStok());


        CreditBy.print("2310112345", "Muhammad Rifqi An Naafi");
    }
}


### Hasil Eksekusi
![
   
](screenshots/hasil.png)
Kode: BNH-001, Nama: Benih Padi IR64, Harga: 25000.0, Stok: 100
Kode: PPK-101, Nama: Pupuk Urea 50kg, Harga: 350000.0, Stok: 40
Kode: ALT-501, Nama: Cangkul Baja, Harga: 90000.0, Stok: 15

credit by: 210202579 - Muhammad Rifqi An Naafi

---

## Analisis
Kode berjalan sesuai dengan konsep Object-Oriented Programming (OOP).

Atribut disembunyikan dengan private untuk menerapkan enkapsulasi.

Akses ke data dilakukan melalui getter dan setter sehingga data lebih aman dan terkontrol.

Dibanding minggu sebelumnya (yang mungkin hanya procedural), pendekatan OOP ini lebih modular dan mudah dikembangkan.

Tidak ada kendala berarti, namun perlu memastikan struktur package sudah sesuai agar import berjalan lancar.

---

## Kesimpulan
Dengan menggunakan konsep class dan object, program menjadi lebih terstruktur, mudah dipelihara, dan dapat dikembangkan untuk sistem yang lebih kompleks seperti aplikasi POS (Point of Sale).
Enkapsulasi membantu menjaga integritas data, sementara class CreditBy memperlihatkan penggunaan method statis dengan baik.


---

## Quiz
1. Mengapa atribut sebaiknya dideklarasikan sebagai private dalam class?
Jawaban:
Atribut sebaiknya dideklarasikan sebagai private untuk menerapkan prinsip enkapsulasi dalam pemrograman berorientasi objek (OOP).
Dengan membuat atribut bersifat private, data hanya dapat diakses dan diubah melalui method yang disediakan (getter dan setter).
Hal ini melindungi data dari perubahan langsung yang tidak diinginkan dan menjaga konsistensi serta keamanan objek.
Contoh:
private double harga; // Tidak bisa diakses langsung dari luar class
Dengan begitu, pengguna objek hanya dapat mengubah nilai harga lewat :
produk.setHarga(30000);

2. Apa fungsi getter dan setter dalam enkapsulasi?
Jawaban:
Getter digunakan untuk mengambil nilai dari atribut private.
Setter digunakan untuk mengubah atau menetapkan nilai atribut tersebut.
Fungsi utamanya adalah mengontrol akses ke data.
Dengan getter dan setter, kita bisa menambahkan validasi, logika bisnis, atau pembatasan ketika data diubah atau diambil.
Contoh :
public double getHarga() {
    return harga; // Getter mengambil nilai atribut
}

public void setHarga(double harga) {
    if (harga > 0) {
        this.harga = harga; // Setter dengan validasi
    } else {
        System.out.println("Harga tidak boleh negatif!");
    }
}

3. Bagaimana cara class Produk mendukung pengembangan aplikasi POS yang lebih kompleks?
Jawaban:
Class Produk menjadi model dasar (blueprint) untuk mewakili data produk dalam sistem POS (Point of Sale).
Dengan atribut seperti kode, nama, harga, dan stok, serta method seperti tambahStok() dan kurangiStok(), class ini bisa digunakan untuk :
1. Menyimpan dan mengelola data produk di database.
2. Menampilkan informasi produk di tampilan kasir atau aplikasi mobile.
3. Menghitung total transaksi dan mengupdate stok otomatis setelah penjualan.
4. Diperluas untuk mendukung fitur tambahan seperti kategori, diskon, atau supplier.
5. Dengan demikian, Produk menjadi komponen inti yang bisa diintegrasikan dengan modul lain seperti Transaksi, Pelanggan, dan Laporan.