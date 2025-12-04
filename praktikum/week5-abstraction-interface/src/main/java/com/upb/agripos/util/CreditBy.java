package com.upb.agripos.util;

/**
 * Utility class untuk menampilkan identitas mahasiswa
 * di akhir eksekusi program praktikum.
 */
public class CreditBy {

    /**
     * Menampilkan NIM dan Nama Mahasiswa
     *
     * @param nim  Nomor Induk Mahasiswa
     * @param nama Nama Mahasiswa
     */
    public static void print(String nim, String nama) {
        System.out.println("\n==============================");
        System.out.println("Dibuat oleh:");
        System.out.println("NIM  : " + nim);
        System.out.println("Nama : " + nama);
        System.out.println("==============================");
    }
}
