package com.upb.agripos;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.CartService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CartServiceTest {

    @Test
    void testTotalBelanja() {
        CartService cartService = new CartService();

        Product p1 = new Product("P001", "Pupuk", 10000, 10);
        Product p2 = new Product("P002", "Bibit", 20000, 5);

        cartService.addItem(p1, 2); // 2 x 10000 = 20000
        cartService.addItem(p2, 1); // 1 x 20000 = 20000

        double total = cartService.getTotal();
        assertEquals(40000, total);
    }
}
