package com.upb.agripos;

class Mahasiswa {
    String nama;
    String nim;

    Mahasiswa(String nama, String nim) {
        this.nama = nama;
        this.nim = nim;
    }

    void sayHello() {
        System.out.println("Hello World, I am " + nama + " - " + nim);
    }
}

public class HelloOOP {
    public static void main(String[] args) {
        Mahasiswa mhs = new Mahasiswa("Muhammad Rifqi An Naafi", "210202579");
        mhs.sayHello();
    }
}