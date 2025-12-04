package com.upb.agripos;

import com.upb.agripos.model.*;
import com.upb.agripos.util.CreditBy;

public class MainPolymorphism {

    public static void main(String[] args) {
// Array dengan tipe Produk tapi isi objek subclass
        Produk[] daftarProduk = {
            new Benih("BNH-001", "Benih Padi IR64", 25000, 100, "IR64"),
            new Pupuk("PPK-101", "Pupuk Urea", 350000, 40, "Urea"),
            new AlatPertanian("ALT-501", "Cangkul Baja", 90000, 15, "Baja"),
            new ObatHama("OBH-301", "Obat Anti Wereng", 120000, 25, "Klorpirifos")
        };

        System.out.println("=== Informasi Produk Agri-POS ===");
        for (Produk p : daftarProduk) {
            System.out.println(p.getInfo()); // Dynamic binding
        }

        // Demonstrasi overloading
        Produk produkDemo = new Produk("GEN-001", "Produk Demo", 10000, 10);
        produkDemo.tambahStok(5);
        produkDemo.tambahStok(3.7);
        System.out.println("\nSetelah tambah stok:");
        System.out.println(produkDemo.getInfo());

        CreditBy.print("210202579", "Muhammad Rifqi An Naafi");
    }
}
