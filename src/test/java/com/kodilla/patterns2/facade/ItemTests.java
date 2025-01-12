package com.kodilla.patterns2.facade;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTests {
    @Test
    void shouldCreateItem(){
        // Given
        Long productId = 101L;
        double qty = 5.5;

        // When
        Item item = new Item(productId, qty);

        // Then
        assertEquals(productId, item.getProductId());
        assertEquals(qty, item.getQty());
    }
}
