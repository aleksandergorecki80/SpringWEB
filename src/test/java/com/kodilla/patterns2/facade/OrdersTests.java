package com.kodilla.patterns2.facade;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrdersTests {

    ProductService productService = new ProductService();

    @Test
    void getItemsTest() {
        // Given
        Order order = new Order(101L, 202L, productService);
        Item item1 = new Item(1L, 2);
        order.getItems().add(item1);

        // When
        List<Item> result = order.getItems();

        // Then
        assertEquals(1L, result.getFirst().getProductId());
    }

    @Test
    void getUserIdTest() {
        // Given
        Order order = new Order(101L, 202L, productService);
        Item item1 = new Item(1L, 2);
        order.getItems().add(item1);

        // When
        Long userId = order.getUserId();

        // Then
        assertEquals(202L, userId);
    }

    @Test
    void isPaidTest() {
        // Given
        Order order = new Order(101L, 202L, productService);
        Item item1 = new Item(1L, 2);
        order.getItems().add(item1);

        // When
        boolean paid = order.isPaid();

        // Then
        assertFalse(paid);

        // When
        order.setPaid(true);
        boolean paid2 = order.isPaid();

        // Then
        assertTrue(paid2);
    }

    @Test
    void isVerifiedTest() {
        // Given
        Order order = new Order(101L, 202L, productService);
        Item item1 = new Item(1L, 2);
        order.getItems().add(item1);

        // When
        boolean verified = order.isVerified();

        // Then
        assertFalse(verified);

        // When
        order.setVerified(true);
        boolean verified2 = order.isVerified();

        // Then
        assertTrue(verified2);
    }

    @Test
    void isSubmittedTest() {
        // Given
        Order order = new Order(101L, 202L, productService);
        Item item1 = new Item(1L, 2);
        order.getItems().add(item1);

        // When
        boolean submitted = order.isSubmitted();

        // Then
        assertFalse(submitted);

        // When
        order.setSubmitted(true);
        boolean submitted2 = order.isSubmitted();

        // Then
        assertTrue(submitted2);
    }

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

