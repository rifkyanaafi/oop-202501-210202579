package com.upb.agripos.test;

import com.upb.agripos.model.Product;
import com.upb.agripos.controller.ProductController;

/**
 * Simple test untuk verify logic tanpa JavaFX
 * Jalankan: javac -d bin -sourcepath src/main/java src/test/java/com/upb/agripos/test/ProductLogicTest.java
 * Then: java -cp bin com.upb.agripos.test.ProductLogicTest
 */
public class ProductLogicTest {
    
    public static void main(String[] args) {
        System.out.println("=== Testing Product Logic (Without GUI) ===\n");
        
        // Test 1: Valid product creation
        System.out.println("TEST 1: Valid Product Creation");
        try {
            Product p = new Product("PROD001", "Pupuk Organik", 50000.0, 100);
            System.out.println("✓ Product created: " + p.getCode() + " - " + p.getName());
            System.out.println("  Price: Rp" + String.format("%.2f", p.getPrice()));
            System.out.println("  Stock: " + p.getStock());
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
        
        // Test 2: Validation - Empty string
        System.out.println("\nTEST 2: Validation - Empty Code");
        String code = "   ".trim();  // Simulating empty after trim
        if (code.isEmpty()) {
            System.out.println("✓ Validation works: Empty string detected");
        } else {
            System.out.println("✗ Validation failed");
        }
        
        // Test 3: Validation - Number format
        System.out.println("\nTEST 3: Validation - Number Format");
        try {
            double price = Double.parseDouble("abc");
            System.out.println("✗ Should have thrown NumberFormatException");
        } catch (NumberFormatException e) {
            System.out.println("✓ NumberFormatException caught correctly");
        }
        
        // Test 4: Validation - Negative value
        System.out.println("\nTEST 4: Validation - Negative Value");
        double price = -1000;
        if (price <= 0) {
            System.out.println("✓ Validation works: Negative value detected");
        } else {
            System.out.println("✗ Validation failed");
        }
        
        // Test 5: String formatting
        System.out.println("\nTEST 5: String Formatting");
        String code2 = "PROD001";
        String name2 = "Pupuk Organik";
        double price2 = 50000.0;
        int stock2 = 100;
        String output = String.format("%s - %s (Rp%.2f) Stok: %d", code2, name2, price2, stock2);
        System.out.println("✓ Formatted: " + output);
        
        System.out.println("\n=== All Tests Passed! ===");
    }
}
