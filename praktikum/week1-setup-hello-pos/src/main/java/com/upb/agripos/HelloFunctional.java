package com.upb.agripos;

import java.util.function.Consumer;

public class HelloFunctional {
    public static void main(String[] args) {
        String nama = "Muhammad Rifqi An Naafi";
        String nim = "210202579";

        Consumer<String> tampilkan = pesan -> System.out.println(pesan);
        tampilkan.accept("Hello World, I am " + nama + " - " + nim);
    }
}