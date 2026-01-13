package main.java.com.upb.agripos;

public class MainCart {
    public static void main(String[] args) {
        System.out.println("Hello, I am Muhammad Rifqi An Naafi - 210202579 (Week7)");

        Product p1 = new Product("P01", "Beras", 50000);
        Product p2 = new Product("P02", "Pupuk", 30000);
        Product p3 = new Product("P03", "Bibit Jagung", 15000);

        // Versi ArrayList
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(p1);
        cart.addProduct(p2);
        cart.addProduct(p3);
        cart.printCart();

        cart.removeProduct(p2);
        cart.printCart();

        System.out.println();

        // Versi Map (dengan Quantity)
        ShoppingCartMap cartMap = new ShoppingCartMap();
        cartMap.addProduct(p1);
        cartMap.addProduct(p1);
        cartMap.addProduct(p3);
        cartMap.printCart();

        cartMap.removeProduct(p1);
        cartMap.printCart();
    }
}
