package com.kodilla.patterns2.facade;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrdersTests {

    ProductService productService = new ProductService();

    @Test
    void testCalculateValue() {
        // Given

        Order order = new Order(101L, 202L, productService);
        Item item1 = new Item(1L, 2);
        order.getItems().add(item1);

        // When
        BigDecimal calculatedValue = order.calculateValue();

        // Then
        assertNotNull(calculatedValue);
    }
}

