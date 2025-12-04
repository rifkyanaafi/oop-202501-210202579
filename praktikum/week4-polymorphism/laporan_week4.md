# Laporan Praktikum Minggu 4
Topik: Polymorphism (Info Produk)

## Identitas
- Nama  : Muhammad Rifqi An Naafi
- NIM   : 210202579
- Kelas : 3IKKA

---

## Tujuan
- Mahasiswa mampu menjelaskan konsep polymorphism dalam OOP.  
- Mahasiswa mampu membedakan method overloading dan overriding.  
- Mahasiswa mampu mengimplementasikan polymorphism (overriding, overloading, dynamic binding) dalam program Java.  
- Mahasiswa mampu menganalisis contoh kasus polymorphism pada sistem nyata (Agri-POS).  

---

## Dasar Teori
1. **Polymorphism** berarti “banyak bentuk” dan memungkinkan objek berbeda merespons method yang sama dengan cara yang berbeda.  
2. **Overloading** terjadi ketika dua atau lebih method memiliki nama yang sama, tetapi parameter berbeda (jumlah atau tipe).  
3. **Overriding** terjadi ketika subclass mengganti implementasi method dari superclass dengan perilaku berbeda.  
4. **Dynamic Binding** menentukan method mana yang dipanggil berdasarkan tipe objek aktual saat runtime, bukan tipe referensi.  
5. Dalam konteks sistem Agri-POS, polymorphism digunakan agar berbagai jenis produk (Benih, Pupuk, AlatPertanian) dapat memiliki perilaku khusus masing-masing melalui method `getInfo()`.

---

## Langkah Praktikum
1. Membuat struktur direktori proyek sesuai ketentuan:
oop-20251-<nim>/praktikum/week4-polymorphism/
2. Membuat class:
- `Produk.java` → berisi atribut umum dan method overloading `tambahStok()`.
- `Benih.java`, `Pupuk.java`, `AlatPertanian.java` → subclass yang melakukan overriding pada method `getInfo()`.
- `CreditBy.java` → menampilkan identitas pembuat program.
- `MainPolymorphism.java` → menjalankan demonstrasi polymorphism (overloading, overriding, dynamic binding).
3. Menjalankan program untuk menampilkan hasil `getInfo()` dari berbagai subclass.  
4. Melakukan commit dengan pesan:
git commit -m "week4-polymorphism"

---

## Kode Program
Contoh kode utama pada file `MainPolymorphism.java`:

```java
package com.upb.agripos;

import com.upb.agripos.model.*;
import com.upb.agripos.util.CreditBy;

public class MainPolymorphism {
 public static void main(String[] args) {
     Produk[] daftarProduk = {
         new Benih("BNH-001", "Benih Padi IR64", 25000, 100, "IR64"),
         new Pupuk("PPK-101", "Pupuk Urea", 350000, 40, "Urea"),
         new AlatPertanian("ALT-501", "Cangkul Baja", 90000, 15, "Baja")
     };

     System.out.println("=== Informasi Produk Agri-POS ===");
     for (Produk p : daftarProduk) {
         System.out.println(p.getInfo()); // Dynamic Binding
     }

     Produk produkDemo = new Produk("GEN-001", "Produk Demo", 10000, 10);
     produkDemo.tambahStok(5);
     produkDemo.tambahStok(3.5);

     System.out.println("\nSetelah tambah stok:");
     System.out.println(produkDemo.getInfo());

     CreditBy.print("<NIM>", "<Nama Mahasiswa>");
 }
}


## Hasil Eksekusi
Informasi Produk Agri-POS ===
Benih -> Produk: Benih Padi IR64 (Kode: BNH-001), Harga: Rp25000.0, Stok: 100, Varietas: IR64
Pupuk -> Produk: Pupuk Urea (Kode: PPK-101), Harga: Rp350000.0, Stok: 40, Jenis: Urea
Alat Pertanian -> Produk: Cangkul Baja (Kode: ALT-501), Harga: Rp90000.0, Stok: 15, Bahan: Baja
Obat Hama -> Produk: Obat Anti Wereng (Kode: OBH-301), Harga: Rp120000.0, Stok: 25, Kandungan Aktif: Klorpirifos

Setelah tambah stok:
Produk: Produk Demo (Kode: GEN-001), Harga: Rp10000.0, Stok: 18

=== Praktikum OOP Week 4 ===
Created by: Muhammad Rifqi An Naafi (210202579)

## Analisis
Program mendemonstrasikan tiga bentuk polymorphism di Java:

Overloading: method tambahStok() memiliki dua versi berbeda berdasarkan tipe parameter.

Overriding: subclass seperti Benih, Pupuk, dan AlatPertanian menimpa method getInfo() milik superclass Produk.

Dynamic Binding: saat getInfo() dipanggil pada array Produk[], Java memanggil method sesuai jenis objek aktualnya di runtime.

Pendekatan minggu ini berbeda dari minggu sebelumnya karena kini satu referensi superclass (Produk) dapat mewakili berbagai jenis objek.

Kendala yang dihadapi: kesalahan @Override atau constructor superclass yang belum dipanggil.
Solusi: memastikan pemanggilan super() di setiap subclass dan penulisan method dengan parameter yang sesuai.

## Kesimpulan
Dengan menerapkan polymorphism, program menjadi lebih fleksibel dan mudah diperluas.
Objek-objek dengan tipe berbeda dapat diperlakukan secara seragam menggunakan referensi superclass, namun tetap menjalankan perilaku spesifiknya masing-masing.

## Quiz
1. Apa perbedaan overloading dan overriding?
Jawaban:
- Overloading terjadi ketika dua atau lebih method memiliki nama sama tetapi parameter berbeda, ditentukan saat compile time.
- Overriding terjadi ketika subclass mengganti method superclass dengan implementasi baru, ditentukan saat runtime.

2. Bagaimana Java menentukan method mana yang dipanggil dalam dynamic binding?
Jawaban:
Java memilih method berdasarkan tipe objek aktual di runtime, bukan berdasarkan tipe referensi variabel.
Jadi, jika referensi bertipe Produk tetapi objeknya Benih, maka method getInfo() milik Benih yang akan dijalankan.

3. Berikan contoh kasus polymorphism dalam sistem POS selain produk pertanian.
Jawaban:
Dalam sistem POS di minimarket:
- Produk bisa memiliki subclass Makanan, Minuman, dan Elektronik.
- Masing-masing subclass mengoverride getInfo() untuk menampilkan atribut berbeda, seperti tanggal kedaluwarsa, volume, atau masa garansi.
- Saat array Produk[] berisi berbagai jenis objek tersebut, pemanggilan getInfo() akan menghasilkan output berbeda sesuai jenis produknya.