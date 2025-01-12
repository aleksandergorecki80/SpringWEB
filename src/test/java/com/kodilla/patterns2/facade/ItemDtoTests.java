package com.kodilla.patterns2.facade;

import com.kodilla.patterns2.facade.api.ItemDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemDtoTests {
    @Test
    void shouldCreateItemDto(){
        // Given
        Long productId = 101L;
        double quantity = 505.5;

        // When
        ItemDto item = new ItemDto(productId, quantity);

        // Then
        assertEquals(productId, item.getProductId());
        assertEquals(quantity, item.getQuantity());
    }
}
